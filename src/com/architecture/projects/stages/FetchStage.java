/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.architecture.projects.stages;

import com.architecture.projects.components.InstructionMemory;
import com.architecture.projects.utilities.Utility;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class FetchStage extends Observable implements Runnable {
    
    private Thread t;
    private final String threadName;
    private String instructionFetched = "";
    private String pc;

    public String getPC() {
        return pc;
    }
    private static FetchStage instance;

    public FetchStage() {

        threadName = "InstructionFetchStage";
        pc = "0000000000000000";
    }
    
    public static FetchStage getInstance() {
        if (instance == null) {
            instance = new FetchStage();            
        }
        return instance;
    }
    
    @Override
    public void run() {
        long startTime = System.nanoTime();
        InstructionMemory instructionMemory = InstructionMemory.getInstance();

        String instruction = instructionMemory.readInstruction(pc);
        instructionFetched = instruction;

        int number0 = Utility.binaryToDecimal(pc);
        int number1 = Utility.binaryToDecimal("1000");

        int sum = number0 + number1;
        pc = Utility.decimalToBinary(sum);
        
        //this.setChanged();
        //this.notifyObservers();
        
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000;
        System.out.println("Tiempo ejecución Fetch: "+totalTime);
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(FetchStage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Punto de entrada del thread.
     */
    public void start() {

        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        } else {
            t.start();
        }
    }

    /**
     * Se retorna el resultado de la instruccion
     *
     * @return
     */
    public String getInstructionFetched() {
        System.out.println("Instruction Fetched: "+instructionFetched);
        return instructionFetched;
    }
    
}
