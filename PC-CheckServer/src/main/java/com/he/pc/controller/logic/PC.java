/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.controller.logic;

import com.he.pc.controller.logic.hardware.oshi.SystemInfo;
import com.he.pc.controller.logic.hardware.oshi.hardware.HardwareAbstractionLayer;
import com.he.pc.model.CPU;
import com.he.pc.model.GPU;
import com.he.pc.model.RAM;
import com.he.pc.view.Main;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>Clase la cual se encarga de recopilar los datos de hardware del PC y enviarlo
 * mediante sockets</p>
 * 
 * @author Garnica1999
 */
public class PC {
    
    /**
     * Campo que guarda los datos del CPU
     */
    private SystemInfo si;
    /**
     * Campo para obtener los datos de hardware del PC
     */
    private HardwareAbstractionLayer hal;
    /**
     * Cargar sensores de la CPU y GPU
     */
    private Components components;
    /**
     * 
     */
    private RamController ramController;
    
    /**
     * 
     */
    private CpuController cpuController;
    
    /**
     * 
     */
    private GpuController gpuController;
    
    /**
     * Logger
     */
    private org.slf4j.Logger log;
    
    /**
     * Constructor por defecto
     */
    public PC() {
        this.loadLogger();
        this.prepareHardware();
        this.sendData();
    }
    
    /**
     * <p>Metodo para instanciar los campos de esta clase</p>
     */
    private void prepareHardware(){
        log.info("Preparando dispositivos...");
        this.si = new SystemInfo();
        this.hal = si.getHardware();
        this.ramController = new RamController(hal);
        this.cpuController = new CpuController(hal);
        this.gpuController = new GpuController();
        log.info("Dispositivos cargados");
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
            log.info("Establishing connection with the PC-CHECK client");
            socket = serverSocket.accept();
            log.info("Successfully established connection");
            log.info("Starting... It may take several seconds");
            oos = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                //actualizar informacion del hardware
                this.updateHardware();
                //Obtencion del hardware
                CPU cpu = this.cpuController.loadCPU();
                RAM ram = this.ramController.loadRAM();
                
                this.gpuController.loadGpu(components);
                GPU[] gpus = this.gpuController.getAllGpus();
                
                //Envio de datos al pc cliente
                log.info("Enviando objeto CPU...");
                oos.writeObject(cpu);
                log.info("Enviando objeto RAM...");
                oos.writeObject(ram);
                log.info("Enviando objetos GPUs...");
                oos.writeObject(gpus);
            }
        } catch (IOException ex) {
            Logger.getLogger(PC.class.getName()).log(Level.SEVERE, null, ex);
            log.error(ex.toString());
            System.err.println("ERROR GRAVE: " + ex.toString());
        } finally {
            try {
                if(oos != null){
                    log.info("Cerrando Flujo de objetos");
                    oos.close();
                }
                if(socket != null){
                    log.info("Cerrando conexion");
                    socket.close();
                }
                if(serverSocket != null){
                    log.info("Cerrando servidor");
                    serverSocket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(PC.class.getName()).log(Level.SEVERE, null, ex);
                log.error(ex.toString());
                System.err.println("ERROR GRAVE: " + ex.toString());
            }
        }
    }
    
    /**
     * Actualiza el hardware de la maquina a los ultimos datos
     */
    private void updateHardware(){
        log.info("Actualizando dispositivos...");
        this.updateSensors();
        this.cpuController.updateData();
        this.cpuController.updateTemperatures(this.components);
        this.ramController.updateData();
        log.info("Dispositivos de hardware actualizados");
    }
    

    /**
     * Actualiza los sensores de la GPU y CPU
     */
    private void updateSensors(){
        log.info("Actualizando sensores...");
        this.components = JSensors.get.components();
        log.info("Sensores actualizados");
    }

    private void loadLogger() {
        // Options: ERROR > WARN > INFO > DEBUG > TRACE
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
        System.setProperty(org.slf4j.impl.SimpleLogger.LOG_FILE_KEY, "System.err");
        this.log = LoggerFactory.getLogger(PC.class);
    }
    
}
