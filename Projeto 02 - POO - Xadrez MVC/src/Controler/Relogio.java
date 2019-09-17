/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class Relogio extends Thread{
    private int secs;
    private int mins;
    private boolean flag;//variavel de controle, para que saiba se o relogio esta ligado ou não

    public Relogio(boolean flag) {
        this.secs = 0;
        this.mins = 0;
        this.flag = flag;
        this.start();
    }

    public int getSecs() {
        return secs;
    }

    public void setSecs(int secs) {
        this.secs = secs;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }
    
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    
    @Override
    public void run(){
       while(true){
           
            try {
                Relogio.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Relogio.class.getName()).log(Level.SEVERE, null, ex);
            }
                
           if(flag == true){
                if(this.secs <59){
                    this.secs++;
                }
                else{
                    this.secs = 0;
                    this.mins ++;

                }
           }

           //System.out.println("mins = "+ this.mins + "secs = "+ this.secs + "flag = "+this.flag);
       }

    }
}
