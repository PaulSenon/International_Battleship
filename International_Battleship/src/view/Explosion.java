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
	private int delay = 50;
	private int currentFrame;

	/**
	 * Constructor of animation
	 * @param result
	 */
	public Explosion(ResultShoot result) {
		super();
		this.currentFrame = 0;
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
			if (this.currentFrame < this.explosionArray.length - 1){
				g.drawImage(this.explosionArray[this.currentFrame], 0, 0, getWidth(), getHeight(), this);
				this.currentFrame ++;
			} else {
				this.touched = false;
			}
		}

		//Display explosion when missed
		if (this.missed == true) {
			if (this.currentFrame < this.exploMissArray.length - 1){
				g.drawImage(this.exploMissArray[this.currentFrame], 0, 0, getWidth(), getHeight(), this);
				this.currentFrame ++;
			} else {
				this.touched = false;
			}
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
