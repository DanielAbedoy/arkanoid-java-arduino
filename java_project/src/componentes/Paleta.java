/**
 * @author Daniel Abedoy
 */
package componentes;
import javax.swing.*;


public class Paleta {
    
    JLabel paleta;
    ImageIcon imgPaleta = new ImageIcon(getClass().getResource("/imagenes/paleta.png"));
    private int posX,posY;
    private int velocidad;
    
    
    public Paleta(int x, int y, int v,JPanel ventana){
        this.posX = x;
        this.posY = y;
        this.velocidad = v;
        
        paleta = new JLabel(imgPaleta);
        paleta.setBounds(x, y, 80, 10);
        paleta.setVisible(true);
        ventana.add(paleta);
    }
    
    
    public void moverPaleta(String dir, int vel){
        if(vel != 0){
            if(this.posX+vel >=600)return;
            this.posX += vel;
        }else{
        
        if (dir.equals("d")) {
            if(this.posX+80 >=600)return;
            this.posX += this.velocidad;
        }else if (dir.equals("i")) {
            if(this.posX <=0)return;
            this.posX -= this.velocidad;
        }
        }
        paleta.setBounds(this.posX, this.posY, 80, 10);   
    }
    
    
    
    //Metodos GET Y SET

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
    
    public int getVelocidad() {
        return this.velocidad;
    }

    public void setVelocidad(int v) {
        this.velocidad = v;
    }
    
    
}
