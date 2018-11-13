package View;

import javax.swing.JButton;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("db9b5155-92c9-431f-aead-5e6646284610")
public class BattleShipButtonGUI extends JButton{

	private static final long serialVersionUID = -8274321262994721188L;
	private String defaultText;
	
	/**
	 * This method creates a JButton with a message inside
	 * @param buttonMessage
	 */
	public BattleShipButtonGUI(String buttonMessage) {
		setDefaultText(buttonMessage);
		this.setText(buttonMessage);
	}
	
	/**
	 * This methods initializes the defaulText value of the Button by the String given for the construction of the Button.
	 * @param buttonMessage
	 */
	private void setDefaultText(String buttonMessage) {
		this.defaultText = buttonMessage;
	}

	/**
	 * This method returns the defaultText value of the Button.
	 * @return
	 */
	public String getDefaultText() {
		return this.defaultText;
	}
	
}