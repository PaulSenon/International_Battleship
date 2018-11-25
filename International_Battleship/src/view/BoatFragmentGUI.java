package view;

import model.BoatName;
import tools.Coord;
import tools.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class BoatFragmentGUI extends JLabel{
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
	public BoatFragmentGUI(Coord coord, BoatName name, int imageIndex) throws IOException {
			super();

			// set attributes
			this.coord = coord;
			this.color = Color.BLACK;
			this.direction = Direction.DEFAULT();

			/*
			try {
				// TODO REFACTORING OPTIMIZATION MEMORY USE (will divide by almost 2 boat fragment image mem consumption):
				// TODO 	=> instantiate the base image in an "ImageManager" or something common.
				// load the image from file
				this.baseImage = ImageIO.read(new File("resources/Sans-titre-1_02.png"));

				// copy the base image to allow post processing avoiding destroying source image
				this.image = BoatFragmentGUI.deepCopy(this.baseImage);
			}
			catch(IOException exc) {
				System.out.println("[BoatFragmentGUI.constructor] Error loading image (maybe wrong path)");
			}

			// TODO : it's just placeholder yet
			this.setBackground(Color.RED);
			*/

			switch (name) {
				case AircraftCarrier:
					try {
						if(imageIndex==0)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Aircraft\\0.png"));
						if(imageIndex==1)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Aircraft\\1.png"));
						if(imageIndex==2)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Aircraft\\2.png"));
						if(imageIndex==3)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Aircraft\\3.png"));
						if(imageIndex==4)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Aircraft\\4.png"));
					}catch(IOException exc){
						System.out.println("[BoatFragmentGUI.constructor] Error loading image (maybe wrong path)");
					}
					break;
				case Cruiser:
					try {
						if(imageIndex==0)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Cruiser\\0.png"));
						if(imageIndex==1)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Cruiser\\1.png"));
						if(imageIndex==2)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Cruiser\\2.png"));
						if(imageIndex==3)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Cruiser\\3.png"));
					}catch(IOException exc){
						System.out.println("[BoatFragmentGUI.constructor] Error loading image (maybe wrong path)");
					}
					break;
				case Submarin:
					try {
						if(imageIndex==0)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Submarin\\0.png"));
						if(imageIndex==1)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Submarin\\1.png"));
						if(imageIndex==2)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Submarin\\2.png"));
					}catch(IOException exc){
						System.out.println("Submarin[BoatFragmentGUI.constructor] Error loading image (maybe wrong path)");
					}
					break;
				case TorpedoBoat:
					try {
						if(imageIndex==0)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Torpedo\\0.png"));
						if(imageIndex==1)
							this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Torpedo\\1.png"));
					}catch(IOException exc){
						System.out.println("Torpedo[BoatFragmentGUI.constructor] Error loading image (maybe wrong path)");
					}
					break;
				case Sentinel:
					try {
						this.baseImage = ImageIO.read(new File("International_Battleship\\resources\\Sentinel\\0.png"));
					}catch(IOException exc){
						System.out.println("Sentinel [BoatFragmentGUI.constructor] Error loading image (maybe wrong path)");
					}
					break;
				default:
					break;
			}
        this.rotate(direction);
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
	public void setCoord(Coord coord){
		this.coord = coord;
	}

}
