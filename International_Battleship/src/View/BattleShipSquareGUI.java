package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Model.Coord;

@objid ("fbbe929d-a036-4054-8d2d-de1a1b3bc7c1")
public class BattleShipSquareGUI extends JPanel {
	private static final long serialVersionUID = 1L;

	private Coord coord;
	private Color color;
	
    @objid ("ca37a9e3-b4c7-4d33-9b0a-529b33df060e")
    public BattleShipSquareGUI(Coord coord) {
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
        
        // draw an outline rectangle
        g.setColor(this.color);
        g.drawRect(0, 0, 
                this.getParent().getSize().width, 
                this.getParent().getSize().height);
        
    }


    @objid ("011f52f4-99dd-4985-8a89-7dcb60504ac2")
    public void getWhatSquareIs() {
    }

    @objid ("3403984d-2403-48e8-8b2d-4ef1c5729b33")
    public Coord getCoord() {
    	return this.coord;
    }

}
