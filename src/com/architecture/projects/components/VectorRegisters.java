/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.architecture.projects.components;

import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class VectorRegisters {
    
    private String pc;
    private final ArrayList<String> registers;
    private static VectorRegisters instance = null;
    
    public VectorRegisters() {

        pc = "0";

        registers = new ArrayList();

        for (int i = 0; i < 16; i++) {
            registers.add("0");
        }
    }
    
    public static VectorRegisters getInstance() {
        if (instance == null) {
            instance = new VectorRegisters();
        }
        return instance;
    }
    
}
