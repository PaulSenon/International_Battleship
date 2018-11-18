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
import javax.swing.JLabel;

import tools.Coord;
import tools.Direction;

public class BattleShipBoatFragmentGUI extends JLabel{
	private static final long serialVersionUID = 1L;

	// the coordinate associated with this boatFragment
	private Coord coord;

	// TODO not used yet.
	private Color color;

	// TODO moving this in a ImageManager may be cool
	// the base sprite image (for reference) => never modify it
	private BufferedImage baseImage;

	// the base image copy to play with
	private BufferedImage image;

	// TODO not used yet.
	// the facing direction of the sprite image
	private Direction direction;

	/**
	 * __CONSTRUCTOR__
	 * @param coord is the coord associated to this boatFragment
	 */
	public BattleShipBoatFragmentGUI(Coord coord) {
    	super();

    	// set attributes
    	this.coord = coord;
    	this.color = Color.BLACK;
    	this.direction = Direction.DEFAULT();


    	try {
    		// TODO REFACTORING OPTIMIZATION MEMORY USE (will divide by almost 2 boat fragment image mem consumption):
			// TODO 	=> instantiate the base image in an "ImageManager" or something common.
			// load the image from file
			this.baseImage = ImageIO.read(new File("src/resources/Sans-titre-1_02.png"));

			// copy the base image to allow post processing avoiding destroying source image 
			this.image = BattleShipBoatFragmentGUI.deepCopy(this.baseImage);
    	}
		catch(IOException exc) {
			System.out.println("[BoatFragmentGUI.constructor] Error loading image (maybe wrong path)");
		}
    	
    	// TODO : it's just placeholder yet
		this.setBackground(Color.RED);
    	
    }
    
    // TODO move into some images Tools class
    /**
     * Just copy a image to another, not just the reference
     * @param bufferedImage is the image to copy byte by byte
     * @return BufferedImage is a total new ref, but look the same as input image
     */
    static BufferedImage deepCopy(BufferedImage bufferedImage) {
    	 ColorModel cm = bufferedImage.getColorModel();
    	 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
    	 WritableRaster raster = bufferedImage.copyData(null);
    	 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
    
    /**
     * PUBLIC : It rotate its displayed image in the wanted direction
     * @param direction is the direction where you want the ship to look toward
     */
    void rotate(Direction direction) {
    	this.direction = direction;
    	this.image = this.rotateImage(this.baseImage, direction.rotation);
    }

    /**
     * It rotate an image to a desired angle (in degree)
     * @param sourceImage is the image you want to rotate
     * @param angleDegree is the rotation angle in degree
     * @return resultImage
     */
    private BufferedImage rotateImage(BufferedImage sourceImage, double angleDegree) {
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = destImage.createGraphics();

        AffineTransform transform = new AffineTransform();
        transform.rotate(angleDegree / 180 * Math.PI, width / 2 , height / 2);
        g2d.drawRenderedImage(sourceImage, transform);

        g2d.dispose();
        return destImage;
    }

	/**
	 * Method called AUTOMATICALLY on repaint() on this or parent UI components
	 * @param g is the graphic component used to custom draw
	 */
	protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        // draw its sprite image on the whole plane
        g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);
        
//        //_OLD_ placeholder to activate for debug purpose if you do not have images
//        g.setColor(this.color);
//
//        // draw cross
//        g.drawLine(0, 0,
//        		this.getSize().width,
//        		this.getSize().height);
//        g.drawLine(
//        		this.getSize().width, 0,
//        		0, this.getSize().height);
    }

    public Coord getCoord() {
    	return this.coord;
    }
	
}
