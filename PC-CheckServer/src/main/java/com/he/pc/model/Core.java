/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.model;

import java.io.Serializable;

/**
 * Clase que guarda informacion unicamente de un nucleo del procesador
 * @author Garnica1999
 */
public class Core implements Serializable{
    /**
     * Identificador del nucleo
     */
    private int id;
    /**
     * Uso normalizado del nucleo (Numero entre 0.0 a 1.0)
     */
    private double use;
    /**
     * Temperatura del nucleo (Celcius)
     */
    private double temperature;
    /**
     * Constructor para instanciar objetos de la clase Core, para guardar informacion 
     * de un solo nucleo.
     * @param id Identificador del nucleo 
     * @param use Uso normalizado del nucleo (Numero entre 0.0 a 1.0)
     * @param temperature Temperatura del nucleo (Celcius)
     */
    public Core(int id, double use, double temperature) {
        this.id = id;
        this.use = use;
        this.temperature = temperature;
    }
    /**
     * Obtiene el identificador de un nucleo
     * @return Numero identificador de un nucleo
     */
    public int getId() {
        return id;
    }
    /**
     * Modifica el identificador del nucleo
     * @param id Nuevo identificador del nucleo seleccionado
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Obtiene el uso normalizado del nucleo seleccionado
     * @return Uso normalizado (Numero entre 0.0 a 1.0) del nucleo seleccionado
     */
    public double getUse() {
        return use;
    }
    /**
     * Modifica el uso normalizado del nucleo
     * @param use Nuevo valor de uso normalizado del nucleo seleccionado
     */
    public void setUse(double use) {
        this.use = use;
    }
    /**
     * Obtiene la temperatura del nucleo seleccionado
     * @return Temperatura actual (Celcius) del nucleo seleccionado
     */
    public double getTemperature() {
        return temperature;
    }
    /**
     * Modifica la temperatura del nucleo seleccionado
     * @param temperature Nueva temperatura (Celcius) del nucleo
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.id;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.use) ^ (Double.doubleToLongBits(this.use) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.temperature) ^ (Double.doubleToLongBits(this.temperature) >>> 32));
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
        final Core other = (Core) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.use) != Double.doubleToLongBits(other.use)) {
            return false;
        }
        if (Double.doubleToLongBits(this.temperature) != Double.doubleToLongBits(other.temperature)) {
            return false;
        }
        return true;
    }
    /**
     * Obtiene toda la informacion ordenada de un nucleo seleccionado
     * @return Informacion del nucleo seleccionado
     */
    @Override
    public String toString() {
        String cad = "";
        cad = cad + "\tCore Id: " + this.id + "\n";
        cad = cad + "\tUse: " + this.use + "%\n";
        cad = cad + "\tTemperature Core " + this.id + ": " + this.temperature + " C\n";
        return cad;
    }
    
    
}
