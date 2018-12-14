package view;


import tools.ActionType;

import javax.swing.*;

public class ButtonGUI extends JButton{

	private static final long serialVersionUID = -8274321262994721188L;
	private String defaultText;
	private String activeText;
	private ButtonType type;
	private ButtonType state;
	

	public ButtonGUI(ButtonType type, String defaultText, String activeText) {
		super(defaultText);

		this.defaultText = defaultText;
		this.activeText = activeText;
		this.state = ButtonType.DEFAULT_STATE;
		this.type = type;
	}

	public void resetDefault(){
		this.setText(this.defaultText);
		this.state = ButtonType.DEFAULT_STATE;
	}

	public void setActive(){
		this.setText(this.activeText);
		this.state = ButtonType.CANCEL_STATE;
	}

	public ButtonType getType() {
		return type;
	}

	public ButtonType getState() {
		return state;
	}

	/**
	 * If you need to change button comportment on currentAction changes
	 * Just write your stuff here. It will be notified by the GameGUI
	 * as soon as a change occur.
	 *
	 * @param actionType the new action
	 */
	public void notifyActionChanged(ActionType actionType) {
		switch (this.getType()){
			case SHOOT:
				if(actionType == ActionType.SHOOT){
					this.setActive();
				}else{
					this.resetDefault();
				}
				break;
			case ROTATECW:
				break;
			case ROTATECCW:
				break;
			default:
				System.out.println("Error ButtonGUI wrong action type");
		}
	}
	
}