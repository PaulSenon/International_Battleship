package View;

import Controler.BattleShipControlerLocal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d08db42b-d9c7-47cb-bb6c-514be0a9c2d4")
public class BattleShipButtonGUIListener implements MouseListener, MouseMotionListener {

	@objid ("07628c86-32d9-4fc3-87b9-306a78896cba")
	public BattleShipButtonGUI battleShipButtonGUI;

	@objid ("ffb1b71d-cf50-4015-9e28-b6318774d0f9")
	public BattleShipControlerLocal controler;

	private String actionChosen;
	private String currentAction;

	public BattleShipButtonGUIListener(BattleShipButtonGUI battleShipButtonGUI) {
		this.battleShipButtonGUI = battleShipButtonGUI;
		this.currentAction  = controler.getCurrentAction();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Check if the Button message is the default message or not and change the message of the Button.
		if (battleShipButtonGUI.getText().equals(battleShipButtonGUI.getDefaultText())) {
			this.actionChosen = battleShipButtonGUI.getText();
			battleShipButtonGUI.setText("Annuler");
		} else {
			String defaultText = battleShipButtonGUI.getDefaultText();
			battleShipButtonGUI.setText(defaultText);
			this.actionChosen = "None";
		}
		controler.setCurrentAction(this.actionChosen);
		System.out.println(this.actionChosen);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) { 
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
