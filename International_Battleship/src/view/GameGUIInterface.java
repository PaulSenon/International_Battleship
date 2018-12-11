package view;

import model.BoatInterface;
import model.BoatName;
import tools.ActionType;
import tools.ProcessedPosition;

import javax.swing.*;
import java.io.IOException;
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

    void setNbAP(int actionPoint);

    void disableAction();
}
