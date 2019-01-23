package view;

import model.BoatInterface;
import tools.ActionType;
import tools.Coord;
import tools.ProcessedPosition;
import tools.ResultShoot;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public interface GameGUIInterface {

    void setCurrentAction(ActionType move);

	ActionType getCurrentAction();

    void setProcessedPosition(ProcessedPosition processedPosition);

    List<BoatInterface> getListOfBoat();

    void setListOfBoat(List<BoatInterface> listOfBoat);

    void initGame(Map<Integer,ProcessedPosition> initBoatPos, Map<Integer, Integer> boatRelatedToPlayer);

    void setSelectedBoat(ProcessedPosition processedPosition);

    void message(String message);

    void setNbAP(int actionPoint);

    void disableAction();

    void messagePopUp(String message);

    void setVisibleCoord(List<Coord> visibleCoords, Map <Coord, Color> visibleCoordsPort);

    void displayResult(ResultShoot first, Coord target);

	void setVisibleBoats(List<Coord> list);
	
	void setVisibleMines(List<Coord> list);

    void setControlsEnabled(Boolean enable);

    void setButtonEnabled(ButtonType type, boolean enabled);

    void resetButtonDefaultState(ButtonType type);

    boolean boatIsSelected();

    void setButtonHighLight(ButtonType type, boolean highlighted);
}
