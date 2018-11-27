package tools;

public class CoordInfos extends Coord {

    //TODO : during the creation of the boat set all part destroyed as false
    private boolean destroyed = false;

    public CoordInfos(int x, int y) {
        super(x, y);
    }

    public boolean getState() {
        return this.destroyed;
    }

    public void destroy() {
        this.destroyed = true;
    }

}
