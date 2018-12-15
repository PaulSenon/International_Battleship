package view;

import tools.ImageManager;
import tools.ResultShoot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class Explosion extends JLabel implements ActionListener {
    private final BufferedImage[] explosionArray;
    private final BufferedImage[] exploMissArray;
    private final Timer animator;
    private boolean missed;
    private boolean touched;
    private int delay = 10;
    private int currentFrame = 0;

    /**
     * Constructor of animation
     * @param result
     */
    public Explosion(ResultShoot result) {
        super();
        if (result.equals(ResultShoot.MISSED)){
            this.missed = true;
        }else{
            this.touched = true;
        }

        this.explosionArray = ImageManager.getExplosionArray();
        this.exploMissArray = ImageManager.getExplomissArray();
        animator = new Timer(this.delay, this);
        animator.start();
        //this.repaint();
    }

    /**
     * Method called AUTOMATICALLY on repaint() on this or parent UI components
     * @param g is the graphic component used to custom draw
     */
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //Display explosion when touched
        if (this.touched == true) {
            if (currentFrame >= explosionArray.length -1) {
                this.touched = false;
                currentFrame = 0;
            }
            g.drawImage(this.explosionArray[currentFrame], 0, 0, getWidth(), getHeight(), this);
            currentFrame++;
        }

        //Display explosion when missed
        if (this.missed == true) {
            if (currentFrame >= exploMissArray.length - 1) {
                this.missed = false;
                currentFrame = 0;
            }
            g.drawImage(this.exploMissArray[currentFrame], 0, 0, getWidth(), getHeight(), this);
            currentFrame++;
        }
    }


    /**
     * Repaint the animation
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
