/**
 *
 * @author Daniel Abedoy
 */
package ventanas;

import componentes.Tabla;
import componentes.Paleta;
import componentes.Pelota;
import java.awt.Color;
import javax.swing.*;
import clases.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Arkaniod_View extends JFrame {

    JFrame ventana;
    JPanel panel;

    //Paleta
    Paleta paleta;

    //Pelota
    Pelota pelota;
    boolean inicio;
    
    Arduino arduino;
    
    
    //Game 
    private Game game;

    public Paleta getPaleta() {
        return paleta;
    }

    public void setPaleta(Paleta paleta) {
        this.paleta = paleta;
    }

    //Tablas
    ArrayList<Tabla> tablas = new ArrayList<Tabla>();

    public Arkaniod_View(Arduino arduino) {
        ventana = new JFrame("ARKANIOD");
        ventana.setBounds(0, 0, 620, 440);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
        ventana.getContentPane().setBackground(new Color(55, 55, 55));
        ventana.setVisible(true);

        panel = new JPanel();
        panel.setBounds(5, 5, 600, 400);
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setBackground(new Color(0, 0, 0));
        ventana.add(panel);       
        

        paleta = new Paleta((panel.getWidth()/2)-40, 360, 10, panel);

        pelota = new Pelota(paleta.getPosX()+30, 350, -2, panel);
        this.inicio = false;

        this.arduino = arduino;
        generarTablas();
        
        this.game = new Game(this, arduino);
        this.game.eventos();      

        panel.update(panel.getGraphics());

    }

    public JFrame getVentana() {
        return ventana;
    }

    public Pelota getPelota() {
        return pelota;
    }

    public void setPelota(Pelota pelota) {
        this.pelota = pelota;
    }

    public boolean isInicio() {
        return inicio;
    }

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    

    //EVENTOS DEL JUEGO
    public void rebotePaleta() {

        int paletaX = paleta.getPosX(), paletaY = paleta.getPosY();
        int pelotaX = pelota.getPosX(), pelotaY = pelota.getPosY() + 10;

        if (pelotaY == paletaY && pelotaX >= paletaX - 38 && pelotaX <= paletaX + 44) {
            pelota.rebotar("y");
        }

    }

    public void generarTablas() {
        for (int i = 0; i < 7; i++) {//columnas
            for (int j = 0; j < 3; j++) {//renglones
                tablas.add(new Tabla((i * 80) + 25, (j * 30) + 80, 1, this.panel));
            }
        }
    }

    public void eliminarTabla() {
        int pelotaX = pelota.getPosX(), pelotaY = pelota.getPosY();

        for (int i = 0; i < tablas.size(); i++) {
            int tablaX = tablas.get(i).getPosX(), tablaY = tablas.get(i).getPosY();

            if (pelotaY >= tablaY-10 && pelotaY <= tablaY+20 && pelotaX >= tablaX - 38 && pelotaX <= tablaX + 25) {
                pelota.rebotar("y");
                tablas.get(i).setVida(tablas.get(i).getVida()-1);
                tablas.get(i).vida();
                tablas.remove(i);
            }
        }
    }

}
