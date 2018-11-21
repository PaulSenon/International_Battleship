package tools;


/**
 * __TESTED__
 * An enum for the sprite (this image fragment) direction.
 * !!!! IT ASSUME THAT YOUR SPRITE IS FACING EAST BY DEFAULT !!!!
 */
public enum Direction{
    NORTH(-90),
    EAST(0),
    SOUTH(90),
    WEST(180);

    public int rotation;

    Direction(int rotation) {
        this.rotation = rotation;
    }
    private static Direction[] vals = values();

    public Direction next(boolean clockWise){
        if(clockWise){
            return vals[(this.ordinal()+1) % vals.length];
        } else {
            int nextPos = this.ordinal()-1;
            int length = vals.length;
            int index = nextPos % length;
            if (index < 0)
            {
                index += length;
            }
            return vals[index];
        }
    }

    public static Direction DEFAULT(){
        return EAST;
    }
}

