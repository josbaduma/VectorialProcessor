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
package com.architecture.projects.compiler;

import com.architecture.projects.utilities.SplitData;
import com.architecture.projects.utilities.Utility;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class Compiler {
    
    private final String instructions;
    private final ArrayList<String> listInstruction;

    public Compiler(String pInstruction) {
        this.instructions = pInstruction;
        this.listInstruction = SplitData.getSplitedString(this.instructions, "\n");
    }

    public ArrayList<String> compile() {
        ArrayList<String> value = new ArrayList<>();
        String data = "";
        for (int i = 0; i < this.listInstruction.size(); i++) {
            String instruct = this.listInstruction.get(i);

            
            value.add(this.completeBinaryInstruction(data, 32));
        }
        return value;
    }

    public String getOpCode(String pOperation) {
        String value = "";
        switch (pOperation) {
            case "ADDVV":
                value = Utility.decimalToBinary(0);
                break;
            case "ADDVS":
                value = Utility.decimalToBinary(1);
                break;
            case "SUBVV":
                value = Utility.decimalToBinary(4);
                break;
            case "SUBVS":
                value = Utility.decimalToBinary(5);
                break;
            case "SUBSV":
                value = Utility.decimalToBinary(6);
                break;
            case "MULVV":
                value = Utility.decimalToBinary(8);
                break;
            case "MULVS":
                value = Utility.decimalToBinary(10);
                break;
            case "ANDVV":
                value = Utility.decimalToBinary(32);
                break;
            case "ANDVS":
                value = Utility.decimalToBinary(33);
                break;
            case "ORVV":
                value = Utility.decimalToBinary(36);
                break;
            case "ORVS":
                value = Utility.decimalToBinary(37);
                break;
            case "XORVV":
                value = Utility.decimalToBinary(40);
                break;
            case "XORVS":
                value = Utility.decimalToBinary(41);
                break;
            case "SV":
                value = Utility.decimalToBinary(68);
                break;
            case "LV":
                value = Utility.decimalToBinary(64);
                break;
            case "SS":
                value = Utility.decimalToBinary(76);
                break;
            case "LS":
                value = Utility.decimalToBinary(72);
                break;
            case "SHIFTR":
                value = Utility.decimalToBinary(96);
                break;
            case "SHIFTL":
                value = Utility.decimalToBinary(100);
                break;
            case "SHIFTC":
                value = Utility.decimalToBinary(104);
                break;
            case "MOV":
                value = Utility.decimalToBinary(12);
                break;
        }
        return Utility.zeroExtends(value, 8);
    }

    public String getReg(String pOperation) {
        String value = "";
        switch (pOperation) {
            case "C0":
                value = Utility.decimalToBinary(0);
                break;
            case "C1":
                value = Utility.decimalToBinary(1);
                break;
            case "C2":
                value = Utility.decimalToBinary(2);
                break;
            case "C3":
                value = Utility.decimalToBinary(3);
                break;
            case "C4":
                value = Utility.decimalToBinary(4);
                break;
            case "C5":
                value = Utility.decimalToBinary(5);
                break;
            case "C6":
                value = Utility.decimalToBinary(6);
                break;
            case "C7":
                value = Utility.decimalToBinary(7);
                break;
            case "C8":
                value = Utility.decimalToBinary(8);
                break;
            case "C9":
                value = Utility.decimalToBinary(9);
                break;
            case "C10":
                value = Utility.decimalToBinary(10);
                break;
            case "C11":
                value = Utility.decimalToBinary(11);
                break;
            case "C12":
                value = Utility.decimalToBinary(12);
                break;
            case "C13":
                value = Utility.decimalToBinary(13);
                break;
            case "C14":
                value = Utility.decimalToBinary(14);
                break;
            case "C15":
                value = Utility.decimalToBinary(15);
                break;
        }
        return Utility.zeroExtends(value, 4);
    }

    public String getAddressingMemory(int pType) {
        String value = "";

        switch (pType) {
            case 0:
                value = Utility.decimalToBinary(0);
                break;
            case 1:
                value = Utility.decimalToBinary(1);
                break;
            case 2:
                value = Utility.decimalToBinary(2);
                break;
            case 3:
                value = Utility.decimalToBinary(3);
                break;
        }
        return value;
    }

    public String immediateToBinary(String imm) {
        String value;
        int tempValue;

        tempValue = Integer.parseInt(imm);
        value = Integer.toBinaryString(tempValue);

        return Utility.zeroExtends(value, 15);
    }
    
    public String completeBinaryInstruction(String binary, int finalsize) {
        String value = binary;
        for (int i = 0; i < (finalsize - binary.length()); i++) {
            value = value + "0";
        }
        return value;
    }
    
}
