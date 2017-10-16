/*********************************************/
/*  Instituto Tecnológico de Costa Rica      */
/*  Ingeniería en Computadores               */
/*  Arquitectura de Computadores II          */
/*  II Semestre 2017                         */
/*                                           */
/*  Author: José Daniel Badilla Umaña        */
/*  Carné: 201271708                         */
/*********************************************/
package com.architecture.projects.components;

import com.architecture.projects.utilities.Utility;

/**
 *
 * @author jose
 */
public class ALU {

    private String result;

    /**
     * Constructor de la clase
     */
    public ALU() {
        this.result = "00000000";//Inicializar el vector resultante      
    }

    /**
     * Realiza las operaciones en la ALU segun el Optype y Opcode
     * de los operandos asignados.
     * @param opType tipo de operacion
     * @param opCode codigo de la operacion
     * @param valueA primer operando
     * @param valueB segundo operando
     * @param immediate valor inmediato
     * @return String result valor binario resultante de la operacion
     */
    public String operation(String opType, String opCode, String valueA, String valueB, String immediate) {
        if (opType.compareTo("001") == 0) {
            switch (opCode) {
                case "000":
                    this.result = Utility.decimalToBinary(Utility.binaryToDecimal(valueA) + Utility.binaryToDecimal(valueB));
                    break;
                case "001":
                    this.result = Utility.decimalToBinary(Utility.binaryToDecimal(valueA) - Utility.binaryToDecimal(valueB));
                    break;
                case "010":
                    this.result = Utility.decimalToBinary(Utility.binaryToDecimal(valueA) * Utility.binaryToDecimal(valueB));
                    break;
                case "011":
                    this.result = valueA;
                    break;
                case "100":
                    this.result = valueA;
                    break;
            }
        } else if (opType.compareTo("010") == 0) {
            switch (opCode) {
                case "000":
                    this.result = Utility.decimalToBinary(Utility.binaryToDecimal(valueA) & Utility.binaryToDecimal(valueB));
                    break;
                case "001":
                    this.result = Utility.decimalToBinary(Utility.binaryToDecimal(valueA) | Utility.binaryToDecimal(valueB));
                    break;
                case "010":
                    this.result = Utility.decimalToBinary(Utility.binaryToDecimal(valueA) ^ Utility.binaryToDecimal(valueB));
                    break;
            }
        } else if (opType.compareTo("100") == 0) {
            switch (opCode) {
                case "000":
                    this.result = Utility.decimalToBinary(Utility.binaryToDecimal(valueA) << Utility.binaryToDecimal(immediate));
                    break;
                case "001":
                    this.result = Utility.decimalToBinary(Utility.binaryToDecimal(valueA) | Utility.binaryToDecimal(valueB));
                    break;
                case "010":
                    this.result = valueA.charAt(valueA.length() - 1) + valueA.substring(0, valueA.length() - 1);
                    break;
            }
        }
        return result;
    }
}
