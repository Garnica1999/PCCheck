/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.model;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author Carlos
 */
public class CPUtilities {
    
    public static double getCpuUse() throws SigarException{
        Sigar s = new Sigar();
        CpuPerc cpuUse = s.getCpuPerc();
        return cpuUse.getCombined() * 100;
    }
}
