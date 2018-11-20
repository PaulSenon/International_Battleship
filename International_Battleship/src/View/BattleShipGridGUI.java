package View;

import java.awt.Point;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


import com.modeliosoft.modelio.javadesigner.annotations.objid;

import tools.Coord;
import tools.Direction;
import tools.BattleShipGameConfig;
import tools.ResultShoot;

@objid ("59f3e563-cd95-4a58-982c-35a753e56132")

public class BattleShipGridGUI extends JLayeredPane {
	private static final long serialVersionUID = 1L;

	// Map of Coord <=> Square
	private HashMap<Coord, BattleShipSquareGUI> squares;

	// TODO not used yet, may need refactoring
    // Map of Coord <=> BoatFragment
	private HashMap<Coord, BattleShipBoatFragmentGUI> boatFragments;

	// TODO not used yet
	private BattleShipSquareGUI selectedSquare;

    // TODO may need some complex stuff, because we have to manipulate multiple boatFragment across multiple SquareGUI instances
	private BattleShipBoatGUI selectedBoat;

    /**
     * __CONSTRUCTOR__
     */
    @objid ("3cceb1f5-4f21-4971-a3ba-024ed2eabd4e")
	public BattleShipGridGUI() {
		super();

		// init class attributes
		this.squares = new HashMap<Coord, BattleShipSquareGUI>();
		this.boatFragments = new HashMap<Coord, BattleShipBoatFragmentGUI>();
		this.selectedSquare = null;
		this.selectedBoat = null;

		// init display
		this.setLayout(new CustomGridLayoutManager());
		this.generateBoard();
		this.repaint();
	}

    /**
     * It generate the gameBoard with squares
     */
    private void generateBoard(){
        // Generates squares of the game board
        for (int i = 0; i < BattleShipGameConfig.getGameGridHeight(); i++) { // y l
            for (int j = 0; j < BattleShipGameConfig.getGameGridWidth(); j++) { // x c
            	JPanel tmpSquare = createSquare(new Coord(j,i));
            	this.add(tmpSquare, new Point(j,i));
            }
        }

        // FOR TMP DEBUG PURPOSE : adding two boat fragments on the board
        	Coord coord = new Coord(2,1);
	        JLabel tmp = createBoatFragments(coord);
	        this.squares.get(coord).add(tmp);
	        // demo rotation :
	        ((BattleShipBoatFragmentGUI) tmp).rotate(Direction.SOUTH);

	        coord = new Coord(2,2);
	        tmp = createBoatFragments(coord);
	        this.squares.get(coord).add(tmp);
	        // demo rotation :
	        ((BattleShipBoatFragmentGUI) tmp).rotate(Direction.WEST);
        // END DEBUG
    }

    /**
     * PUBLIC It take some mouse click position and find the targeted square
     * @param  xEvent (int) x coordinate of the mouse pointer (from click event)
     * @param yEvent (int) y coordinate of the mouse pointer (from click event)
	 * @return the selected squareGUI (not necessary to use it, it's just to avoid
	 * a call to get selected square in case you need it)
     */
    BattleShipSquareGUI selectSquare(int xEvent, int yEvent) {
    	this.selectedSquare = findSquareFromEvent(xEvent, yEvent);
    	if(this.selectedSquare != null) {
    		System.out.println("You've clicked on : "+this.selectedSquare.getCoord().toString());
    	}
    	return this.selectedSquare;
    }

    /**
     * It create a square at coord and add it to squares Map
     * @param coord is the coordinate where to create the squareGUI
     * @return JPanel is the created SquareGUI
     */
    private JPanel createSquare(Coord coord){
    	BattleShipSquareGUI square = new BattleShipSquareGUI(coord);
        this.squares.put(coord, square);
        return square;
    }

    /**
     * It create a boatFragment at coord and add it to boatFragments Map
     * @param coord is the coordinate of the SquareGUI where to create the boatFragmentGUI
     * @return JLabel is the created boatFragmentGUI
     */
    private JLabel createBoatFragments(Coord coord){
    	BattleShipBoatFragmentGUI fragment = new BattleShipBoatFragmentGUI(coord);
    	this.boatFragments.put(coord, fragment);
    	return fragment;
    }

	/**
	 * Retrieve the squareGUI associated with this coord (from HashMap)
	 * @param coord is the coord of a squareGUI
	 * @return SquareGUI
	 */
	private BattleShipSquareGUI findSquare(Coord coord) {
    	return squares.get(coord);
    }

	public void messageToUser(ResultShoot shoot) {
		String resultOfShoot = shoot.toString();
		JOptionPane.showMessageDialog(null, resultOfShoot, null , JOptionPane.INFORMATION_MESSAGE);
	}

    /**
     * It take some mouse click position and find the targeted square
     * @param  xEvent (int) x coordinate of the mouse pointer (from click event)
     * @param yEvent (int) y coordinate of the mouse pointer (from click event)
     * @return BattleShipSquareGUI | null => the SquareGUI at mouse pointer position
     */
    private BattleShipSquareGUI findSquareFromEvent(int xEvent, int yEvent) {
    	try {
    		// try to get squareGUI at position
            // we get the raw component and try to cast it as SquareGUI
    		return (BattleShipSquareGUI) this.getComponentAt(xEvent, yEvent);
    	}catch(ClassCastException e) {
    		// if it cannot be casted as a SquareGUI, we're not interested in.
    		return null;
    	}

    	// @Paul : OLD STUFF, BUT KEEP THIS OR ASK ME BEFORE DELETING
        // => to generate Square coord from click coord (dosn't work with our custom grid layout)
    	/*int min = Math.min(this.getWidth(), this.getHeight());
    	int diff = this.getWidth() - this.getHeight();
    	Coord coord;
    	if(diff>0) {
    		 coord = new Coord(
                    (x-diff/2)/(min/BattleShipGameConfig.getGameGridWidth()),
                    (y)/(min/BattleShipGameConfig.getGameGridHeight())
            );
    	} else {
    		coord = new Coord(
                    (x)/(min/BattleShipGameConfig.getGameGridWidth()),
                    (y+diff/2)/(min/BattleShipGameConfig.getGameGridHeight())
            );
    	}

    	try{
    		return this.findSquare(coord);
    	}catch(NullPointerException e) {
    		return null;
    	}*/
    }


//	@objid ("36830e37-3f6b-4a49-9771-eecf425dec5c")
//    public BattleShipBoatGUI battleShipBoatGUI;
//
//    @objid ("e78df3ec-ed3c-4ff8-a842-2ff369cb31a6")
//    public BattleShipSquareGUI battleShipSquareGUI;
//
//    @objid ("ed7393e2-7af8-47c1-8c1f-5f74b55dec18")
//    public void setBoatToMove() {
//    }
//
//    @objid ("38933c80-466d-4813-922d-b72b050a2270")
//    public void setVisibility() {
//    }
//
//    @objid ("2522fe29-617f-406f-9437-7668af3a46ed")
//    public void movePiece() {
//    }
//
//    @objid ("201de8c0-2b1b-4bdc-91e1-2bea44d1e06b")
//    public void undoMovePiece() {
//    }
//
//    @objid ("324f210b-a38f-46f5-9460-357023fd912e")
//    public void findCoord() {
//    }
//
//    @objid ("717ace36-b5e2-4191-b136-6dee37668dc1")
//    public void setCheckerBoard() {
//    }
//
//    @objid ("7ec2238c-6895-4ae2-b0a1-378c4296fec6")
//    private void createBattleshipBoard() {
//    }


}
