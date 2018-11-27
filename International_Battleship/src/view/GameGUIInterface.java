package view;

import tools.Coord;
import tools.ActionType;
import tools.ProcessedPosition;
import tools.ResultShoot;

import javax.swing.*;
import java.util.List;

public interface GameGUIInterface {

    void setCurrentAction(ActionType move);

    void setBoatPos(Coord coord);

    void setBoatDirection(ProcessedPosition processedPosition);

	ActionType getCurrentAction();

    void messageToUser(ResultShoot shoot);

    void repaintAllButtons();

    void changeButtonText(ButtonGUI button, String text);

    List<ButtonGUI> getListButton();

    // OLD
//    void setBoatToMove();
//
//    void setVisibility();
//
//    void movePiece();
//
//    void undoMovePiece();
}
