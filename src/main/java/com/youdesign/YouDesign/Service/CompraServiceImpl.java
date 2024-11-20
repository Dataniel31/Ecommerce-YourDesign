package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Dto.Compradto;
import com.youdesign.YouDesign.Dto.DetalleCompradto;
import com.youdesign.YouDesign.Entity.Compra;
import com.youdesign.YouDesign.Entity.DetalleCompra;
import com.youdesign.YouDesign.Entity.Producto;
import com.youdesign.YouDesign.Entity.Usuario;
import com.youdesign.YouDesign.Repository.CompraRepository;
import com.youdesign.YouDesign.Repository.DetalleCompraRepository;
import com.youdesign.YouDesign.Repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompraServiceImpl implements CompraService{
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Transactional
    public Compra realizarCompra(Compradto compraDTO, Usuario usuario) {
        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setFechaCompra(new Date());
        compra.setTotal(compraDTO.getTotal());

        List<DetalleCompra> detalles = new ArrayList<>();

        for (DetalleCompradto detalleDTO : compraDTO.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < detalleDTO.getCantidad()) {
                throw new RuntimeException("Stock insuficiente");
            }

            producto.setStock(producto.getStock() - detalleDTO.getCantidad());
            productoRepository.save(producto);

            DetalleCompra detalle = new DetalleCompra();
            detalle.setCompra(compra);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
            detalle.setSubtotal(detalleDTO.getSubtotal());

            detalles.add(detalle);
        }

        compra.setDetalles(detalles);
        return compraRepository.save(compra);
    }

    @Override
    public List<Compra> obtenerComprasPorUsuario(Usuario usuario) {
        return compraRepository.findByUsuario(usuario);
    }

    @Override
    public void generarPDF(Compra compra) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("compra_" + compra.getId_compra() + ".pdf"));

        document.open();
        document.add(new Paragraph("Detalle de Compra"));
        document.add(new Paragraph("Fecha: " + compra.getFechaCompra()));
        document.add(new Paragraph("Cliente: " + compra.getUsuario().getNombre()));

        PdfPTable table = new PdfPTable(4);
        table.addCell("Producto");
        table.addCell("Cantidad");
        table.addCell("Precio Unitario");
        table.addCell("Subtotal");

        for (DetalleCompra detalle : compra.getDetalles()) {
            table.addCell(detalle.getProducto().getNombre_prod());
            table.addCell(String.valueOf(detalle.getCantidad()));
            table.addCell(String.valueOf(detalle.getPrecioUnitario()));
            table.addCell(String.valueOf(detalle.getSubtotal()));
        }

        document.add(table);
        document.add(new Paragraph("Total: " + compra.getTotal()));
        document.close();
    }
}
