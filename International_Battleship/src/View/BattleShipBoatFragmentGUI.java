package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import Model.Coord;

public class BattleShipBoatFragmentGUI extends JLabel{
	private static final long serialVersionUID = 1L;
	
	private Coord coord;
	private Color color; // TODO currently useless (but maybe use to color something to show team color)
	private BufferedImage baseImage;
	private BufferedImage image;
	private Direction direction; // TODO may change
	
	// TODO externalize it
	/**
	 * An enum for the sprite (this image fragment) direction.
	 * !!!! IT ASSUME THAT YOUR SPRITE IS FACING EAST BY DEFAULT !!!!
	 */
	public enum Direction{
			NORTH(-90),
			EAST(0),
			SOUTH(90),
			WEST(180);
			
			public int rotation;

		Direction(int rotation) { 
			this.rotation = rotation;
		} 
	}
	
    public BattleShipBoatFragmentGUI(Coord coord) {
    	super();
    	this.coord = coord;
    	this.color = Color.BLACK; 
    	
    	// default direction of base image must be EAST
    	this.direction = Direction.EAST;

    	try {
    		// TODO instantiate the base image in an "BoatFramgmentImageManager" or something common.
			this.baseImage = ImageIO.read(new File("International_Battleship/src/resources/Sans-titre-1_02.png"));

			// copy the base image to allow post processing avoiding destroying source image 
			this.image = BattleShipBoatFragmentGUI.deepCopy(this.baseImage);
    	}
		catch(IOException exc) {
			System.out.println("[BoatFragmentGUI] Error loading image (maybe wrong path)");
		}
    	
    	// TODO 
    		this.setBackground(Color.RED);
    	
    }
    
    // TODO move into some images Tools class
    /**
     * Just copy a image to another, not just the reference (like a clone)
     * @param BufferedImage
     * @return BufferedImage
     */
    static BufferedImage deepCopy(BufferedImage bi) {
    	 ColorModel cm = bi.getColorModel();
    	 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
    	 WritableRaster raster = bi.copyData(null);
    	 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
    
    /**
     * PUBLIC : It rotate its displayed image in the wanted direction
     * @param direction
     */
    public void rotate(Direction direction) {  
    	this.direction = direction;
    	this.image = this.rotateImage(this.baseImage, direction.rotation);
    }

    /**
     * It rotate an image to a desired angle (in degree)
     * @param sourceImage
     * @param angle
     * @return resultImage
     */
    private BufferedImage rotateImage(BufferedImage sourceImage, double angle) {
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = destImage.createGraphics();

        AffineTransform transform = new AffineTransform();
        transform.rotate(angle / 180 * Math.PI, width / 2 , height / 2);
        g2d.drawRenderedImage(sourceImage, transform);

        g2d.dispose();
        return destImage;
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        // draw its sprite
        g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);
        
        /* _OLD_ placeholder
        g.setColor(this.color);
        
        // draw cross
        g.drawLine(0, 0, 
        		this.getSize().width, 
        		this.getSize().height);
        g.drawLine(
        		this.getSize().width, 0,
        		0, this.getSize().height);
		*/
    }

    public Coord getCoord() {
    	return this.coord;
    }
	
}
