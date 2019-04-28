/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.awt.Graphics2D;
/**
 *
 * @author USUARIOS+10734290
 */
public class Cavalo extends Peca{
    public Cavalo(Cor cor, int x, int y)  {
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 260, 20, 300, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 260, 72, 300, 112, null);
        }
    }
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Cavalo Preta";
        } else {
            return "Cavalo Branca";
        }
    }
    
    @Override
    public boolean move_to(int x, int y, boolean mat[][]) {
        boolean flag = false;
        if(x == this.quadrante.x + 2 && y == this.quadrante.y + 1){
            flag = true;
        }
        else if(x == this.quadrante.x + 1 && y == this.quadrante.y +2){
            flag = true;
        }
        else if(x == this.quadrante.x + 2 && y == this.quadrante.y -1){
            flag = true;
        }
        else if(x == this.quadrante.x + 1 && y == this.quadrante.y -2){
            flag = true;
        }
        else if(x == this.quadrante.x - 2 && y == this.quadrante.y + 1){
            flag = true;
        }
        else if(x == this.quadrante.x - 1 && y == this.quadrante.y +2){
            flag = true;
        }
        else if(x == this.quadrante.x - 2 && y == this.quadrante.y -1){
            flag = true;
        }
        else if(x == this.quadrante.x - 1 && y == this.quadrante.y -2){
            flag = true;
        }
        
        if(flag){
            this.quadrante.x = x;
            this.quadrante.y = y;
        }
        return flag;
    }
}
