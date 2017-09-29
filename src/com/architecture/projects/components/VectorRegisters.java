/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.architecture.projects.components;

/**
 *
 * @author jose
 */
public class VectorRegisters {
    
    private String[] register_1;
    private String[] register_2;
    private String[] register_3;
    private String[] register_4;
    private String[] register_5;
    private String[] register_6;
    private String[] register_7;
    private String[] register_8;
    
    private static VectorRegisters instance = null;
    
    public VectorRegisters() {

        register_1 = new String[8];
        register_2 = new String[8];
        register_3 = new String[8];
        register_4 = new String[8];
        register_5 = new String[8];
        register_6 = new String[8];
        register_7 = new String[8];
        register_8 = new String[8];

        for (int i = 0; i < 8; i++) {
            register_1[i] = "00000000";
            register_2[i] = "00000000";
            register_3[i] = "00000000";
            register_4[i] = "00000000";
            register_5[i] = "00000000";
            register_6[i] = "00000000";
            register_7[i] = "00000000";
            register_8[i] = "00000000";
        }
    }
    
    public static VectorRegisters getInstance() {
        if (instance == null) {
            instance = new VectorRegisters();
        }
        return instance;
    }

    /**
     * Reads a specific register
     *
     * @param address expected in binary
     * @return register value, expected in binary
     */
    public String[] readAddress(String address) {

        String[] value = null;

        switch (address) {

            case "0000":
                value = register_1;
                break;
            case "0001":
                value = register_2;
                break;
            case "0010":
                value = register_3;
                break;
            case "0011":
                value = register_4;
                break;
            case "0100":
                value = register_5;
                break;
            case "0101":
                value = register_6;
                break;
            case "0110":
                value = register_7;
                break;
            case "0111":
                value = register_8;
                break;
        }
        return value;
    }

    /**
     * Writes to a specific register
     *
     * @param address expected in binary
     * @param value expected in binary
     */
    public void writeAddress(String address, String[] value) {

        switch (address) {

            case "0000":
                register_1 = value;
                break;
            case "0001":
                register_2 = value;
                break;
            case "0010":
                register_3 = value;
                break;
            case "0011":
                register_4 = value;
                break;
            case "0100":
                register_5 = value;
                break;
            case "0101":
                register_6 = value;
                break;
            case "0110":
                register_7 = value;
                break;
            case "0111":
                register_8 = value;
                break;
        }
    }    
}
