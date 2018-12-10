package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.*;

import java.util.ArrayList;
import java.util.List;

import static tools.Direction.DEFAULT;

@objid ("b8092a75-8965-4c51-bf15-701b45673ed5")
public abstract class AbstractBoat implements BoatInterface {

	protected Coord pivot;

	// TODO not used yet, but it may be used to avoid processing every time we needs them
	private List<Coord> coords;
	private List<Integer> touchedGragmentIds;
	private List<Coord> visibleCoords;
	private boolean coordsNeedToBeProcessed;

	protected BoatName name;

	protected int size;
	protected int id;

	protected Direction facingDirection;

	private Direction lastDirection;
	private Coord lastPosition;

	// TODO will probably be a Color enum
	@objid ("809e8204-31c8-42eb-bb6a-467d73259045")
    private String Color;

    @objid ("b61536b1-2df5-41fc-b99e-982da0c79602")
    private int moveCost;

    @objid ("7792e884-e1c1-4f24-a745-3a285a50c7d3")
    public SpecialActionInterface actionSpeciale;
    private boolean coordsVisibleToBeProcessed;

    @objid ("2da5b5ca-2907-436a-a330-f175ddec396f")
    public AbstractBoat(BoatName name, Coord pivot, int id) {
        this.pivot = pivot;
        this.facingDirection = DEFAULT();
        this.name = name;
        this.coords = new ArrayList<>();
        this.coordsNeedToBeProcessed = true;
        this.coordsVisibleToBeProcessed = true;
        this.touchedGragmentIds = new ArrayList<>();
        this.id = id;

        this.lastDirection = this.facingDirection;
        this.lastPosition = this.pivot;
    }

	public Coord getCoord() {
		return this.pivot;
	}

	public BoatName getName() {
		return this.name;
	}


    /**
     * __TESTED__
     * @param target
     * @return
     * @throws Exception
     */
	@objid ("0494bc65-840e-4841-82ae-0d3272bcaf6b")
    // TODO throw a custom exception like a "ShootException" instead of an "Exception"
	public Pair<ResultShoot, ProcessedPosition> shoot(Coord target) throws Exception {
            int id = this.getIdOfFragment(target);
            if(this.touchedGragmentIds.contains(id)){
                return new Pair<>(ResultShoot.ALREADY_TOUCHED, this.getProcessedPosition());
            }else{
                this.touchedGragmentIds.add(id);
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
    @objid ("472b38fc-f87c-44e6-9e76-b96a4c5d3f7b")
    public void move(Coord destCoord){
        if(this.isMoveOk(destCoord)){
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

    @objid ("901d66d1-b1e1-4f7b-8c07-246f568ba2db")
    public void rotateClockWise(){
        // save last direction
        this.lastDirection = this.facingDirection;
        // rotate
        this.facingDirection = this.facingDirection.next(true);
        this.refreshCoords();
    }

    @objid ("c01df1a1-a009-44f4-a9a5-5e7c9d11b6ef")
    public void rotateCounterClockWise() {
        // save last direction
        this.lastDirection = this.facingDirection;
        // rotate
        this.facingDirection = this.facingDirection.next(false);
        this.refreshCoords();
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


	@objid ("7c56179d-d096-4f01-992b-2b6a8816e013")
	public void useSpecialAction() {
	}

	@objid ("1f7f3f00-23b8-4a7b-af22-acbdf24850ae")
	public String getColor() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.Color;
	}

	@objid ("e6dc5908-9d22-424e-97a3-b3ac8d752ce1")
	public void setColor(final String value) {
		// Automatically generated method. Please delete this comment before entering specific code.
		this.Color = value;
	}

    /**
     * __PARTIALLY_TESTED__
     *
     * undo the last move (rotation or move)
     */
    @objid ("ea27622f-ef86-414e-a871-91e521f336d4")
    public void undoLastMove() {
        this.facingDirection = this.lastDirection;
        this.pivot = this.lastPosition;
        this.refreshCoords();
    }

    /**
     * @return ProcessedPosition (coords + direction)
     */
    public ProcessedPosition getProcessedPosition(){
        return new ProcessedPosition(this.id, this.name, this.facingDirection, this.getCoords(), this.touchedGragmentIds, getVisibleCoords());
    }

    public List<Coord> getVisibleCoords(){
        List<Coord> visibleCoords = new ArrayList<Coord>();
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
            List <Coord> cleanList = new ArrayList<Coord>();
            for (Coord coord : visibleCoords){
                if (!cleanList.contains(coord)){
                    cleanList.add(coord);
                }
            }
            this.coordsVisibleToBeProcessed = false;
            this.visibleCoords = cleanList;
        }
        return this.visibleCoords;
    }

    @objid ("b5186b6f-fae1-4d24-9f3b-377baa516a55")
    public int getMoveCost() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.moveCost;
    }

    @objid ("101b32ca-ec00-4e67-9937-034520e8f2f8")
    public void setMoveCost(final int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.moveCost = value;
    }

	@objid ("5b8e138a-1e09-4beb-a980-60255795d4b7")
	public boolean isShootOk(Coord target) {
		//TODO:implements method
		return false;
	}

	@objid ("aa408278-c5e0-463b-812a-00b416431338")
	public boolean isShootable(Coord target) {
		//TODO:implements method
		return false;
	}

	@objid ("8843f023-42d1-4d07-8e22-0e8d8adfa52e")
	public void getVisibleArea() {
	}

    @objid ("dfb55234-05af-4b32-b572-882581380e93")
    public Coord getPivot() {
        return this.pivot;
    }

    @objid ("21694e9f-8e7d-41a8-b512-40f58f0c6c9a")
    private void setPivot(final Coord value) {
        this.lastPosition = this.pivot;
        this.pivot = value;
        this.refreshCoords();
    }

//	@objid ("c15251b2-c1fb-4ac8-9800-21b0b04c201b")
//	@Deprecated
//	public Square getBoatPart(int x, int y) {
//		//Browse all boats and get the part boat of the coord given, if it exists
//		for (Square part : boatPart) {
//			if (part.coord.getX() == x && part.coord.getY() == y) {
//				return part;
//			}
//		}
//		return null;
//	}

//	@objid ("b9904940-cd00-4cc0-a704-ac5cae79ea57")
//	public void setBoatPart(final Square[] value) {
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
		return "Object BoatInterface name=" + name + " " + this.pivot;
	}

    public int getId() {
        return id;
    }
}
