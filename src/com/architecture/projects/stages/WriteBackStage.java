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

import com.architecture.projects.components.ScalarRegisters;
import com.architecture.projects.components.VectorRegisters;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jose
 */
public class WriteBackStage extends Observable implements Runnable, Observer {
    
    private static WriteBackStage instance;
    private final MemoryStage memStage;
    private Thread t;
    private final String threadName;
    private final ScalarRegisters scalarReg;
    private final VectorRegisters vectorReg;
    private String opCode;
    private String encode;
    private String[] resultVector;
    private String regDest;
    private String resultScalar;
    
    public WriteBackStage() {
        this.threadName = "WriteBackStage";
        this.scalarReg = ScalarRegisters.getInstance();
        this.vectorReg = VectorRegisters.getInstance();
        this.memStage = MemoryStage.getInstance();
        this.memStage.addObserver(this);
    }
    
    public static WriteBackStage getInstance() {
        if (instance == null) {
            instance = new WriteBackStage();
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
        if(this.opCode.compareTo("010") == 0) {
            if(this.encode.compareTo("000") == 0){
                this.vectorReg.writeAddress(this.regDest, resultVector);
            } else if(this.encode.compareTo("010") == 0){
                this.scalarReg.writeAddress(regDest, resultScalar);
            } else {
                System.out.println("Store Registers");
            }
        } else {
            this.vectorReg.writeAddress(this.regDest, resultVector);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.opCode = memStage.getOpType();
        this.encode = memStage.getOpCode();
        this.regDest = memStage.getDestiny();
        
        this.resultScalar = memStage.getResultScalar();
        this.resultVector = memStage.getResultVector();
        
        this.start();
    }
    
}
