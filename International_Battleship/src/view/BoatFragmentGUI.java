package view;

import model.BoatType;
import tools.Coord;
import tools.Direction;
import tools.ImageFilter;
import tools.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BoatFragmentGUI extends JLabel{
	private static final long serialVersionUID = 1L;

	// the coordinate associated with this boatFragment
	private Coord coord;

	// TODO not used yet.
	private Color color;

	private String imageName;
	private String imageSelectedName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	// the base image copy to play with
	private BufferedImage image;
	private BufferedImage imageSelected;

	// TODO not used yet.
	// the facing direction of the sprite image
	private Direction direction;

	private int boatId;

	private boolean broken;

	private int index;

	private boolean fragmentVisible;

	private boolean isSelected;

	/**
	 * __CONSTRUCTOR__
	 * @param coord is the coord associated to this boatFragment
	 */
	public BoatFragmentGUI(int boatId, Coord coord, BoatType name, int imageIndex) {
			super();

			// set attributes
			this.coord = coord;
			this.color = Color.RED;
			this.direction = Direction.DEFAULT();
			this.boatId = boatId;
			this.broken = false;
			this.index = imageIndex;
			this.imageName = "";
			this.fragmentVisible = false;

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
					switch (imageIndex) {
						case 0:
							this.imageName = "Aircraft/0.png";
							break;
						case 1:
							this.imageName = "Aircraft/1.png";
							break;
						case 2:
							this.imageName = "Aircraft/2.png";
							break;
						case 3:
							this.imageName = "Aircraft/3.png";
							break;
						case 4:
							this.imageName = "Aircraft/4.png";
							break;
					}
					break;
				case Cruiser:
					switch (imageIndex) {
						case 0:
							this.imageName = "Cruiser/0.png";
							break;
						case 1:
							this.imageName = "Cruiser/1.png";
							break;
						case 2:
							this.imageName = "Cruiser/2.png";
							break;
						case 3:
							this.imageName = "Cruiser/3.png";
							break;
					}
					break;
				case Submarine:
					switch (imageIndex) {
						case 0:
							this.imageName = "Submarine/0.png";
							break;
						case 1:
							this.imageName = "Submarine/1.png";
							break;
						case 2:
							this.imageName = "Submarine/2.png";
							break;
					}
					break;
				case TorpedoBoat:
					switch (imageIndex) {
						case 0:
							this.imageName = "Torpedo/0.png";
							break;
						case 1:
							this.imageName = "Torpedo/1.png";
							break;
					}
					break;
				case Sentinel:
					switch (imageIndex) {
						case 0:
							this.imageName = "Sentinel/0.png";
							break;
					}
					break;
				default:
					System.out.println("Wrong boat type");
					break;
			}
			this.image = ImageManager.getImageCopy(this.imageName);
			this.repaint();
	}

	public void setSelected(){
		this.isSelected=true;
		this.repaint();
	}

    public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}


	public void setNotSelected(){
		this.isSelected=false;
		this.repaint();
	}

    /**
     * PUBLIC : It rotate its displayed image in the wanted direction
     * @param direction is the direction where you want the ship to look toward
     */
    void rotate(Direction direction) {
    	// optimization avoiding new image loading
    	if(this.direction.equals(direction)){
    		return;
		}
    	this.direction = direction;
		try {
			this.image = ImageManager.getImageCopyRotated(this.imageName, direction.rotation);
			this.imageSelected = ImageManager.getImageCopyRotated(this.imageSelectedName, direction.rotation);
		} catch (IOException e) {
			System.out.println("ERROR BoatFragmentGUI : loading rotated copy image failed");
		}
	}



	/**
	 * Method called AUTOMATICALLY on repaint() on this or parent UI components
	 * @param g is the graphic component used to custom draw
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if (!this.isFragmentVisible()) return;

        // draw its sprite image on the whole plane
		if(isSelected) {
			//Appel de la méthode edgingColoration et draw l'image colorée
			this.image = ImageFilter.edgingColoration(this.image);
			g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);
		}
		else {
			this.image = ImageManager.getImageCopy(this.imageName);
			g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);
		}

        // display something to show that the fragment is broken
		if(this.broken){
			g.setColor(this.color);
			BufferedImage explosion = ImageManager.getImageCopy("explosion.png");
			g.drawImage(explosion, 0, 0, getWidth(), getHeight(), this);
		}
    }

    private boolean isFragmentVisible() {
		return this.fragmentVisible;
	}

	public Coord getCoord() {
    	return this.coord;
    }

	public void setCoord(Coord coord){
		this.coord = coord;
	}

    @Override
    public String toString(){
        String s = "X : " + this.getCoord().getX() + " Y : "+ this.getCoord().getY() + " boatId : " + this.getBoatId();
        return s;
    }

	public int getBoatId() {
		return boatId;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}

	public int getIndex() {
		return index;
	}

	public void setFragmentVisible(boolean fragmentVisible) {
		this.fragmentVisible = fragmentVisible;
	}
}
