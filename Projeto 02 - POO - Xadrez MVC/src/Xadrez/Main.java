/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Xadrez;

import Controler.TabuleiroController;
import Controler.Save;
import Model.ModelTabuleiro;
import View.Tabuleiro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felipelageduarte
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args){
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        /*Menu para usuário escolher entre novo jogo e carregar algum já salvo*/
        System.out.println("Bem Vindo ao Xadrez !");
        System.out.println("Para iniciar um novo jogo, digite 0.");
        System.out.println("Para carregar o último jogo, digite 1.");

        /*Variáveis a serem utilizadas no carregamento*/
        int opcao = -1;
        int jogador = 1;
        int novo_jogador = 1;
        boolean flag = false;
        /*Lê a opção do usuário*/
        Scanner ler = new Scanner(System.in);
        opcao = ler.nextInt();
        
        ModelTabuleiro model = new ModelTabuleiro();  
          // cria o View (Janela do TAbuleiro)
        Tabuleiro viewTabuleiro = new Tabuleiro(model);  
        // Cria o Controller do TAbuleiro.... todos eventos tratados aqui..
        TabuleiroController tabuleiroController = new TabuleiroController(); 
        // Inicia um novo save
        Save salvar = new Save();
        /*
        ATENCAO: a classe ModelTAbuleiro extends Observable !!!!!!
        Isso significa que a classe tem a disposicao o metodo addObserver()
        AHA: o observer no nosso caso é exatamente a nossa classe view -> Tabuleiro !!!
        a ideia é a seguinte: SEMPRE QUE HOUVER NECESSIDADE, em funcao de uma alteracao 
        dos dados, que isso se REFLITA visualmente na INTERFACE GRAFICA (o nosso view 
        Tabuleiro), então, o observable (ModelTAbuleiro) NOTIFICARÁ por meio do método
        notifyObservers(), no nosso caso o VIEW Tabuleiro !!!!
        
        Para isso devemos dizer qual é o observer do Observable
        */
        
         while((opcao != 0 || opcao != 1) && flag != true){
                /*Começar novo jogo*/
               if(opcao == 0){
                   flag = true;
                   System.out.println("Começando nova Partida");
                   jogador = 1;
                   // define o tratamento de eventos dos atributos do view para o controller
                   viewTabuleiro.addController(tabuleiroController);
                   // associa o view ao seu repectivo controller
                   tabuleiroController.addView(viewTabuleiro);
                   tabuleiroController.addModel(model, jogador);
                   tabuleiroController.addSave(salvar);
                   tabuleiroController.addMat();
                   // finalmente, executa o view.....
                   tabuleiroController.runTabuleiro();
                   break;
               }

               /*Carregar último jogo*/
               else if(opcao == 1){
                   flag = true;
                   System.out.println("Abrindo o jogo...");
                   
                   ModelTabuleiro model2 = null;
                   Object new_obj = new Object();
                   boolean new_mat[][] = new boolean[8][8];

                 /*Carrega o model do jogo*/
                 try{
                     FileInputStream f_in = new FileInputStream("xadrez.bin");
                     ObjectInputStream objin = new ObjectInputStream (f_in);
                     model2 = (ModelTabuleiro)objin.readObject();
                     objin.close();
                     f_in.close();
                     
                     FileInputStream f_in3 = new FileInputStream("jogador.bin");
                     ObjectInputStream obj3 = new ObjectInputStream(f_in3);
                     novo_jogador = (Integer)obj3.readObject();
                     obj3.close();
                     f_in3.close();
                     
                     FileInputStream f_in2 = new FileInputStream("matriz.data");
                     ObjectInputStream objin2 = new ObjectInputStream (f_in2);
                     new_obj = objin2.readObject();
                     objin2.close();
                     f_in2.close();
                     new_mat = (boolean[][])new_obj;

                 }catch(IOException e){
                     flag = false;
                     System.out.println("Não há jogos salvos !");
                     opcao = ler.nextInt();
                 }catch(ClassNotFoundException e){}
                 
                 /*Se flag igual verdadeiro, carrega o jogo*/
                  if(flag == true){
                  /*Com tudo carregado, inicia as etapas do jogo*/
                     Tabuleiro viewTabuleiro2 = new Tabuleiro(model2); 
                     // define o tratamento de eventos dos atributos do view para o controller
                     viewTabuleiro2.addController(tabuleiroController);
                     // associa o view ao seu repectivo controller
                     tabuleiroController.addView(viewTabuleiro2);
                     tabuleiroController.addModel(model2, novo_jogador);
                     tabuleiroController.addSave(salvar);
                     tabuleiroController.loadMat(new_mat);
                     // finalmente, executa o view.....
                     tabuleiroController.runTabuleiro();
                     break;
                  }
               }
               /*Usuário entrou com outra opção sem ser 0 ou 1*/
                else{
                    System.out.println("Opção " + opcao +  " Inválida, entre com nova opção");
                    opcao = ler.nextInt();
                }
              
         }
      }
    });
  }
}