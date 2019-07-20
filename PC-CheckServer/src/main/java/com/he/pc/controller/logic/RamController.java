/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.controller.logic;

import com.he.pc.controller.logic.hardware.oshi.hardware.GlobalMemory;
import com.he.pc.controller.logic.hardware.oshi.hardware.HardwareAbstractionLayer;
import com.he.pc.model.RAM;
import org.slf4j.LoggerFactory;

/**
 * <p>Clase apra obtener informacion de la ram mas facilmente</p>
 * @author Garnica1999
 */
public class RamController {
    /**
     * Campo para guardar la informacion de la memoria RAM
     */
    private final GlobalMemory memory;
    
    /**
     * Logger
     */
    private org.slf4j.Logger log;

    public RamController(HardwareAbstractionLayer hal) {
        this.loadLogger();
        this.memory = hal.getMemory();
    }

    /**
     * Obtiene la memoria ram que no esta en uso
     * @param memory <p>objeto de la clase GlobalMemory, para obtener informacion
     * vital de la memoria ram del sistema, obtenida por la API OSHI</p>
     * @return Memoria RAM disponible (En Bytes)
     */
    public long getMemoryAvailable(GlobalMemory memory){
        return memory.getAvailable();
    }
    /**
     * Obtiene la informacion de la memoria RAM total instalada en el sistema
     * @param memory <p>objeto de la clase GlobalMemory, para obtener informacion
     * vital de la memoria ram del sistema, obtenida por la API OSHI</p>
     * @return Memoria ram total instalada en el sistema (En Bytes)
     */
    public long getMemoryTotal(GlobalMemory memory){
        return memory.getTotal();
    }
    /**
     * Obtiene la memoria ram en uso
     * @param memory <p>objeto de la clase GlobalMemory, para obtener informacion
     * vital de la memoria ram del sistema, obtenida por la API OSHI</p>
     * @return Memoria ram en uso (En Bytes)
     */
    public long getMemoryUsed(GlobalMemory memory){
        return memory.getTotal() - memory.getAvailable();
    }
    /**
     * Obtiene el procentaje disponible de la memoria RAM
     * @param memory <p>objeto de la clase GlobalMemory, para obtener informacion
     * vital de la memoria ram del sistema, obtenida por la API OSHI</p>
     * @return Porcentaje normalizado de la memoria ram disponible (Numero entre 0.0 a 1.0)
     */
    public double getPorcentAvailableMem(GlobalMemory memory){
        long available = memory.getAvailable();
        long total = memory.getTotal();
        
        double porcent = (((double)(available))/((double)(total)));
        return porcent;
    }
    /**
     * Obtiene el procentaje de la memoria ram en uso
     * @param memory <p>objeto de la clase GlobalMemory, para obtener informacion
     * vital de la memoria ram del sistema, obtenida por la API OSHI</p>
     * @return Porcentaje normalizado (Entre 0.0 a 1.0) de la memoria ram en uso
     */
    public double getPorcentUsedMem(GlobalMemory memory){
        long used = this.getMemoryUsed(memory);
        long total = memory.getTotal();
        double porcent = ((double)used)/((double)total);
        return porcent;
    }
    
    /**
     * Obtiene la informacion de uso de la memoria RAM del equipo
     * @return Un objeto de la clase RAM, guardando la informacion de uso de la
     * memoria ram
     */
    public RAM loadRAM(){
        /*
        Obtencion de los datos de uso de la memoria RAM (ram usada, disponible, 
        total, porcentado disponible y porcentaje utilizado)
        */
        long used = this.getMemoryUsed(memory);
        long total = this.getMemoryTotal(memory);
        long available = this.getMemoryAvailable(memory);
        double porcentAvailable = this.getPorcentAvailableMem(memory);
        double porcentUsed = this.getPorcentUsedMem(memory);
        
        return new RAM(total, used, available, porcentAvailable, porcentUsed);
    }
    /**
     * Actualiza los datos de la memoria RAM
     */
    public void updateData(){
        log.info("Actualizando datos de la memoria RAM");
        this.memory.updateAttributes();
    }
    
    private void loadLogger() {
        // Options: ERROR > WARN > INFO > DEBUG > TRACE
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
        System.setProperty(org.slf4j.impl.SimpleLogger.LOG_FILE_KEY, "System.err");
        this.log = LoggerFactory.getLogger(RamController.class);
    }
}
