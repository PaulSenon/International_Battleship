package view;

import controler.ControllerModelViewInterface;
import tools.ActionType;
import tools.Coord;
import tools.MessageManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

public class GridGUIListener implements EventListener, MouseListener {

	private GridGUI gridGUI;
    private ControllerModelViewInterface controller;

	GridGUIListener(GridGUI gridGUI, ControllerModelViewInterface gameController){
		this.gridGUI = gridGUI;
		this.controller = gameController;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// reset txt console GUI
		MessageManager.displayMessageConsole("GUI", "");

	    // TODO URGENT
		Coord coordSquare = gridGUI.selectSquare(e.getX(), e.getY());

		ActionType actionType = this.gridGUI.getCurrentAction();
		System.out.println("GRID LISTENER ACTION TYPE " + actionType);

		switch (actionType){
			case SELECT:
				this.controller.selectBoat(coordSquare.getX(), coordSquare.getY());
				break;
			case MOVE:
				this.controller.moveBoat(coordSquare.getX(), coordSquare.getY());
				break;
			case SHOOT:
				this.controller.shoot(coordSquare.getX(), coordSquare.getY());
				break;
			case SPECIAL:
				this.controller.specialAction(coordSquare.getX(), coordSquare.getY());
				break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
