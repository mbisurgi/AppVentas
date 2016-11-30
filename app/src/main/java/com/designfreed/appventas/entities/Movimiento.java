package com.designfreed.appventas.entities;

import java.util.Date;

public class Movimiento {
    private Long id;
    private Date fecha;
    private Chofer chofer;
    private Integer visitas;
    private Integer ventas;
    private Float objetivo;
    private Float ventaKilos;
    private Float ventaPesos;

    public Movimiento() {
    }

    public Movimiento(Date fecha, Chofer chofer, Integer visitas, Integer ventas, Float objetivo, Float ventaKilos, Float ventaPesos) {
        this.fecha = fecha;
        this.chofer = chofer;
        this.visitas = visitas;
        this.ventas = ventas;
        this.objetivo = objetivo;
        this.ventaKilos = ventaKilos;
        this.ventaPesos = ventaPesos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

    public Integer getVentas() {
        return ventas;
    }

    public void setVentas(Integer ventas) {
        this.ventas = ventas;
    }

    public Float getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Float objetivo) {
        this.objetivo = objetivo;
    }

    public Float getVentaKilos() {
        return ventaKilos;
    }

    public void setVentaKilos(Float ventaKilos) {
        this.ventaKilos = ventaKilos;
    }

    public Float getVentaPesos() {
        return ventaPesos;
    }

    public void setVentaPesos(Float ventaPesos) {
        this.ventaPesos = ventaPesos;
    }
}
