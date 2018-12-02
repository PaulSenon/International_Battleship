package view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import model.BoatName;
import tools.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

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

	private List<BoatFragmentGUI> selectedBoat;

	private ActionType currentAction;

    /**
     * __CONSTRUCTOR__
     */
    @objid ("3cceb1f5-4f21-4971-a3ba-024ed2eabd4e")
	public GridGUI() {
		super();

		// init class attributes
		this.squares = new HashMap<Coord, SquareGUI>();
		this.boatFragments = new HashMap<Coord, BoatFragmentGUI>();
		this.selectedSquare = null;
		this.selectedBoat = new ArrayList<>();

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

//        // FOR TMP DEBUG PURPOSE : adding two boat fragments on the board
//        	Coord coord = new Coord(2,1);
//	        JLabel tmp = createBoatFragments(coord, BoatName.Cruiser, 1);
//	        this.squares.get(coord).add(tmp);
//	        // demo rotation :
//
//	        coord = new Coord(2,2);
//	        tmp = createBoatFragments(coord, BoatName.Cruiser, 0);
//	        this.squares.get(coord).add(tmp);
//	        // demo rotation :
//	        ((BoatFragmentGUI) tmp).rotate(Direction.WEST);
//        // END DEBUG
    }

	/**
	 * TODO write description
	 * @param processedPosition
	 */
	public void setProcessedPosition(ProcessedPosition processedPosition) {
		List<BoatFragmentGUI> boat = this.getBoatFragmentsById(processedPosition.boatId);
		this.setProcessedPosForBoat(boat, processedPosition);
	}

	/**
	 * TODO write description
	 * @param boatId
	 * @param processedPosition
	 */
	public void setProcessedPosition(int boatId, ProcessedPosition processedPosition){
		List<BoatFragmentGUI> boat = this.getBoatFragmentsById(boatId);
		this.setProcessedPosForBoat(boat, processedPosition);
	}

	/**
	 * TODO write description
	 * @param boatFragments
	 * @param processedPosition
	 */
	private void setProcessedPosForBoat(List<BoatFragmentGUI> boatFragments, ProcessedPosition processedPosition){
		HashMap<Coord, BoatFragmentGUI> tmpFragments = new HashMap<>();

		// move fragments
		// TODO may need a rework, no so clean,
		// TODO but mind we MUST do to loop, because if some of new pos == last pos,
		// TODO we cannot have multiple ref for the same coord in boatFragments

		int i = 0;
		for(BoatFragmentGUI fragment : boatFragments){
			fragment.rotate(processedPosition.direction);

			Coord dest = processedPosition.coords.get(i);
			// save and remove from HashMap
			tmpFragments.put(fragment.getCoord(), fragment);
			this.boatFragments.remove(fragment.getCoord());
			// update fragment prop
			fragment.setCoord(dest);
			fragment.setBroken(processedPosition.brokenPartIds.contains(i));
			// remove from UI
			this.squares.get(dest).remove(fragment);

			i++;
		}
		i = 0;
		for(BoatFragmentGUI fragment : boatFragments){
			Coord dest = processedPosition.coords.get(i);

			// add to fragments HashMap
			this.boatFragments.put(dest, fragment);

			// add in GUI
			this.squares.get(dest).add(fragment);
			this.repaint();

			i++;
		}
	}

	/**
	 * TODO write description
	 * @param initBoatPos
	 */
	public void initGrid(Map<BoatName, ProcessedPosition> initBoatPos) {
		int i;
		// foreach boat to create
		for (BoatName name: initBoatPos.keySet()) {
			i = 0;
			// foreach boatFragment to create
			for (Coord coord : initBoatPos.get(name).coords) {
				System.out.println("Fragment de " + name + " généré au coord : " + coord);
				BoatFragmentGUI boatFragment = (BoatFragmentGUI)createBoatFragments(initBoatPos.get(name).boatId, coord, name, i);
				this.squares.get(coord).add(boatFragment);
				i++;
			}
			// create boat with processedPotion and name, and add store it
//			this.listOfBoat.add(new BoatGUI(name, initBoatPos.get(name).coords, initBoatPos.get(name).direction));
		}
	}

    /**
     * PUBLIC It take some mouse click position and find the targeted square
     * @param  xEvent (int) x coordinate of the mouse pointer (from click event)
     * @param yEvent (int) y coordinate of the mouse pointer (from click event)
	 * @return the selected squareGUI (not necessary to use it, it's just to avoid
	 * a call to get selected square in case you need it)
     */
    Coord selectSquare(int xEvent, int yEvent) {
    	this.selectedSquare = findSquareFromEvent(xEvent, yEvent);
    	if(this.selectedSquare != null) {
    		System.out.println("You've clicked on : "+this.selectedSquare.getCoord().toString());
    		return this.selectedSquare.getCoord();
    	}
    	return null;
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
    private JLabel createBoatFragments(int boatId, Coord coord,BoatName name, int index){
        BoatFragmentGUI fragment = null;
		fragment = new BoatFragmentGUI(boatId, coord, name, index);
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

    /**
     * This method allow to move a fragment on the grid
     * @param fragmentGUI the fragment
     * @param dest (coord) coord who fragment must be move
     */
	public void moveFragment(BoatFragmentGUI fragmentGUI, Coord dest){
    	// move in HashMap
    	this.boatFragments.remove(fragmentGUI.getCoord());
    	this.boatFragments.put(dest, fragmentGUI);

    	// update fragment prop
		fragmentGUI.setCoord(dest);

		// move in GUI
    	this.squares.get(dest).add(fragmentGUI);
    	this.repaint();
	}

	private List<BoatFragmentGUI> getBoatFragmentsById(int boatId){
		BoatFragmentGUI[] boatFragments = new BoatFragmentGUI[this.getBoatSizeFromId(boatId)];
		for(BoatFragmentGUI boatFragment : this.boatFragments.values()){
			if(boatFragment.getBoatId() == boatId){
				boatFragments[boatFragment.getIndex()] = boatFragment;
			}
		}

		return new ArrayList<>(Arrays.asList(boatFragments));
	}

	private int getBoatSizeFromId(int boatId){
		int res = 0;
		for(BoatFragmentGUI boatFragment : this.boatFragments.values()){
			if(boatFragment.getBoatId() == boatId){
				res++;
			}
		}
		return res;
	}

	public void setSelectedBoat(ProcessedPosition processedPosition) {
//    	this.selectedBoat = new BoatGUI(processedPosition.boatId, processedPosition.name, processedPosition.coords, processedPosition.direction);
		List<BoatFragmentGUI> selectedFragments = new ArrayList<>();
		for(Coord coord : processedPosition.coords){
			selectedFragments.add(this.boatFragments.get(coord));
		}
		this.selectedBoat = selectedFragments;
	}

	public void setCurrentAction(ActionType actionType) {
    	this.currentAction = actionType;
	}

	public ActionType getCurrentAction() {
		return currentAction;
	}

}
