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

	ActionType getCurrentAction();

    void setSelectedBoatByCoord(int x, int y);

    void setProcessedPotion(ProcessedPosition processedPosition);

    List<BoatInterface> getListOfBoat();

    void setListOfBoat(List<BoatInterface> listOfBoat);

    void initGame(Map<BoatName,ProcessedPosition> initBoatPos);
}
