package tools;


/**
 * An enum for the sprite (this image fragment) direction.
 * !!!! IT ASSUME THAT YOUR SPRITE IS FACING EAST BY DEFAULT !!!!
 */
public enum Direction{
        NORTH(-90),
        EAST(0),
        SOUTH(90),
        WEST(180),
        DEFAULT(EAST);

        public int rotation;

    Direction(int rotation) {
        this.rotation = rotation;
    }
    Direction(tools.Direction direction){
        this.rotation =  direction.rotation;
    }
}

