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
package com.architecture.projects.components;

import com.architecture.projects.utilities.Utility;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class LDST implements Runnable {
    
    private Thread t;
    private final String threadName;
    private ArrayList<String> input;

    public LDST() {
        this.threadName = "LDST";
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

    public void addInput(ArrayList<String> input) {
        this.input = input;
    }

    @Override
    public void run() {
        DataMemory dataMemory = DataMemory.getInstance();
        //RegisterBank reg = RegisterBank.getInstance();

        String opcode = input.get(0);
        String type = input.get(1);
        String encode = input.get(2);
        String Ct = input.get(3);
        String Imm = input.get(5);
        //String data = reg.readAddress(Ct);
        String address = Utility.zeroExtends(Imm, 32);

        String readMemory = "00000000000000000000000000000000";

        if (opcode.equals("0000") || opcode.equals("0010") || opcode.equals("0100")) {
            //dataMemory.writeMemory(address, Utility.completeBinaryInstruction(data, 32));
        } else {
            readMemory = dataMemory.readMemory(address);
        }

        System.out.println("Write Back stage LD/ST");
        if (opcode.equals("0001") || opcode.equals("0011") || opcode.equals("0101")) {
            //reg.writeAddress(Ct, Utility.completeBinary(readMemory, 32));
        }
    }
    
}
