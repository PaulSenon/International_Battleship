package view;

import tools.ActionType;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import controler.ControllerModelViewInterface;

@objid ("d08db42b-d9c7-47cb-bb6c-514be0a9c2d4")
public class ButtonGUIListener implements MouseListener, MouseMotionListener {

	@objid ("07628c86-32d9-4fc3-87b9-306a78896cba")
	public ButtonGUI battleShipButtonGUI;
	private ControllerModelViewInterface controller;

	public ButtonGUIListener(ButtonGUI battleShipButtonGUI, ControllerModelViewInterface gameController) {
		this.battleShipButtonGUI = battleShipButtonGUI;
		this.controller = gameController;
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
	public void mouseClicked(MouseEvent e){
	    ActionType actionType = this.controller.getCurrentAction();
		//Check that the previous action is not select
		if (actionType != ActionType.SELECT){
			//Check if the Button message is the default message or not and change the message of the Button.
			if (this.battleShipButtonGUI.getText().equals(this.battleShipButtonGUI.getDefaultText())){
				if (this.battleShipButtonGUI.getText().contains("Tirer")){
					//Change the actionType with the Button clicked on.
                    actionType = ActionType.SHOOT;
                    //Change the tet of the button.
                    this.controller.changeButtonText(this.battleShipButtonGUI,"Annuler");
                }
            } else {
			    this.controller.changeButtonText(this.battleShipButtonGUI, this.battleShipButtonGUI.getDefaultText());
                actionType = ActionType.MOVE;
			}
		} else {
            actionType = ActionType.SELECT;
			JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
		}
		//Change the actionType in the controller.
		this.controller.setCurrentAction(actionType);
		System.out.println(actionType);
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
