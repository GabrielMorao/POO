/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;
import Model.Peca;
import java.io.*;

/**
 *
 *
 */
public class MovimentoInvalidoException extends Exception{
    
    @Override
    public String toString(){
        return "Movimento Inválido";
    }
          
        
}

