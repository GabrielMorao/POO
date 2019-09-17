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
public class Rei extends Peca{
    public Rei(Cor cor, int x, int y)  {
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 18, 20, 58, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 18, 72, 58, 112, null);
        }
    }

    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Rei Preta";
        } else {
            return "Rei Branca";
        }
    }
    
    @Override
    public boolean move_to(int x, int y, boolean mat[][]) throws MovimentoInvalidoException{
        boolean flag = false;
        //como o rei pode se mover em todos os lados, nao importa se e preto ou branco
        if(this.quadrante.x == x && this.quadrante.y == y+1){
            flag = true;
        }
        else if(this.quadrante.x == x+1 && this.quadrante.y == y+1){
            flag = true;
        }
        else if(this.quadrante.x == x-1 && this.quadrante.y == y+1){
            flag = true;
        }
        else if(this.quadrante.x == x+1 && this.quadrante.y == y){
            flag = true;
        }
        else if(this.quadrante.x == x-1 && this.quadrante.y == y){
            flag = true;
        }
        if(this.quadrante.x == x && this.quadrante.y == y-1){
            flag = true;
        }
        else if(this.quadrante.x == x+1 && this.quadrante.y == y-1){
            flag = true;
        }
        else if(this.quadrante.x == x-1 && this.quadrante.y == y-1){
            flag = true;
        }
        else{
            throw new MovimentoInvalidoException();
        }
        //se o movimento for valido
        if(flag){
            this.quadrante.x = x;
            this.quadrante.y = y; 
        }
        return flag;
    }
}
