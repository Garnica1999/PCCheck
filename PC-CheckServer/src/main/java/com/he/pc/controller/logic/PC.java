/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.controller.logic;

import com.he.pc.controller.logic.hardware.oshi.SystemInfo;
import com.he.pc.controller.logic.hardware.oshi.hardware.CentralProcessor;
import com.he.pc.controller.logic.hardware.oshi.hardware.GlobalMemory;
import com.he.pc.controller.logic.hardware.oshi.hardware.HardwareAbstractionLayer;
import com.he.pc.model.CPU;
import com.he.pc.model.CPUtilities;
import com.he.pc.model.Core;
import com.he.pc.model.RAM;
import com.he.pc.model.RAMUtilities;
import com.he.pc.view.Main;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * <p>Clase la cual se encarga de recopilar los datos de hardware del PC y enviarlo
 * mediante sockets</p>
 * 
 * @author Garnica1999
 */
public class PC {
    /**
     * Campo para gardar los datos de la clase Sigar
     */
    private final Sigar sigar;
    /**
     * Campo que guarda los datos del CPU
     */
    private CPU cpuMain;
    /**
     * Campo que guarda los datos de la memoria RAM
     */
    private RAM ram;
    /**
     * Campo para inicializar el acceso al hardware
     */
    private SystemInfo si;
    /**
     * Campo para obtener los datos de hardware del PC
     */
    private HardwareAbstractionLayer hal;
    /**
     * Campo para obtener la informacion del procesador
     */
    private CentralProcessor processor;
    /**
     * Campo para guardar la informacion de la memoria RAM
     */
    private GlobalMemory memory;
    /**
     * Campo utilizado para obtener temperaturas, carga, e informacion del CPU
     */
    private CPUtilities cpuUtilities;

    /**
     * Constructor por defecto
     */
    public PC() {
        this.sigar = new Sigar();
        this.prepareHardware();
        this.sendData();
    }
    
    /**
     * <p>Metodo para instanciar los campos de esta clase</p>
     */
    private void prepareHardware(){
        this.si = new SystemInfo();
        this.hal = si.getHardware();
        this.processor = hal.getProcessor();
        this.memory = hal.getMemory();
        this.cpuUtilities = new CPUtilities();
    }
    
    /**
     * <p>Su uso es para enviar los datos mediante Sockets, se utiliza el puerto
     * 5000 para enviar estos datos. Se utiliza para enviar todos los datos del 
     * hardware disponible</p>
     */
    private void sendData() {
        //Declarar, instanciar e inicializar
        ObjectOutputStream oos = null;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            //Puerto 5000
            int puerto = 5000;
            serverSocket = new ServerSocket(puerto);
            System.out.println("Establishing connection with the PC-CHECK client");
            socket = serverSocket.accept();
            System.out.println("Successfully established connection");
            System.out.println("Starting... It may take several seconds");
            oos = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                //Obtencion del hardware
                this.cpuMain = this.loadCPU();
                this.ram = this.loadRAM();
                //Envio de datos al pc cliente
                oos.writeObject(this.cpuMain);
                oos.writeObject(this.ram);
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null){
                    oos.close();
                }
                if(socket != null){
                    socket.close();
                }
                if(serverSocket != null){
                    serverSocket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * <p>Carga la informacion completa del CPU, incluyendo modelos, frencuencias, 
     * fabricantes, uso del CPU, uso de los cores, cantidad de nucleos logicos y 
     * fisicos.</p>
     * @return Objeto de la clase CPU con informacion del procesador
     */
    private CPU loadCPU() {
        try {
            //actualizar informacion del hardware
            this.updateHardware();
            
            /*Declarar, instanciar e inicializar una lista para guardar la 
            informacion de los nucleos*/
            ArrayList<Core> cores = new ArrayList<>();
            /*
            Obtener informacion del CPU
            */
            String model = CPUtilities.getCpuName(processor);
            String vendor = CPUtilities.getCpuVendor(processor);
            double cpuUse = CPUtilities.getCpuUseNorm(processor);
            long frecuency = CPUtilities.getCpuFrecuency(processor);
            int physicalCores = CPUtilities.getCpuCoresCount(processor);
            int logicalCores = CPUtilities.getCpuLogicalCoresCount(processor);
            CpuPerc[] porcent = this.sigar.getCpuPercList();
            //Instanciar e inicializar objeto c del CPU
            CPU c = new CPU(model, vendor, physicalCores, logicalCores, frecuency, cpuUse, this.cpuUtilities.getTempCpuCore(porcent.length));
           
            //Guardar la informacion de todos los nucleos del procesador
            
            for(int core = 0; core < porcent.length; core++){
                //CpuInfo cpu = infos[core];
                
                cores.add(new Core(core, porcent[core].getCombined(), this.cpuUtilities.getTempCpuCore(core)));
                
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
     * Actualiza el hardware de la maquina a los ultimos datos
     */
    private void updateHardware(){
        this.processor.updateAttributes();
        this.memory.updateAttributes();
        this.cpuUtilities.updateTemperatures();
    }
    /**
     * Obtiene la informacion de uso de la memoria RAM del equipo
     * @return Un objeto de la clase RAM, guardando la informacion de uso de la
     * memoria ram
     */
    private RAM loadRAM(){
        /*
        Obtencion de los datos de uso de la memoria RAM (ram usada, disponible, 
        total, porcentado disponible y porcentaje utilizado)
        */
        long used = RAMUtilities.getMemoryUsed(memory);
        long total = RAMUtilities.getMemoryTotal(memory);
        long available = RAMUtilities.getMemoryAvailable(memory);
        double porcentAvailable = RAMUtilities.getPorcentAvailableMem(memory);
        double porcentUsed = RAMUtilities.getPorcentUsedMem(memory);
        
        return new RAM(total, used, available, porcentAvailable, porcentUsed);
    } 

    
}
