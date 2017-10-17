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

import com.architecture.projects.components.Clock;
import com.architecture.projects.components.InstructionMemory;
import com.architecture.projects.utilities.Utility;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jose
 */
public class FetchStage extends Observable implements Observer, Runnable {

    private Thread t;
    private final String threadName;
    private String instructionFetched = "";
    private String pc;
    private final InstructionMemory instructionMemory;
    private static FetchStage instance;
    private final Clock clockInstance;
    private boolean clock;
    private final int numInstruction;
    private int countInstruction;

    public FetchStage() {

        threadName = "InstructionFetchStage";
        pc = "0000000000000000";
        instructionFetched = "00000000000000000000000000000000";
        clockInstance = Clock.getInstance();
        clockInstance.addObserver(this);
        clock = false;
        instructionMemory = InstructionMemory.getInstance();
        numInstruction = instructionMemory.getInstructions().size();
        countInstruction = 0;
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
        while (countInstruction < numInstruction) {
            if(clock){
                long startTime = System.nanoTime();

                instructionFetched = instructionMemory.readInstruction(pc);

                int number0 = Utility.binaryToDecimal(pc);

                int sum = number0 + 1;
                pc = Utility.decimalToBinary(sum);
                
                countInstruction++;

                long endTime = System.nanoTime();
                long totalTime = (endTime - startTime) / 1000;
                System.out.println("Tiempo ejecución Fetch: " + totalTime + " us");
            }
        }
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
     * Se retorna el resultado de la instruccion
     *
     * @return
     */
    public String getInstructionFetched() {        
        return instructionFetched;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.clock = clockInstance.isClock();
    }

}
