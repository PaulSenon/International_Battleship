package view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import controler.ControllerModelViewInterface;
import model.BoatInterface;
import model.BoatName;
import tools.ActionType;
import tools.ProcessedPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Map;

@objid ("7f86e906-82ca-4124-8c92-c6f6305ed941")
public class GameGUI extends JFrame implements GameGUIInterface{
    private static final long serialVersionUID = 7636412061294453620L;
	private List<BoatInterface> listOfBoat;
    private GridGUI gridGUI;
    private ButtonGUI buttonGUITirer;
    private ButtonGUI buttonRotateCounterClockWise;
	private ButtonGUI buttonRotateClockWise;
    private ButtonGUI buttonGUIDéplacer;
    private ButtonGUI buttonGUIActionSpéciale;
    private JPanel controlsPanel;

    @objid ("dc33ec30-907d-43b8-8b28-bbc7f57950c3")
    public GridGUI battleShipGridGUI;

    @objid ("a6ff4313-0cc1-4317-8f8f-cad40722f639")
    public ButtonGUI battleShipButtonGUI;

    public List<ButtonGUI> listOfButtons;

    public ActionType actionType;


    // Constructor
    public GameGUI() {
    	super();

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
		        c.weightx = 2.0;
		        c.weighty = 2.0;
			contentPane.add(gridContainer, c);


		// TODO currently, just a placeholder
		// Create "actions" panel and add it to the right
			this.controlsPanel = new JPanel();
			controlsPanel.setLayout(new GridBagLayout());
				GridBagConstraints buttonsConstraints = new GridBagConstraints();
				buttonsConstraints.gridx = 0;
				buttonsConstraints.gridy = 0;
			controlsPanel.setBackground(Color.GRAY);

            // Create of Button in the "actions" panel
			this.buttonGUITirer = new ButtonGUI(ButtonType.SHOOT, "Tirer", "Annuler");
			this.controlsPanel.add(this.buttonGUITirer, buttonsConstraints);

            buttonsConstraints.gridy++;
            this.buttonRotateClockWise =  new ButtonGUI(ButtonType.ROTATECW, "RotateCW", "");
            this.controlsPanel.add(this.buttonRotateClockWise, buttonsConstraints);

			buttonsConstraints.gridy++;
			this.buttonRotateCounterClockWise =  new ButtonGUI(ButtonType.ROTATECCW, "RotateCCW", "");
			this.controlsPanel.add(this.buttonRotateCounterClockWise, buttonsConstraints);


            //Store buttons in a list // TODO is this useful ?
            this.listOfButtons = new ArrayList<>();
            listOfButtons.add(this.buttonGUITirer);
            listOfButtons.add(this.buttonRotateClockWise);
            listOfButtons.add(this.buttonRotateCounterClockWise);
    		//listOfButtons.add(this.buttonGUIDéplacer);
    		//listOfButtons.add(this.buttonGUIActionSpéciale);

        // tweak GridBagConstraints for "action" panel
				c.fill = GridBagConstraints.BOTH;
		        c.gridx = 1;
		        c.gridy = 0;
		        c.weightx = 1.0;
		        c.weighty = 1.0;
			contentPane.add(controlsPanel, c);

    }

    public void initListeners(ControllerModelViewInterface gameController){
		// Create and attach event listener on grid GUI
		EventListener mouseEventListener = new GridGUIListener( this.gridGUI, gameController);
		this.gridGUI.addMouseListener((MouseListener) mouseEventListener);

		ActionListener buttonListener = new ButtonGUIListener(gameController);
		this.buttonGUITirer.addActionListener(buttonListener);
		this.buttonRotateCounterClockWise.addActionListener(buttonListener);
		this.buttonRotateClockWise.addActionListener(buttonListener);
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

	public void initGame(Map<BoatName,ProcessedPosition> initBoatPos){
		this.gridGUI.initGrid(initBoatPos);
		this.revalidate();
		this.repaint();
	}

	@Override
	public void setSelectedBoat(ProcessedPosition processedPosition) {
		this.gridGUI.setSelectedBoat(processedPosition);
	}
}