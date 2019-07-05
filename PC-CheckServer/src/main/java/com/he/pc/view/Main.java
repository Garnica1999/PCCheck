/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.view;

import com.he.pc.model.CPU;
import com.he.pc.model.CPUtilities;
import com.he.pc.model.RAM;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author Carlos
 */
public class Main {

    private Sigar sigar;
    private CPU c;
    private RAM ram;

    public Main() {
        this.sigar = new Sigar();
        //CARGAR USO DEL CPU
        this.sendData();
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m = new Main();
    }

    private void sendData() {
        ObjectOutputStream oos = null;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            int puerto = 5000;
            serverSocket = new ServerSocket(puerto);
            socket = serverSocket.accept();
            oos = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                
                this.c = this.loadCPU();
                this.ram = this.loadRAM();
                this.loadTemperatures();
                //System.out.println(this.c.getUse());
                oos.writeObject(this.c);
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
    
    private CPU loadCPU() {
        try {
            CpuInfo[] infos = this.sigar.getCpuInfoList();
            CpuInfo cpu = infos[0];
            
            return new CPU(cpu.getModel(), cpu.getVendor(), cpu.getTotalCores(), cpu.getTotalSockets(), cpu.getMhz(), CPUtilities.getCpuUse());
        } catch (SigarException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private RAM loadRAM(){
        try {
            Mem mem = this.sigar.getMem();
            System.out.println(mem.getRam());
            System.out.println(mem.getActualFree());
            System.out.println(mem.getActualUsed());
            
            System.out.println("______________________________");
            return new RAM(mem.getRam(), mem.getActualUsed(), mem.getActualFree(), mem.getFreePercent(), mem.getUsedPercent());
            
           
        } catch (SigarException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private void loadTemperatures(){
        this.loadCPUTemp();
        this.loadGPUTemp();
    }

    private void loadCPUTemp() {
        //Components components = JSensors.get.components();
    }

    private void loadGPUTemp() {

    }
}
