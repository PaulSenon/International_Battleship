package View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import Controler.BattleShipControlerLocal;

public class BattleShipGridGUIListener implements EventListener, MouseListener {

	private BattleShipGridGUI gridGUI;
	private String actionChosen;
	private BattleShipControlerLocal controler;
	
	BattleShipGridGUIListener(BattleShipGridGUI gridGUI/*, BattleshipGameControlerModelView controller*/){
		this.gridGUI = gridGUI;
//		this.actionChosen = controler.getCurrentAction();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		BattleShipSquareGUI squareSelected = gridGUI.selectSquare(e.getX(), e.getY());
		System.out.println("Je suis là " + this.actionChosen);
		if (squareSelected != null && this.actionChosen.equals("Tirer")) {
			System.out.println("Je tire sur la case blabla");
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
