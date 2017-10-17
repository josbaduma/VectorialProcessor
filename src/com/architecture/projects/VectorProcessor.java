/*********************************************/
/*  Instituto Tecnológico de Costa Rica      */
/*  Ingeniería en Computadores               */
/*  Arquitectura de Computadores II          */
/*  II Semestre 2017                         */
/*                                           */
/*  Author: José Daniel Badilla Umaña        */
/*  Carné: 201271708                         */
/*********************************************/
package com.architecture.projects;

import com.architecture.projects.components.Clock;
import com.architecture.projects.components.InstructionMemory;
import com.architecture.projects.stages.DecodeStage;
import com.architecture.projects.stages.ExecutionStage;
import com.architecture.projects.stages.FetchStage;
import com.architecture.projects.stages.MemoryStage;
import com.architecture.projects.stages.WriteBackStage;
import com.architecture.projects.compiler.Compiler;
import com.architecture.projects.utilities.CodeAndImageManager;
import com.architecture.projects.utilities.Utility;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class VectorProcessor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*InstructionMemory instr = InstructionMemory.getInstance();
        instr.addInstruction("00100100000100100011000000000000");
        
        Clock clock = Clock.getInstance();
        
        FetchStage fetch = FetchStage.getInstance();
        DecodeStage decode = DecodeStage.getInstance();
        ExecutionStage execute = ExecutionStage.getInstance();
        MemoryStage memory = MemoryStage.getInstance();
        WriteBackStage writeBack = WriteBackStage.getInstance();*/
        
        //fetch.start();
        //decode.start();
        //execute.start();
        //memory.start();
        //writeBack.start();
        
        /*Compiler comp = new Compiler("ADDVV R3, R4, R2\nANDVS R5, R5, R3\nMOVS R6, 100\nLS R4, R3\nSHIFTL R1, 10(R5)");
        ArrayList<String> instructions = comp.compile();
        String inst1 = instructions.get(0);
        System.out.println("Instrucción #1: " + inst1);
        String inst2 = instructions.get(1);
        System.out.println("Instrucción #2: " + inst2);
        String inst3 = instructions.get(2);
        System.out.println("Instrucción #3: " + inst3);
        String inst4 = instructions.get(3);
        System.out.println("Instrucción #4: " + inst4);
        String inst5 = instructions.get(4);
        System.out.println("Instrucción #5: " + inst5);*/
        
        /*CodeAndImageManager im = new CodeAndImageManager();
        im.getImage("/home/jose/Imágenes/paisaje1.jpg");
        if(im.getPixelToMemory() != null){
            System.out.println("Correcto");
        }*/
        System.out.println("Binario 100010101, rotado: "+ rotarIzquierda("100010101"));
        
    }
    
    public static String rotarIzquierda(String binario){
        String binarioRotado;
        binarioRotado = binario.substring(1) + binario.charAt(0);
        return binarioRotado;
    }
}
