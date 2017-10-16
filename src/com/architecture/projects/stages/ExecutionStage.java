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
    
    private String opType;
    private String opCode;
    private String type;
    private String destiny;
    
    private String[] vectorA, vectorB, resultVector;
    private String scalarA, scalarB, resultScalar;
    
    public ExecutionStage() {
        this.threadName = "ExecutionStage";
        decode = DecodeStage.getInstance();
        decode.addObserver(this);
        
        this.opType = "000";
        this.opCode = "000";
        this.type = "00";
        this.destiny = "0000";
        
        this.vectorA = new String[8];
        this.vectorB = new String[8];
        this.resultVector = new String[8];
        
        for(int i=0; i<8; i++){
            this.vectorA[i] = "00000000";
            this.vectorB[i] = "00000000";
            this.resultVector[i] = "00000000";
        }
        
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
        } else  {
            t.start();
        }
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000;
        System.out.println("Tiempo ejecución Execution: "+totalTime+" us");
        
        this.setChanged();
        this.notifyObservers();     
    }

    @Override
    public void update(Observable o, Object arg) {
        
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
