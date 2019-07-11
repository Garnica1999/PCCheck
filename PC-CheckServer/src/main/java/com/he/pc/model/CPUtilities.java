/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.model;

import com.he.pc.controller.logic.hardware.oshi.hardware.CentralProcessor;
import com.he.pc.controller.logic.hardware.oshi.util.Util;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import java.util.ArrayList;
import java.util.List;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * Clase que se utiliza paera obtener informacion del procesador mas facilmente
 * @author Garnica1999
 */
public class CPUtilities {
    /**
     * Lista que guarda las temperaturas del procesador. El ultimo
     * elemento de la lista es la temperatura del package del procesador, el resto 
     * es la temeperatura de los nucleos
     */
    private ArrayList<Double> tempsCpu;
    
    /**
     * Constructor principal, inicializa la lista de temperaturas del CPU
     */
    public CPUtilities() {
        this.tempsCpu = new ArrayList<>();
    }
    
    /**
     * <p>Obtiene el uso de todos los nucleos del procesador</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return Arreglo con el uso normalizado de cada nucleo
     */
    public static double[] getCpuCoresUse(CentralProcessor processor) {
        
        long[][] prevProcTicks = processor.getProcessorCpuLoadTicks();

        processor.updateAttributes();

        //StringBuilder procCpu = new StringBuilder("CPU load per processor:");
        double[] load = processor.getProcessorCpuLoadBetweenTicks(prevProcTicks);
        for(int i = 0; i < load.length; i++){
            load[i] = load[i];
        }
        Util.sleep(500);
        return load;
    }
    /**
     * Obtiene el modelo (nombre) del procesador del equipo
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return El nombre del procesador. Por ejemplo Intel(R) Core(TM) i5-7400 CPU @ 3.00GHz
     */
    public static String getCpuName(CentralProcessor processor){
        return processor.getName();
    }
    /**
     * <p>Obtiene el fabricante del procesador instalado en el equipo</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return El fabricante del procesador. Por ejemplo: GenuineIntel
     */
    public static String getCpuVendor(CentralProcessor processor){
        return processor.getVendor();
    }
    /**
     * <p>Obtiene la frecuencia base del procesador</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return La frecuencia del procesador (En Hz)
     */
    public static long getCpuFrecuency(CentralProcessor processor){
        return processor.getMaxFreq();
    }
    /**
     * <p>Obtiene la cantidad de nucleos fisicos del procesador</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return Cantidad de nucleos fisicos del procesador
     */
    public static int getCpuCoresCount(CentralProcessor processor){
        return processor.getPhysicalProcessorCount();
    }
    /**
     * <p>Obtiene la cantidad de nucleos logicos del procesador. En caso de Intel, 
     * si el procesador tiene la tecnologia HyperThreading, o en AMD, si tiene 
     * habilitado la tecnologia Simultaneous Multithreading, siempre es el doble 
     * de la cantidad de nucleos fisicos. Si esto no se cumple, sera la misma 
     * cantidad que de nucleos fisicos</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return Cantidad de nucleos logicos del procesador instalado en el equipo
     */
    public static int getCpuLogicalCoresCount(CentralProcessor processor){
        return processor.getLogicalProcessorCount();
    }
    /**
     * <p>Obtiene el uso del procesador, entre un numero entre 0 a 1</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return Uso del procesador, numero entre 0 a 1
     */
    public static double getCpuUseNorm(CentralProcessor processor){
        return processor.getSystemCpuLoad();
    }
    /**
     * <p>Obtiene el porcentaje uso del procesador utilizando la API Sigar</p>
     * @return El porcentaje de uso del procesador.
     * @throws SigarException Sigar API ejecutara esta excepcion en caso de tener 
     * un error de lectura del hardware
     */
    public static double getCpuUse() throws SigarException{
        Sigar s = new Sigar();
        CpuPerc cpuUse = s.getCpuPerc();
        return cpuUse.getCombined() * 100;
    }
    
    /**
     * Carga y obtiene la temperatura (Grados Celcius) del package del procesador
     * @return Temperatura (En celcius) del procesador
     * @deprecated <p>Este metodo se dejo de utilizar en la segunda version alpha
     * del programa, ya que provocaba que el programa se demorara en actualizar
     * la informacion de hardware, debido al diseño de la API Jsensor</p>
     */
    @Deprecated
    public static double loadCpuTemp() {
        Components components = JSensors.get.components();
        List<Cpu> cpus = components.cpus;
        if(cpus != null){
            for(Cpu cpu : cpus){
                if(cpu.sensors != null){
                    List<com.profesorfalken.jsensors.model.sensors.Temperature> temps = cpu.sensors.temperatures;
                    return temps.get(temps.size() - 1).value;
                    /*for(com.profesorfalken.jsensors.model.sensors.Temperature temp : temps){
                        //System.out.println(temp.name + ": " + temp.value + " C");
                    }*/
                }
            }
        }
        //List<Gpu> gpu = components.gpus;
        return 0;
    }
    
    /**
     * Obtiene la temperatura por un nucleo dado del procesador
     * @param core numero del nucleo, de 0 a N, donde N es el numero de nucleos
     * del procesador - 1
     * @return la temperatura del nucleo especificado del procesador
     * @deprecated <p>Este metodo se dejo de utilizar en la segunda version alpha
     * del programa, ya que provocaba que el programa se demorara en actualizar
     * la informacion de hardware, debido al diseño de la API Jsensor</p>
     */
    @Deprecated
    public static double loadCpuCoreTemp(int core) {
        Components components = JSensors.get.components();
        List<Cpu> cpus = components.cpus;
        if(cpus != null){
            for(Cpu cpu : cpus){
                if(cpu.sensors != null){
                    List<com.profesorfalken.jsensors.model.sensors.Temperature> temps = cpu.sensors.temperatures;
                    return temps.get(core).value;
                    /*for(com.profesorfalken.jsensors.model.sensors.Temperature temp : temps){
                        //System.out.println(temp.name + ": " + temp.value + " C");
                    }*/
                }
            }
        }
        //List<Gpu> gpu = components.gpus;
        return 0;
    }
    /**
     * <p>Carga todas las temperaturas del procesador en una lista. El ultimo
     * index de la lista es la temperatura del package del procesador, el resto 
     * es la temeperatura de los nucleos</p>
     */
    private void loadCpuTemps(){
        this.tempsCpu.clear();
        Components components = JSensors.get.components();
        List<Cpu> cpus = components.cpus;
        if(cpus != null){
            for(Cpu cpu : cpus){
                if(cpu.sensors != null){
                    List<com.profesorfalken.jsensors.model.sensors.Temperature> temps = cpu.sensors.temperatures;
                    for(com.profesorfalken.jsensors.model.sensors.Temperature temp : temps){
                        tempsCpu.add(temp.value);
                    }
                }
            }
        }
    }
    /**
     * Obtiene la temperatura de un nucleo dado como entrada
     * @param core <p>Temperatura (Celcius) de un nucleo del procesador</p>
     * @return Temperatura (en celcius) de un nucleo del procesador
     */
    public double getTempCpuCore(int core){
        return this.tempsCpu.get(core);
    }
    /**
     * <p>Actualiza el listado con nuevas temperaturas que actualmente tenga el
     * procesador</p>
     */
    public void updateTemperatures(){
        this.loadCpuTemps();
    }
}
