package view;


import controler.ControllerModelViewInterface;
import model.BoatInterface;
import tools.ActionType;
import tools.Coord;
import tools.ProcessedPosition;
import tools.ProcessedProps;
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
    public ButtonGUI buttonGUIFinTour;

    public List<ButtonGUI> listOfButtons;

    public ActionType currentAction;


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
                    this.buttonGUIShoot = new ButtonGUI(ButtonType.SHOOT, "Tirer", "Annuler");
                    controlsPanel.add(this.buttonGUIShoot, buttonsConstraints);
                    this.buttons.add(this.buttonGUIShoot);

                    buttonsConstraints.gridy++;
                    this.buttonRotateClockWise =  new ButtonGUI(ButtonType.ROTATECW, "RotateCW", "");
                    controlsPanel.add(this.buttonRotateClockWise, buttonsConstraints);
					this.buttons.add(this.buttonRotateClockWise);

                    buttonsConstraints.gridy++;
                    this.buttonRotateCounterClockWise =  new ButtonGUI(ButtonType.ROTATECCW, "RotateCCW", "");
                    controlsPanel.add(this.buttonRotateCounterClockWise, buttonsConstraints);
					this.buttons.add(this.buttonRotateCounterClockWise);

					buttonsConstraints.gridy++;
					this.buttonGUISpecialAction = new ButtonGUI(ButtonType.SPECIALACTION, "Action Spéciale", "Annuler");
					controlsPanel.add(this.buttonGUISpecialAction, buttonsConstraints);
					this.buttons.add(this.buttonGUISpecialAction);

					buttonsConstraints.gridy++;
					this.buttonGUIFinTour = new ButtonGUI(ButtonType.ENDTURN, "Fin de tour", "Annuler");
					controlsPanel.add(this.buttonGUIFinTour, buttonsConstraints);
					this.buttons.add(this.buttonGUIFinTour);


                //Store buttons in a list // TODO is this useful ?
                    this.listOfButtons = new ArrayList<>();
                    listOfButtons.add(this.buttonGUIShoot);
                    listOfButtons.add(this.buttonRotateClockWise);
                    listOfButtons.add(this.buttonRotateCounterClockWise);
                    listOfButtons.add(this.buttonGUISpecialAction);
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
		this.buttonGUIShoot.addActionListener(buttonListener);
		this.buttonRotateCounterClockWise.addActionListener(buttonListener);
		this.buttonRotateClockWise.addActionListener(buttonListener);
		this.buttonGUISpecialAction.addActionListener(buttonListener);
		this.buttonGUIFinTour.addActionListener(buttonListener);
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

    @Override
	public void setProcessedProps(List<ProcessedProps> processedProps) {
		this.gridGUI.setProcessedProps(processedProps);
	}
	
	public List<BoatInterface> getListOfBoat() {
		return this.listOfBoat;
	}

    @Override
	public void setListOfBoat(List<BoatInterface> listOfBoat) {
		this.listOfBoat = listOfBoat;
	}

	public void initGame(Map<Integer,ProcessedPosition> initBoatPos, Map<Integer, ProcessedProps> initMinesPos, Map<Integer, Integer> boatRelatedToPlayer){
		this.gridGUI.initGrid(initBoatPos, initMinesPos, boatRelatedToPlayer);
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
	
	@Override
	public void setVisibleMines(List<Coord> visibleCoordCurrentPlayer) {
		this.gridGUI.setVisibleMines(visibleCoordCurrentPlayer);
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