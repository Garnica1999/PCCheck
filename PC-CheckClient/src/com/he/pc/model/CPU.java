/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Carlos
 */
public class CPU implements Serializable{
    private String model;
    private String vendor;
    private int cores;
    private int totalCores;
    private int frecuency;
    private double use;

    public CPU(String model, String vendor, int cores, int totalCores, int frecuency, double use) {
        this.model = model;
        this.vendor = vendor;
        this.cores = cores;
        this.totalCores = totalCores;
        this.frecuency = frecuency;
        this.use = use;
    }

    public CPU(String model, String vendor, int cores, int totalCores, int frecuency) {
        this.model = model;
        this.vendor = vendor;
        this.cores = cores;
        this.totalCores = totalCores;
        this.frecuency = frecuency;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getTotalCores() {
        return totalCores;
    }

    public void setTotalCores(int totalCores) {
        this.totalCores = totalCores;
    }

    public int getFrecuency() {
        return frecuency;
    }

    public void setFrecuency(int frecuency) {
        this.frecuency = frecuency;
    }

    public double getUse() {
        return use;
    }

    public void setUse(double use) {
        this.use = use;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.model);
        hash = 67 * hash + Objects.hashCode(this.vendor);
        hash = 67 * hash + this.cores;
        hash = 67 * hash + this.totalCores;
        hash = 67 * hash + this.frecuency;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.use) ^ (Double.doubleToLongBits(this.use) >>> 32));
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
        final CPU other = (CPU) obj;
        if (this.cores != other.cores) {
            return false;
        }
        if (this.totalCores != other.totalCores) {
            return false;
        }
        if (this.frecuency != other.frecuency) {
            return false;
        }
        if (Double.doubleToLongBits(this.use) != Double.doubleToLongBits(other.use)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.vendor, other.vendor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CPU{" + "model=" + model + ", vendor=" + vendor + ", cores=" + cores + ", totalCores=" + totalCores + ", frecuency=" + frecuency + ", use=" + use + '}';
    }
    
}
