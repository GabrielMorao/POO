/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import Exceptions.MovimentoInvalidoException;

/**
 *
 * @author jbatista
 */
public abstract class Peca implements Serializable{
    
    protected final static String imgPath = "img/pecas.png";
    protected static BufferedImage pecasImg = null;    
    protected Cor cor;
    protected Point quadrante;
    private static final long serialVersionUID = 1L;
    
    public enum Cor{
        PRETO,
        BRANCO
    }
    
    public Peca(Cor cor, int x, int y)  {
        this.cor = cor;
        this.quadrante = new Point(x,y);
        if(pecasImg == null){
            try {
                pecasImg = ImageIO.read(new File(imgPath));
            } catch (IOException ex) {
                Logger.getLogger(Peca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean inSquare(int x, int y){
        if(x == quadrante.x && y == quadrante.y) return true;
        else return false;
    }
    
    public void setQuadrante(int x, int y){
        quadrante.setLocation(x, y);
    }

    public Point getQuadrante() {
        return quadrante;
    }
        
    public void printQuadrante(){
        System.out.println("X = "+ this.quadrante.x + " " + this.quadrante.y);
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }
    
    
    public abstract void draw(Graphics2D g);
    public abstract boolean move_to(int x, int y, boolean mat[][]) throws MovimentoInvalidoException;//retorna true se o movimento
    //for valido
   
}
