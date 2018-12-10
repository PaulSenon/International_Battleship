package view;

import model.BoatInterface;
import model.BoatName;
import tools.ActionType;
import tools.Coord;
import tools.ProcessedPosition;

import java.util.List;
import java.util.Map;

public interface GameGUIInterface {

    void setCurrentAction(ActionType move);

	ActionType getCurrentAction();

    void setProcessedPotion(ProcessedPosition processedPosition);

    List<BoatInterface> getListOfBoat();

    void setListOfBoat(List<BoatInterface> listOfBoat);

    void initGame(Map<BoatName,ProcessedPosition> initBoatPos);

    void setSelectedBoat(ProcessedPosition processedPosition);

    void message(String message);

    void setVisibleCoord(List<Coord> visibleCoords);
}
