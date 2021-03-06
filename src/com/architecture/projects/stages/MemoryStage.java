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

import com.architecture.projects.components.DataMemory;
import com.architecture.projects.components.InstructionMemory;
import com.architecture.projects.utilities.Utility;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class MemoryStage extends Observable implements Runnable, Observer {
    
    private static MemoryStage instance;
    private final ExecutionStage execution;
    private final DataMemory dataMem;
    private Thread t;
    private final String threadName;
    private boolean clock;
    
    private final InstructionMemory instructionMemory;
    private int numInstruction;
    private int countInstruction;
    
    private String opType;
    private String opCode;
    private String destiny;
    
    private String[] resultVector;
    private String resultScalar;
    
    public MemoryStage() {
        this.threadName = "MemoryStage";
        this.dataMem = DataMemory.getInstance();
        execution = ExecutionStage.getInstance();
        execution.addObserver(this);
        
        instructionMemory = InstructionMemory.getInstance();
        numInstruction = 10;
        this.resultVector = new String[8];

    }
    
    public static MemoryStage getInstance() {
        if (instance == null) {
            instance = new MemoryStage();
        }
        return instance;
    }
    
    public void start(int numInst) {
        clock = false;
        countInstruction = 0;
        numInstruction = numInst;

        this.opType = "000";
        this.opCode = "000";
        this.destiny = "0000";

        this.resultScalar = "00000000000000000000000000000000";

        for (int i = 0; i < 8; i++) {
            this.resultVector[i] = "00000000";
        }

        t = new Thread(this, threadName);
        t.start();

    }

    @Override
    public void run() {
        while(countInstruction < numInstruction) {
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException ie) {
            }
            if(clock) {

                if(this.opType.compareTo("011") == 0){
                    if(this.opCode.compareTo("001") == 0) {
                        int tempDest = Utility.binaryToDecimal(destiny);
                        this.dataMem.writeMemory(Utility.decimalToBinary(tempDest), resultVector[0]);
                        this.dataMem.writeMemory(Utility.decimalToBinary(tempDest+1), resultVector[1]);
                        this.dataMem.writeMemory(Utility.decimalToBinary(tempDest+2), resultVector[2]);
                        this.dataMem.writeMemory(Utility.decimalToBinary(tempDest+3), resultVector[3]);
                        this.dataMem.writeMemory(Utility.decimalToBinary(tempDest+4), resultVector[4]);
                        this.dataMem.writeMemory(Utility.decimalToBinary(tempDest+5), resultVector[5]);
                        this.dataMem.writeMemory(Utility.decimalToBinary(tempDest+6), resultVector[6]);
                        this.dataMem.writeMemory(Utility.decimalToBinary(tempDest+7), resultVector[7]);
                    } else if(this.opCode.compareTo("011") == 0){
                        this.dataMem.writeMemory(destiny, resultScalar);
                    } else if(this.opCode.compareTo("000") == 0){
                        int tempDest = Utility.binaryToDecimal(destiny);
                        resultVector[0] = this.dataMem.readMemory(Utility.decimalToBinary(tempDest));
                        resultVector[1] = this.dataMem.readMemory(Utility.decimalToBinary(tempDest+1));
                        resultVector[2] = this.dataMem.readMemory(Utility.decimalToBinary(tempDest+2));
                        resultVector[3] = this.dataMem.readMemory(Utility.decimalToBinary(tempDest+3));
                        resultVector[4] = this.dataMem.readMemory(Utility.decimalToBinary(tempDest+4));
                        resultVector[5] = this.dataMem.readMemory(Utility.decimalToBinary(tempDest+5));
                        resultVector[6] = this.dataMem.readMemory(Utility.decimalToBinary(tempDest+6));
                        resultVector[7] = this.dataMem.readMemory(Utility.decimalToBinary(tempDest+7));
                    } else if(this.opCode.compareTo("010") == 0){
                        resultScalar = this.dataMem.readMemory(destiny);
                    }
                }     
                
                System.out.println("Memory Output: Result Scalar "+resultScalar+"\n"+
                            "resultVector" + resultVector[0] + " " + resultVector[1] + " " + resultVector[2] + " " + resultVector[3] + " "
                                           + resultVector[4] + " " + resultVector[5] + " " + resultVector[6] + " " + resultVector[7]);
                countInstruction++;
                clock = false;
                
                try {
                    Thread.sleep(85);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MemoryStage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                setChanged();
                notifyObservers();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.clock = true;
        
        this.opType = execution.getOpType();
        this.opCode = execution.getOpCode();
        this.destiny = execution.getDestiny();
        
        this.resultScalar = execution.getResultScalar();
        this.resultVector = execution.getResultVector();
        
        synchronized(this)
        {
            this.notify();
        }
    }

    public String getOpType() {
        return opType;
    }

    public String getDestiny() {
        return destiny;
    }

    public String getOpCode() {
        return opCode;
    }

    public String[] getResultVector() {
        return resultVector;
    }

    public String getResultScalar() {
        return resultScalar;
    }
    
    
}
