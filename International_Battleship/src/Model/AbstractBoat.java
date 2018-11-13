package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("b8092a75-8965-4c51-bf15-701b45673ed5")
public abstract class AbstractBoat implements Boat {
	@objid ("809e8204-31c8-42eb-bb6a-467d73259045")
	private String Color;

	@objid ("b61536b1-2df5-41fc-b99e-982da0c79602")
	private String moveCost;

	@objid ("164c9342-f8d8-4246-9ca9-51a969254f83")
	private Square pivot;

	@objid ("d57670c4-f951-401f-a6d4-1e55ddd860ac")
	private Square [] boatPart;

	@objid ("ee5dbf40-2474-46af-8923-08df1a978c41")
	public Square [] square;

	@objid ("7792e884-e1c1-4f24-a745-3a285a50c7d3")
	public ActionSpécial actionSpécial;

	@objid ("02d2183f-ab50-48cd-a7c7-7c62d45b0ade")
	private Square[] occuped;

	@objid ("0494bc65-840e-4841-82ae-0d3272bcaf6b")
	public void shoot(Coord target) {
		int x = target.getX();
		int y = target.getY();
		Square part = getBoatPart(x, y);
		if (part != null) {
			if (part.isDestroyed) {
				//If a destoyed boat is targeted.
				System.out.println("Boat Part already destroyed");
			} else {
				part.destroy();
				//If a functional boat is targeted
				System.out.println("Boat Part touched");
			}
		}
		else {
			//If the sea is targeted
			System.out.println("Nothing touched");
		}
	}

	@objid ("472b38fc-f87c-44e6-9e76-b96a4c5d3f7b")
	public abstract void move();

	@objid ("901d66d1-b1e1-4f7b-8c07-246f568ba2db")
	public abstract void hourlyRotation();

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

	@objid ("c01df1a1-a009-44f4-a9a5-5e7c9d11b6ef")
	public abstract void antiHourlyRotation();

	@objid ("ea27622f-ef86-414e-a871-91e521f336d4")
	public void undoLastMove() {
	}

	@objid ("b5186b6f-fae1-4d24-9f3b-377baa516a55")
	public String getMoveCost() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.moveCost;
	}

	@objid ("101b32ca-ec00-4e67-9937-034520e8f2f8")
	public void setMoveCost(final String value) {
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

	@objid ("2da5b5ca-2907-436a-a330-f175ddec396f")
	public AbstractBoat() {
	}

	@objid ("dfb55234-05af-4b32-b572-882581380e93")
	public Square getPivot() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.pivot;
	}

	@objid ("21694e9f-8e7d-41a8-b512-40f58f0c6c9a")
	public void setPivot(final Square value) {
		// Automatically generated method. Please delete this comment before entering specific code.
		this.pivot = value;
	}

	@objid ("c15251b2-c1fb-4ac8-9800-21b0b04c201b")
	public Square getBoatPart(int x, int y) {
		//Browse all boats and get the part boat of the coord given, if it exists
		for (Square part : boatPart) {
			if (part.coord.getX() == x && part.coord.getY() == y) {
				return part;
			}
		}
		return null;
	}

	@objid ("b9904940-cd00-4cc0-a704-ac5cae79ea57")
	public void setBoatPart(final Square[] value) {
		// Automatically generated method. Please delete this comment before entering specific code.
		this.boatPart = value;
	}

	public int getSize() {
		return this.getSize();
	}

}
