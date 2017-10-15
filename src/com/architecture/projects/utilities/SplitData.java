/**
 * *****************************************
 */
/*Instituto Tecnológico de Costa Rica 	*/
 /*Ingeniería en Computadores           	*/
 /*Arquitectura de Computadores II    	*/
 /*II Semestre 2017                     	*/
 /*			*/
 /*Author: José Daniel Badilla Umaña    	*/
 /*Carné: 201271708                     	*/
/**
 * ******************************************
 */
package com.architecture.projects.utilities;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author jose
 */
public class SplitData {
    
    private static final String[] listAritmetic= {"ADDVV", "ADDVS", "SUBVV", "SUBVS", "SUBSV", "MULVV", "MULVS"};
    private static final String[] listLogic = {"ANDVV", "ANDVS", "ORVV", "ORVS", "XORVV", "XORVS"};
    private static final String[] listMemory = {"LV", "LS", "SV", "SS"};
    private static final String[] listDisplacement = {"SHIFTL", "SHIFTR", "SHIFTC"};
        
    public static ArrayList<String> getSplitedString(String pData, String pDelimit){
        ArrayList<String> instruction = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(pData, pDelimit);
        while (st.hasMoreElements()) {
            instruction.add(st.nextElement().toString());
        }
        return instruction;
    }
    
    public static int getNumberOfChar(String pData, String pSearched) {
        String sTexto = pData;
        int contador = 0;
        while (sTexto.contains(pSearched)) {
            sTexto = sTexto.substring(sTexto.indexOf(
                    pSearched) + pSearched.length(), sTexto.length());
            contador++;
        }
        return contador;
    }
    
    public static boolean containsAritmetic(String pData) {
        int i = 0;
        boolean flag = false;
        while(i < listAritmetic.length) {
            if(pData.contains(listAritmetic[i])) {
                flag = true;
                break;
            }
            i++;
        }
        return flag;
    }
    
    public static boolean containsLogic(String pData) {
        int i = 0;
        boolean flag = false;
        String mnemonic = getSplitedString(pData, ", !").get(0);
        while(i < listLogic.length) {
            if(mnemonic.equals(listLogic[i])) {
                flag = true;
                break;
            }
            i++;
        }
        return flag;
    }
    
    public static boolean containsMemory(String pData) {
        int i = 0;
        boolean flag = false;
        while(i < listMemory.length) {
            if(pData.contains(listMemory[i])) {
                flag = true;
                break;
            }
            i++;
        }
        return flag;
    }
    
    public static boolean containsDisplacement(String pData) {
        int i = 0;
        boolean flag = false;
        while(i < listDisplacement.length) {
            if(pData.contains(listDisplacement[i])) {
                flag = true;
                break;
            }
            i++;
        }
        return flag;
    }
    
}
