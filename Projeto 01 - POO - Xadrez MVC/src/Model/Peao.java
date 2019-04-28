/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics2D;

/**
 *
 * @author jbatista
 */
public class Peao  extends Peca{

    public Peao(Cor cor, int x, int y)  {
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 320, 20, 360, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 320, 72, 360, 112, null);
        }
    }
    
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Peao Preta";
        } else {
            return "Peao Branca";
        }
    }
    @Override
    public boolean move_to(int x, int y, boolean mat[][]) {
        boolean flag = false;
        if(this.getCor() == Peca.Cor.PRETO){
            if(y == this.quadrante.y + 1 && x == this.quadrante.x){
                if(mat[y][x]== true){
                    return false;
                }
                flag = true;
            }
            else if(y == this.quadrante.y + 1 && (x == this.quadrante.x + 1 || x == this.quadrante.x - 1)){
                if(mat[y][x] == false){
                    return false;
                }
                flag = true;
            }
        }
        else{
            if(y == this.quadrante.y - 1 && x == this.quadrante.x){
                if(mat[y][x]== true){
                    return false;
                }
                flag = true;
            }
            else if(y == this.quadrante.y - 1 && (x == this.quadrante.x + 1 || x == this.quadrante.x - 1)){
                if(mat[y][x]== false){
                    return false;
                }
                flag = true;
            }
        }
        //se o movimento for valido mudo o quadrante
        if(flag){
            this.quadrante.x = x;
            this.quadrante.y = y;
    }
        return flag;
    }
}
