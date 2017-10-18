/**********************************************/
/*  Instituto Tecnológico de Costa Rica       */
/*  Ingeniería en Computadores                */
/*  Arquitectura de Computadores II           */
/*  II Semestre 2017                          */
/*                                            */
/*  Author: José Daniel Badilla Umaña         */
/*  Carné: 201271708                          */
/**********************************************/
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
    private final InstructionMemory instructionMemory;
    private static FetchStage instance;
    private int numInstruction;
    private int countInstruction;

    public FetchStage() {
        threadName = "InstructionFetchStage";
        pc = "0000000000000000";

        instructionMemory = InstructionMemory.getInstance();
        numInstruction = 10;
    }

    /**
     * Se obtiene el PC actual
     *
     * @return
     */
    public String getPC() {
        return pc;
    }

    public static FetchStage getInstance() {
        if (instance == null) {
            instance = new FetchStage();
        }
        return instance;
    }

    @Override
    public void run() {
        while ((countInstruction < numInstruction)) {

            instructionFetched = instructionMemory.readInstruction(pc);

            int number0 = Utility.binaryToDecimal(pc);

            int sum = number0 + 1;
            pc = Utility.decimalToBinary(sum);

            countInstruction++;
            System.out.println("Fetch output: "+instructionFetched);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(FetchStage.class.getName()).log(Level.SEVERE, null, ex);
            }

            setChanged();
            notifyObservers();
        }
    }

    /**
     * Punto de entrada del thread.
     * @param numInst
     */
    public void start(int numInst) {

        countInstruction = 0;
        numInstruction = numInst;
        pc = "0000000000000000";
        instructionFetched = "00000000000000000000000000000000";
        t = new Thread(this, threadName);
        t.start();
    }
    

    /**
     * Se retorna el resultado de la instruccion
     *
     * @return
     */
    public String getInstructionFetched() {
        return instructionFetched;
    }
}
