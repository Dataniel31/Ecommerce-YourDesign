package com.youdesign.YouDesign.Service;

import com.youdesign.YouDesign.Dto.Compradto;
import com.youdesign.YouDesign.Entity.Compra;
import com.youdesign.YouDesign.Entity.Usuario;

import java.util.List;

public interface CompraService {
    Compra realizarCompra(Compradto compraDTO, Usuario usuario);

    List<Compra> obtenerComprasPorUsuario(Usuario usuario);

    void generarPDF(Compra compra) throws Exception;
}
