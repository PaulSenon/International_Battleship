package view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import controler.ControllerModelViewInterface;
import tools.ActionType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@objid ("d08db42b-d9c7-47cb-bb6c-514be0a9c2d4")
public class ButtonGUIListener implements ActionListener {

	@objid ("07628c86-32d9-4fc3-87b9-306a78896cba")
	private ControllerModelViewInterface gameController;

	public ButtonGUIListener(ControllerModelViewInterface gameController) {
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
					gameController.requestActioType(ActionType.SHOOT);
				}else if(button.getState() == ButtonType.CANCEL_STATE){
					gameController.requestActioType(ActionType.MOVE);
				}
				break;
			case ROTATECW:
				this.gameController.rotateBoatClockWise();
				break;
			case ROTATECCW:
				this.gameController.rotateBoatCounterClockWise();
				break;
			case ENDTURN:
				this.gameController.EndActionsPlayer();
				break;
			default:
				System.out.println("Error ButtonGUIListener wrong button type");
		}
	}

}
