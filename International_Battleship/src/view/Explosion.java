package view;

import tools.FxType;
import tools.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class Explosion extends JLabel implements ActionListener {
    private final BufferedImage[] imageArray;
    private final Timer animator;
    private int delay = 50;
    private int currentFrame = 0;
    private FxType type;

    /**
     * Constructor of animation
     * @param fxType
     */
    public Explosion(FxType fxType) {
        super();
        this.type = fxType;

        switch (fxType){
            case EXPLOSION:
                this.imageArray = ImageManager.getExplosionArray();
                break;
            case SPLASH:
                this.imageArray = ImageManager.getExplomissArray();
                break;
            default:
                System.err.println("Requested FxType:"+fxType.name()+" not supported yet");
                this.imageArray = null;
        }

        animator = new Timer(this.delay, this);
        animator.start();
    }

    /**
     * Method called AUTOMATICALLY on repaint() on this or parent UI components
     * @param g is the graphic component used to custom draw
     */
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if (currentFrame < this.imageArray.length ) {
            g.drawImage(this.imageArray[currentFrame], 0, 0, getWidth(), getHeight(), this);
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
