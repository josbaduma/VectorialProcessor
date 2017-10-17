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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jose
 */
public class CodeAndImageManager {

    private BufferedImage imageActual;
    private File file;

    public void getImage(String Path) {
        file = new File(Path);
        try {
            imageActual = ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(CodeAndImageManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (imageActual != null) {
            System.out.println("Se cargo correctamente.");
        }
    }

    public String[] getPixelToMemory() {
        String[] returnValue = null;

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
        String code = "MOVS R2, "+key;
        
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
    
    public String getAlgorithSHIFT(String dir) {
        int size = imageActual.getWidth() * imageActual.getHeight();
        String code = "";
        
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
    
    public String getAlgorithADD() {
        int size = imageActual.getWidth() * imageActual.getHeight();
        String code = "MOVS R2, 1000";
        
        if((size % 8) == 0){
            for(int i=0; i < size/8; i++){
                code += "MOVS R1, "+i+"\nLV R2, R1\nNOP\nADDVV R4, R2, R3\nSV R4, R1\n";
            }
        } else {
            for(int i=0; i < (size/8)+1; i++){
                code += "MOVS R1, "+i+"\nLV R2, R1\nNOP\nADDVV R4, R2, R3\nSV R4, R1\n";
            }
        }
        return code;
    }
}
