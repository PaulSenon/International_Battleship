package view;


import controler.ControllerModelViewInterface;
import model.BoatInterface;
import tools.ActionType;
import tools.Coord;
import tools.ProcessedPosition;
import tools.ResultShoot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Map;

public class GameGUI extends JFrame implements GameGUIInterface{

	private final JTextArea textArea;
    private static final long serialVersionUID = 7636412061294453620L;
    private ActionPointGUI PAPanel;
    private List<BoatInterface> listOfBoat;
    private GridGUI gridGUI;
    private List<ButtonGUI> buttons;
    private ButtonGUI buttonGUITirer;
    private ButtonGUI buttonRotateCounterClockWise;
	private ButtonGUI buttonRotateClockWise;
    private ButtonGUI buttonGUIDéplacer;
    private ButtonGUI buttonGUIActionSpéciale;
    public ButtonGUI battleShipButtonGUI;
    public ButtonGUI buttonGUIFinTour;
    public GridGUI battleShipGridGUI;

    public List<ButtonGUI> listOfButtons;

    public ActionType actionType;


    // Constructor
    public GameGUI(){
    	super();
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


		// TODO currently, just a placeholder
		// Create "actions" panel and add it to the right withe a borderlayout
            JPanel layoutControlPanel = new JPanel();
            layoutControlPanel.setLayout(new BorderLayout());

            //Add Button on Center of BorderLayout
                JPanel controlsPanel = new JPanel();
                controlsPanel.setLayout(new GridBagLayout());
                    GridBagConstraints buttonsConstraints = new GridBagConstraints();
                    buttonsConstraints.gridx = 0;
                    buttonsConstraints.gridy = 0;
                controlsPanel.setBackground(Color.GRAY);

                // Create of Button in the "actions" panel
                    this.buttonGUITirer = new ButtonGUI(ButtonType.SHOOT, "Tirer", "Annuler");
                    controlsPanel.add(this.buttonGUITirer, buttonsConstraints);
                    this.buttons.add(this.buttonGUITirer);

                    buttonsConstraints.gridy++;
                    this.buttonRotateClockWise =  new ButtonGUI(ButtonType.ROTATECW, "RotateCW", "");
                    controlsPanel.add(this.buttonRotateClockWise, buttonsConstraints);
					this.buttons.add(this.buttonRotateClockWise);

                    buttonsConstraints.gridy++;
                    this.buttonRotateCounterClockWise =  new ButtonGUI(ButtonType.ROTATECCW, "RotateCCW", "");
                    controlsPanel.add(this.buttonRotateCounterClockWise, buttonsConstraints);
					this.buttons.add(this.buttonRotateCounterClockWise);

					buttonsConstraints.gridy++;
					this.buttonGUIActionSpéciale = new ButtonGUI(ButtonType.SPECIALACTION, "Action Spéciale", "Annuler");
					controlsPanel.add(this.buttonGUIActionSpéciale, buttonsConstraints);
					this.buttons.add(this.buttonGUIActionSpéciale);

					buttonsConstraints.gridy++;
					this.buttonGUIFinTour = new ButtonGUI(ButtonType.ENDTURN, "Fin de tour", "Annuler");
					controlsPanel.add(this.buttonGUIFinTour, buttonsConstraints);
					this.buttons.add(this.buttonGUIFinTour);


                //Store buttons in a list // TODO is this useful ?
                    this.listOfButtons = new ArrayList<>();
                    listOfButtons.add(this.buttonGUITirer);
                    listOfButtons.add(this.buttonRotateClockWise);
                    listOfButtons.add(this.buttonRotateCounterClockWise);
                    listOfButtons.add(this.buttonGUIActionSpéciale);
                    listOfButtons.add(this.buttonGUIFinTour);

				//Create text area
					this.textArea = new JTextArea();
					this.textArea.setEditable(false);
					layoutControlPanel.add(this.textArea, BorderLayout.NORTH);

            // Add ButtonPanel in center of the BorderLayout
			    layoutControlPanel.add(controlsPanel, BorderLayout.CENTER);

		    //Create PA Panel in the south of BorderLayout
                this.PAPanel = new ActionPointGUI();
                PAPanel.setBackground(Color.GRAY);
            // Add ButtonPanel in center of the BorderLayout
                layoutControlPanel.add(PAPanel, BorderLayout.SOUTH);

        // tweak GridBagConstraints for "action" panel
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 1;
            c.gridy = 0;
            c.weightx = 1.0;
            c.weighty = 1.0;
        // Add LayoutControlPanel to the GeneralPanel
        contentPane.add(layoutControlPanel, c);
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
		this.buttonGUITirer.addActionListener(buttonListener);
		this.buttonRotateCounterClockWise.addActionListener(buttonListener);
		this.buttonRotateClockWise.addActionListener(buttonListener);
		this.buttonGUIActionSpéciale.addActionListener(buttonListener);
		this.buttonGUIFinTour.addActionListener(buttonListener);
	}

	@Override
	public void setCurrentAction(ActionType actionType) {
		this.actionType = actionType;
		this.gridGUI.setCurrentAction(actionType);
		System.out.println("GameGUI : Action is now : "+this.actionType);

		notifyChanges();
	}

	/**
	 * Used to notify some classes of some changes
	 */
	private void notifyChanges(){
    	// notify all buttons
		for(ButtonGUI button : this.listOfButtons){
			button.notifyActionChanged(this.actionType);
		}

		// add some other notif here if you need it
	}

	@Override
	public ActionType getCurrentAction() {
		return this.actionType;
	}

	@Override
	public void setProcessedPotion(ProcessedPosition processedPosition) {
		this.gridGUI.setProcessedPosition(processedPosition);
	}

	public List<BoatInterface> getListOfBoat() {
		return this.listOfBoat;
	}

    @Override
	public void setListOfBoat(List<BoatInterface> listOfBoat) {
		this.listOfBoat = listOfBoat;
	}

	public void initGame(Map<Integer,ProcessedPosition> initBoatPos){
		this.gridGUI.initGrid(initBoatPos);
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
	public void setVisibleCoord(List<Coord> visibleCoords){
		this.gridGUI.setVisibleCoords(visibleCoords);
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
    }

    /**
     * Distribute the controller request when the nb of ActionPoint is equal zero to the ActionPoint Class
     */
    @Override
    public void disableAction() {
        this.PAPanel.disableAction();
    }

	public void displayResult(ResultShoot result, Coord target){
		this.gridGUI.displayResult(result, target);
		if(!result.equals(ResultShoot.DESTROYED)) {
			this.textArea.setText("");
		}
		else {
			this.textArea.setText("Le bateau ciblé a été détruit.");
		}
    }

	@Override
	public void setVisibleBoats(List<Coord> visibleCoordCurrentPlayer) {
		this.gridGUI.setVisibleBoats(visibleCoordCurrentPlayer);
		this.revalidate();
		this.repaint();
	}

	public void setControlsEnable(Boolean enable){
		for (ButtonGUI button: this.buttons) {
			button.setEnabled(enable);
		}
	}

    public boolean boatIsSelected(){
    	return this.gridGUI.boatIsSelected();
	}
}