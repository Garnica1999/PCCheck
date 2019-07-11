/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.model;

import java.io.Serializable;

/**
 * Clase que guarda los datos de la Memoria RAM y su uso general
 * @author Garnica1999
 */
public class RAM implements Serializable{
    /**
     * Total RAM Memory on Bytes
     */
    private long total;
    /**
     * Used RAM Memory on Bytes
     */
    private long used;
    /**
     * Free RAM Memory on Bytes
     */
    private long free;
    /**
     * Porcent free RAM Memory
     */
    private double porcentFree;
    /**
     * Porcent used RAM Memory
     */
    private double porcentUsed;

    /**
     * Contructor para generar un objeto que contenga datos de la memoria RAM
     * @param total Total RAM Memory on Bytes
     * @param used Used RAM Memory on Bytes
     * @param free Free RAM Memory on Bytes
     * @param porcentFree Porcent free RAM Memory
     * @param porcentUsed Porcent used RAM Memory
     */
    public RAM(long total, long used, long free, double porcentFree, double porcentUsed) {
        this.total = total;
        this.used = used;
        this.free = free;
        this.porcentFree = porcentFree;
        this.porcentUsed = porcentUsed;
    }

    /**
     * Contructor para generar un objeto que contenga datos basicos de la memoria RAM
     * @param total Total RAM Memory on Bytes
     * @param used Used RAM Memory on Bytes
     * @param free Free RAM Memory on Bytes
     */
    public RAM(long total, long used, long free) {
        this.total = total;
        this.used = used;
        this.free = free;
    }
    
    /**
     * Obtiene la memoria RAM total instalada en el sistema
     * @return Cantidad total del memoria ram instalada en el equipo
     */
    public long getTotal() {
        return total;
    }
    /**
     * Modifica la ram total por otro valor
     * @param total Nueva cantidad total de ram
     */
    public void setTotal(long total) {
        this.total = total;
    }
    /**
     * Obtiene la cantidad de memoria ram utilizada
     * @return Cantidad de memoria ram en uso
     */
    public long getUsed() {
        return used;
    }
    /**
     * Modifica el valor de ram en uso
     * @param used Nuevo valor de ram en uso
     */
    public void setUsed(long used) {
        this.used = used;
    }
    /**
     * Obtiene el valor disponible para uso de la memoria ram
     * @return Cantidad disponible de Memoria RAM
     */
    public long getFree() {
        return free;
    }
    /**
     * Modifica el valor disponible para uso de la memoria ram
     * @param free Nuevo valor disponible para uso de la memoria ram
     */
    public void setFree(long free) {
        this.free = free;
    }
    /**
     * Obtiene el porcentaje de memoria ram libre
     * @return Porcentaje (Numero decimal del 0.0 al 100.0) de la ram libre.
     */
    public double getPorcentFree() {
        return porcentFree;
    }
    /**
     * Modifica el porcentaje de memoria ram disponible
     * @param porcentFree Nuevo porcentaje de memoria ram disponible
     */
    public void setPorcentFree(double porcentFree) {
        this.porcentFree = porcentFree;
    }
    /**
     * Obtiene el porcentaje de memoria ram en uso
     * @return Porcentaje (Numero decimal del 0.0 al 100.0) de memoria ram en uso
     */
    public double getPorcentUsed() {
        return porcentUsed;
    }
    /**
     * Modifica el valor de porcentaje de memoria ram en uso
     * @param porcentUsed Nuevo valor de porcentaje de memoria ram en uso
     */
    public void setPorcentUsed(double porcentUsed) {
        this.porcentUsed = porcentUsed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.total ^ (this.total >>> 32));
        hash = 89 * hash + (int) (this.used ^ (this.used >>> 32));
        hash = 89 * hash + (int) (this.free ^ (this.free >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.porcentFree) ^ (Double.doubleToLongBits(this.porcentFree) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.porcentUsed) ^ (Double.doubleToLongBits(this.porcentUsed) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RAM other = (RAM) obj;
        if (this.total != other.total) {
            return false;
        }
        if (this.used != other.used) {
            return false;
        }
        if (this.free != other.free) {
            return false;
        }
        if (Double.doubleToLongBits(this.porcentFree) != Double.doubleToLongBits(other.porcentFree)) {
            return false;
        }
        if (Double.doubleToLongBits(this.porcentUsed) != Double.doubleToLongBits(other.porcentUsed)) {
            return false;
        }
        return true;
    }

    /**
     * Guarda los datos de la memoria ram para su impresion
     * @return Cadena con todos los datos de uso de la memoria ram
     */
    @Override
    public String toString() {
        String cad = "";
        cad = cad + "Total: " + this.total + "MB\n";
        cad = cad + "Used: " + this.used + "KB\n";
        cad = cad + "Free: " + this.free + "KB\n";
        cad = cad + "Porcent Used: " + this.porcentUsed * 100 + "%\n";
        cad = cad + "Porcent Free: " + this.porcentFree * 100 + "%\n";
        return cad;
    }

    
}
