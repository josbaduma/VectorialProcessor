/**********************************************/
/*  Instituto Tecnológico de Costa Rica       */
 /*  Ingeniería en Computadores               */
 /*  Arquitectura de Computadores II          */
 /*  II Semestre 2017                         */
 /*                                           */
 /*  Author: José Daniel Badilla Umaña        */
 /*  Carné: 201271708                         */
/**********************************************/
package com.architecture.projects.stages;

import com.architecture.projects.components.InstructionMemory;
import com.architecture.projects.components.ScalarRegisters;
import com.architecture.projects.components.VectorRegisters;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class WriteBackStage extends Observable implements Runnable, Observer {

    private static WriteBackStage instance;
    private final MemoryStage memStage;
    private Thread t;
    private final String threadName;
    private boolean clock;
    
    private final InstructionMemory instructionMemory;
    private int numInstruction;
    private int countInstruction;

    private final ScalarRegisters scalarReg;
    private final VectorRegisters vectorReg;
    private String opType;
    private String opCode;
    private String[] resultVector;
    private String regDest;
    private String resultScalar;

    public WriteBackStage() {
        this.threadName = "WriteBackStage";
        this.scalarReg = ScalarRegisters.getInstance();
        this.vectorReg = VectorRegisters.getInstance();
        
        instructionMemory = InstructionMemory.getInstance();
        numInstruction = 10;

        this.memStage = MemoryStage.getInstance();
        this.memStage.addObserver(this);

        this.resultVector = new String[8];
    }

    public static WriteBackStage getInstance() {
        if (instance == null) {
            instance = new WriteBackStage();
        }
        return instance;
    }

    public void start(int numInst) {
        countInstruction = 0;
        numInstruction = numInst;
        clock = false;

        this.opType = "000";
        this.opCode = "000";
        this.regDest = "0000";
        this.resultScalar = "00000000000000000000000000000000";
        
        for (int i = 0; i < 8; i++) {
            this.resultVector[i] = "00000000";
        }

        t = new Thread(this, threadName);
        t.start();
    }

    @Override
    public void run() {
        while (countInstruction < numInstruction) {
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException ie) {
            }
            if (clock) {
                long startTime = System.nanoTime();

                if (this.opType.compareTo("011") == 0) {
                    if (this.opCode.compareTo("000") == 0) {
                        this.vectorReg.writeAddress(this.regDest, resultVector);
                    } else if (this.opCode.compareTo("010") == 0) {
                        this.scalarReg.writeAddress(regDest, resultScalar);
                    }
                } else {
                    this.vectorReg.writeAddress(this.regDest, resultVector);
                }
                
                System.out.println("Write Back output");
                clock = false;
                countInstruction++;
                
                try {
                    Thread.sleep(80);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WriteBackStage.class.getName()).log(Level.SEVERE, null, ex);
                }                
                
                setChanged();
                notifyObservers();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.clock = true;

        this.opType = memStage.getOpType();
        this.opCode = memStage.getOpCode();
        this.regDest = memStage.getDestiny();

        this.resultScalar = memStage.getResultScalar();
        this.resultVector = memStage.getResultVector();
        synchronized(this)
        {
            this.notify();
        }
    }

}
