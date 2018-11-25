package view;

import tools.ActionType;
import tools.ProcessedPosition;

public interface GameGUIInterface {

    void setCurrentAction(ActionType move);

	ActionType getCurrentAction();

    void setSelectedBoatByCoord(int x, int y);

    void setProcessedPotion(ProcessedPosition processedPosition);
}
