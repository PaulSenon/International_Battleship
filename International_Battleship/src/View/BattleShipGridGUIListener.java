package View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import tools.ActionType;
import tools.Coord;
import Controler.BattleshipGameControlerModelView;

public class BattleShipGridGUIListener implements EventListener, MouseListener {

	private BattleShipGridGUI gridGUI;
	private BattleshipGameControlerModelView controller;
	
	BattleShipGridGUIListener(BattleShipGridGUI gridGUI, BattleshipGameControlerModelView controller){
		this.gridGUI = gridGUI;
		this.controller = controller;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Coord coord = gridGUI.selectSquare(e.getX(), e.getY());
		ActionType action = controller.selectBoat(coord.getX(), coord.getY());
		if(action==ActionType.MOVE){
			controller.moveBoat(coord.getX()+5, coord.getY()+5);
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
