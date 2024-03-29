package view;


import model.BoatType;
import tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Timer;


public class GridGUI extends JLayeredPane {
	private static final long serialVersionUID = 1L;
	private final int delay = 10;
	private Timer animator;

	// Map of Coord <=> Square
	private HashMap<Coord, SquareGUI> squares;

	// TODO not used yet, may need refactoring
    // Map of Coord <=> BoatFragment
	private HashMap<Coord, BoatFragmentGUI> boatFragments;

	// TODO not used yet
	private SquareGUI selectedSquare;

	private List<BoatFragmentGUI> selectedBoat;

	private ActionType currentAction;
	
	private HashMap<Coord, MineGUI> mines;

	private List<BufferedImage> fogs;
	private List<BufferedImage> seas;


    /**
     * __CONSTRUCTOR__
     */
    	public GridGUI() {
		super();

		// init class attributes
		this.squares = new HashMap<Coord, SquareGUI>();
		this.boatFragments = new HashMap<Coord, BoatFragmentGUI>();
		this.mines = new HashMap<Coord, MineGUI>();
		this.selectedSquare = null;
		this.selectedBoat = new ArrayList<>();

		// load images
        this.fogs = new ArrayList<>();
        try {
            fogs.add(ImageManager.getImageCopyRotated("fog.png",Direction.EAST.rotation));
            fogs.add(ImageManager.getImageCopyRotated("fog.png",Direction.WEST.rotation));
            fogs.add(ImageManager.getImageCopyRotated("fog.png",Direction.NORTH.rotation));
            fogs.add(ImageManager.getImageCopyRotated("fog.png",Direction.SOUTH.rotation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.seas = new ArrayList<>();
        try {
            seas.add(ImageManager.getImageCopyRotated("sea.jpg",Direction.EAST.rotation));
            seas.add(ImageManager.getImageCopyRotated("sea.jpg",Direction.WEST.rotation));
            seas.add(ImageManager.getImageCopyRotated("sea.jpg",Direction.NORTH.rotation));
            seas.add(ImageManager.getImageCopyRotated("sea.jpg",Direction.SOUTH.rotation));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
//	        JLabel tmp = createBoatFragments(coord, BoatType.Cruiser, 1);
//	        this.squares.get(coord).add(tmp);
//	        // demo rotation :
//
//	        coord = new Coord(2,2);
//	        tmp = createBoatFragments(coord, BoatType.Cruiser, 0);
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
		if(processedPosition == null) return;
		List<BoatFragmentGUI> boat = this.getBoatFragmentsById(processedPosition.boatId);
		this.setProcessedPosForBoat(boat, processedPosition);
	}

	/**
	 * Process a list of processedProps of any type
	 * @param processedProps
	 */
	public void setProcessedProps(List<ProcessedProps> processedProps) {
		if(processedProps == null) return;
		for (ProcessedProps props : processedProps) {
			setProcessedProps(props);
		}
	}

	/**
	 * process one processedprops
	 * @param props
	 */
	public void setProcessedProps(ProcessedProps props) {
		switch (props.type){
			case MINE:
				this.setProcessedPropsForMine(props);
				break;
			case FX:
				this.setProcessedPropsForFx(props);
				break;
		}
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
		// TODO but mind we MUST do two loop, because if some of new pos == last pos,
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
			fragment.setBroken(processedPosition.getBrokenPartIds().contains(i));
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

			i++;
		}
		this.repaint();
	}

	/**
	 *
	 * @param props (type must be PropsType.MINE)
	 */
	private void setProcessedPropsForMine(ProcessedProps props){
		if(props.type != PropsType.MINE)return;

		if(props.stateMine == StateMine.DESTROY){
			this.squares.get(props.coord).remove(this.mines.get(props.coord));
			this.mines.remove(props.coord);
		}else if (!this.mines.containsKey(props.coord)){
			MineGUI mine = (MineGUI) this.createMines(props.idMine, props.coord);
			this.squares.get(props.coord).add(mine);
			this.mines.put(props.coord, mine);
		}
	}

	/**
	 * Process FX props to display fx
	 * @param props (type must be PropsType.FX)
	 */
	private void setProcessedPropsForFx(ProcessedProps props){
		if(props.type != PropsType.FX)return;

		JLabel fx = null;
		switch (props.typeFx){
			case EXPLOSION:
			case SPLASH:
				fx = new Explosion(props.typeFx);
			default:
				// none
				break;
		}
		if(fx != null){
			this.squares.get(props.coord).add(fx, 0);
			this.squares.remove(fx);
			this.squares.get(props.coord).revalidate();
			this.squares.get(props.coord).repaint();
		}
	}

	/**
	 * TODO write description
	 * @param initBoatPos
	 */
	public void initGrid(Map<Integer, ProcessedPosition> initBoatPos, Map<Integer, Integer> boatRelatedToPlayer, List<ProcessedProps> processedProps) {
		int i;
		int playerPosition = -1;
		int previousId = -1;
		int currentId;
		// foreach boat to create
		for (Map.Entry<Integer, ProcessedPosition> boatEntry : initBoatPos.entrySet()) {
			i = 0;
			Integer player;
			if (boatRelatedToPlayer.containsKey(boatEntry.getKey())){
				player = boatRelatedToPlayer.get(boatEntry.getKey());
				currentId = player.intValue();
				if (currentId != previousId){
					previousId = currentId;
					playerPosition ++;
				}
			}
			// foreach boatFragment to create
			for (Coord coord : boatEntry.getValue().coords) {
				System.out.println("Fragment de " + boatEntry.getValue().name + " généré au coord : " + coord);
				BoatFragmentGUI boatFragment = (BoatFragmentGUI) createBoatFragments(boatEntry.getKey(), coord, boatEntry.getValue().name, i, boatEntry.getValue().direction, playerPosition);
				try{
					this.squares.get(coord).add(boatFragment);
				}catch(Exception e){
					System.out.println("GridGUI: coord:"+coord+" is out of grid");
				}
				i++;
			}
		}
		this.setProcessedProps(processedProps);
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
    private JLabel createBoatFragments(int boatId, Coord coord, BoatType name, int index, Direction direction, int playerPosition){
        BoatFragmentGUI fragment = null;
		fragment = new BoatFragmentGUI(boatId, coord, name, index, playerPosition);
		fragment.rotate(direction);
        this.boatFragments.put(coord, fragment);
        return fragment;
    }
    
    /**
     * It create a mine at coord and add it to mines Map
     * @param coord is the coordinate of the MineGUI where to create the MineGUI
     * @return JLabel is the created MineGUI
     */
    private JLabel createMines(int mineId, Coord coord){
        MineGUI mine = null;
		mine = new MineGUI(mineId, coord);
        this.mines.put(coord, mine);
        return mine;
    }

	/**
	 * Retrieve the squareGUI associated with this coord (from HashMap)
	 * @param coord is the coord of a squareGUI
	 * @return SquareGUI
	 */
	private SquareGUI findSquare(Coord coord) {
    	return squares.get(coord);
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
		if (processedPosition==null){
			this.selectedBoat=null;
			for (BoatFragmentGUI frag: this.boatFragments.values())
					frag.setNotSelected();
		}else{
			//this.selectedBoat = new BoatGUI(processedPosition.boatId, processedPosition.type, processedPosition.coords, processedPosition.direction);
			List<BoatFragmentGUI> selectedFragments = new ArrayList<>();
			for(Coord coord : processedPosition.coords){
				selectedFragments.add(this.boatFragments.get(coord));
			}
			this.selectedBoat = selectedFragments;
			for (BoatFragmentGUI frag: this.boatFragments.values()){
				if (this.selectedBoat.contains(frag)){
					frag.setSelected();
				}else{
					frag.setNotSelected();
				}
			}
		}
	}

	public void setCurrentAction(ActionType actionType) {
    	this.currentAction = actionType;
	}

	public ActionType getCurrentAction() {
		return currentAction;
	}

	public void setVisibleCoords(List<Coord> visibleCoords, Map <Coord, Color> visibleCoordsPort){
		for (Map.Entry<Coord, SquareGUI> entry : this.squares.entrySet()) {
			Coord coord = entry.getKey();
			SquareGUI square = entry.getValue();
			Random random = new Random();
			int randomDirection = random.nextInt(this.fogs.size());
			if (visibleCoords.contains(coord)) {
				square.image = this.seas.get(randomDirection);
				if (visibleCoordsPort.containsKey(coord)){
					Color color = visibleCoordsPort.get(coord);
					if (color != null)
						square.image = ImageFilter.tintImage(square.image, color, true);
				}
				square.repaint();
			} else {
				square.image = this.fogs.get(randomDirection);
				square.repaint();
			}
		}
	}

	public void setVisibleBoats(List<Coord> visibleCoordCurrentPlayer) {
		for (Map.Entry<Coord, BoatFragmentGUI> entry : this.boatFragments.entrySet()) {
			Coord coord = entry.getKey();
			BoatFragmentGUI boatFragment = entry.getValue();
			if(visibleCoordCurrentPlayer.contains(coord)){
				boatFragment.setFragmentVisible(true);
			}
			else{
				boatFragment.setFragmentVisible(false);
			}
		}
	}
	
	public void setVisibleMines(List<Coord> visibleCoordCurrentPlayer) {
		for (Map.Entry<Coord, MineGUI> entry : this.mines.entrySet()) {
			Coord coord = entry.getKey();
			MineGUI mine = entry.getValue();
			if(visibleCoordCurrentPlayer.contains(coord)){
				mine.setMineVisible(true);
			}
			else{
				mine.setMineVisible(false);
			}
		}
	}


	public boolean boatIsSelected() {
		if(this.selectedBoat!=null){
			return true;
		}
		return false;
	}


}
