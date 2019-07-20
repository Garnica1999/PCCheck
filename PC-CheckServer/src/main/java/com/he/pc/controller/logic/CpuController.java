/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.controller.logic;

import com.he.pc.controller.logic.hardware.oshi.hardware.CentralProcessor;
import com.he.pc.controller.logic.hardware.oshi.hardware.HardwareAbstractionLayer;
import com.he.pc.controller.logic.hardware.oshi.util.Util;
import com.he.pc.model.CPU;
import com.he.pc.model.Core;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.slf4j.LoggerFactory;

/**
 * Clase que se utiliza paera obtener informacion del procesador mas facilmente
 * @author Garnica1999
 */
public class CpuController {
    /**
     * Lista que guarda las temperaturas del procesador. El ultimo
     * elemento de la lista es la temperatura del package del procesador, el resto 
     * es la temeperatura de los nucleos
     */
    private ArrayList<Double> tempsCpu;
    
    /**
     * Campo para gardar los datos de la clase Sigar
     */
    private final Sigar sigar;
    
    /**
     * Campo para obtener la informacion del procesador
     */
    private CentralProcessor processor;
    
    /**
     * Logger
     */
    private org.slf4j.Logger log;
    
    /**
     * Constructor principal, inicializa la lista de temperaturas del CPU
     * @param hal Abstraccion del hardware
     */
    public CpuController(HardwareAbstractionLayer hal) {
        this.loadLogger();
        this.sigar = new Sigar();
        this.tempsCpu = new ArrayList<>();
        this.processor = hal.getProcessor();
    }
    
    /**
     * <p>Obtiene el uso de todos los nucleos del procesador</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return Arreglo con el uso normalizado de cada nucleo
     * @deprecated No se utiliza por no dar los datos de uso del cpu correctamente
     */
    @Deprecated
    public double[] getCpuCoresUse(CentralProcessor processor) {
        
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
    public String getCpuName(CentralProcessor processor){
        return processor.getName();
    }
    /**
     * <p>Obtiene el fabricante del procesador instalado en el equipo</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return El fabricante del procesador. Por ejemplo: GenuineIntel
     */
    public String getCpuVendor(CentralProcessor processor){
        return processor.getVendor();
    }
    /**
     * <p>Obtiene la frecuencia base del procesador</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return La frecuencia del procesador (En Hz)
     */
    public long getCpuFrecuency(CentralProcessor processor){
        return processor.getMaxFreq();
    }
    /**
     * <p>Obtiene la cantidad de nucleos fisicos del procesador</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return Cantidad de nucleos fisicos del procesador
     */
    public int getCpuCoresCount(CentralProcessor processor){
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
    public int getCpuLogicalCoresCount(CentralProcessor processor){
        return processor.getLogicalProcessorCount();
    }
    /**
     * <p>Obtiene el uso del procesador, entre un numero entre 0 a 1</p>
     * @param processor <p>objeto de la clase CentralProcessor, contiene la 
     * informacion del procesador</p>
     * @return Uso del procesador, numero entre 0 a 1
     */
    public double getCpuUseNorm(CentralProcessor processor){
        return processor.getSystemCpuLoad();
    }
    /**
     * <p>Obtiene el porcentaje uso del procesador utilizando la API Sigar</p>
     * @return El porcentaje de uso del procesador.
     * @throws SigarException Sigar API ejecutara esta excepcion en caso de tener 
     * un error de lectura del hardware
     */
    public double getCpuUse() throws SigarException{
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
    public double loadCpuTemp() {
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
    public double loadCpuCoreTemp(int core) {
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
    private void loadCpuTemps(Components components){
        this.tempsCpu.clear();
        
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
    public Double getTempCpuCore(int core){
        try{
            return this.tempsCpu.get(core);
        }catch(IndexOutOfBoundsException ex){
            Logger.getLogger(PC.class.getName()).log(Level.SEVERE, null, ex);
            log.error(ex.toString());
            System.err.println("ERROR GRAVE: " + ex.toString());
            return null;
        }
    }
    /**
     * <p>Actualiza el listado con nuevas temperaturas que actualmente tenga el
     * procesador</p>
     * @param components the param
     */
    public void updateTemperatures(Components components){
        log.info("Actualizando temperaturas del procesador");
        this.loadCpuTemps(components);
    }
    
    /**
     * <p>Carga la informacion completa del CPU, incluyendo modelos, frencuencias, 
     * fabricantes, uso del CPU, uso de los cores, cantidad de nucleos logicos y 
     * fisicos.</p>
     * @return Objeto de la clase CPU con informacion del procesador
     */
    CPU loadCPU() {
        try {
            
            
            /*Declarar, instanciar e inicializar una lista para guardar la 
            informacion de los nucleos*/
            ArrayList<Core> cores = new ArrayList<>();
            /*
            Obtener informacion del CPU
            */
            String model = this.getCpuName(processor);
            String vendor = this.getCpuVendor(processor);
            double cpuUse = this.getCpuUseNorm(processor);
            long frecuency = this.getCpuFrecuency(processor);
            int physicalCores = this.getCpuCoresCount(processor);
            int logicalCores = this.getCpuLogicalCoresCount(processor);
            CpuPerc[] porcent = this.sigar.getCpuPercList();
            //Instanciar e inicializar objeto c del CPU
            CPU c = new CPU(model, vendor, physicalCores, logicalCores, frecuency, cpuUse, this.getTempCpuCore(porcent.length));
           
            //Guardar la informacion de todos los nucleos del procesador
            
            for(int core = 0; core < porcent.length; core++){
                //CpuInfo cpu = infos[core];
                cores.add(new Core(core, porcent[core].getCombined(), this.getTempCpuCore(core)));
            }
            //Agregar la lista de nucleos al objeto de la clase CPU
            c.setCores(cores);
            return c;
        } catch (SigarException ex) {
            Logger.getLogger(PC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /**
     * Actualiza los datos del procesador
     */
    public void updateData(){
        log.info("Actualizando datos del procesador");
        this.processor.updateAttributes();
    }
    
    private void loadLogger() {
        // Options: ERROR > WARN > INFO > DEBUG > TRACE
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
        System.setProperty(org.slf4j.impl.SimpleLogger.LOG_FILE_KEY, "System.err");
        this.log = LoggerFactory.getLogger(GpuController.class);
    }
}
