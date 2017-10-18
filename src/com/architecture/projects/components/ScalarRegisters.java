/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.architecture.projects.components;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author jose
 */
public class ScalarRegisters extends Observable {
    
    private final ArrayList<String> registers;
    private static ScalarRegisters instance = null;

    /**
     * constructor, setea una lista de 16 elementos
     */
    public ScalarRegisters() {
        
        registers = new ArrayList();

        for (int i = 0; i < 8; i++) {
            registers.add("00000000");
        }

        setChanged();
        notifyObservers();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static ScalarRegisters getInstance() {
        if (instance == null) {
            instance = new ScalarRegisters();
        }
        return instance;
    }



    /**
     * Reads a specific register
     *
     * @param address expected in binary
     * @return register value, expected in binary
     */
    public String readAddress(String address) {

        String value = "";

        switch (address) {

            case "0000":
                value = registers.get(0);
                break;
            case "0001":
                value = registers.get(1);
                break;
            case "0010":
                value = registers.get(2);
                break;
            case "0011":
                value = registers.get(3);
                break;
            case "0100":
                value = registers.get(4);
                break;
            case "0101":
                value = registers.get(5);
                break;
            case "0110":
                value = registers.get(6);
                break;
            case "0111":
                value = registers.get(7);
                break;
        }
        
        setChanged();
        notifyObservers();
        
        return value;
    }

    /**
     * Writes to a specific register
     *
     * @param address expected in binary
     * @param value expected in binary
     */
    public void writeAddress(String address, String value) {

        switch (address) {

            case "0000":
                registers.set(0, value);
                break;
            case "0001":
                registers.set(1, value);
                break;
            case "0010":
                registers.set(2, value);
                break;
            case "0011":
                registers.set(3, value);
                break;
            case "0100":
                registers.set(4, value);
                break;
            case "0101":
                registers.set(5, value);
                break;
            case "0110":
                registers.set(6, value);
                break;
            case "0111":
                registers.set(7, value);
                break;
        }

        setChanged();
        notifyObservers();
    }

    /**
     * obtiene todos los registros para depuracion
     *
     * @return
     */
    public ArrayList<String> getRegisters() {
        return registers;
    }
}
