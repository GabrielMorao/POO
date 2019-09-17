/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * tabuleiro.java
 *
 * Created on 03/03/2011, 18:28:20
 */
package View;

import Controler.Relogio;
import Controler.TabuleiroController;
import Model.ModelTabuleiro;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 *
 * @author felipelageduarte
 */
public class Tabuleiro extends javax.swing.JFrame implements Observer{

    
  private  Canvas canvas;  
  private  Point mouseCoord;
  private  ModelTabuleiro model;
  private  Relogio clock;
  private  Relogio clockB, clockP;
  //private JPanel CanvasD;//atributo com a janela de desenho
  
  /** Creates new form tabuleiro */
  public Tabuleiro(ModelTabuleiro model) {
    initComponents();
    canvas = new Canvas();
    mouseCoord = new Point();
    canvas.registerObserver(this);
    this.model = model;
    canvas.registerObserver(model);
    clock = new Relogio(true);
    clockB = new Relogio(true);
    clockP = new Relogio(false);
    Dimension area = new Dimension(jPCanvas.getWidth(), jPCanvas.getHeight());
    canvas.setPreferredSize(area);//set dimensao do painel de desenho
    jPCanvas.setLayout(new GridLayout(1, 1));
    jPCanvas.add(canvas);  
    
  }

    public Point getMouseCoord() {
        return mouseCoord;
    }

    public void setMouseCoord(Point mouseCoord) {
        this.mouseCoord = mouseCoord;
    }
  
    public JLabel getClickLabel() {
        return clickLabel;
    }

    public JLabel getCoordenadaLabel() {
        return coordenadaLabel;
    }

    public void setClickLabel(JLabel clickLabel) {
        this.clickLabel = clickLabel;
    }

    public void setCoordenadaLabel(JLabel coordenadaLabel) {
        this.coordenadaLabel = coordenadaLabel;
    }

    public JLabel getLabel_team() {
        return Label_team;
    }

    public void setLabel_team(JLabel Label_team) {
        this.Label_team = Label_team;
    }

    public JLabel getGameStatus() {
        return GameStatus;
    }

    public void setGameStatus(JLabel GameStatus) {
        this.GameStatus = GameStatus;
    }

    public JLabel getRelogio() {
        return Relogio;
    }

    public void setRelogio(JLabel Relogio) {
        this.Relogio = Relogio;
   }
       
    public void addController(TabuleiroController controller){
        jPCanvas.addMouseListener(controller);
        jPCanvas.addMouseMotionListener(controller);
    }

    public JLabel getRelogioPlayers() {
        return RelogioPlayers;
    }

    public void setRelogioPlayers(JLabel RelogioPlayers) {
        this.RelogioPlayers = RelogioPlayers;
    }

    public JLabel getRelogiocor() {
        return Relogiocor;
    }

    public void setRelogiocor(JLabel Relogiocor) {
        this.Relogiocor = Relogiocor;
    }

    public JLabel getRelogioPlayers1() {
        return RelogioPlayers1;
    }

    public void setRelogioPlayers1(JLabel RelogioPlayers1) {
        this.RelogioPlayers1 = RelogioPlayers1;
    }

    public JLabel getRelogiocorP() {
        return RelogiocorP;
    }

    public void setRelogiocorP(JLabel RelogiocorP) {
        this.RelogiocorP = RelogiocorP;
    }
    
    
    
    public void atualiza_relogios(){
        //Relogio principal
        if(clock.getMins() < 10 && clock.getSecs() < 10){
            this.getRelogio().setText("00:" + "0"+ clock.getMins() + ":" + "0" + clock.getSecs());
        }
        else if(clock.getMins() < 10 && clock.getSecs() >= 10){
            this.getRelogio().setText("00:" + "0"+ clock.getMins() + ":" + clock.getSecs());
        }
        else if(clock.getMins() > 10 && clock.getSecs() < 10){
            this.getRelogio().setText("00:" + clock.getMins() + ":" + "0" + clock.getSecs());
        }
        else if(clock.getMins() >= 10 && clock.getSecs() < 10){
            this.getRelogio().setText("00:" + clock.getMins() + ":" + "0" + clock.getSecs());
        }
        else{
            this.getRelogio().setText("00:" + clock.getMins() + ":" + clock.getSecs());
        }

        if(this.getLabel_team().getText().equals("Brancas")){
            clockP.setFlag(false);//para relogio preto
            clockB.setFlag(true);//liga o branco
        }
        else{
            //mesmo que o de cima so que inverso
            clockB.setFlag(false);
            clockP.setFlag(true);
        }
        
            if(clockB.getMins() < 10 && clockB.getSecs() < 10){
                this.getRelogioPlayers().setText("00:" + "0"+ clockB.getMins() + ":" + "0" + clockB.getSecs());
            }
            else if(clockB.getMins() < 10 && clockB.getSecs() >= 10){
                this.getRelogioPlayers().setText("00:" + "0"+ clockB.getMins() + ":" + clockB.getSecs());
            }
            else if(clockB.getMins() > 10 && clockB.getSecs() < 10){
                this.getRelogioPlayers().setText("00:" + clockB.getMins() + ":" + "0" + clockB.getSecs());
            }
            else if(clockB.getMins() >= 10 && clockB.getSecs() < 10){
                this.getRelogioPlayers().setText("00:" + clockB.getMins() + ":" + "0" + clockB.getSecs());
            }
            else{
                this.getRelogioPlayers().setText("00:" + clockB.getMins() + ":" + clockB.getSecs());
            }
            
            
            if(clockP.getMins() < 10 && clockP.getSecs() < 10){
                this.getRelogioPlayers1().setText("00:" + "0"+ clockP.getMins() + ":" + "0" + clockP.getSecs());
            }
            else if(clockP.getMins() < 10 && clockP.getSecs() >= 10){
                this.getRelogioPlayers1().setText("00:" + "0"+ clockP.getMins() + ":" + clockP.getSecs());
            }
            else if(clockP.getMins() > 10 && clockP.getSecs() < 10){
                this.getRelogioPlayers1().setText("00:" + clockP.getMins() + ":" + "0" + clockP.getSecs());
            }
            else if(clockP.getMins() >= 10 && clockP.getSecs() < 10){
                this.getRelogioPlayers1().setText("00:" + clockP.getMins() + ":" + "0" + clockP.getSecs());
            }
            else{
                this.getRelogioPlayers1().setText("00:" + clockP.getMins() + ":" + clockP.getSecs());
            }

        
        
    }
  
 public void drawMouseQuadrante(Graphics2D g) {
        
        int width = canvas.getWidth()/8;
        int height = canvas.getHeight()/8;
        
        int qx = mouseCoord.x/width;
        int qy = mouseCoord.y/height;
        //System.out.println(qx + "   " + qy);
        
        int squareWidth = g.getClip().getBounds().width / 8;
        int squareHeight = g.getClip().getBounds().height / 8;
        
        squareWidth++; // gambiarra 1
        squareHeight++;  // gambiarra 2

        //System.out.println(squareWidth + "   " + squareHeight);
        
        g.setColor(Color.red);
        g.setStroke(new BasicStroke(2));
        g.drawRect(qx * squareWidth, qy * squareHeight, squareWidth, squareHeight);
        g.setColor(Color.BLACK);
    }   
    
    
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPCanvas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        coordenadaLabel = new javax.swing.JLabel();
        clickLabel = new javax.swing.JLabel();
        GameStatus = new javax.swing.JLabel();
        Label_team = new javax.swing.JLabel();
        Tempo_game = new javax.swing.JLabel();
        Relogio = new javax.swing.JLabel();
        Relogiocor = new javax.swing.JLabel();
        RelogioPlayers = new javax.swing.JLabel();
        RelogiocorP = new javax.swing.JLabel();
        RelogioPlayers1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0204 - Xadrez");
        setResizable(false);

        jPCanvas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPCanvas.setMaximumSize(new java.awt.Dimension(400, 400));
        jPCanvas.setMinimumSize(new java.awt.Dimension(400, 400));
        jPCanvas.setPreferredSize(new java.awt.Dimension(480, 480));

        javax.swing.GroupLayout jPCanvasLayout = new javax.swing.GroupLayout(jPCanvas);
        jPCanvas.setLayout(jPCanvasLayout);
        jPCanvasLayout.setHorizontalGroup(
            jPCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );
        jPCanvasLayout.setVerticalGroup(
            jPCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        jLabel2.setText("Coordenada:");

        jLabel3.setText("click:");

        coordenadaLabel.setText(" ");

        clickLabel.setText(" ");

        GameStatus.setText("Vez das peças:");

        Label_team.setText("Brancas");

        Tempo_game.setText("Tempo de partida:");

        Relogio.setText("::");

        Relogiocor.setText("Relogio Branco:");

        RelogioPlayers.setText("::");

        RelogiocorP.setText("Relogio Preto:");

        RelogioPlayers1.setText("::");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(coordenadaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clickLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(62, 62, 62)))
                        .addComponent(GameStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Label_team, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Tempo_game)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Relogio)
                                .addGap(18, 18, 18)
                                .addComponent(Relogiocor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RelogioPlayers)
                                .addGap(18, 18, 18)
                                .addComponent(RelogiocorP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RelogioPlayers1)))
                        .addContainerGap(23, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Relogiocor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Tempo_game)
                                .addComponent(Relogio)
                                .addComponent(RelogioPlayers)))
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RelogioPlayers1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RelogiocorP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_team, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(coordenadaLabel)
                        .addComponent(GameStatus)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(clickLabel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GameStatus;
    private javax.swing.JLabel Label_team;
    private javax.swing.JLabel Relogio;
    private javax.swing.JLabel RelogioPlayers;
    private javax.swing.JLabel RelogioPlayers1;
    private javax.swing.JLabel Relogiocor;
    private javax.swing.JLabel RelogiocorP;
    private javax.swing.JLabel Tempo_game;
    private javax.swing.JLabel clickLabel;
    private javax.swing.JLabel coordenadaLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPCanvas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        drawMouseQuadrante((Graphics2D) arg);
        this.atualiza_relogios();
        this.repaint();
    }

    /*@Override
    public void actionPerformed(ActionEvent ae) {
        //System.out.println("Batatatatatatat");
        this.getRelogio().setText("00:" + clock.getMins() + ":" + clock.getSecs());
    }*/
}

