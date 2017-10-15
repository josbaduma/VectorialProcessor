/**
 * *****************************************
 */
/*Instituto Tecnológico de Costa Rica 	*/
 /*Ingeniería en Computadores           	*/
 /*Arquitectura de Computadores II    	*/
 /*II Semestre 2017                     	*/
 /*			*/
 /*Author: José Daniel Badilla Umaña    	*/
 /*Carné: 201271708                     	*/
/**
 * ******************************************
 */
package com.architecture.projects.stages;

import com.architecture.projects.components.DataMemory;
import com.architecture.projects.utilities.Utility;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jose
 */
public class MemoryStage extends Observable implements Runnable, Observer {
    
    private static MemoryStage instance;
    private Thread t;
    private final String threadName;
    private String opType;
    private String opCode;
    private String destiny;
    private String[] resultVector;
    private String resultScalar;
    private final DataMemory dataMem;
    
    public MemoryStage() {
        this.threadName = "MemoryStage";
        this.dataMem = DataMemory.getInstance();
        
    }
    
    public static MemoryStage getInstance() {
        if (instance == null) {
            instance = new MemoryStage();
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
        if(this.opType.compareTo("010") == 0){
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
                
            } else if(this.opCode.compareTo("010") == 0){
                
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        
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
