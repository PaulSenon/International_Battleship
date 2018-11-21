package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import tools.ActionType;
import controler.ControllerModelViewInterface;

public class GridGUIListener implements EventListener, MouseListener {

	private GridGUI gridGUI;
    private ControllerModelViewInterface controller;

	GridGUIListener(GridGUI gridGUI, ControllerModelViewInterface gameController){
		this.gridGUI = gridGUI;
		this.controller = gameController;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	    // TODO URGENT
		gridGUI.selectSquare(e.getX(), e.getY());
		ActionType actionType = this.controller.getCurrentAction();
		System.out.println("GRID LISTENER ACTION TYPE " + actionType);
		SquareGUI squareSelected = gridGUI.selectSquare(e.getX(), e.getY());
		if(actionType.equals(ActionType.SHOOT) /* && check enough ActiontPoint*/) {
			//Shoot on the target.
			this.controller.shoot(squareSelected);
		}else if(actionType.equals(ActionType.MOVE)){
			controller.moveBoat(squareSelected.getX()+2, squareSelected.getY()+2); //les coords de destination sont en durs pour le moment
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
