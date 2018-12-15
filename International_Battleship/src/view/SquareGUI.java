package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import tools.Coord;

@objid ("fbbe929d-a036-4054-8d2d-de1a1b3bc7c1")
public class SquareGUI extends JPanel{
	private static final long serialVersionUID = 1L;

    private Coord coord;
	private Color color;
    // the base image copy to play with
    public BufferedImage image;

    @objid ("ca37a9e3-b4c7-4d33-9b0a-529b33df060e")
    public SquareGUI(Coord coord) {
    	super();
    	this.coord = coord;

    	// TODO //
	    	this.color = Color.BLACK;
	    	this.setBackground(Color.ORANGE);
    	//////////

    	this.setLayout(new BorderLayout());
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);

        // draw an outline rectangle
        g.setColor(this.color);
        g.drawRect(0, 0,
                this.getParent().getSize().width,
                this.getParent().getSize().height);
    }

    public void changeBackground(Color color){
        this.setBackground(color);
    }


    @objid ("011f52f4-99dd-4985-8a89-7dcb60504ac2")
    public void getWhatSquareIs() {
    }

    @objid ("3403984d-2403-48e8-8b2d-4ef1c5729b33")
    public Coord getCoord() {
    	return this.coord;
    }
}
