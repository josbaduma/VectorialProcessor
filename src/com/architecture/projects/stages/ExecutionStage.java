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

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jose
 */
public class ExecutionStage extends Observable implements Runnable, Observer {
    
    private static ExecutionStage instance;
    private Thread t;
    private final String threadName;
    private String opCode;
    private String encode;
    private String destiny;
    private String[] resultVector;
    private String resultScalar;
    
    public ExecutionStage() {
        this.threadName = "ExecutionStage";
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
        
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }

    public String getOpCode() {
        return opCode;
    }

    public String getEncode() {
        return encode;
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
