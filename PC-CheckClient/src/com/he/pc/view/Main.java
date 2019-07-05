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
import com.he.pc.model.RAM;

/**
 *
 * @author Carlos
 */
public class Main {

    private CPU cpu;
    private RAM ram;
    private Socket socket = null;
    private ObjectInputStream ois = null;
    
    public Main() {
        
        try {
            socket = new Socket("192.168.0.19", 5000);
            ois = new ObjectInputStream(socket.getInputStream());
            while(true){
                this.cpu = (CPU)ois.readObject();
                System.out.println(cpu.toString());
                this.ram = (RAM) ois.readObject();
                System.out.println(this.ram.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
        Main m = new Main();
    }
    
}
