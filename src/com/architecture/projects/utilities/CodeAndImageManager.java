/**********************************************/
/*  Instituto Tecnológico de Costa Rica       */
/*  Ingeniería en Computadores                */
/*  Arquitectura de Computadores II           */
/*  II Semestre 2017                          */
/*                                            */
/*  Author: José Daniel Badilla Umaña         */
/*  Carné: 201271708                          */
/**********************************************/
package com.architecture.projects.utilities;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author jose
 */
public class CodeAndImageManager {

    private BufferedImage imageActual;
    private File file;

    public void getImage(BufferedImage image) {

        imageActual = image;

    }

    public String[] getPixelToMemory() {
        String[] returnValue;

        int width, height, memSize;
        width = imageActual.getWidth();
        height = imageActual.getHeight();
        memSize = width * height;

        System.out.println("Cantidad de pixeles: " + memSize);

        returnValue = new String[memSize];
        int value;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                value = (imageActual.getRGB(i, j) & 0xFF);
                returnValue[i * width + j] = Utility.zeroExtends(Utility.decimalToBinary(value), 8);
            }
        }
        return returnValue;
    }
    
    public String getAlgorithXOR(int key) {
        int size = imageActual.getWidth() * imageActual.getHeight();
        String code = "MOVS R2, "+key+"\n";
        
        if((size % 8) == 0){
            for(int i=0; i < size/8; i++){
                code += "MOVS R1, "+i+"\nLV R2, R1\nNOP\nXORVS R3, R2, R2\nSV R3, R1\n";
            }
        } else {
            for(int i=0; i < (size/8)+1; i++){
                code += "MOVS R1, "+i+"\nLV R2, R1\nNOP\nXORVS R3, R2, R2\nSV R3, R1\n";
            }
        }
        return code;
    }
    
    public String getAlgorithSHIFT(String dir, int shamt) {
        int size = imageActual.getWidth() * imageActual.getHeight();
        String code = "";
        
        if((size % 8) == 0){
            for(int i=0; i < size/8; i++){
                String shift;
                if(dir.compareTo("derecha") == 0) {
                    shift = "SHIFTCR";
                }else {
                    shift = "SHIFTCL";
                }
                code += "MOVS R1, "+i+"\nLV R2, R1\nNOP\n"+shift+" R3, "+shamt+"(R2)\nSV R3, R1\n";
            }
        } else {
            for(int i=0; i < (size/8)+1; i++){
                String shift;
                if(dir.compareTo("derecha") == 0) {
                    shift = "SHIFTCR";
                }else {
                    shift = "SHIFTCL";
                }
                code += "MOVS R1, "+i+"\nLV R2, R1\nNOP\n"+shift+" R3, "+shamt+"(R2)\nSV R3, R1\n";
            }
        }
        return code;
    }
    
    public String getAlgorithSHIFTC(String dir, int shamt) {
        int size = imageActual.getWidth() * imageActual.getHeight();
        String code = "";
        
        if((size % 8) == 0){
            for(int i=0; i < size/8; i++){
                String shift;
                if(dir.compareTo("derecha") == 0) {
                    shift = "SHIFTR";
                }else {
                    shift = "SHIFTL";
                }
                code += "MOVS R1, "+i+"\nLV R2, R1\nNOP\n"+shift+" R3, "+shamt+"(R2)\nSV R3, R1\n";
            }
        } else {
            for(int i=0; i < (size/8)+1; i++){
                String shift;
                if(dir.compareTo("derecha") == 0) {
                    shift = "SHIFTR";
                }else {
                    shift = "SHIFTL";
                }
                code += "MOVS R1, "+i+"\nLV R2, R1\nNOP\n"+shift+" R3, "+shamt+"(R2)\nSV R3, R1\n";
            }
        }
        return code;
    }
    
    public String getAlgorithADD(int[] vector) {
        int size = imageActual.getWidth() * imageActual.getHeight();
        System.out.println(size);
        String code = "MOVS R2, 0\nMOVS R3, "+vector[0]+"\nMOVS R4, "+vector[1]+
                      "\nSS R3, R2\nMOVS R2, 1\nSS R4, R2\nMOVS R2, 2"+
                      "\nMOVS R5, "+vector[2]+"\nMOVS R6, "+vector[3]+
                      "\nSS R5, R2\nMOVS R2, 3\nSS R5, R2\nMOVS R2, 4"+
                      "\nMOVS R3, "+vector[4]+"\nMOVS R4, "+vector[5]+
                      "\nSS R3, R2\nMOVS R2, 5\nSS R4, R2\nMOVS R2, 6"+
                      "\nMOVS R5, "+vector[6]+"\nMOVS R6, "+vector[7]+
                      "\nSS R5, R2\nMOVS R2, 7\nSS R5, R2\nMOVS R2, 0\nLV R0, R2\n";

        
        if((size % 8) == 0){
            for(int i=0; i < size/8; i++){
                code += "MOVS R1, "+(i+8)+"\nLV R2, R1\nNOP\nADDVV R4, R2, R0\nSV R4, R1\n";
            }
        } else {
            for(int i=0; i < (size/8)+1; i++){
                code += "MOVS R1, "+(i+8)+"\nLV R2, R1\nNOP\nADDVV R4, R2, R0\nSV R4, R1\n";
            }
        }
        return code;
    }
}
