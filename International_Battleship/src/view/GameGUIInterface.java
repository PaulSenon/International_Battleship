package view;

import tools.ActionType;
import tools.ProcessedPosition;

public interface GameGUIInterface {

    void setCurrentAction(ActionType move);

    void setBoatPos(ProcessedPosition processedPosition);

    void setBoatDirection(ProcessedPosition processedPosition);

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
