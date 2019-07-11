/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.model;

import com.he.pc.controller.logic.hardware.oshi.hardware.GlobalMemory;

/**
 * <p>Clase apra obtener informacion de la ram mas facilmente</p>
 * @author Garnica1999
 */
public class RAMUtilities {
    /**
     * Obtiene la memoria ram que no esta en uso
     * @param memory <p>objeto de la clase GlobalMemory, para obtener informacion
     * vital de la memoria ram del sistema, obtenida por la API OSHI</p>
     * @return Memoria RAM disponible (En Bytes)
     */
    public static long getMemoryAvailable(GlobalMemory memory){
        return memory.getAvailable();
    }
    /**
     * Obtiene la informacion de la memoria RAM total instalada en el sistema
     * @param memory <p>objeto de la clase GlobalMemory, para obtener informacion
     * vital de la memoria ram del sistema, obtenida por la API OSHI</p>
     * @return Memoria ram total instalada en el sistema (En Bytes)
     */
    public static long getMemoryTotal(GlobalMemory memory){
        return memory.getTotal();
    }
    /**
     * Obtiene la memoria ram en uso
     * @param memory <p>objeto de la clase GlobalMemory, para obtener informacion
     * vital de la memoria ram del sistema, obtenida por la API OSHI</p>
     * @return Memoria ram en uso (En Bytes)
     */
    public static long getMemoryUsed(GlobalMemory memory){
        return memory.getTotal() - memory.getAvailable();
    }
    /**
     * Obtiene el procentaje disponible de la memoria RAM
     * @param memory <p>objeto de la clase GlobalMemory, para obtener informacion
     * vital de la memoria ram del sistema, obtenida por la API OSHI</p>
     * @return Porcentaje normalizado de la memoria ram disponible (Numero entre 0.0 a 1.0)
     */
    public static double getPorcentAvailableMem(GlobalMemory memory){
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
    public static double getPorcentUsedMem(GlobalMemory memory){
        long used = RAMUtilities.getMemoryUsed(memory);
        long total = memory.getTotal();
        double porcent = ((double)used)/((double)total);
        return porcent;
    }
}
