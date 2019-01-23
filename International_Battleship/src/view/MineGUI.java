package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;

import tools.Coord;
import tools.Direction;
import tools.ImageFilter;
import tools.ImageManager;

public class MineGUI extends JLabel {
	private static final long serialVersionUID = 2L;
	private int idMine;
	private Coord coord;
	private String imageName;
	private BufferedImage image;
	private boolean mineVisible;
	private boolean broken;
	
	public MineGUI(int idMine, Coord coord){
		this.idMine = idMine;
		this.coord = coord;
		this.imageName = "mine.png";
		this.setMineVisible(false);
		this.broken = false;
		this.image = ImageManager.getImageCopy(this.imageName);
		this.repaint();
	}
	
	/**
	 * Method called AUTOMATICALLY on repaint() on this or parent UI components
	 * @param g is the graphic component used to custom draw
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if (!this.isMineVisible()) return;

        // draw its sprite image on the whole plane
		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);
    }
	


	/**
	 * @return the mineVisible
	 */
	public boolean isMineVisible() {
		return mineVisible;
	}

	/**
	 * @param mineVisible the mineVisible to set
	 */
	public void setMineVisible(boolean mineVisible) {
		this.mineVisible = mineVisible;
	}
	
	
	public boolean isBroken() {
		return broken;
	}



	public void setBroken(boolean broken) {
		this.broken = broken;
	}
}
