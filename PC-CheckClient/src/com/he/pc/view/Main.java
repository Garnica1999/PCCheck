/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.he.pc.model.CPU;
import com.he.pc.model.GPU;
import com.he.pc.model.RAM;
import com.he.pc.model.Utilities;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Carlos
 */
public class Main {

    private CPU cpu;
    private RAM ram;
    private GPU[] gpus;
    private Socket socket = null;
    private ObjectInputStream ois = null;
    
    public Main(String ip) {
        
        try {
            socket = new Socket(ip, 5000);
            System.out.println("Successfully established connection with server PC-CHECK");
            System.out.println("Starting... It may take several seconds");
            ois = new ObjectInputStream(socket.getInputStream());
            while(true){
                this.cpu = (CPU)ois.readObject();
                Utilities.clear();
                System.out.println("CPU: ");
                System.out.println(cpu.toString());
                
                this.ram = (RAM) ois.readObject();
                System.out.println("RAM: ");
                System.out.println(this.ram.toString());
                
                this.gpus = (GPU[]) ois.readObject();
                System.out.println("GPUs: ");
                for(int i = 0; i < this.gpus.length; i++){
                    System.out.println("GPU " + i + ": ");
                    System.out.println(this.gpus[i].toString());
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ip = "";
        boolean todoBien = false;
        Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        if (args.length > 0) {
            Matcher matcherIp = pattern.matcher(args[0]);
            if (matcherIp.find()) {
                ip = args[0];
                todoBien = true;
            } else {
                System.out.println("IP no valid... Closing...");
                todoBien = false;
                return;
            }
            if(args[1].equals("-console")){
                todoBien = true;
            } else {
                System.out.println("GUI Mode not found.");
                System.out.println("Argument " + args[1] + " no valid. Closing...");
                todoBien = false;
                return;
            }
            if(todoBien){
                Main m = new Main(ip);
            }
        }
    }
    
}
