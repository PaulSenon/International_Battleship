package tools;



import java.io.Serializable;

public class Coord implements Serializable {
	private static final long serialVersionUID = 1L;

	    private int x;
        private int y;

        public Coord(int x, int y) {
    	this.x = x;
    	this.y = y;
    }

        public int getY() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.y;
    }

        public int getX() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.x;
    }

    public void setX(int x) {
    	this.x = x;
	}

	public void setY(int y) {
    	this.y = y;
	}

	public void addStepDirection(Direction direction, int step){
    	switch (direction){
			case EAST: this.x += step; break;
			case WEST: this.x -= step; break;
			case SOUTH: this.y += step; break;
			case NORTH: this.y -= step; break;
		}
	}

	public Coord sub(Coord coord){
    	return new Coord(this.x - coord.getX(), this.y - coord.getY());
	}

	public Coord add(Coord coord){
		return new Coord(this.x + coord.getX(), this.y + coord.getY());
	}

	public int getDistanceTo(Coord coord){
		Coord tmp = coord.sub(this);
		return tmp.getY() + tmp.getX();
	}

	@Override
	public String toString() {
		return "(" + x + ";" + y + ")";
	}

	/**
	 * Required to be used as key in hashMap
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}


	@Override
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
