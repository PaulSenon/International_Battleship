package View;

import java.awt.Point;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import Model.Coord;
import View.BattleShipBoatFragmentGUI.Direction;
import tools.BattleShipGameConfig;

@objid ("59f3e563-cd95-4a58-982c-35a753e56132")

public class BattleShipGridGUI extends JLayeredPane implements BattleshipGameGUI {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<Coord, BattleShipSquareGUI> squares;
	private HashMap<Coord, BattleShipBoatFragmentGUI> boatFragments;
	
	BattleShipSquareGUI selectedSquare;
	
    @objid ("3cceb1f5-4f21-4971-a3ba-024ed2eabd4e")
	public BattleShipGridGUI() {
		super();
		
		this.squares = new HashMap<Coord, BattleShipSquareGUI>();
		this.boatFragments = new HashMap<Coord, BattleShipBoatFragmentGUI>();

		this.setLayout(new CustomGridLayoutManager());
		
		this.generateBoard();
		this.repaint();
	
	}
    
    /**
     * It generate the gameBoard with squares
     */
    private void generateBoard(){
        for (int i = 0; i < BattleShipGameConfig.getGameGridHeight(); i++) { // y l
            for (int j = 0; j < BattleShipGameConfig.getGameGridWidth(); j++) { // x c
            	JPanel tmpSquare = createSquare(new Coord(j,i));
            	this.add(tmpSquare, new Point(j,i));
            }
        }
        
        // DEBUG TEST : adding two boat fragments on the board
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
        // DEBUG TEST
    }
    
    /**
     * It create a square at coord and add it to squares Map
     * @param coord
     * @return JPanel
     */
    private JPanel createSquare(Coord coord){
    	BattleShipSquareGUI square = new BattleShipSquareGUI(coord);
        this.squares.put(coord, square);
        return square;
    }
    
    /**
     * It create a boatFragment at coord and add it to boatFragments Map
     * @param coord
     * @return JPanel
     */
    private JLabel createBoatFragments(Coord coord){
    	BattleShipBoatFragmentGUI fragment = new BattleShipBoatFragmentGUI(coord);
    	this.boatFragments.put(coord, fragment);
    	return fragment;
    }
    
    /**
     * PUBLIC It take some mouse click position and find the targeted square
     * @param int x
     * @param int y
     */
    public void selectSquare(int x, int y) {
    	this.selectedSquare = findSquareFromEvent(x, y);
    	if(this.selectedSquare != null) {    		
    		System.out.println("You've clicked on : "+this.selectedSquare.getCoord().toString());
    	}
    }
    
    /**
     * It take some mouse click position and find the targeted square
     * @param int x
     * @param int y
     * @return BattleShipSquareGUI
     */
    private BattleShipSquareGUI findSquareFromEvent(int x, int y) {
    	try {
    		// try to get squareGUI at position
    		BattleShipSquareGUI square = (BattleShipSquareGUI) this.getComponentAt(x, y);
    		return square;
    	}catch(ClassCastException e) {
    		// if it cannot be casted as a SquareGUI, ignore...
    		return null;
    	}
    	
    	// _OLD_ => to generate Square coord from click coord (dosn't work with our custom grid layout)
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
    
    private BattleShipSquareGUI findSquare(Coord coord) {
    	return squares.get(coord);
    }

	@objid ("36830e37-3f6b-4a49-9771-eecf425dec5c")
    public BattleShipBoatGUI battleShipBoatGUI;

    @objid ("e78df3ec-ed3c-4ff8-a842-2ff369cb31a6")
    public BattleShipSquareGUI battleShipSquareGUI;

    @objid ("ed7393e2-7af8-47c1-8c1f-5f74b55dec18")
    public void setBoatToMove() {
    }

    @objid ("38933c80-466d-4813-922d-b72b050a2270")
    public void setVisibility() {
    }

    @objid ("2522fe29-617f-406f-9437-7668af3a46ed")
    public void movePiece() {
    }

    @objid ("201de8c0-2b1b-4bdc-91e1-2bea44d1e06b")
    public void undoMovePiece() {
    }

    @objid ("324f210b-a38f-46f5-9460-357023fd912e")
    public void findCoord() {
    }

    @objid ("717ace36-b5e2-4191-b136-6dee37668dc1")
    public void setCheckerBoard() {
    }

    @objid ("7ec2238c-6895-4ae2-b0a1-378c4296fec6")
    private void createBattleshipBoard() {
    }

}
