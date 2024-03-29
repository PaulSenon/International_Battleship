package model;


import model.exceptions.SelectBoatException;
import tools.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

public class GameModel implements GameModelInterface{

    // The implementor use to manage boats
    private BoatsImplementorInterface battleshipImplementor;

    // The implementor use to manage players
    private PlayersImplementorInterface playersImplementor;

    // The implementor use to manage mines
    private MineImplementorInterface minesImplementor;

	// The selected boat (may be null)
    private BoatInterface selectedBoat;
    private PlayerInterface currentPlayer;
    private PlayerInterface clientPlayer;
    private PortImplementor portImplementor;
    private int turn;
	private int day;

    /**
     * __CONSTRUCTOR__
     */
    public GameModel() {
        // Create UID static instance
        UniqueIdGenerator.newInstance();

        this.selectedBoat = null;
        this.day = 1;
        this.turn = 1;

        try {
        	this.minesImplementor = new MineImplementor();
            this.playersImplementor = new PlayersImplementor(GameConfig.getPlayers());
            this.battleshipImplementor = new BoatsImplementor(this.playersImplementor.getPlayers(), this.minesImplementor);
            this.battleshipImplementor.generateRandomMines(20);

            // TODO debug, to set the first player... May be not clean...
            this.currentPlayer = this.playersImplementor.getPlayers().get(0);
            this.portImplementor = new PortImplementor(this.playersImplementor.getPlayers());
            this.battleshipImplementor.setPortImplementor(this.portImplementor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameModel(boolean isServer) {
        // Create UID static instance
        UniqueIdGenerator.newInstance();

        this.selectedBoat = null;

        try {
            if(isServer){
                this.playersImplementor = new PlayersImplementor();
            }else {
                this.playersImplementor = new PlayersImplementor(GameConfig.getPlayers());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameModel(List<PlayerInterface> players) {
        // Create UID static instance
        UniqueIdGenerator.newInstance(4);

        this.selectedBoat = null;

        try {
            this.minesImplementor = new MineImplementor();
            this.playersImplementor = new PlayersImplementor(players);
            this.portImplementor = new PortImplementor(this.playersImplementor.getPlayers());

            setupGame();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public PlayerInterface createPlayer(int idPlayer) {
        return playersImplementor.createPlayer(idPlayer);
    }

    @Override
    public void setProcessedPosition(ProcessedPosition processedPosition) {
        this.battleshipImplementor.setProcessedPosition(processedPosition);

    }

    @Override
    public void setupGame() {
        this.battleshipImplementor = new BoatsImplementor(this.playersImplementor.getPlayers(), this.minesImplementor);
        this.battleshipImplementor.setPortImplementor(this.portImplementor);
        this.currentPlayer = playersImplementor.getPlayers().get(0);
        this.battleshipImplementor.generateRandomMines(20);
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
    public ProcessedPosition moveBoat(int xDest, int yDest){
        // error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }
        // processing :
        Coord destCoord = new Coord(xDest, yDest);
        ProcessedPosition processedPosition = this.battleshipImplementor.moveBoat(this.currentPlayer, this.selectedBoat, destCoord);

        // regarder si toutes les coords sont ok (sortie de plateau && déclanchement mines)
        if(this.isNewPosOk(processedPosition.coords)){
            return processedPosition;
        }else {
            this.playersImplementor.undoLastMove(this.currentPlayer);
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
    public ProcessedPosition rotateBoatClockWise(){
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
    public ProcessedPosition rotateBoatCounterClockWise(){
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
        ProcessedPosition processedPosition = this.battleshipImplementor.rotateBoat(this.currentPlayer, this.selectedBoat, clockWise);
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
    public ProcessedPosition selectBoat(int x, int y) throws SelectBoatException {
        // Test for multiplayer
        if(clientPlayer!= null && clientPlayer.getId() != currentPlayer.getId()){
            throw new SelectBoatException("Not your turn");
        }


        // transform into coord to use through model
        Coord coord = new Coord(x, y);

        // set the selected boat (may return null)
        BoatInterface boat = this.battleshipImplementor.findBoatByCoord(coord);
        if(boat == null){
            throw new SelectBoatException("There is no boat here");
        }
        // Verify that the boat belongs to the current player
        if(this.currentPlayer.getId() == this.battleshipImplementor.findPlayerIdFromBoat(boat)){
            this.selectedBoat = boat;
        }else{
            throw new SelectBoatException("The boat you selected doesn't belong to you !");
        }

        // tell if it doing great or not
        // TODO it might by undefined, do tests
        try {
            return this.selectedBoat.getProcessedPosition();
        }catch (Exception e){
            throw new SelectBoatException("Something went wrong...");
        }
    }

    /**
     *
     */
    public void isEnd(){
    }

    /**
     *
     * @return
     */
    private List<BoatType> DEBUG_get_test_fleet_from_enum(){
    	List<BoatType> fleetList = new LinkedList<>();
        Collections.addAll(fleetList, BoatType.values());
    	return fleetList;
    }

    /**
     *
     * @return
     */
    public PlayerInterface getCurrentPlayer() {
            return this.currentPlayer;
    }

    /**
     *
     * @param currentPlayer
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    /**
     *
     * @param target
     * @return
     */
    @Override
	public ProcessedPosition shoot(Coord target) {
    	// error case :
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
        }
		ProcessedPosition ret = battleshipImplementor.shootBoat(this.currentPlayer, this.selectedBoat, target);
        endActionRoutine();
		return ret;
	}

	private void endActionRoutine(){
        // TODO test endgame and other things
        if(this.battleshipImplementor.getRemainsBoatsByPlayer(this.currentPlayer.getId()) == 0){
            this.playersImplementor.findById(this.currentPlayer.getId()).setEliminate(true);
        }
    }

    public Map<Integer, ProcessedPosition> getListOfBoat(){
        return this.battleshipImplementor.getBoats();
    }

	@Override
	public Map<Integer, ProcessedProps> getListOfMine() {
		return this.minesImplementor.getListOfMines();
	}

    public Map<Integer, Integer> getBoatsAndPlayersId(){
        return this.battleshipImplementor.getBoatsAndPlayersId();
    }

    @Override
    public int getApCurrentPlayer() {
        return this.currentPlayer.getNbActionPoint();
    }

    public List<Coord> getVisibleCoords(PlayerInterface player) {
    	List<Coord> visibleCoords = new ArrayList<>();
    	visibleCoords.addAll(new ArrayList<>(this.portImplementor.getVisibleCoords(player).keySet())); // add ports visible coords
    	visibleCoords.addAll(this.battleshipImplementor.getVisibleCoords(player)); // add boats visible coords
    	visibleCoords.addAll(this.minesImplementor.getVisibleCoords(player.getId())); // add boats visible coords

        // remove duplicates
        visibleCoords = new ArrayList<>(new HashSet<>(visibleCoords));
        return visibleCoords;
    }

    public List<Coord> getVisibleCoordsCurrentPlayer() {
        return this.getVisibleCoords(this.currentPlayer);
    }

    public Map <Coord, Color> getPortsCoords(PlayerInterface clientPlayer){
    	Map<Coord, Color> visibleCoordsBoat = this.portImplementor.getColorOfCoord(this.battleshipImplementor.getVisibleCoords(clientPlayer));
    	Map<Coord, Color> visibleCoordsPort = this.portImplementor.getVisibleCoords(clientPlayer);
    	for (Entry<Coord, Color> entry : visibleCoordsBoat.entrySet()){
    		if(!visibleCoordsPort.containsKey(entry.getKey())){
    			visibleCoordsPort.put(entry.getKey(), entry.getValue());
    		}
    	}
    	return visibleCoordsPort;
    }

    public Map <Coord, Color> getPortsCoordsCurrentPlayer() {
    	return getPortsCoords(this.currentPlayer);
    }

    @Override
    public void unselectBoat(){
        this.selectedBoat = null;
    }

	@Override
	public List<ProcessedPosition> specialAction(Coord coordSquare) {
        if(this.selectedBoat == null){
            // TODO just placeholder yet.
            System.out.println("No boat has been selected");
            return null;
        }
		return this.battleshipImplementor.specialAction(this.currentPlayer, this.selectedBoat,coordSquare);
	}

	@Override
	public void EndActionsOfPlayer() {
        for (PlayerInterface player : playersImplementor.getPlayers()){
            if (!player.isEliminate()) {
                if(this.battleshipImplementor.getRemainsBoatsByPlayer(player.getId())<= 0){
                    player.setEliminate(true);
                }
            }
        }
        endTurn();
		if (this.turn%this.playersImplementor.remainsPlayers() == 0) {
			endDay();
			if (this.playersImplementor.idWinner() != -1) {
			    // TODO remove this, not responsibility of the game model :
				MessageManager.broadcastMessagePopUp("It's Finished !! And the winner is " + this.playersImplementor.findById(this.playersImplementor.idWinner()).getName());
			}
			initDay();
		}
		initTurn();
	}

	@Override
	public List<BoatInterface> getVisibleBoats(PlayerInterface player) {
		return this.battleshipImplementor.getVisibleBoats(player);
	}

	@Override
	public List<BoatInterface> getVisibleBoatsCurrentPlayer() {
		return this.getVisibleBoats(this.currentPlayer);
	}

	@Override
	public void initTurn() {
		this.turn++;
		MessageManager.broadcastMessageConsole("It's your turn : "+this.currentPlayer.getName());
		this.currentPlayer.creditActionPoint(this.currentPlayer.getMaxActionPoint()); //pour initialiser les PA
        allowAllBoatToMove();
	}

    /**
     * Allow all the boats of the players to move at the beginning of the turn
     */
    private void allowAllBoatToMove() {
        List<BoatInterface> fleet = battleshipImplementor.getPlayerFleet(this.currentPlayer);
        for (BoatInterface boat : fleet) {
            if (boat != null)
                boat.moveAutorization();
        }
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

	@Override
	public void nextPlayer() {
		System.out.println(this.getCurrentPlayer());
		int idPlayer = 0;
		for (int i = 0; i < this.playersImplementor.getPlayers().size(); i++) {
			if (this.currentPlayer.equals(this.playersImplementor.getPlayers().get(i))) {
				idPlayer = i;
				break;
			}
		}
		do{
			if(idPlayer ==  this.playersImplementor.getPlayers().size()-1){
				idPlayer = 0;
			}
			else{
				idPlayer++;
			}
			if (!this.playersImplementor.getPlayers().get(idPlayer).isEliminate()) {
				this.setCurrentPlayer((Player)this.playersImplementor.getPlayers().get(idPlayer));
			}

		}while(!this.currentPlayer.equals(this.playersImplementor.getPlayers().get(idPlayer)));
	}

    public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

    public PlayersImplementorInterface getPlayersImplementor() {
		return playersImplementor;
	}

    /**
     * To check if the current player has a selected boat
     * @return
     */
    public boolean hasSelectedBoat(){
        return this.selectedBoat != null && this.currentPlayer.getId() == this.selectedBoat.getPlayerId();
    }

    public void setClientPlayer(PlayerInterface clientPlayer) {
        this.clientPlayer = clientPlayer;
    }

    public PlayerInterface getClientPlayer() {
        return clientPlayer;
    }

    @Override
    public boolean itsTurn() {
        if (currentPlayer.equals(clientPlayer)) {
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean canCurrentBoatShoot() {
        PlayerInterface playerToTest = null;
        if(this.clientPlayer == null){
            playerToTest = this.currentPlayer;
        }else {
            playerToTest = this.clientPlayer;
        }
        if(
            this.selectedBoat == null
            || playerToTest == null
            || this.portImplementor.isInPort(this.selectedBoat.getPivot())
        ){
            return false;
        }
        return this.selectedBoat.getShootCost() <= this.currentPlayer.getNbActionPoint();
    }

    @Override
    public boolean canCurrentBoatRotate() {
        if(this.selectedBoat == null || this.currentPlayer == null) return false;

        return this.selectedBoat.getRotateCost() <= this.currentPlayer.getNbActionPoint();
    }

    @Override
    public boolean canCurrentBoatDoSpecialAction() {
        PlayerInterface playerToTest = null;
        if(this.clientPlayer == null){
            playerToTest = this.currentPlayer;
        }else {
            playerToTest = this.clientPlayer;
        }
        if(
            this.selectedBoat == null
            || playerToTest == null
            || this.portImplementor.isInPort(this.selectedBoat.getPivot())
        ) return false;
        return this.selectedBoat.getSpecialActionCost() <= this.currentPlayer.getNbActionPoint();
    }

    @Override
    public boolean isInstantActionForCurrentBoat() {
        return this.selectedBoat.getSpecialAction() instanceof InstantAction;
    }

    @Override
    public void setProcessedPosition(ProcessedProps processedProps) {
        if(processedProps.type == PropsType.FX) return;

        this.minesImplementor.processMineProcessedProps(processedProps);
    }

    @Override
	public List<ProcessedProps> getProcessedPropsToUpdate() {
        return ProcessedPropsManager.flushQueue();
	}
}
