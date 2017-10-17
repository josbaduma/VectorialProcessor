/**
 * *******************************************
 */
/*  Instituto Tecnológico de Costa Rica       */
 /*  Ingeniería en Computadores                */
 /*  Arquitectura de Computadores II           */
 /*  II Semestre 2017                          */
 /*                                            */
 /*  Author: José Daniel Badilla Umaña         */
 /*  Carné: 201271708                          */
/**
 * *******************************************
 */
package com.architecture.projects.compiler;

import com.architecture.projects.utilities.Utility;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author jose
 */
public class Compiler {

    private final String instructions;
    private ArrayList<String> listInstruction;
    private final String[] listAritmetic = {"ADDVV", "ADDVS", "SUBVV", "SUBVS", "SUBSV", "MULVV", "MULVS", "MOVV", "MOVS"};
    private final String[] listLogic = {"ANDVV", "ANDVS", "ORVV", "ORVS", "XORVV", "XORVS"};
    private final String[] listMemory = {"LV", "LS", "SV", "SS"};
    private final String[] listDisplacement = {"SHIFTL", "SHIFTR", "SHIFTCL", "SHIFTCR"};

    public Compiler(String pInstruction) {
        this.instructions = pInstruction;
        this.listInstruction = null;
    }

    public ArrayList<String> compile() {
        this.listInstruction = this.getSplitedString(this.instructions, "\n");
        ArrayList<String> value = new ArrayList<>();
        String data = "";
        for (int i = 0; i < this.listInstruction.size(); i++) {
            String instruct = this.listInstruction.get(i);

            if (this.containsAritmetic(instruct)) {
                ArrayList<String> instructionString = this.getSplitedString(instruct, ", ");
                if (instruct.contains("MOVV") || instruct.contains("MOVS")) {
                    data = this.getOpCode(instructionString.get(0)) + this.getReg(instructionString.get(1)) + "0000" + this.immediateToBinary(instructionString.get(2));
                } else {
                    data = this.getOpCode(instructionString.get(0)) + this.getReg(instructionString.get(1)) + this.getReg(instructionString.get(2)) + this.getReg(instructionString.get(3));
                }
            } else if (this.containsLogic(instruct)) {
                ArrayList<String> instructionString = this.getSplitedString(instruct, ", ");
                data = this.getOpCode(instructionString.get(0)) + this.getReg(instructionString.get(1)) + this.getReg(instructionString.get(2)) + this.getReg(instructionString.get(3));
            } else if (this.containsMemory(instruct)) {
                ArrayList<String> instructionString = this.getSplitedString(instruct, ", ");
                data = this.getOpCode(instructionString.get(0)) + this.getReg(instructionString.get(1)) + this.getReg(instructionString.get(2));
            } else if (this.containsDisplacement(instruct)) {
                ArrayList<String> instructionString = this.getSplitedString(instruct, ",( )");
                String shamt = Utility.decimalToBinary(Integer.parseInt(instructionString.get(2)));
                data = this.getOpCode(instructionString.get(0)) + this.getReg(instructionString.get(1)) + this.getReg(instructionString.get(3)) + Utility.zeroExtends(shamt, 16);
            }

            value.add(Utility.completeBinaryInstruction(data, 32));
        }
        return value;
    }

    public String getOpCode(String pOperation) {
        String value = "";
        switch (pOperation) {
            case "ADDVV":
                value = Utility.decimalToBinary(32);
                break;
            case "ADDVS":
                value = Utility.decimalToBinary(33);
                break;
            case "SUBVV":
                value = Utility.decimalToBinary(36);
                break;
            case "SUBVS":
                value = Utility.decimalToBinary(37);
                break;
            case "MULVV":
                value = Utility.decimalToBinary(40);
                break;
            case "MULVS":
                value = Utility.decimalToBinary(41);
                break;
            case "MOVV":
                value = Utility.decimalToBinary(44);
                break;
            case "MOVS":
                value = Utility.decimalToBinary(48);
                break;
            case "ANDVV":
                value = Utility.decimalToBinary(64);
                break;
            case "ANDVS":
                value = Utility.decimalToBinary(65);
                break;
            case "ORVV":
                value = Utility.decimalToBinary(68);
                break;
            case "ORVS":
                value = Utility.decimalToBinary(69);
                break;
            case "XORVV":
                value = Utility.decimalToBinary(72);
                break;
            case "XORVS":
                value = Utility.decimalToBinary(73);
                break;
            case "LV":
                value = Utility.decimalToBinary(96);
                break;
            case "SV":
                value = Utility.decimalToBinary(100);
                break;
            case "LS":
                value = Utility.decimalToBinary(104);
                break;
            case "SS":
                value = Utility.decimalToBinary(108);
                break;
            case "SHIFTR":
                value = Utility.decimalToBinary(128);
                break;
            case "SHIFTL":
                value = Utility.decimalToBinary(132);
                break;
            case "SHIFTCL":
                value = Utility.decimalToBinary(136);
                break;
            case "SHIFTCR":
                value = Utility.decimalToBinary(140);
                break;
        }
        return Utility.zeroExtends(value, 8);
    }

    public String getReg(String pOperation) {
        String value = "";
        switch (pOperation) {
            case "R0":
                value = Utility.decimalToBinary(0);
                break;
            case "R1":
                value = Utility.decimalToBinary(1);
                break;
            case "R2":
                value = Utility.decimalToBinary(2);
                break;
            case "R3":
                value = Utility.decimalToBinary(3);
                break;
            case "R4":
                value = Utility.decimalToBinary(4);
                break;
            case "R5":
                value = Utility.decimalToBinary(5);
                break;
            case "R6":
                value = Utility.decimalToBinary(6);
                break;
            case "R7":
                value = Utility.decimalToBinary(7);
                break;
            case "R8":
                value = Utility.decimalToBinary(8);
                break;
            case "R9":
                value = Utility.decimalToBinary(9);
                break;
            case "R10":
                value = Utility.decimalToBinary(10);
                break;
            case "R11":
                value = Utility.decimalToBinary(11);
                break;
            case "R12":
                value = Utility.decimalToBinary(12);
                break;
            case "R13":
                value = Utility.decimalToBinary(13);
                break;
            case "R14":
                value = Utility.decimalToBinary(14);
                break;
            case "R15":
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

        return Utility.zeroExtends(value, 16);
    }

    public ArrayList<String> getSplitedString(String pData, String pDelimit) {
        ArrayList<String> instruction = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(pData, pDelimit);
        while (st.hasMoreElements()) {
            instruction.add(st.nextElement().toString());
        }
        return instruction;
    }

    public int getNumberOfChar(String pData, String pSearched) {
        String sTexto = pData;
        int contador = 0;
        while (sTexto.contains(pSearched)) {
            sTexto = sTexto.substring(sTexto.indexOf(
                    pSearched) + pSearched.length(), sTexto.length());
            contador++;
        }
        return contador;
    }

    public boolean containsAritmetic(String pData) {
        int i = 0;
        boolean flag = false;
        while (i < listAritmetic.length) {
            if (pData.contains(listAritmetic[i])) {
                flag = true;
                break;
            }
            i++;
        }
        return flag;
    }

    public boolean containsLogic(String pData) {
        int i = 0;
        boolean flag = false;
        String mnemonic = getSplitedString(pData, ", !").get(0);
        while (i < listLogic.length) {
            if (mnemonic.equals(listLogic[i])) {
                flag = true;
                break;
            }
            i++;
        }
        return flag;
    }

    public boolean containsMemory(String pData) {
        int i = 0;
        boolean flag = false;
        while (i < listMemory.length) {
            if (pData.contains(listMemory[i])) {
                flag = true;
                break;
            }
            i++;
        }
        return flag;
    }

    public boolean containsDisplacement(String pData) {
        int i = 0;
        boolean flag = false;
        while (i < listDisplacement.length) {
            if (pData.contains(listDisplacement[i])) {
                flag = true;
                break;
            }
            i++;
        }
        return flag;
    }
}
