/********************************************/
/*Instituto Tecnológico de Costa Rica             */
/*Ingeniería en Computadores                         */
/*Arquitectura de Computadores II                  */
/*II Semestre 2017                                             */
/*                                                                           */
/*Author: José Daniel Badilla Umaña                 */
/*Carné: 201271708                                          */
/*********************************************/
package com.architecture.projects.stages;

import com.architecture.projects.utilities.Utility;
import java.util.ArrayList;
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
    
    private String opCode;
    private String type;
    private String encode;
    private String destiny;
    private String source1;

    private String source2;
    private String inmediate;
    
    public DecodeStage() {
        fetch = FetchStage.getInstance();
        fetch.addObserver(this);
        threadName = "DecodeStage";
        instruction = fetch.getInstructionFetched();
    }
    
    public static DecodeStage getInstance() {
        if (instance == null) {
            instance = new DecodeStage();            
        }
        return instance;
    }

    @Override
    public void run() {
        opCode = instruction.substring(0, 3);
        type = instruction.substring(3, 6);
        encode = instruction.substring(6, 8);
        
        destiny = instruction.substring(8, 12);
        String regS1 = instruction.substring(12, 16);
        String regS2 = instruction.substring(16, 20);
        
        inmediate = instruction.substring(16, 32);
        
        //if()
        
        this.setChanged();
        this.notifyObservers();    
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
    
        public String getOpCode() {
        return opCode;
    }

    public String getType() {
        return type;
    }

    public String getEncode() {
        return encode;
    }

    public String getDestiny() {
        return destiny;
    }

    public String getSource1() {
        return source1;
    }

    public String getSource2() {
        return source2;
    }

    public String getInmediate() {
        return Utility.zeroExtends(inmediate, 32);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.instruction = fetch.getInstructionFetched();
        
        this.start();
    }
}
