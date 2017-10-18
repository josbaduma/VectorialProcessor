/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.architecture.projects.components;

import com.architecture.projects.utilities.Utility;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author jose
 */
public class DataMemory extends Observable {
    
     private final ArrayList<String> dataMemory;
    private static DataMemory instance = null;

    
    public DataMemory() {

        dataMemory = new ArrayList();
                for (int i = 0; i < 16384; i++) {
            dataMemory.add("00000000");
        }
    }
    
    /**
     * Singleton
     * @return 
     */
   public static DataMemory getInstance() {
      if(instance == null) {
         instance = new DataMemory();
      }
      return instance;
   }
   
    /**
     * Reads from the data memory Return the content of the memory address
     *
     * @param address the address to read from.
     * @return
     */
    public String readMemory(String address) {

        int index = Utility.binaryToDecimal(address);

        System.out.println(dataMemory.get(index).getClass());
        return dataMemory.get(index);

    }

    /**
     * Writes to data memory in a specific address
     *
     * @param address the address to write, expected in binary
     * @param data the data to write, expected in binary
     */
    public void writeMemory(String address, String data) {
        int index = Utility.binaryToDecimal(address);
        dataMemory.set(index, data);
        setChanged();
        notifyObservers();
    }

    public ArrayList<String> getDataMemory() {
        return dataMemory;
    }
    
    public void setDataMemory(String[] array){
        for(int i=0; i<array.length; i++){
            dataMemory.set(i, array[i]);
        }
        setChanged();
        notifyObservers();
    }
    
}
