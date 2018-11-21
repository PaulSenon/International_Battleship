package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;
import tools.Direction;
import tools.ProcessedPosition;

import java.util.ArrayList;
import java.util.List;

import static tools.Direction.*;

import tools.ResultShoot;

@objid ("b8092a75-8965-4c51-bf15-701b45673ed5")
public abstract class AbstractBoat implements BoatInterface {

	protected Coord pivot;

	// TODO not used yet, but it may be used to avoid processing every time we needs them
	private List<Coord> coords;
	private boolean coordsNeedToBeProcessed;

	protected BoatName name;

	protected int size;

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

    @objid ("2da5b5ca-2907-436a-a330-f175ddec396f")
    public AbstractBoat(BoatName name, Coord pivot) {
        this.pivot = pivot;
        this.facingDirection = DEFAULT();
        this.name = name;
        this.coords = new ArrayList<>();
        this.coordsNeedToBeProcessed = true;

        this.lastDirection = this.facingDirection;
        this.lastPosition = this.pivot;
    }

	public Coord getCoord() {
		return this.pivot;
	}

	public BoatName getName() {
		return this.name;
	}


	@objid ("0494bc65-840e-4841-82ae-0d3272bcaf6b")
	public ResultShoot shoot(Coord target) {
		// TODO FIX#47 : Fix this, we do not use "getBoatPart()" anymore.
		// TODO 	=> I think it's the implementor who has to call this method
		// TODO 	=> but where to store the data to say that a boat is shot on one fragment ?

//		int x = target.getX();
//		int y = target.getY();
//		Square part = getBoatPart(x,y);
//		ResultShoot result = ResultShoot.MISSED;
//		if (part != null) {
//			if (part.isDestroyed) {
//				//If a destoyed boat is targeted.
//				result = ResultShoot.ALREADY_TOUCHED;
//			} else {
//				//If a functional boat is targeted
//				part.destroy();
//				result = ResultShoot.DESTROYED;
//				//TODO: when a boat is destroyed, re-init its value
//				for(Square fragment : boatPart) {
//					if (!fragment.isDestroyed) {
//						result = ResultShoot.TOUCHED;
//					}
//				}
//			}
//		}
//		else {
//			//If the sea is targeted
//			result = ResultShoot.MISSED;
//		}
//		return result;

		// TODO remove this, it's just a placeholder :
		return ResultShoot.TOUCHED;
	}

	// TODO mind to refreshCoords
    @objid ("472b38fc-f87c-44e6-9e76-b96a4c5d3f7b")
    public abstract void move();

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

    public boolean hasCoord(Coord coord) {
        for (Coord coordTmp: this.getCoords()) {
            if(coordTmp.equals(coord)){
                return true;
            }
        }
        return false;
    }

    public void refreshCoords(){
        this.coordsNeedToBeProcessed = true;
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
     * @param direction the direction to process coords for
     * @return a list of coords
     */
    public List<Coord> getCoordsForDirection(Direction direction){
        List<Coord> coords = new ArrayList<>();
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
     * __PARTIALLY_TESTED__
     *
     * @return ProcessedPosition (coords + direction)
     */
    public ProcessedPosition getProcessedPosition(){
        return new ProcessedPosition(this.facingDirection, this.getCoords());
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
    public void setPivot(final Coord value) {
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

    @Override
	public String toString() {
		return "Object BoatInterface name=" + name + " " + this.pivot;
	}

}
