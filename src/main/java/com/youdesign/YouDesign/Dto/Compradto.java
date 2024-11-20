package com.youdesign.YouDesign.Dto;

import java.util.List;

public class Compradto {
    private List<DetalleCompradto> detalles;
    private double total;

    public List<DetalleCompradto> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompradto> detalles) {
        this.detalles = detalles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
