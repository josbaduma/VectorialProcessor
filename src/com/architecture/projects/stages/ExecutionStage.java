/**********************************************/
/*  Instituto Tecnológico de Costa Rica       */
/*   Ingeniería en Computadores               */
/*   Arquitectura de Computadores II          */
/*   II Semestre 2017                         */
/*                                            */
/*   Author: José Daniel Badilla Umaña        */
/*   Carné: 201271708                         */
/**********************************************/
package com.architecture.projects.stages;

import com.architecture.projects.components.ALU;
import com.architecture.projects.components.InstructionMemory;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class ExecutionStage extends Observable implements Runnable, Observer {

    private static ExecutionStage instance;
    private final DecodeStage decode;
    private Thread t;
    private final String threadName;
    private final ALU alu1;
    private final ALU alu2;
    private final ALU alu3;
    private final ALU alu4;
    private final ALU alu5;
    private final ALU alu6;
    private final ALU alu7;
    private final ALU alu8;
    
    private final InstructionMemory instructionMemory;
    private int numInstruction;
    private int countInstruction;

    private String opType;
    private String opCode;
    private String type;
    private String destiny;

    private String[] vectorA, vectorB;
    private final String[] resultVector;
    private String scalarA, scalarB;
    private String resultScalar;
    private String immediate;
    private boolean clock;

    public ExecutionStage() {
        this.threadName = "ExecutionStage";
        decode = DecodeStage.getInstance();
        decode.addObserver(this);
        
        instructionMemory = InstructionMemory.getInstance();
        numInstruction = 10;

        this.alu1 = new ALU();
        this.alu2 = new ALU();
        this.alu3 = new ALU();
        this.alu4 = new ALU();
        this.alu5 = new ALU();
        this.alu6 = new ALU();
        this.alu7 = new ALU();
        this.alu8 = new ALU();

        this.vectorA = new String[8];
        this.vectorB = new String[8];
        this.resultVector = new String[8];
    }

    public static ExecutionStage getInstance() {
        if (instance == null) {
            instance = new ExecutionStage();
        }
        return instance;
    }

    public void start(int numInst) {
        clock = false;
        countInstruction = 0;
        numInstruction = numInst;

        this.opType = "000";
        this.opCode = "000";
        this.type = "00";
        this.destiny = "0000";

        for (int i = 0; i < 8; i++) {
            this.vectorA[i] = "00000000";
            this.vectorB[i] = "00000000";
            this.resultVector[i] = "00000000";
        }

        this.immediate = "000000000000000000000000000000000000";
        this.scalarA = "000000000000000000000000000000000000";
        this.scalarB = "000000000000000000000000000000000000";
        this.resultScalar = "000000000000000000000000000000000000";
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
                switch (type) {
                    case "00":
                        resultVector[0] = alu1.operation(opType, opCode, vectorA[0], vectorB[0], immediate);
                        resultVector[1] = alu2.operation(opType, opCode, vectorA[1], vectorB[1], immediate);
                        resultVector[2] = alu3.operation(opType, opCode, vectorA[2], vectorB[2], immediate);
                        resultVector[3] = alu4.operation(opType, opCode, vectorA[3], vectorB[3], immediate);
                        resultVector[4] = alu5.operation(opType, opCode, vectorA[4], vectorB[4], immediate);
                        resultVector[5] = alu6.operation(opType, opCode, vectorA[5], vectorB[5], immediate);
                        resultVector[6] = alu7.operation(opType, opCode, vectorA[6], vectorB[6], immediate);
                        resultVector[7] = alu8.operation(opType, opCode, vectorA[7], vectorB[7], immediate);
                        break;
                    case "01":
                        resultVector[0] = alu1.operation(opType, opCode, vectorA[0], scalarB, immediate);
                        resultVector[1] = alu2.operation(opType, opCode, vectorA[1], scalarB, immediate);
                        resultVector[2] = alu3.operation(opType, opCode, vectorA[2], scalarB, immediate);
                        resultVector[3] = alu4.operation(opType, opCode, vectorA[3], scalarB, immediate);
                        resultVector[4] = alu5.operation(opType, opCode, vectorA[4], scalarB, immediate);
                        resultVector[5] = alu6.operation(opType, opCode, vectorA[5], scalarB, immediate);
                        resultVector[6] = alu7.operation(opType, opCode, vectorA[6], scalarB, immediate);
                        resultVector[7] = alu8.operation(opType, opCode, vectorA[7], scalarB, immediate);
                        break;
                }
                resultScalar = resultVector[0];
                countInstruction++;
                clock = false;
                
                System.out.println("Execution output: " + resultVector[0] + " " + resultVector[1] + " " + resultVector[2] + " " + resultVector[3] + " "
                                + resultVector[4] + " " + resultVector[5] + " " + resultVector[6] + " " + resultVector[7]);
                try {
                    Thread.sleep(90);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExecutionStage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                setChanged();
                notifyObservers();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        clock = true;
        
        this.opType = decode.getOpType();
        this.opCode = decode.getOpCode();
        this.type = decode.getType();

        this.vectorA = decode.getVectorSource1();
        this.vectorB = decode.getVectorSource2();

        this.scalarA = decode.getScalarSource1();
        this.scalarB = decode.getScalarSource2();

        this.immediate = decode.getInmediate();
        this.destiny = decode.getDestiny();
        
        synchronized(this)
        {
            this.notify();
        }
    }

    public String getOpType() {
        return opType;
    }

    public String getOpCode() {
        return opCode;
    }

    public String getDestiny() {
        return destiny;
    }

    public String[] getResultVector() {
        return resultVector;
    }

    public String getResultScalar() {
        return resultScalar;
    }
}
