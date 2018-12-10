package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;



import tools.Coord;

public class SquareGUI extends JPanel {
	private static final long serialVersionUID = 1L;

    private Coord coord;
	private Color color;
    // the base image copy to play with
    public BufferedImage image;

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


        public void getWhatSquareIs() {
    }

        public Coord getCoord() {
    	return this.coord;
    }

}
