/**
 *
 * @author Daniel Abedoy
 */
package componentes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Pelota {
    JLabel pelota;
    ImageIcon imgPaleta = new ImageIcon(getClass().getResource("/imagenes/pelota.png"));
    private int posX,posY;
    private int velocidadX,velocidadY;
    
    
    public Pelota(int x,int y, int v, JPanel ventana){
        this.posX = x;
        this.posY = y;
        this.velocidadX = v;
        this.velocidadY = v;
        
        pelota = new JLabel(imgPaleta);
        pelota.setBounds(x, y, 10, 10);
        pelota.setVisible(true);
        ventana.add(pelota);
    }
    
    public void rebotar(String dir){
        if (this.posX <= -35 || this.posX+10 >= 565|| dir.equals("x")){ 
            this.velocidadX *=- 1;
        }
        if (this.posY <= 0 || this.posY+10 >= 400 || dir.equals("y")) {
            this.velocidadY *= - 1;   
        }        
    }
    
    public void moverPelota(){
        this.posX += this.velocidadX;
        this.posY = this.posY + this.velocidadY;
        pelota.setBounds(posX, posY, 80, 10);
    }
    
    
    //Metodos GET Y SET

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
        pelota.setBounds(posX, posY, 80, 10);
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public int getVelocidadX() {
        return this.velocidadX;
    }

    public void setVelocidad(int v) {
        this.velocidadX = v;
    }
    
    
}
