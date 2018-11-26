package view;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


import com.modeliosoft.modelio.javadesigner.annotations.objid;

import model.BoatName;
import tools.*;

@objid ("59f3e563-cd95-4a58-982c-35a753e56132")

public class GridGUI extends JLayeredPane {
	private static final long serialVersionUID = 1L;

	// Map of Coord <=> Square
	private HashMap<Coord, SquareGUI> squares;

	// TODO not used yet, may need refactoring
    // Map of Coord <=> BoatFragment
	private HashMap<Coord, BoatFragmentGUI> boatFragments;

	// TODO not used yet
	private SquareGUI selectedSquare;

    // TODO may need some complex stuff, because we have to manipulate multiple boatFragment across multiple SquareGUI instances
	private BoatGUI selectedBoat;
	private List<BoatGUI> listOfBoat;

	/**
     * __CONSTRUCTOR__
     */
    @objid ("3cceb1f5-4f21-4971-a3ba-024ed2eabd4e")
	public GridGUI() {
		super();

        System.out.println("GridGUI\n");
		// init class attributes
		this.squares = new HashMap<Coord, SquareGUI>();
		this.boatFragments = new HashMap<Coord, BoatFragmentGUI>();
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
        for (int i = 0; i < GameConfig.getGameGridHeight(); i++) { // y l
            for (int j = 0; j < GameConfig.getGameGridWidth(); j++) { // x c
            	JPanel tmpSquare = createSquare(new Coord(j,i));
            	this.add(tmpSquare, new Point(j,i));
            }
        }
/*
        // FOR TMP DEBUG PURPOSE : adding two boat fragments on the board
        	Coord coord = new Coord(2,1);
	        JLabel tmp = createBoatFragments(coord);
	        this.squares.get(coord).add(tmp);
	        // demo rotation :
	        ((BoatFragmentGUI) tmp).rotate(Direction.SOUTH);

	        coord = new Coord(2,2);
	        tmp = createBoatFragments(coord);
	        this.squares.get(coord).add(tmp);
	        // demo rotation :
	        ((BoatFragmentGUI) tmp).rotate(Direction.WEST);
        // END DEBUG*/
    }

    /**
     * PUBLIC It take some mouse click position and find the targeted square
     * @param  xEvent (int) x coordinate of the mouse pointer (from click event)
     * @param yEvent (int) y coordinate of the mouse pointer (from click event)
	 * @return the selected squareGUI (not necessary to use it, it's just to avoid
	 * a call to get selected square in case you need it)
     */
    SquareGUI selectSquare(int xEvent, int yEvent) {
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
    	SquareGUI square = new SquareGUI(coord);
        this.squares.put(coord, square);
        return square;
    }

    /**
     * It create a boatFragment at coord and add it to boatFragments Map
     * @param coord is the coordinate of the SquareGUI where to create the boatFragmentGUI
     * @return JLabel is the created boatFragmentGUI
     */
    private JLabel createBoatFragments(Coord coord){
		BoatFragmentGUI fragment = null;
		try {
			fragment = new BoatFragmentGUI(coord, BoatName.AircraftCarrier, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.boatFragments.put(coord, fragment);
    	return fragment;
    }

    /**
     * It create a boatFragment at coord and add it to boatFragments Map
     * @param coord is the coordinate of the SquareGUI where to create the boatFragmentGUI
     * @return JLabel is the created boatFragmentGUI
     */
    private JLabel createBoatFragments(Coord coord,BoatName name, int index){
        BoatFragmentGUI fragment = null;
        try {
            fragment = new BoatFragmentGUI(coord, name, index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.boatFragments.put(coord, fragment);
        return fragment;
    }

	/**
	 * Retrieve the squareGUI associated with this coord (from HashMap)
	 * @param coord is the coord of a squareGUI
	 * @return SquareGUI
	 */
	private SquareGUI findSquare(Coord coord) {
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
     * @return SquareGUI | null => the SquareGUI at mouse pointer position
     */
    private SquareGUI findSquareFromEvent(int xEvent, int yEvent) {
    	try {
    		// try to get squareGUI at position
            // we get the raw component and try to cast it as SquareGUI
    		return (SquareGUI) this.getComponentAt(xEvent, yEvent);
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
                    (x-diff/2)/(min/GameConfig.getGameGridWidth()),
                    (y)/(min/GameConfig.getGameGridHeight())
            );
    	} else {
    		coord = new Coord(
                    (x)/(min/GameConfig.getGameGridWidth()),
                    (y+diff/2)/(min/GameConfig.getGameGridHeight())
            );
    	}

    	try{
    		return this.findSquare(coord);
    	}catch(NullPointerException e) {
    		return null;
    	}*/
    }

	public void initGrid(Map<BoatName, ProcessedPosition> initBoatPos) {
		this.listOfBoat = new ArrayList<>();
		int i;
		for (BoatName name: initBoatPos.keySet()) {
            List<JLabel> boatFragments = new ArrayList<>();
            i = 0;
            for (Coord coord : initBoatPos.get(name).coords) {
                JLabel boatFragment = createBoatFragments(coord, name, i);
                boatFragments.add(boatFragment);
                System.out.println("Fragment de " + name + " généré au coord : " + coord);
                this.squares.get(coord).add(boatFragment);
                i++;
            }
            this.listOfBoat.add(new BoatGUI(name, boatFragments, initBoatPos.get(name).direction));
        }
	}

//	@objid ("36830e37-3f6b-4a49-9771-eecf425dec5c")
//    public BoatGUI battleShipBoatGUI;
//
//    @objid ("e78df3ec-ed3c-4ff8-a842-2ff369cb31a6")
//    public SquareGUI battleShipSquareGUI;
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
