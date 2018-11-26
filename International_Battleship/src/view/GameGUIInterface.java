package view;

import model.BoatInterface;
import model.BoatName;
import tools.Coord;
import tools.ActionType;
import tools.ProcessedPosition;

import java.util.List;
import java.util.Map;

public interface GameGUIInterface {

    void setCurrentAction(ActionType move);

    void setBoatPos(Coord coord);

    void setBoatDirection(ProcessedPosition processedPosition);

	ActionType getCurrentAction();

    List<BoatInterface> getListOfBoat();

    void setListOfBoat(List<BoatInterface> listOfBoat);

    void initGame(Map<BoatName,ProcessedPosition> initBoatPos);

    // OLD
//    void setBoatToMove();
//
//    void setVisibility();
//
//    void movePiece();
//
//    void undoMovePiece();
}
