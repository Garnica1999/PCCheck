/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Carlos
 */
public class GPU implements Serializable{
    private String vendor;
    private String model;
    
    private ArrayList<Double> temperatures;
    private ArrayList<Double> fans;
    private ArrayList<Double> loads;
    
    public final static int LOAD_CORE = 0;
    public final static int LOAD_MEMORY_CONTROLLER = 1;
    public final static int LOAD_VIDEO_ENGINE = 2;
    public final static int LOAD_GPU_MEMORY = 3;

    public GPU(String vendor, String model, ArrayList<Double> temperatures, ArrayList<Double> fans, ArrayList<Double> loads) {
        this.vendor = vendor;
        this.model = model;
        this.temperatures = temperatures;
        this.fans = fans;
        this.loads = loads;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ArrayList<Double> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(ArrayList<Double> temperatures) {
        this.temperatures = temperatures;
    }

    public ArrayList<Double> getFans() {
        return fans;
    }

    public void setFans(ArrayList<Double> fans) {
        this.fans = fans;
    }

    public ArrayList<Double> getLoads() {
        return loads;
    }

    public void setLoads(ArrayList<Double> loads) {
        this.loads = loads;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.vendor);
        hash = 59 * hash + Objects.hashCode(this.model);
        hash = 59 * hash + Objects.hashCode(this.temperatures);
        hash = 59 * hash + Objects.hashCode(this.fans);
        hash = 59 * hash + Objects.hashCode(this.loads);
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
        final GPU other = (GPU) obj;
        if (!Objects.equals(this.vendor, other.vendor)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.temperatures, other.temperatures)) {
            return false;
        }
        if (!Objects.equals(this.fans, other.fans)) {
            return false;
        }
        if (!Objects.equals(this.loads, other.loads)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String cad = "";
        cad = cad + "\tVendor: " + vendor + "\n";
        cad = cad + "\tModel: " + model + "\n";
        cad = cad + "\tTemperatures: " + "\n";
        for(Double temps : this.temperatures){
            cad = cad + "\t\t" + temps + " C\n";
        }
        cad = cad + "\tLoads: " + "\n";
        cad = cad + "\t\tLoad GPU Core: " + this.loads.get(GPU.LOAD_CORE) + "%\n";
        cad = cad + "\t\tLoad GPU Memory Controller: " + this.loads.get(GPU.LOAD_MEMORY_CONTROLLER) + "%\n";
        cad = cad + "\t\tLoad GPU Video Engine: " + this.loads.get(GPU.LOAD_VIDEO_ENGINE) + "%\n";
        cad = cad + "\t\tLoad GPU Memory: " + this.loads.get(GPU.LOAD_GPU_MEMORY) + "%\n";
        
        cad = cad + "\tFans: " + "\n";
        for(int  i = 0 ; i < this.fans.size(); i++){
            Double fan = this.fans.get(i);
            cad = cad  + "\t\tFan #" + i + ": " + fan + " rpm\n";
        }
        return cad;
    }
}
