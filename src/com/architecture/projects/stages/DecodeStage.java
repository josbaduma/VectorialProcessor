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

import com.architecture.projects.components.Clock;
import com.architecture.projects.components.ScalarRegisters;
import com.architecture.projects.components.VectorRegisters;
import com.architecture.projects.utilities.Utility;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jose
 */
public class DecodeStage extends Observable implements Runnable, Observer {
    
    private Thread t;
    private final String threadName;
    private final FetchStage fetch;
    private static DecodeStage instance;
    private String instruction; 
    private final VectorRegisters vectorRegs;
    private final ScalarRegisters scalarRegs;
    
    private String opType;
    private String type;
    private String opCode;
    
    private String destiny;
    private String[] vectorSource1;
    private String[] vectorSource2;
    private String scalarSource1;
    private String scalarSource2;
    private String inmediate;
    
    private final Clock clockInstance;
    private boolean clock;
    
    public DecodeStage() {
        fetch = FetchStage.getInstance();
        threadName = "DecodeStage";
        
        clockInstance = Clock.getInstance();
        clockInstance.addObserver(this);
        clock = false;
        
        vectorRegs = VectorRegisters.getInstance();
        scalarRegs = ScalarRegisters.getInstance();
        
        opType = "000";
        opCode = "000";
        type = "00";
        
        destiny = "0000";
        vectorSource1 = new String[8];
        vectorSource2 = new String[8];
        
        for(int i=0; i<8; i++) {
            vectorSource1[i] = "00000000";
            vectorSource2[i] = "00000000";
        }
        
        scalarSource1 = "00000000000000000000000000000000";
        scalarSource2 = "00000000000000000000000000000000";
        
        inmediate = "0000000000000000";
    }
    
    public static DecodeStage getInstance() {
        if (instance == null) {
            instance = new DecodeStage();            
        }
        return instance;
    }

    @Override
    public void run() {
        while(true){
            if(clock){
                long startTime = System.nanoTime();
                opType = instruction.substring(0, 3);
                type = instruction.substring(3, 6);
                opCode = instruction.substring(6, 8);

                destiny = instruction.substring(8, 12);
                String regS1 = instruction.substring(12, 16);
                String regS2 = instruction.substring(16, 20);

                inmediate = instruction.substring(16, 32);

                vectorSource1 = vectorRegs.readAddress(regS1);
                vectorSource2 = vectorRegs.readAddress(regS2);

                scalarSource1 = scalarRegs.readAddress(regS1);
                scalarSource2 = scalarRegs.readAddress(regS2);

                long endTime   = System.nanoTime();
                long totalTime = (endTime - startTime)/1000;
                System.out.println("Tiempo ejecución Decode: "+totalTime+" us");
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
    
        public String getOpType() {
        return opType;
    }

    public String getType() {
        return type;
    }

    public String getOpCode() {
        return opCode;
    }

    public String getDestiny() {
        return destiny;
    }

    public String[] getVectorSource1() {
        return vectorSource1;
    }

    public String[] getVectorSource2() {
        return vectorSource2;
    }

    public String getScalarSource1() {
        return scalarSource1;
    }

    public String getScalarSource2() {
        return scalarSource2;
    }

    public String getInmediate() {
        return Utility.zeroExtends(inmediate, 32);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.clock = clockInstance.isClock();
        this.instruction = fetch.getInstructionFetched();
    }
}
