/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.architecture.projects;

import com.architecture.projects.stages.FetchStage;

/**
 *
 * @author jose
 */
public class VectorProcessor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FetchStage fetch = FetchStage.getInstance();
        fetch.start();
        fetch.getInstructionFetched();
    }
    
}
