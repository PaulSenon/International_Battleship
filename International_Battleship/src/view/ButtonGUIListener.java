package view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import controler.ControllerModelViewInterface;
import tools.ActionType;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

@objid ("d08db42b-d9c7-47cb-bb6c-514be0a9c2d4")
public class ButtonGUIListener implements MouseListener, MouseMotionListener {

	@objid ("07628c86-32d9-4fc3-87b9-306a78896cba")
	public ButtonGUI battleShipButtonGUI;
	private ActionType actionType;
	private ControllerModelViewInterface controller;

	public ButtonGUIListener(ButtonGUI battleShipButtonGUI, ControllerModelViewInterface gameController) {
		this.battleShipButtonGUI = battleShipButtonGUI;
		this.controller = gameController;
		this.actionType = this.controller.getCurrentAction();
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
		//Check that the previous action is not select
		if (this.actionType != ActionType.SELECT) {
			//Check if the Button message is the default message or not and change the message of the Button.
			if (battleShipButtonGUI.getText().equals(battleShipButtonGUI.getDefaultText())) {
				if (battleShipButtonGUI.getText().contains("Tirer")){
					//Change the actionType with the Button clicked on.
					this.actionType = ActionType.SHOOT;
				}
				battleShipButtonGUI.setText("Annuler");
			} else {
				battleShipButtonGUI.setText(battleShipButtonGUI.getDefaultText());
				this.actionType = ActionType.MOVE;
			}
		} else {
			this.actionType = ActionType.SELECT;
			JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
		}
		//Change the actionType in the controller.
		this.controller.setCurrentAction(this.actionType);
		System.out.println(this.actionType);
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
