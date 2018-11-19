package View;

import tools.Coord;
import tools.ActionType;
import tools.Direction;

public interface GameGUIInterface {

    void setCurrentAction(ActionType move);

    void setBoatPos(Coord coord);

    void setBoatDirection(Direction direction);

	ActionType getCurrentAction();

    // OLD
//    void setBoatToMove();
//
//    void setVisibility();
//
//    void movePiece();
//
//    void undoMovePiece();
}
