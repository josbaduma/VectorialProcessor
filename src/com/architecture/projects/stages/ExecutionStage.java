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
import com.architecture.projects.components.Clock;
import java.util.Observable;
import java.util.Observer;

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

    private String opType;
    private String opCode;
    private String type;
    private String destiny;

    private String[] vectorA, vectorB;
    private final String[] resultVector;
    private String scalarA, scalarB;
    private final String resultScalar;
    private String immediate;
    private final Clock clockInstance;
    private boolean clock;

    public ExecutionStage() {
        this.threadName = "ExecutionStage";
        clockInstance = Clock.getInstance();
        clockInstance.addObserver(this);
        clock = false;
        decode = DecodeStage.getInstance();

        this.alu1 = new ALU();
        this.alu2 = new ALU();
        this.alu3 = new ALU();
        this.alu4 = new ALU();
        this.alu5 = new ALU();
        this.alu6 = new ALU();
        this.alu7 = new ALU();
        this.alu8 = new ALU();

        this.opType = "000";
        this.opCode = "000";
        this.type = "00";
        this.destiny = "0000";

        this.vectorA = new String[8];
        this.vectorB = new String[8];
        this.resultVector = new String[8];

        for (int i = 0; i < 8; i++) {
            this.vectorA[i] = "00000000";
            this.vectorB[i] = "00000000";
            this.resultVector[i] = "00000000";
        }

        this.immediate = "000000000000000000000000000000000000";
        this.scalarA = "000000000000000000000000000000000000";
        this.scalarB = "000000000000000000000000000000000000";
        this.resultScalar = "000000000000000000000000000000000000";

    }

    public static ExecutionStage getInstance() {
        if (instance == null) {
            instance = new ExecutionStage();
        }
        return instance;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (clock) {
                long startTime = System.nanoTime();
                switch (type) {
                    case "00":
                        alu1.operation(opType, opCode, vectorA[0], vectorB[0], immediate);
                        alu2.operation(opType, opCode, vectorA[1], vectorB[1], immediate);
                        alu3.operation(opType, opCode, vectorA[2], vectorB[2], immediate);
                        alu4.operation(opType, opCode, vectorA[3], vectorB[3], immediate);
                        alu5.operation(opType, opCode, vectorA[4], vectorB[4], immediate);
                        alu6.operation(opType, opCode, vectorA[5], vectorB[5], immediate);
                        alu7.operation(opType, opCode, vectorA[6], vectorB[6], immediate);
                        alu8.operation(opType, opCode, vectorA[7], vectorB[7], immediate);
                        break;
                    case "01":
                        alu1.operation(opType, opCode, vectorA[0], scalarB, immediate);
                        alu2.operation(opType, opCode, vectorA[1], scalarB, immediate);
                        alu3.operation(opType, opCode, vectorA[2], scalarB, immediate);
                        alu4.operation(opType, opCode, vectorA[3], scalarB, immediate);
                        alu5.operation(opType, opCode, vectorA[4], scalarB, immediate);
                        alu6.operation(opType, opCode, vectorA[5], scalarB, immediate);
                        alu7.operation(opType, opCode, vectorA[6], scalarB, immediate);
                        alu8.operation(opType, opCode, vectorA[7], scalarB, immediate);
                        break;
                    case "10":
                        alu1.operation(opType, opCode, scalarA, vectorB[0], immediate);
                        alu2.operation(opType, opCode, scalarA, vectorB[1], immediate);
                        alu3.operation(opType, opCode, scalarA, vectorB[2], immediate);
                        alu4.operation(opType, opCode, scalarA, vectorB[3], immediate);
                        alu5.operation(opType, opCode, scalarA, vectorB[4], immediate);
                        alu6.operation(opType, opCode, scalarA, vectorB[5], immediate);
                        alu7.operation(opType, opCode, scalarA, vectorB[6], immediate);
                        alu8.operation(opType, opCode, scalarA, vectorB[7], immediate);
                        break;
                }
                long endTime = System.nanoTime();
                long totalTime = (endTime - startTime) / 1000;
                System.out.println("Tiempo ejecución Execution: " + totalTime + " us");
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        clock = clockInstance.isClock();
        
        this.opType = decode.getOpType();
        this.opCode = decode.getOpCode();
        this.type = decode.getType();

        this.vectorA = decode.getVectorSource1();
        this.vectorB = decode.getVectorSource2();

        this.scalarA = decode.getScalarSource1();
        this.scalarB = decode.getScalarSource2();

        this.immediate = decode.getInmediate();
        this.destiny = decode.getDestiny();

        this.start();
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
