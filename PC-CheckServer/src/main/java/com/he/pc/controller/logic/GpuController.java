/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.controller.logic;

import com.he.pc.model.GPU;
import com.he.pc.view.Main;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Gpu;
import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Sensors;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos
 */
public class GpuController {
    private ArrayList<GPU> gpus;
    /**
     * Logger
     */
    private org.slf4j.Logger log;
    

    public GpuController() {
        this.loadLogger();
        gpus = new ArrayList<>();
    }
    
    public void loadGpu(Components components){
        this.gpus.clear();
        List<Gpu> gpus = components.gpus;
        for(Gpu gpu : gpus){
            if(gpu.sensors != null){
                Sensors sensors = gpu.sensors;
                List<Temperature> temps = sensors.temperatures;
                List<Fan> fansTemp = sensors.fans;
                List<Load> loadsTemp = sensors.loads;
                String name = gpu.name;
                String vendor = name.split(" ")[0];
                
                ArrayList<Double> fans = new ArrayList<>();
                for(Fan fan : fansTemp){
                    fans.add(fan.value);
                }
                ArrayList<Double> temperatures = new ArrayList<>();
                for(Temperature t : temps){
                    temperatures.add(t.value);
                }
                
                ArrayList<Double> loads = new ArrayList<>();
                for(Load l : loadsTemp){
                    loads.add(l.value);
                }
                temps = null;
                fansTemp = null;
                loadsTemp = null;
                
                GPU g = new GPU(vendor, name, temperatures, fans, loads);
                this.gpus.add(g);
            }
        }
    }
    
    public GPU getGPU(int numberGPU){
        try{
            return this.gpus.get(numberGPU);
        }catch(IndexOutOfBoundsException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            log.error(ex.toString());
            System.err.println("ERROR GRAVE: " + ex.toString());
            return null;
        }
    }
    
    public double[] getTemperatures(int numberGpu) throws IndexOutOfBoundsException{
        GPU g = this.gpus.get(numberGpu);
        int sizeTemps = g.getTemperatures().size();
        double[] temperatures =  new double[sizeTemps];
        for(int i = 0; i < sizeTemps; i++){
            temperatures[i] = g.getTemperatures().get(i);
        }
        return temperatures;
    }
    
    public GPU[] getAllGpus() throws IndexOutOfBoundsException{
        GPU[] gpus = new GPU[this.gpus.size()];
        for(int i = 0 ; i < gpus.length; i++){
            gpus[i] = this.gpus.get(i);
        }
        return gpus;
    }
    
    private void loadLogger() {
        // Options: ERROR > WARN > INFO > DEBUG > TRACE
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
        System.setProperty(org.slf4j.impl.SimpleLogger.LOG_FILE_KEY, "System.err");
        this.log = LoggerFactory.getLogger(GpuController.class);
    }
}
