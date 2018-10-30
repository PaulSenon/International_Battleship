package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("32513812-f778-410d-8f17-b0cd7e2f800d")
public class Coord {
    @objid ("00be0f63-df7c-40e7-bcd2-30549050842b")
    private int x;

    @objid ("b2e2c4cd-0c6c-408f-be62-c2c5020eb1ef")
    private int y;

    @objid ("03cecc97-4fd5-4b93-bb0b-65177bf286ec")
    public Coord(int x, int y) {
    	this.x=x;
    	this.y=y;
    }
    
    @objid ("9abc1ab7-259e-4e8c-ae79-9896ff6bab87")
    public int getY() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.y;
    }

    @objid ("a1c62fb6-fc56-4ff3-b1a2-320690a08434")
    public int getX() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.x; 
    }

    public String toString(){
    	return "[x=" + x + ", y=" + y + "]";
    }
    
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
