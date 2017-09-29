/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.architecture.projects.stages;

import com.architecture.projects.components.VectorRegisters;

/**
 *
 * @author jose
 */
public class FetchStage implements Runnable {
    
    private Thread t;
    private final String threadName;
    private String instructionFetched = "";

    public FetchStage() {

        threadName = "InstructionFetchStage";
    }
    @Override
    public void run() {
        VectorRegisters register = VectorRegisters.getInstance();
        //String address = register.readAddress("1111");
        //InstructionMemory instructionMemory = InstructionMemory.getInstance();

        //String instruction = instructionMemory.readInstruction(address);
        //instructionFetched = instruction;

        //int number0 = Integer.parseInt(address, 2);
        //int number1 = Integer.parseInt("1", 2);

        //int sum = number0 + number1;
        //register.writeAddress("1111", Integer.toBinaryString(sum));
    }
    
    /**
     * Punto de entrada del thread.
     */
    public void start() {

        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    /**
     * Se retorna el resultado de la instruccion (el fetch)
     *
     * @return
     * @throws InterruptedException
     */
    public String getInstructionFetched() throws InterruptedException {
        Thread.sleep(1);
        System.out.println("output fetch " + instructionFetched);

        return instructionFetched;
    }
    
}
