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
public class Rainha extends Peca{
    public Rainha(Cor cor, int x, int y)  {
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
            g.drawImage(pecasImg, x0, y0, x1, y1, 77, 20, 117, 60, null);
        } else {
            g.drawImage(pecasImg, x0, y0, x1, y1, 77, 72, 117, 112, null);
        }
    }
    @Override
    public String toString() {
        if(this.cor == Peca.Cor.PRETO){
            return "Rainha Preta";
        } else {
            return "Rainha Branca";
        }
    }
    @Override
    public boolean move_to(int x, int y, boolean mat[][]) {
        

        boolean flag = false;
        for(int i=0; i<8; i++){
            if(x == this.quadrante.x + i && y == this.quadrante.y - i){
               System.out.println(y - this.quadrante.y);
                for(int j = 1; j< Math.abs(y - this.quadrante.y); j++){
                     if(mat[this.quadrante.y - j][this.quadrante.x + j] == true){
                         System.out.println("Entrou 1");
                         return false;
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
                        return false;
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
                         return false;
                     }
                }
                flag = true;
                break;
            }
            if(x == this.quadrante.x - i && y == this.quadrante.y - i){
               System.out.println(y - this.quadrante.y);
                for(int j =1 ; j<Math.abs(y - this.quadrante.y); j++){
                     if(mat[this.quadrante.y - j][this.quadrante.x - j] == true){
                         System.out.println("Entrou 4");
                         return false;
                     }
                }
                flag = true;
                break;
            }
            //mechi para a esquerda
            if(x == this.quadrante.x - i && y == this.quadrante.y){
                for(int j = this.quadrante.x-1; j>x; j--){
                    if(mat[y][j]== true){
                        return false;
                    }
                }
                flag = true;
                break;
            }
            //mechi para a direita
            if(x == this.quadrante.x + i && y == this.quadrante.y){
                for(int j = this.quadrante.x+1; j<x; j++){
                    if(mat[y][j]== true){
                        return false;
                    }
                }
                flag = true;
                break;
            }
            //mechi para cima ou baixo depende da cor
            if(x == this.quadrante.x && y == this.quadrante.y  + i){
                for(int j = this.quadrante.y+1; j<y;j++){
                    System.out.println("M1 = " +mat[j][y]);
                    if(mat[j][x]== true){
                        return false;
                }
            }
                flag = true;
                break;
            }
            if(x == this.quadrante.x && y == this.quadrante.y  - i){
                 for(int j = this.quadrante.y-1; j>y;j--){
                    if(mat[j][x]== true){
                        return false;
                    }
                }
                flag = true;
                break;
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
