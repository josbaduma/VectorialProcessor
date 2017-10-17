/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.architecture.projects.utilities;

/**
 *
 * @author jose
 */
public class Utility {

    /**
     * Binary to Decimal converter
     *
     * @param number
     * @return
     */
    public static int binaryToDecimal(String number) {

        int n = Integer.parseInt(number, 2);
        return n;
    }

    public static String decimalToBinary(int number) {
        String n = Integer.toBinaryString(number);
        return n;
    }

    public static String zeroExtends(String number, int lenght) {
        String newNumber = number;
        for (int i = number.length(); i < lenght; i++) {
            newNumber = "0" + newNumber;
        }
        return newNumber;
    }

    public static String signExtends(String number, int lenght) {
        String newNumber = number;
        String sign = number.substring(0, 1);
        for (int i = number.length(); i < lenght; i++) {
            newNumber = newNumber + sign;
        }
        return newNumber;

    }

    public static String completeBinaryInstruction(String binary, int finalsize) {
        String value = binary;
        for (int i = 0; i < (finalsize - binary.length()); i++) {
            value = value + "0";
        }
        return value;
    }

}
