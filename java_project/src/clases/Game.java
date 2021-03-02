/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import ventanas.Arkaniod_View;

/**
 *
 * @author Daniel Abedoy
 */
public class Game {

    //Timer
    private Timer runGame;
    private Arkaniod_View view;
    private Arduino arduino;

    public Game(Arkaniod_View view, Arduino arduino) {
        this.view = view;
        this.arduino = arduino;

        exeGame();

    }

    //Timer del Juego
    public void exeGame() {

        this.runGame = new Timer(10, (ae -> {
            if (view.isInicio()) {
                view.getPelota().moverPelota();
                view.getPelota().rebotar("");
                view.rebotePaleta();
                view.eliminarTabla();
            }
        }));

        this.runGame.start();
    }

    public void eventos() {

        if (arduino == null) {
            //EVENTOS DE TECLADO
            this.view.getVentana().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent ke) {
                }

                @Override
                public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                        view.getPaleta().moverPaleta("i",0);
                        if (!view.isInicio()) {
                            view.getPelota().setPosX(view.getPaleta().getPosX());
                        }
                    } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                        view.getPaleta().moverPaleta("d",0);
                        if (!view.isInicio()) {
                            view.getPelota().setPosX(view.getPaleta().getPosX());
                        }
                    }

                    if (ke.getKeyCode() == KeyEvent.VK_P) {
                        view.setInicio(!view.isInicio());
                    }

                }

                @Override
                public void keyReleased(KeyEvent ke) {
                }
            }
            );

        } else {
            if (!this.arduino.conectar(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent spe) {
                    try {
                        String comando = arduino.getArduino().printMessage();

                        if (comando.equals("l")) {
                            view.getPaleta().moverPaleta("i",-2);
                            if (!view.isInicio()) {
                                view.getPelota().setPosX(view.getPaleta().getPosX());
                            }
                        } else if (comando.equals("r")) {
                            view.getPaleta().moverPaleta("d",2);
                            if (!view.isInicio()) {
                                view.getPelota().setPosX(view.getPaleta().getPosX());
                            }
                        }

                        if (comando.equals("d") || comando.equals("u")) {
                            view.setInicio(!view.isInicio());
                        }

                    } catch (Exception e) {
                    }
                }
            })) {
                this.arduino = null;
                JOptionPane.showMessageDialog(null, "Algo salio mal, teclado activado");
                eventos();
            }
        }

    }

}
