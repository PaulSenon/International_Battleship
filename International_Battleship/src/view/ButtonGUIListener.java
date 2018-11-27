package view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import controler.ControllerModelViewInterface;
import tools.ActionType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@objid ("d08db42b-d9c7-47cb-bb6c-514be0a9c2d4")
public class ButtonGUIListener implements ActionListener {

	@objid ("07628c86-32d9-4fc3-87b9-306a78896cba")
	private GameGUIInterface gameGUI;
	private ControllerModelViewInterface gameController;

	public ButtonGUIListener(GameGUIInterface gameGUI, ControllerModelViewInterface gameController) {
		this.gameGUI = gameGUI;
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ButtonGUI button;
		try{
			button = (ButtonGUI) e.getSource();
		}catch (Exception err){
			System.out.println("Error ButtonGUIListener : target is not type of ButtonGUI");
			return;
		}

		switch (button.getType()){
			case SHOOT:
				if(button.getState() == ButtonType.DEFAULT_STATE){
					button.setText("Annuler");
					button.switchState();
				}else if(button.getState() == ButtonType.CANCEL_STATE){
					// TODO maybe better if we store the last currentAction and restore it by un undo method on gameGUI
					button.resetDefaultText();
					button.switchState();
				}
				break;
			case ROTATECW:
				this.gameController.rotateBoatClockWise();
				break;
			case ROTATECCW:
				this.gameController.rotateBoatCounterClockWise();
				break;
			default:
				System.out.println("Error ButtonGUIListener wrong button type");
		}
	}

//	@Override
//	public void mouseDragged(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		//Check if the previous action is not SELECT
//		if (this.gameGUI.getCurrentAction() != ActionType.SELECT) {
//			//Check if the Button message is the default message or not and change the message of the Button.
//			// TODO use switch case on button text
//
//
//			if (battleShipButtonGUI.getText().equals(battleShipButtonGUI.getDefaultText())) {
//				if (battleShipButtonGUI.getText().contains("Tirer")){
//					//Change the actionType with the Button clicked on.
//					this.gameGUI.setCurrentAction(ActionType.SHOOT);
//				}
//				battleShipButtonGUI.setText("Annuler");
//			} else {
//				battleShipButtonGUI.setText(battleShipButtonGUI.getDefaultText());
//				this.gameGUI.setCurrentAction(ActionType.MOVE);
//			}
//		} else {
////			this.actionType = ActionType.SELECT;
//			JOptionPane.showMessageDialog(null, "Un bateau doit être sélectionné.", null , JOptionPane.INFORMATION_MESSAGE);
//		}
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//
//	}

}
