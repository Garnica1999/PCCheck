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
 * <p>Clase para cargar datos del procesador instalado en el equipo. En esta clase 
 * se guarda especificamente el modelo, fabricante, cantidad de nucleos fisicos 
 * y logicos, temperaturas, informacion de los nucleos y frencuecias del procesador 
 * instalado en el sistema.</p><br>
 * <p>NOTA: Recuerde que la cantidad de nucleos logicos depende exclusivamente de 
 * las tecnologias como HyperThreading (Intel) o Simultaneous Multithreading (AMD). 
 * En caso de no tener un procesador compatible con estas tecnologias, la cantidad 
 * de nucleos logicos sera la misma que la de nucleos fisicos.</p>
 * @author Garnica1999
 */
public class CPU implements Serializable{
    /**
     * Modelo del procesador. Por ejemplo: Intel(R) Core(TM) i5-7400 CPU @ 3.00GHz
     */
    private String model;
    /**
     * Fabricante del procesador. Por ejemplo: GenuineIntel
     */
    private String vendor;
    /**
     * Lista de nucleos del procesador
     */
    private ArrayList<Core> cores;
    /**
     * Numero de nucleos fisicos del procesador
     */
    private int coresNumber;
    /**
     * Numero de nucleos logicos del procesador
     */
    private int totalCores;
    /**
     * Frecuencia base (Hz) del procesador
     */
    private long frecuency;
    /**
     * Uso (Entre 0.0 a 1.0) del procesador
     */
    private double use;
    /**
     * The package temperature of cpu (Celcius)
     */
    private double temperature;
    /**
     * Constructor para instanciar objetos y guardar informacion del procesador
     * @param model <p>El modelo del procesador. Por ejemplo: Intel(R) Core(TM) 
     * i5-7400 CPU @ 3.00GHz</p>
     * @param vendor El fabricante del procesador. Por ejemplo: GenuineIntel
     * @param coresNumber Numero de nucleos fisicos del procesador
     * @param totalCores Numero de nucleos logicos del procesador
     * @param frecuency Frecuencia base (En Hz) del procesador
     * @param use Uso (Numero entre 0.0 a 1.0) del procesador
     * @param temperature Temperatura (En celcius) del procesador
     */
    public CPU(String model, String vendor, int coresNumber, int totalCores, long frecuency, double use, double temperature) {
        this.model = model;
        this.vendor = vendor;
        this.coresNumber = coresNumber;
        this.totalCores = totalCores;
        this.frecuency = frecuency;
        this.use = use;
        this.temperature = temperature;
    }
    /**
     * Obtiene el modelo del procesador
     * @return <p>Modelo del procesador. Por ejemplo: Intel(R) Core(TM) i5-7400 
     * CPU @ 3.00GHz</p>
     */
    public String getModel() {
        return model;
    }
    /**
     * Modifica el modelo del procesador
     * @param model Nuevo modelo del procesador
     */
    public void setModel(String model) {
        this.model = model;
    }
    /**
     * Obtiene el fabricante del procesador
     * @return Fabricante del procesador. Ejemplo: Genuine Intel
     */
    public String getVendor() {
        return vendor;
    }
    /**
     * Modifica el fabricante del procesador
     * @param vendor Nuevo fabricante del procesador
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    /**
     * Obtiene la lista de todos los nucleos y su informacion del CPU
     * @return Lista con la informacion de los nucleos del procesador
     */
    public ArrayList<Core> getCores() {
        return cores;
    }
    /**
     * Modifica la lista de nucleos del procesador, reemplazandola por una nueva
     * @param cores Nueva lista de nucleos con informacion del procesador
     */
    public void setCores(ArrayList<Core> cores) {
        this.cores = cores;
    }
    /**
     * Obtiene el numero de nucleos fisicos del procesador
     * @return Nuemro de procesadores fisicos del procesador
     */
    public int getCoresNumber() {
        return coresNumber;
    }
    /**
     * Modifica la informacion del numero de nucleos fisicos del procesador
     * @param coresNumber Nuevo numero de nucleos fisicos del procesador
     */
    public void setCoresNumber(int coresNumber) {
        this.coresNumber = coresNumber;
    }
    /**
     * Obtiene los nucleos logicos del procesador
     * @return Numero de nucleos logicos del procesador
     */
    public int getTotalCores() {
        return totalCores;
    }
    /**
     * modifica el numero de nucleos logicos del procesador
     * @param totalCores Nuevo numero de nucleos logicos del procesador
     */
    public void setTotalCores(int totalCores) {
        this.totalCores = totalCores;
    }
    /**
     * Obtiene la frecuencia base del procesador (Hz)
     * @return Frecuencia base del procesador (Hz)
     */
    public long getFrecuency() {
        return frecuency;
    }
    /**
     * Modifica la frecuencia base del procesador
     * @param frecuency Nueva frecuencia base del procesador
     */
    public void setFrecuency(long frecuency) {
        this.frecuency = frecuency;
    }
    /**
     * Obtiene el uso del procesador
     * @return Uso normalizado del procesador (Entre 0.0 a 1.0)
     */
    public double getUse() {
        return use;
    }
    /**
     * Modifica el uso del procesador
     * @param use Nuevo uso del procesador (Entre 0.0 a 1.0)
     */
    public void setUse(double use) {
        this.use = use;
    }
    /**
     * Obtiene la temperatura del procesador
     * @return Temperatura (Celcius) del procesador
     */
    public double getTemperature() {
        return temperature;
    }
    /**
     * Modifica la temperatura del procesador
     * @param temperature Nueva temperatura del procesador (Celcius)
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.model);
        hash = 67 * hash + Objects.hashCode(this.vendor);
        hash = 67 * hash + Objects.hashCode(this.cores);
        hash = 67 * hash + this.coresNumber;
        hash = 67 * hash + this.totalCores;
        hash = 67 * hash + (int) (this.frecuency ^ (this.frecuency >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.use) ^ (Double.doubleToLongBits(this.use) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.temperature) ^ (Double.doubleToLongBits(this.temperature) >>> 32));
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
        if (this.coresNumber != other.coresNumber) {
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
        if (Double.doubleToLongBits(this.temperature) != Double.doubleToLongBits(other.temperature)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.vendor, other.vendor)) {
            return false;
        }
        if (!Objects.equals(this.cores, other.cores)) {
            return false;
        }
        return true;
    }
    /**
     * Obtiene toda la informacion del procesador
     * @return Informacion del procesador
     */
    @Override
    public String toString() {
        String cad = "__________________________________\n";
        cad = cad + "Processor : " + this.vendor + "-" + this.model + "\n";
        cad = cad + "Frecuency: " + this.frecuency + " Hz" + "\n";
        cad = cad + "Use: " + this.use + "%" + "\n";
        cad = cad + "Physic Cores: " + this.coresNumber + "\n";
        cad = cad + "Logic Cores: " + this.totalCores + "\n";
        cad = cad + "Package Temperature: " + this.temperature + " C\n";
        cad = cad + "Core Information: \n";
        for(Core core : this.cores){
            cad = cad + core.toString();
            cad = cad + "-----------------------------------\n";
        }
        cad = cad + "\n" + "__________________________________";
        return cad;
    }
}
