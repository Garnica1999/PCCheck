/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.model;

import java.io.Serializable;

/**
 *
 * @author Carlos
 */
public class RAM implements Serializable{
    /**
     * Total RAM Memory on MB (Megabytes)
     */
    private long total;
    /**
     * Used RAM Memory on KB (Kilobytes)
     */
    private long used;
    /**
     * Free RAM Memory on KB (Kilobytes)
     */
    private long free;
    /**
     * Porcent free RAM Memory on KB (Kilobytes)
     */
    private double porcentFree;
    /**
     * Porcent used RAM Memory on KB (Kilobytes)
     */
    private double porcentUsed;

    public RAM(long total, long used, long free, double porcentFree, double porcentUsed) {
        this.total = total;
        this.used = used;
        this.free = free;
        this.porcentFree = porcentFree;
        this.porcentUsed = porcentUsed;
    }

    public RAM(long total, long used, long free) {
        this.total = total;
        this.used = used;
        this.free = free;
    }
    
    

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public double getPorcentFree() {
        return porcentFree;
    }

    public void setPorcentFree(double porcentFree) {
        this.porcentFree = porcentFree;
    }

    public double getPorcentUsed() {
        return porcentUsed;
    }

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

    @Override
    public String toString() {
        return "RAM{" + "total=" + total + ", used=" + used + ", free=" + free + ", porcentFree=" + porcentFree + ", porcentUsed=" + porcentUsed + '}';
    }

    
}
