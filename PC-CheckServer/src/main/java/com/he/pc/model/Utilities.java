/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.he.pc.model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que almacena metodos utilitarios para la consola y el programa en general
 * @author Garnica1999
 */
public class Utilities {
    /**
     * Limpia la consola de texto anterior
     */
    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
