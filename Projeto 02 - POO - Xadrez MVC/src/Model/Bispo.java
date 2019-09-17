/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Exceptions.MovimentoInvalidoException;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.lang.Math;
/**
 *
 * 
 */
public class Bispo extends Peca{
    public Bispo(Cor cor, int x, int y)  {
        super(cor, x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        int squareWidth = g.getClip().getBounds().width / 8;
        int squareHeight = g.getClip().getBounds().height / 8;
        
        int x0 = quadrante.x * squareWidth;
        int y0 = quadrante.y * squareHeight;
        int x1 = x0 + squareWidth;
        int y1 = y0 + squareHeight;
        
        if(this.cor == Peca.Cor.PRETO){
            g.drawImage(pecasImg, x0, y0, x1, y1, 198, 20, 238, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 198, 72, 238, 112, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Bispo Preta";
        } else {
            return "Bispo Branca";
        }
    }

   @Override
    public boolean move_to(int x, int y, boolean mat[][]) throws MovimentoInvalidoException{
        boolean flag = false;
        for(int i=0; i<8; i++){
            if(x == this.quadrante.x + i && y == this.quadrante.y - i){
                System.out.println(y - this.quadrante.y);
                for(int j = 1; j< Math.abs(y - this.quadrante.y); j++){
                     if(mat[this.quadrante.y - j][this.quadrante.x + j] == true){
                         System.out.println("Entrou 1");
                         throw new MovimentoInvalidoException();
                     }
                }
                flag = true;
                break;
            }
            if(x == this.quadrante.x - i && y == this.quadrante.y + i){
                System.out.println(y - this.quadrante.y);
                for(int j = 1; j< Math.abs(y - this.quadrante.y); j++){
                     if(mat[this.quadrante.y + j][this.quadrante.x - j] == true){
                        System.out.println("Entrou 2");
                        throw new MovimentoInvalidoException();
                     }
                }
                flag = true;
                break;
            }
           if(x == this.quadrante.x + i && y == this.quadrante.y + i){
               System.out.println(y - this.quadrante.y);
                for(int j = 1; j<Math.abs(y - this.quadrante.y); j++){
                     if(mat[this.quadrante.y + j][this.quadrante.x + j] == true){
                        System.out.println("Entrou 3");
                         throw new MovimentoInvalidoException();
                     }
                }
                flag = true;
                break;
            }
            if(x == this.quadrante.x - i && y == this.quadrante.y - i ){
                System.out.println(y - this.quadrante.y);
                for(int j =1 ; j<Math.abs(y - this.quadrante.y); j++){
                     if(mat[this.quadrante.y - j][this.quadrante.x - j] == true){
                         System.out.println("Entrou 4");
                         throw new MovimentoInvalidoException();
                     }
                }
                flag = true;
                break;
            }
            else{
                if(i == 7){
                    throw new MovimentoInvalidoException();
                }
            }
        }
        //se o movimento for valido
        if(flag){
            this.quadrante.x = x;
            this.quadrante.y = y;
        }
        
        return flag;
    }
}
