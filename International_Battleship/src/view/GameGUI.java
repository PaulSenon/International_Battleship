package view;


import controler.ControllerModelViewInterface;
import model.BoatInterface;
import tools.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Map;

public class GameGUI extends JFrame implements GameGUIInterface{

	private static final long serialVersionUID = 7636412061294453620L;
	private final JTextArea textArea;
    private ActionPointGUI PAPanel;
    private List<BoatInterface> listOfBoat;
    private GridGUI gridGUI;
    private List<ButtonGUI> buttons;
    private ButtonGUI buttonGUIShoot;
    private ButtonGUI buttonRotateCounterClockWise;
	private ButtonGUI buttonRotateClockWise;
    private ButtonGUI buttonGUISpecialAction;
    private ButtonGUI buttonGUIEndOfTurn;
	private final JLabel counterPA;
    private List<ButtonGUI> listOfButtons;
    private ActionType currentAction;


    // Constructor
    public GameGUI(){
    	super();
    	this.setMinimumSize(new Dimension(1200, 800));
		this.buttons = new ArrayList<>();
    	Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());

    	// Create grid and add it to the left
			this.gridGUI = new GridGUI();
			JPanel gridContainer = new JPanel(new BorderLayout());
			gridContainer.setBackground(Color.BLACK);
			gridContainer.add(this.gridGUI);

			// tweak GridBagConstraints for GridGUI
		        GridBagConstraints c = new GridBagConstraints();
		        c.anchor = GridBagConstraints.FIRST_LINE_START;
		        c.fill = GridBagConstraints.BOTH;
		        c.gridx = 0;
		        c.gridy = 0;
		        c.weightx = 100.0;
		        c.weighty = 100.0;
			contentPane.add(gridContainer, c);

		// Create controlPanel and add it to the left
			ControlPanel controlsPanel = new ControlPanel();

			// Create of Button in the "actions" panel
				this.buttonGUIShoot = (ButtonGUI) controlsPanel.getButtonGUIShoot();
				this.buttonGUIShoot.setIcon(new ImageIcon(ImageManager.getImageCopy("fire.png")));
				this.buttonGUIShoot.setBorder(null);
				this.buttons.add(this.buttonGUIShoot);

				this.buttonRotateClockWise = (ButtonGUI) controlsPanel.getButtonRotateClockWise();
				this.buttonRotateClockWise.setIcon(new ImageIcon(ImageManager.getImageCopy("rotateclockwise.png")));
				this.buttonRotateClockWise.setBorder(null);
				this.buttons.add(this.buttonRotateClockWise);

				this.buttonRotateCounterClockWise =  (ButtonGUI) controlsPanel.getButtonRotateCounterClockWise();
				this.buttonRotateCounterClockWise.setIcon(new ImageIcon(ImageManager.getImageCopy("rotateclockcounterwise.png")));
				this.buttonRotateCounterClockWise.setBorder(null);
				this.buttons.add(this.buttonRotateCounterClockWise);

				this.buttonGUISpecialAction = (ButtonGUI) controlsPanel.getButtonGUISpecialAction();
				this.buttonGUISpecialAction.setIcon(new ImageIcon(ImageManager.getImageCopy("specialAction.png")));
				this.buttonGUISpecialAction.setBorder(null);
				this.buttons.add(this.buttonGUISpecialAction);

				this.buttonGUIEndOfTurn =(ButtonGUI) controlsPanel.getButtonGUIEndOfTurn();
				this.buttonGUIEndOfTurn.setBackground(Color.black);
				this.buttons.add(this.buttonGUIEndOfTurn);

			//Store buttons in a list
				this.listOfButtons = new ArrayList<>();
				listOfButtons.add(this.buttonGUIShoot);
				listOfButtons.add(this.buttonRotateClockWise);
				listOfButtons.add(this.buttonRotateCounterClockWise);
				listOfButtons.add(this.buttonGUISpecialAction);
				listOfButtons.add(this.buttonGUIEndOfTurn);

			//Affect text area
				this.textArea = controlsPanel.getConsoleTextArea();
				this.textArea.setEditable(false);

		    //Affect PA Panel in the south of BorderLayout
                this.PAPanel = (ActionPointGUI) controlsPanel.getPAPanel();
                this.counterPA = controlsPanel.getCounterPA();
                this.counterPA.setText(this.PAPanel.getCounterPA());

			// tweak GridBagConstraints for "action" panel
				c.fill = GridBagConstraints.BOTH;
				c.gridx = 1;
				c.gridy = 0;
				c.weightx = 1.0;
				c.weighty = 1.0;
			// Add ControlPanel to the GeneralPanel
			contentPane.add(controlsPanel.$$$getRootComponent$$$(), c);
    }

    /**
     * This method enbale to init the Listener in launcher
     * @param gameController
     */
    public void initListeners(ControllerModelViewInterface gameController){
		// Create and attach event listener on grid GUI
		EventListener mouseEventListener = new GridGUIListener( this.gridGUI, gameController);
		this.gridGUI.addMouseListener((MouseListener) mouseEventListener);
		ActionListener buttonListener = new ButtonGUIListener(gameController);
		this.buttonGUIShoot.addActionListener(buttonListener);
		this.buttonRotateCounterClockWise.addActionListener(buttonListener);
		this.buttonRotateClockWise.addActionListener(buttonListener);
		this.buttonGUISpecialAction.addActionListener(buttonListener);
		this.buttonGUIEndOfTurn.addActionListener(buttonListener);
	}

	@Override
	public void setCurrentAction(ActionType currentAction) {
		this.currentAction = currentAction;
		this.gridGUI.setCurrentAction(currentAction);
		System.out.println("GameGUI : Action is now : "+this.currentAction);

		notifyChanges();
	}

	/**
	 * Used to notify some classes of some changes
	 */
	private void notifyChanges(){
    	// notify all buttons
		for(ButtonGUI button : this.listOfButtons){
			button.notifyActionChanged(this.currentAction);
		}

		// add some other notif here if you need it
	}

	@Override
	public ActionType getCurrentAction() {
		return this.currentAction;
	}

	@Override
	public void setProcessedPosition(ProcessedPosition processedPosition) {
		this.gridGUI.setProcessedPosition(processedPosition);
	}

	public List<BoatInterface> getListOfBoat() {
		return this.listOfBoat;
	}

    @Override
	public void setListOfBoat(List<BoatInterface> listOfBoat) {
		this.listOfBoat = listOfBoat;
	}

	public void initGame(Map<Integer,ProcessedPosition> initBoatPos, Map<Integer, Integer> boatRelatedToPlayer){
		this.gridGUI.initGrid(initBoatPos, boatRelatedToPlayer);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void message(String message) {
		// TODO add visual
		System.out.println("MESSAGE TO USER : "+message);
	}

	public void messagePopUp(String message){
		JOptionPane.showMessageDialog(null, message, null , JOptionPane.INFORMATION_MESSAGE);
	}

    @Override
	public void setVisibleCoord(List<Coord> visibleCoords, Map <Coord, Color> visibleCoordsPort){
		this.gridGUI.setVisibleCoords(visibleCoords, visibleCoordsPort);
	}

	/**
     * This method propagate the info to the Grid that a boat is selected
     * @param processedPosition the processed pos containing all infos about the boat
     */
	@Override
	public void setSelectedBoat(ProcessedPosition processedPosition) {
	    this.gridGUI.setSelectedBoat(processedPosition);
	}

    /**
     * Distribute the controller request when the nb of ActionPoint is changed to the ActionPoint class
     * @param nbActionPoint
     */
	public void setNbAP(int nbActionPoint) {
		this.PAPanel.setNbAP(nbActionPoint);
		this.counterPA.setText(this.PAPanel.getCounterPA());
    }

    /**
     * Distribute the controller request when the nb of ActionPoint is equal zero to the ActionPoint Class
     */
    @Override
    public void disableAction() {
        this.PAPanel.disableAction();
    }

	public void displayResult(ResultShoot result, Coord target){
		if(result != ResultShoot.FORBIDDEN){
			this.gridGUI.displayResult(result, target);
		}
		if(!result.equals(ResultShoot.DESTROYED)) {
			this.textArea.setText("");
		}
		else {
			this.textArea.setText("Le bateau ciblé a été détruit.");
		}

		// TODO c'est pas beau mais sinon
		this.revalidate();
		this.repaint();
    }

	@Override
	public void setVisibleBoats(List<Coord> visibleCoordCurrentPlayer) {
		this.gridGUI.setVisibleBoats(visibleCoordCurrentPlayer);
		this.revalidate();
		this.repaint();
	}

	public void setControlsEnabled(Boolean enable){
		for (ButtonGUI button: this.buttons) {
			button.setEnabled(enable);
		}
	}

	public void setButtonEnabled(ButtonType type, boolean enabled){
    	for(ButtonGUI button: this.buttons){
    		if(button.getType() == type){
    			button.setEnabled(enabled);
			}
		}
	}

	public void resetButtonDefaultState(ButtonType type){
		for(ButtonGUI button: this.buttons){
			if(button.getType() == type){
				button.resetDefault();
			}
		}
	}

	public void setButtonHighLight(ButtonType type, boolean highlighted) {
		for(ButtonGUI button: this.buttons){
			if(button.getType() == type){
				button.setHighlighted(highlighted);
			}
		}
	}

    public boolean boatIsSelected(){
    	return this.gridGUI.boatIsSelected();
	}

}