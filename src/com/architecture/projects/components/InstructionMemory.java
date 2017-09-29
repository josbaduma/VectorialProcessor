/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.architecture.projects.components;

import com.architecture.projects.utilities.Utility;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class InstructionMemory {
    private final ArrayList<String> instructions;
    private static InstructionMemory instance = null;
    private int pointer = 0;

    /**
     * Instancia de nueva memoria de instrucciones
     * Setea en o mil posiciones de memoria.
     */
    public InstructionMemory() {

        instructions = new ArrayList();

        for (int i = 0; i < 65536; i++) {
            instructions.add("00000000000000000000000000000000");
        }
    }

    /**
     * Singleton
     * @return 
     */
    public static InstructionMemory getInstance() {
        if (instance == null) {
            instance = new InstructionMemory();
        }
        return instance;
    }

    /**
     * Access the instruction memory and returns the value.
     *
     * @param address, expected in binary
     * @return an instruction encoded in binary
     */
    public String readInstruction(String address) {

        int index = Utility.binaryToDecimal(address);
        return instructions.get(index);
    }
    
    /**
     * Agrega una nueva instruccion
     * @param instruction 
     */
    public void addInstruction( String instruction){
        
        instructions.set(pointer, instruction);
        pointer++;
    }

    /**
     * Obtiene una instruccion
     * para debug
     * @return 
     */
    public ArrayList<String> getInstructions() {
        return instructions;
    }
    
    public int getPointer(){
        return pointer;
    }
        public void setPointer(int value){
         pointer=value;
    }
}
