/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import Exceptions.MovimentoInvalidoException;
import java.awt.Graphics2D;
import java.io.Serializable;
/**
 *
 * @author USUARIOS+10734290
 */
public class Torre extends Peca{
    public Torre(Cor cor, int x, int y)  {
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 140, 20, 180, 60, null);//40 de tamanho e a altura e sempre a mesma
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 140, 72, 180, 112, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Torre Preta";
        } else {
            return "Torre Branca";
        }
    }

    @Override
    public boolean move_to(int x, int y, boolean mat[][]) throws MovimentoInvalidoException{
        boolean flag = false;
        boolean flag_mat = true;
        if(this.quadrante.x == x && this.quadrante.y != y){
            if(y > this.quadrante.y){
                for(int j = this.quadrante.y+1; j<y;j++){
                    System.out.println("M1 = " +mat[j][y]);
                    if(mat[j][x]== true){
                        throw new MovimentoInvalidoException();
                }
            }
            }
            else{
                for(int j = this.quadrante.y-1; j>y;j--){
                    if(mat[j][x]== true){
                       throw new MovimentoInvalidoException();
                    }
                }
            }
            
            flag = true;
        }
        else if(this.quadrante.x != x && this.quadrante.y == y){
            if(x > this.quadrante.x){
                for(int j = this.quadrante.x+1; j<x; j++){
                    if(mat[y][j]== true){
                        throw new MovimentoInvalidoException();
                    }
                }
            }
            else{
                for(int j = this.quadrante.x-1; j>x; j--){
                    if(mat[y][j]== true){
                        throw new MovimentoInvalidoException();
                    }
                }
            }  
            
            flag = true;
        }
        else{
            throw new MovimentoInvalidoException();
        }
        //se o movimento for valido
        if(flag && flag_mat){
            this.quadrante.x = x;
            this.quadrante.y = y;
            return true;
        }
        return false;
    }
}
