package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import tools.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;

@objid ("2d5b787d-2269-4d70-9e4e-dd727dfa9336")
public class GameModel implements GameModelInterface {

    // The implementor use to manage boats
    private BoatsImplementorInterface battleshipImplementor;

    // player list
    private List<PlayerInterface> players;

    // The selected boat (may be null)
    private BoatInterface selectedBoat;

    AtomicInteger atomicInteger;
    
    private int turn;
    
    private int day;
    
    private Player currentPlayer;

    /**
     * __CONSTRUCTOR__
     */
    public GameModel() {
        //Set the ids for boats
        this.atomicInteger = new AtomicInteger();
        // set attributes
        this.players = new ArrayList<>();
        //For testing the creation of player in model in the setting of the GameModel
        try {
            createPlayer("Player1", "Port1");
            createPlayer("Player2", "Port2");	//to test
            createPlayer("Player3", "Port3");	//to test
            createPlayer("Player4", "Port4");	//to test
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.selectedBoat = null;
    	//battleshipImplementor = new BoatsImplementor(this.players, this.DEBUG_get_test_fleet_from_enum());
        battleshipImplementor = new BoatsImplementor(this.players);
        this.setCurrentPlayer((Player) this.players.get(0));
        this.turn = 1;
        this.day = 1;
    }

    /**
     * // TODO Tests
     * TO BE CALLED FROM CONTROLLER
     *
     * Move the selectedBoat to the desired position if possible.
     * You must select a boat before calling this method
     *
     * @param xDest is the desired destination x coordinate on the game board
     * @param yDest is the desired destination y coordinate on the game board
     * @return Coord is the pivot coordinate where the boat is after processing (may no change)
     */
    @objid ("fa9a7c83-14a9-4a47-8786-28afdc857cac")
    public ProcessedPosition moveBoat(int xDest, int yDest) {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }

        // processing :
        Coord destCoord = new Coord(xDest, yDest);
        ProcessedPosition processedPosition = this.battleshipImplementor.moveBoat(this.selectedBoat, destCoord);

        // regarder si toutes les coords sont ok (sortie de plateau && déclanchement mines)
        if(this.isNewPosOk(processedPosition.coords)){
            return processedPosition;
        }else{
            processedPosition = this.battleshipImplementor.undoLastBoatMove(this.selectedBoat);
            return processedPosition;
        }
    }

    /**
     * __TESTED__
     * TO BE CALLED FROM CONTROLLER
     *
     * Rotate the selectedBoat clock wise.
     * You must select a boat before calling this method.
     *
     * @return ProcessedPositions (coords + direction)
     */
    public ProcessedPosition rotateBoatClockWise() {
        return this.rotateSelectedBoat(true);
    }

    /**
     * __TESTED__
     * TO BE CALLED FROM CONTROLLER
     *
     * Rotate the selectedBoat counter clock wise.
     * You must select a boat before calling this method.
     *
     * @return ProcessedPositions (coords + direction)
     */
    public ProcessedPosition rotateBoatCounterClockWise() {
        return this.rotateSelectedBoat(false);
    }

    /**
     * __TESTED__
     *
     * Rotate the selectedBoat.
     *
     * @return ProcessedPositions (coords + direction)
     */
    private ProcessedPosition rotateSelectedBoat(boolean clockWise){
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }

        // processing : boat rotation
        ProcessedPosition processedPosition = this.battleshipImplementor.rotateBoat(this.selectedBoat, clockWise);

        // regarder si toutes les coords sont ok (sortie de plateau && déclanchement mines)
        if(this.isNewPosOk(processedPosition.coords)){
            return processedPosition;
        }else{
            processedPosition = this.battleshipImplementor.undoLastBoatMove(this.selectedBoat);
            return processedPosition;
        }
    }

    /**
     * TODO
     * __PARTIALLY_TESTED__
     *
     * @param coords list of coords to check
     * @return is coords are accessible
     */
    private boolean isNewPosOk(List<Coord> coords){
        // TODO, just a placeholder yet
        // TODO     => handle mine collision
        for(Coord coord : coords){
            if( // check out of bounds
                    coord.getX()< 0
                    || coord.getY() < 0
                    || coord.getX() > GameConfig.getGameGridWidth() - 1
                    || coord.getY() > GameConfig.getGameGridHeight() -1
            ){
                return false;
            }
        }
        return true;
    }

    /**
     * // TODO Tests
     * TO BE CALLED FROM CONTROLLER
     *
     * This method tell the model to set its selectedBoat instance
     * if there is one at the given coordinates
     *
     * NOTE that we get raw x,y that we transform into Coord.
     *      it's to separate the model from the other stuff (controller and view)
     *
     * @param x is the x coordinate on the game board
     * @param y is the y coordinate on the game board
     * @return boolean (to tell if it's doing well or not)
     */
    public ProcessedPosition selectBoat(int x, int y){
        // transform into coord to use through model
        Coord coord = new Coord(x, y);

        // set the selected boat (may return null)
        this.selectedBoat = this.battleshipImplementor.findBoatByCoord(coord);

        // tell if it doing great or not
        // TODO it might by undefined, do tests
        try {
            return this.selectedBoat.getProcessedPosition();
        }catch (Exception e){
            return null;
        }
    }

    @objid ("0afc1bfb-1667-4d42-92d9-745fb5663841")
    public void isEnd(){

    }

    private List<BoatName> DEBUG_get_test_fleet_from_enum(){
    	List<BoatName> fleetList = new LinkedList<>();
        Collections.addAll(fleetList, BoatName.values());
    	return fleetList;
    }

    @objid ("2ae2da19-fa1b-4a19-bb09-b4aa95f3903c")
    public void getColorCurrentPlayer() {
        // TODO Auto-generated stub
    }

	@Override
	public Pair<ResultShoot, ProcessedPosition> shoot(Coord target) {
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }
		return battleshipImplementor.shootBoat(target);
	}

    public Map<BoatName, ProcessedPosition> getListOfBoat(){
        return this.battleshipImplementor.getBoats();
    }

    /**
     * Create a player and add it to the list of players
     * @param name
     * @param port
     */
	public void createPlayer(String name, String port) throws Exception{
        for (PlayerInterface player : players){
            if (player.getName().equals(name) || player.getPortName().equals(port)){
                throw new Exception("Player or Port already existing");
            }
        }
	    Player player = new Player(name, port);
        //TODO : Remove the following lines, it's just for DEBUG
        List<BoatName> fleetList = new LinkedList<>();
        Collections.addAll(fleetList, BoatName.values());
        int i = 5;
        for (BoatName boat : fleetList){
            addBoat(player,boat,new Coord(5,i));
            i++;
        }
        //END of lines for DEBUG
        this.players.add(player);
    }

    public void addBoat(Player player, BoatName boatName, Coord coord){
	    int id = this.atomicInteger.getAndIncrement();
	    player.addBoatInFleet(boatName, coord, id);
    }

    /**
     * Delete a player thanks to its name and delete it from the list of the players in the GameModel
     * @param name
     * @return result of the destruction of the player
     */
    public void deletePlayer(String name) throws Exception{
        PlayerInterface toDestroy = null;
        for (PlayerInterface player : this.players){
            if (player.getName().equals(name)) {
                toDestroy = player;
                //TODO: delete object player
            }
        }
        if (toDestroy != null) {
            this.players.remove(toDestroy);
        }
        else {
            throw new Exception("The player you want to delete isn't existing");
        }
    }

    public List<PlayerInterface> getPlayers() {
        return players;
    }

	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public void EndActionsPlayer() {
		endTurn();
		if (this.turn%this.players.size() == 0) {
			endDay();
			if (this.players.size() == 1) {
				JOptionPane.showMessageDialog(null, "C'est la fin du jeu ! Le gagnant est " + this.players.get(0).getName(), null , JOptionPane.INFORMATION_MESSAGE);	
			}
			initDay();
		}
		initTurn();
	}

	@Override
	public void initTurn() {
		this.turn++;
		this.currentPlayer.calculationOfMaxActionPoint(); //pour initialiser les PA		
	}

	@Override
	public void endTurn() {
		this.nextPlayer();
		System.out.println("fin du tour " + this.turn);	//to test
	}

	@Override
	public void initDay() {
		this.day++;
		this.turn = 0;	
	}

	@Override
	public void endDay() {
		System.out.println("--fin du jour--" + this.day);	//to test		
	}

	/**
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	@Override
	public void nextPlayer() {
		for (int i = 0; i < players.size(); i++) {
			if (this.currentPlayer.equals(players.get(i))) {
				if (players.size()-1 == i) {
					this.setCurrentPlayer((Player) this.players.get(0));
				}else{
					this.setCurrentPlayer((Player) this.players.get(i+1));
				}
				break;
			}
		}
	}
}
