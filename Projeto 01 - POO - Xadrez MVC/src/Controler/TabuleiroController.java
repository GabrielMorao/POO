/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.ModelTabuleiro;
import View.Tabuleiro;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import Model.Peca;
import java.awt.Graphics2D;
import java.util.Observable;


/**
 *
 * @author felipelageduarte
 */
public class TabuleiroController implements  MouseListener, MouseMotionListener{

  private Tabuleiro view;
  private ModelTabuleiro model;
  private Peca p;
  private Peca peca_clicada;
  private boolean flag;                                     //checa se o jogador e o certo
  private int jogador;
  private boolean mat[][] = new boolean[8][8];
  
  
    public void addView (Tabuleiro view){
        this.view = view;
    }
    
    public void addModel (ModelTabuleiro model){
        this.model = model;
        p = null;
        peca_clicada = null;
        jogador = 1;
    }
    
    public void addMat (){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(i == 0 || i== 1 || i == 6 || i== 7){
                    this.mat[i][j] = true;
                }
                else{
                    this.mat[i][j] = false;
                }
            }
        }
        
    }
    
    public void printMat (){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                System.out.printf("%b", mat[i][j]);
                if (j == 7){
                    System.out.printf("\n");
                }
            }
        }
        
    }
    /*
      USe este metodo para iniciar o seu VIEW... neste caso, antes de motra-lo
    na tela, o posicionamos no centro dela....
    */
    public void runTabuleiro() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - view.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - view.getHeight()) / 2);
        view.setLocation(x, y);
        
        view.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();//pega as coordenadas do mouse
        int y = e.getY();
        int x_aux;
        int y_aux;
        view.getClickLabel().setText("x:"+x+"  y:"+y+"   -   Quadrante: ["+x/60+","+y/60+"]");
        //se tem uma peca onde eu cliquei, tenho que salvar as cordenadas dela
        p = model.findPeca(x/60, y/60);
        System.out.println("peca = " + p);
        this.flag = true;
        System.out.println("VEZ DO " + this.jogador);
                
        if(p != null && peca_clicada == null && flag == true){//cliquei numa peca e nao tinha nada salvo
            if(p.getCor() == Peca.Cor.BRANCO && jogador != 1){
                System.out.println("NAO E A VEZ DO JOGADOR BRANCO");
                this.flag = false;
            }
            else if(p.getCor() == Peca.Cor.PRETO && jogador != 2){
                System.out.println("NAO E A VEZ DO JOGADOR PRETO");
                this.flag = false;
            }
            else{
                 peca_clicada = p;
            }     
        }
        else if(p == null && peca_clicada != null && flag == true){//se eu clickei num espaco vazio e tenho algo salvo
                //Graphics2D g = null;
                System.out.println("peca clicada = " + peca_clicada);
                x_aux= peca_clicada.getQuadrante().x;
                y_aux= peca_clicada.getQuadrante().y;
                if(peca_clicada.move_to(x/60, y/60, mat) == true){
                    System.out.println("Movimento valido do " + peca_clicada);
                    if(peca_clicada.getCor() == Peca.Cor.BRANCO){
                        view.getLabel_team().setText("Pretas");
                        this.jogador = 2;
                    }
                    else{
                        view.getLabel_team().setText("Brancas");
                        this.jogador = 1;
                    }
                    mat[y_aux][x_aux] = false;
                    mat[y/60][x/60] = true;
                }
                else{
                    System.out.println("ERRO : Movimento INVALIDO do " + peca_clicada);
                }
                peca_clicada = null;
        }
        else if(p != null && peca_clicada != null &&(p.getCor() != peca_clicada.getCor()) && flag == true){//movimento de ataque a outra peca que nao seja da sua cor
                System.out.println("peca clicada antes = " + peca_clicada);
                System.out.println("peca a ser comida = " + p);
               x_aux= peca_clicada.getQuadrante().x;
                y_aux= peca_clicada.getQuadrante().y;
                if(peca_clicada.move_to(x/60, y/60,  mat) == true){
                    System.out.println("Movimento valido do " + peca_clicada);
                    p.setQuadrante(-1, -1);//sumo com a peca comida do tabuleiro
                    if(peca_clicada.getCor() == Peca.Cor.BRANCO){
                        view.getLabel_team().setText("Pretas");
                        this.jogador = 2;
                    }
                    else{
                        view.getLabel_team().setText("Brancas");
                        this.jogador = 1;
                    }
                    //tratamento caso o REI morra
                    if(p.toString().equals("Rei Preta")){
                        System.out.println("Game Over, As peças brancas venceram");
                        view.getGameStatus().setText("Game Over, As peças brancas venceram");
                        view.getLabel_team().setText("");
                    }
                    else if(p.toString().equals("Rei Branca")){
                        System.out.println("Game Over, As peças pretas venceram");
                        view.getGameStatus().setText("Game Over, As peças pretas venceram");
                        view.getLabel_team().setText("");
                    }
                    mat[y_aux][x_aux] = false;
                    mat[y/60][x/60] = true;
                }
                else{
                    System.out.println("ERRO : Movimento INVALIDO do " + peca_clicada);
                }
                peca_clicada = null;
        }
        System.out.printf("\n");
        view.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();//pega as coordenadas do mouse
        int y = e.getY();
        view.getCoordenadaLabel().setText("x:"+x+"  y:"+y+"   -   Quadrante: ["+x/60+","+y/60+"]");
        view.getMouseCoord().setLocation(x, y);
        view.repaint();
    }

}
