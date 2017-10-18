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

import com.architecture.projects.components.InstructionMemory;
import com.architecture.projects.stages.DecodeStage;
import com.architecture.projects.stages.ExecutionStage;
import com.architecture.projects.stages.FetchStage;
import com.architecture.projects.stages.MemoryStage;
import com.architecture.projects.stages.WriteBackStage;


/**
 *
 * @author jose
 */
public class VectorProcessor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InstructionMemory instr = InstructionMemory.getInstance();
        instr.addInstruction("00100100000100100011000000000000");
        instr.addInstruction("00101000000100100010000000000000");
        
        FetchStage fetch = FetchStage.getInstance();
        DecodeStage decode = DecodeStage.getInstance();
        ExecutionStage execute = ExecutionStage.getInstance();
        MemoryStage memory = MemoryStage.getInstance();
        WriteBackStage writeBack = WriteBackStage.getInstance();
        
        writeBack.start(2);
        memory.start(2);
        execute.start(2);
        decode.start(2);
        fetch.start(2);        
    }
}
