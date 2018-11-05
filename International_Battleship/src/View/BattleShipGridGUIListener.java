package View;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JLayeredPane;

import Controler.BattleshipGameControlerModelView;
import Model.Coord;

public class BattleShipGridGUIListener implements EventListener, MouseListener {

	private BattleShipGridGUI battleShipGridGUI;
    private BattleshipGameControlerModelView boatControler;
    private	BattleShipBoatFragmentGUI boat;
	private int xAdjustment, yAdjustment;
	private Coord boatCoordSelect;
	
	BattleShipGridGUIListener(BattleShipGridGUI gridGUI, BattleshipGameControlerModelView controller){
		this.battleShipGridGUI = gridGUI;
		this.boatControler = controller;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		battleShipGridGUI.selectSquare(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		this.boat = null;
		this.boatCoordSelect = null;
		Component c = this.battleShipGridGUI.findComponentAt(x, y);
		
		if(c instanceof BattleShipBoatFragmentGUI){
			this.boatCoordSelect = battleShipGridGUI.getCoordSquareGUI(x, y);
			Point coinCase = c.getParent().getLocation();
			Point coinPiece = c.getLocation();
			
			xAdjustment = coinCase.x + coinPiece.x - e.getX();
			yAdjustment = coinCase.y + coinPiece.y - e.getY();
			
			boat = (BattleShipBoatFragmentGUI) c;
			boatControler.ActionWhenBoatIsSelectedOnGUI(boatCoordSelect);
			boat.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
			boat.setSize(boat.getWidth(), boat.getHeight());
			battleShipGridGUI.add(boat,JLayeredPane.DRAG_LAYER);
		}
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(boat != null){

			boat.setVisible(false);
			battleShipGridGUI.setLayer(boat, JLayeredPane.DEFAULT_LAYER, -1);

			Coord target = battleShipGridGUI.getCoordSquareGUI(x,y);
			boatControler.ActionWhenBoatIsMovedOnGUI(this.boatCoordSelect, target);

			battleShipGridGUI.repaint();
			boat.setVisible(true);
		}
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
