/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Daniel Abedoy
 */
public class Tabla {
    
    JLabel tabla;
    ImageIcon imgTabla;
    private int vida;
    private int posX,posY;
    
    public Tabla(int x,int y,int vida,JPanel ventana){
        this.posX = x;
        this.posY = y;
        this.vida = vida;
        
        imgTabla = new ImageIcon(getClass().getResource("/imagenes/tablaN"+ 1 +".png"));
        tabla = new JLabel(imgTabla);
        tabla.setBounds(x,y,60,20);
        tabla.setVisible(true);
        ventana.add(tabla);
        
    }
    
    
    public void vida(){
        if (this.vida == 0) {
            this.tabla.setVisible(false);
        }
    }
    
    
    //Metodos GET Y SET
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
     public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
}
