/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import Model.ModelTabuleiro;
import Model.ModelTabuleiro;
import Model.Peca;
import View.Tabuleiro;
import java.io.File;

/**
 *
 * 
 */
public class Save{
    
        public void salva_jogo(ModelTabuleiro model, boolean mat[][], int jogador){
            
            try{
                  System.out.println("Salvando o jogo...");
                  FileOutputStream f_out = new FileOutputStream("xadrez.bin");
                  ObjectOutputStream objout = new ObjectOutputStream (f_out);
                  objout.writeObject(model);
                  objout.writeObject(jogador);
                  objout.flush();
                  objout.close();
                  f_out.close();
              }catch(Exception er){
                  System.out.println(er.toString());
                  System.exit (1);
              }
            try{
                  FileOutputStream f_out2 = new FileOutputStream("matriz.data");
                  ObjectOutputStream objout2 = new ObjectOutputStream (f_out2);
                  objout2.writeObject(mat);
                  objout2.flush();
                  objout2.close();
                  f_out2.close();
            }catch(Exception er){
                  System.out.println(er.toString());
                  System.exit (1);
              }
            
             try{
                  FileOutputStream f_out3 = new FileOutputStream("jogador.bin");
                  ObjectOutputStream objout3 = new ObjectOutputStream (f_out3);
                  objout3.writeObject(jogador);
                  objout3.flush();
                  objout3.close();
                  f_out3.close();
              }catch(Exception er){
                  System.out.println(er.toString());
                  System.exit (1);
              }
        }
  
}