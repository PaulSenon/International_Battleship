package View;

import tools.Coord;
import tools.ActionType;
import tools.Direction;
import tools.ProcessedPosition;

public interface GameGUIInterface {

    void setCurrentAction(ActionType move);

    void setBoatPos(Coord coord);

    void setBoatDirection(ProcessedPosition processedPosition);

    // OLD
//    void setBoatToMove();
//
//    void setVisibility();
//
//    void movePiece();
//
//    void undoMovePiece();
}
