/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.view;

import com.he.pc.controller.logic.PC;

/**
 * Clase principal
 * @author Garnica1999
 */
public class Main {

    /**
     * Contructor principal, inicializa un objeto de la clase PC
     */
    public Main() {
        System.out.println("Welcome to PC-CHECK");
        //System.out.println(System.getProperty("java.library.path"));
        PC pc = new PC();
        
    }

    
    /**
     * Metodo principal
     * @param args Los argumentos dados por consola
     */
    public static void main(String[] args) {
        Main m = new Main();
    }

    
}
