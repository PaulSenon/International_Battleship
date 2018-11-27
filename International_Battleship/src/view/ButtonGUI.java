package view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import javax.swing.*;

@objid ("db9b5155-92c9-431f-aead-5e6646284610")
public class ButtonGUI extends JButton{

	private static final long serialVersionUID = -8274321262994721188L;
	private String defaultText;
	private ButtonType type;
	private ButtonType state;
	

	public ButtonGUI(ButtonType type, String defaultText) {
		super(defaultText);

		this.defaultText = defaultText;
		this.state = ButtonType.DEFAULT_STATE;
		this.type = type;
	}

	public void resetDefaultText(){
		this.setText(this.defaultText);
	}

	public ButtonType getType() {
		return type;
	}

	public ButtonType getState() {
		return state;
	}

	public void switchState(){
		if(this.state == ButtonType.DEFAULT_STATE){
			this.state = ButtonType.CANCEL_STATE;
		}else if (this.state == ButtonType.CANCEL_STATE){
			this.state = ButtonType.DEFAULT_STATE;
		}
	}



	//	/**
//	 * This methods initializes the defaulText value of the Button by the String given for the construction of the Button.
//	 * @param buttonMessage
//	 */
//	private void setDefaultText(String buttonMessage) {
//		this.defaultText = buttonMessage;
//	}
//
//	/**
//	 * This method returns the defaultText value of the Button.
//	 * @return
//	 */
//	public String getDefaultText() {
//		return this.defaultText;
//	}
	
}