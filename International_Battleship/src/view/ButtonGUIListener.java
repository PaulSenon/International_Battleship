package view;


import controler.ControllerModelViewInterface;
import tools.ActionType;
import tools.MessageManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonGUIListener implements ActionListener {

		private ControllerModelViewInterface gameController;

	public ButtonGUIListener(ControllerModelViewInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// reset txt console GUI
		MessageManager.displayMessageConsole("GUI", "");

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
					gameController.requestActionType(ActionType.SHOOT);
				}else if(button.getState() == ButtonType.CANCEL_STATE){
					gameController.requestActionType(ActionType.DEFAULT());
				}
				break;
			case ROTATECW:
				this.gameController.rotateBoatClockWise();
				break;
			case ROTATECCW:
				this.gameController.rotateBoatCounterClockWise();
				break;
			case SPECIALACTION:
				if(button.getState() == ButtonType.DEFAULT_STATE){
					gameController.requestActionType(ActionType.SPECIAL);
				}else if(button.getState() == ButtonType.CANCEL_STATE){
					gameController.requestActionType(ActionType.DEFAULT());
				}
				break;
			case ENDTURN:
				this.gameController.EndActionsOfPlayer();
				break;
			default:
				System.out.println("Error ButtonGUIListener wrong button type");
		}
	}

}
