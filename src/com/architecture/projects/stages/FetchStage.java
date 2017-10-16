/*********************************************/
/*  Instituto Tecnológico de Costa Rica      */
/*  Ingeniería en Computadores               */
/*  Arquitectura de Computadores II          */
/*  II Semestre 2017                         */
/*                                           */
/*  Author: José Daniel Badilla Umaña        */
/*  Carné: 201271708                         */
/*********************************************/
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
    private InstructionMemory instructionMemory;

    public String getPC() {
        return pc;
    }
    private static FetchStage instance;

    public FetchStage() {

        threadName = "InstructionFetchStage";
        pc = "0000000000000000";
        instructionFetched = "00000000000000000000000000000000";
        
         instructionMemory = InstructionMemory.getInstance();
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

        instructionFetched = instructionMemory.readInstruction(pc);

        int number0 = Utility.binaryToDecimal(pc);

        int sum = number0 + 1;
        pc = Utility.decimalToBinary(sum);
        
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000;
        System.out.println("Tiempo ejecución Fetch: "+totalTime+" us");
        
        this.setChanged();
        this.notifyObservers();
               
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
