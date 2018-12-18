package model;


import tools.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static tools.Direction.DEFAULT;

public abstract class AbstractBoat implements BoatInterface {

    public boolean move;
    private boolean destroyed;
    protected Coord pivot;
	protected SpecialActionInterface mySpecialAction;

	// TODO not used yet, but it may be used to avoid processing every time we needs them
	private List<Coord> coords;
	private List<Integer> touchedGragmentIds;
	private List<Coord> visibleCoords;
	private boolean coordsNeedToBeProcessed;
	protected BoatType type;
	protected int size;
	protected int id;
	protected Direction facingDirection;
	private Direction lastDirection;
	private Coord lastPosition;
    private String Color;
    private int playerId;
    public SpecialActionInterface actionSpeciale;
    private boolean coordsVisibleToBeProcessed;

    public AbstractBoat(BoatType type, int id, Coord pivot, int playerId) {
        this.pivot = pivot;
        this.facingDirection = DEFAULT();
        this.type = type;
        this.coords = new ArrayList<>();
        this.coordsNeedToBeProcessed = true;
        this.coordsVisibleToBeProcessed = true;
        this.touchedGragmentIds = new ArrayList<>();
        this.size = this.type.getSize();
        this.id = id;
        this.lastDirection = this.facingDirection;
        this.lastPosition = this.pivot;
        this.playerId = playerId;
        this.move = true;
        this.destroyed = false;
    }

	public Coord getCoord() {
		return this.pivot;
	}

	public BoatType getType() {
		return this.type;
	}


    /**
     * __TESTED__
     * @param target
     * @return
     * @throws Exception
     */
    // TODO throw a custom exception like a "ShootException" instead of an "Exception"
	public Pair<ResultShoot, ProcessedPosition> shoot(Coord target) throws Exception {
            int id = this.getIdOfFragment(target);
            if(this.touchedGragmentIds.contains(id)){
                return new Pair<>(ResultShoot.ALREADY_TOUCHED, this.getProcessedPosition());
            }else{
                this.touchedGragmentIds.add(id);
                if(this.touchedGragmentIds.size() >= getNbFrontParts()) {this.move = false;}
                if(this.getCoords().size() == this.touchedGragmentIds.size()){
                    return new Pair<>(ResultShoot.DESTROYED, this.getProcessedPosition());
                }
                return new Pair<>(ResultShoot.TOUCHED, this.getProcessedPosition());
            }
	}

	private int getIdOfFragment(Coord coord) throws Exception {
        int i=0;
        for(Coord fragment : this.getCoords()){
            if(coord.equals(fragment)){
                return i;
            }
            i++;
        }
        throw new Exception("Coord not on this boat");
    }

	// TODO mind to refreshCoords
    public void move(Coord destCoord){
        if(this.isMoveOk(destCoord) && move){
            this.setPivot(destCoord); // It does the refreshCoord()
        }
    }

    /**
     *  same as move() but without check
     * @param destCoord is the coordinate where to move the boat
     */
    public void moveHard(Coord destCoord){
        this.setPivot(destCoord); // It does the refreshCoord()
    }

    public void rotateClockWise(){
        // save last direction
        this.lastDirection = this.facingDirection;
        // rotate
        if (move) {
            this.facingDirection = this.facingDirection.next(true);
            this.refreshCoords();
        }
    }

    public void rotateCounterClockWise() {
        // save last direction
        this.lastDirection = this.facingDirection;
        // rotate
        if (move) {
            this.facingDirection = this.facingDirection.next(false);
            this.refreshCoords();
        }
    }

    /**
     * TODO TEST
     * @param coord is the coord you want to check for
     * @return if the given coord belong to this boat
     */
    public boolean hasCoord(Coord coord) {
        for (Coord coordTmp: this.getCoords()) {
            if(coordTmp.equals(coord)){
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to say we changed something in the boat position
     * it may be direction or coords update.
     * MIND TO USE IT when changed something
     */
    public void refreshCoords(){
        this.coordsNeedToBeProcessed = true;
        this.coordsVisibleToBeProcessed = true;
    }

    // TODO : may needs some refactoring to remove crappy switch case and copy/pasts
    //
    // TODO : optimize, save processed coords and return them if no changes.
    // TODO :   => Add a change notif in methods that change position
    //
    // => Optimization DONE !!!! mind to use "refreshCoords" when modify sensitive data
    // => (8 nov 2018) for BattleshipModelTest, we skip the coords calculation 122/146 times
    //      => that's an optimisation of 83%
    /**
     * __TESTED__
     *
     * It return the list of coords where the boat is
     * @return List of Coord
     */
    public List<Coord> getCoords() {
        if(this.coordsNeedToBeProcessed){
            this.coords = this.getCoordsForDirection(this.facingDirection);
            this.coordsNeedToBeProcessed = false;
        }
        return this.coords;
    }

    /**
     * __TESTED__
     *
     * This method return how many front part of a boat has (with pivot)
     * for example :
     *   - boat size of 5 will return 3
     *   - boat size of 4 will return 2
     * @return nb front parts of the boat
     */
    public int getNbFrontParts(){
        if(this.getSize()%2 ==0){
            return this.getSize()/2;
        }else{
            return (this.getSize()/2) + 1;
        }
    }

    /**
     * __TESTED__
     *
     * This method return how many back part of a boat has (without pivot)
     * for example :
     *   - boat size of 5 will return 2
     *   - boat size of 4 will return 2
     * @return nb back parts of the boat
     */
    public int getNbBackParts(){
        return this.getSize()/2;
    }

    /**
     * __TESTED__
     *
     * @param direction the direction to process coords for
     * @return a list of coords
     */
    public List<Coord> getCoordsForDirection(Direction direction){
        List<Coord> coords = new ArrayList<>();
        // TODO @Paul use getNbParts
        int frontParts = this.getSize()/2 - (this.getSize()%2==0 ? 1 : 0);
        int backParts = this.getSize()/2;
        int start, stop;
        switch (direction){
            case EAST:
                start = this.pivot.getX() - backParts;
                stop = this.pivot.getX() + frontParts;
                for (int i = start; i<=stop; i++) {
                    coords.add(new Coord(i,this.pivot.getY()));
                }
                break;
            case WEST:
                start = this.pivot.getX() + backParts;
                stop = this.pivot.getX() - frontParts;
                for (int i = start; i>=stop; i--) {
                    coords.add(new Coord(i,this.pivot.getY()));
                }
                break;
            case SOUTH:
                start = this.pivot.getY() - backParts;
                stop = this.pivot.getY() + frontParts;
                for (int i = start; i<=stop; i++) {
                    coords.add(new Coord(this.pivot.getX(), i));
                }
                break;
            case NORTH:
                start = this.pivot.getY() + backParts;
                stop = this.pivot.getY() - frontParts;
                for (int i = start; i>=stop; i--) {
                    coords.add(new Coord(this.pivot.getX(), i));
                }
                break;
        }
        return coords;
    }

    /**
     * __TESTED__
     *
     * Just supposed to check if destination coordinates are in straight line toward facing direction.
     * @param coord is the destination coordinate
     * @return if move is allowed or not
     */
    public boolean isMoveOk(Coord coord){
        switch (this.getDirection()){
            case EAST:
                return (
                    coord.getY() == this.pivot.getY() // freeze Y
                    && coord.getX() >= this.pivot.getX() // go forward on X
                );
            case WEST:
                return (
                    coord.getY() == this.pivot.getY() // freeze Y
                    && coord.getX() <= this.pivot.getX() // go backward on X
                );
            case SOUTH:
                return (
                    coord.getX() == this.pivot.getX() // freeze X
                    && coord.getY() >= this.pivot.getY() // go downward on Y
                );
            case NORTH:
                return (
                    coord.getX() == this.pivot.getX() // freeze X
                    && coord.getY() <= this.pivot.getY() // go upward on Y
                );
            default:
                return false;
        }
    }

	public void actionSpecial(Coord target) {
		this.mySpecialAction.doAction(target);}

	public String getColor() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.Color;
	}

	public void setColor(final String value) {
		// Automatically generated method. Please delete this comment before entering specific code.
		this.Color = value;
	}

    /**
     * __PARTIALLY_TESTED__
     *
     * undo the last move (rotation or move)
     */
    public void undoLastMove() {
        this.facingDirection = this.lastDirection;
        this.pivot = this.lastPosition;
        this.refreshCoords();
    }

    /**
     * @return ProcessedPosition (coords + direction)
     */
    public ProcessedPosition getProcessedPosition(){
        return new ProcessedPosition(this.id, this.type, this.facingDirection, this.getCoords(), this.touchedGragmentIds);
    }

    public List<Coord> getVisibleCoords(){
        List<Coord> visibleCoords = new ArrayList<Coord>();
        if(this.destroyed){
            return this.getCoords();
        }
        if(this.coordsVisibleToBeProcessed){
            int radius = this.getSize() /2 ;
            if(radius == 0){radius = 1;}
            for (Coord coord : this.getCoords()){
                int lineBeginning = coord.getY() - radius;
                int lineEnd = coord.getY() + radius + 1;
                int columnBeginning = coord.getX() - radius;
                int columnEnd = coord.getX() + radius + 1;
                for (int line = lineBeginning; line<lineEnd; line ++){
                    for(int column = columnBeginning; column<columnEnd; column ++) {
                        if (!((line==lineBeginning && column==columnBeginning) || (line==lineBeginning && column==columnEnd - 1) || (line==lineEnd - 1 && column==columnBeginning) || (line==lineEnd - 1 && column==columnEnd - 1))){
                            Coord visibleCoord = new Coord(column, line);
                            visibleCoords.add(visibleCoord);
                        }
                    }
                }
            }
            this.coordsVisibleToBeProcessed = false;
            this.visibleCoords = new ArrayList<Coord>(new HashSet<>(visibleCoords));
        }
        return this.visibleCoords;
    }

    public int getMoveCost(int distance){
        // Define algorithm to calculate move cost for a distance
        int moveCost = Math.abs(this.getMoveCostByUnit() * distance);
        System.out.println("Boat move cost is "+moveCost+" for a distance of "+distance+" units");
        return moveCost;
    }

    protected int getMoveCostByUnit(){
//        return this.size;
        return 1;
    }

    public int getRotateCost(){
        return 1;
    }

    public int getShootCost(){
        return 1;
    }

    public int getSpecialActionCost(){
        return 1;
    }

    public boolean isShootOk(Coord target) {
		//TODO:implements method
		return false;
	}

	public boolean isShootable(Coord target) {
		//TODO:implements method
		return false;
	}

	public abstract void getVisibleArea();

    public Coord getPivot() {
        return this.pivot;
    }

    private void setPivot(final Coord value) {
        this.lastPosition = this.pivot;
        this.pivot = value;
        this.refreshCoords();
    }

//	//	@Deprecated
//	public Square getBoatPart(int x, int y) {
//		//Browse all boats and get the part boat of the coord given, if it exists
//		for (Square part : boatPart) {
//			if (part.coord.getX() == x && part.coord.getY() == y) {
//				return part;
//			}
//		}
//		return null;
//	}

//	//	public void setBoatPart(final Square[] value) {
//		// Automatically generated method. Please delete this comment before entering specific code.
//		this.boatPart = value;
//	}

	public int getSize() {
		return this.size;
	}

    protected void setSize(int size){
        this.size = size;
        this.refreshCoords();
    }

    public Direction getDirection(){
        return this.facingDirection;
    }

    protected void setFacingDirection(Direction direction){
        this.lastDirection = this.facingDirection;
        this.facingDirection = direction;
        this.refreshCoords();
    }

    public void setLastPosition(Coord pivot){
        this.lastPosition = pivot;
    }

    @Override
	public String toString() {
		return "Object BoatInterface type=" + type + " " + this.pivot;
	}

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void destroy(){
        this.coordsVisibleToBeProcessed = true;
        this.destroyed = true;
    }

    public boolean getDestroy(){
        return this.destroyed;
    }
}
