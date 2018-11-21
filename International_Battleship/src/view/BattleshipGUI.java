package view;

import controler.BattleshipGameControlerModelView;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.ActionType;
import tools.Coord;
import tools.ProcessedPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;

@objid ("7f86e906-82ca-4124-8c92-c6f6305ed941")
public class BattleshipGUI extends JFrame implements GameGUIInterface{
    private static final long serialVersionUID = 7636412061294453620L;

    private BattleShipGridGUI gridGUI;
    private BattleShipButtonGUI buttonGUITirer;
    private BattleShipButtonGUI buttonGUIDéplacer;
    private BattleShipButtonGUI buttonGUIActionSpéciale;

    @objid ("dc33ec30-907d-43b8-8b28-bbc7f57950c3")
    public BattleShipGridGUI battleShipGridGUI;

    @objid ("a6ff4313-0cc1-4317-8f8f-cad40722f639")
    public BattleShipButtonGUI battleShipButtonGUI;

    public ArrayList<BattleShipButtonGUI> listOfButtons;

    @objid ("81916075-5be2-4b77-b7e6-32f83af649a3")
    public BattleshipGameControlerModelView controller;

    public ActionType actionType = null;


    // Constructor
    public BattleshipGUI(BattleshipGameControlerModelView gameController) {
    	super();

    	Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
    	
        this.controller = gameController;

    	// Create grid and add it to the left
			this.gridGUI = new BattleShipGridGUI();
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
			JPanel actionPlaceholder = new JPanel();
			actionPlaceholder.setBackground(Color.GRAY);

            // Create of Button in the "actions" panel
            this.buttonGUITirer =  new BattleShipButtonGUI("Tirer");
    		//this.buttonGUIDéplacer = new BattleShipButtonGUI("Déplacer");
    		//this.buttonGUIActionSpéciale = new BattleShipButtonGUI("Action Spéciale");
            actionPlaceholder.add(this.buttonGUITirer);
    		//actionPlaceholder.add(this.buttonGUIDéplacer);
    		//actionPlaceholder.add(this.buttonGUIActionSpéciale);

            //Store buttons in a list
            this.listOfButtons = null;
            listOfButtons.add(this.buttonGUITirer);
    		//listOfButtons.add(this.buttonGUIDéplacer);
    		//listOfButtons.add(this.buttonGUIActionSpéciale);

        // tweak GridBagConstraints for "action" panel
				c.fill = GridBagConstraints.BOTH;
		        c.gridx = 1;
		        c.gridy = 0;
		        c.weightx = 1.0;
		        c.weighty = 1.0;
			contentPane.add(actionPlaceholder, c);



		// Create and attach event listener on grid GUI
			EventListener mouseEventListener = new BattleShipGridGUIListener( this.gridGUI, gameController);
			this.gridGUI.addMouseListener((MouseListener) mouseEventListener);
			this.pack();

        //Create and attach event listener on "actions" button
        EventListener mouseEventListenerShoot = new BattleShipButtonGUIListener(this.buttonGUITirer, gameController);
//		EventListener mouseEventListenerMove = new BattleShipButtonGUIListener(this.buttonGUIDéplacer/*,controller*/);
//		EventListener mouseEventListenerSpecialAction = new BattleShipButtonGUIListener(this.buttonGUIActionSpéciale/*,controller*/);
        this.buttonGUITirer.addMouseListener((MouseListener) mouseEventListenerShoot);
//		this.buttonGUIDéplacer.addMouseListener((MouseListener) mouseEventListenerMove);
//		this.buttonGUIActionSpéciale.addMouseListener((MouseListener) mouseEventListenerSpecialAction);

    }

    public static void repaintAllButtons() {
//		for (BattleShipButtonGUI button : listOfButtons) {
//			button.setText(button.getDefaultText());
//		}
    }

	@Override
	public void setCurrentAction(ActionType actionType) {
		this.actionType = actionType;
	}

	@Override
	public void setBoatPos(Coord coord) {
		// TODO move the selectedBoat to wanted location
		//this.gridGUI.getComponentAt(coord.getX(), coord.getY());
		//a compléter
	}

	@Override
	public void setBoatDirection(ProcessedPosition processedPosition) {
    	// TODO add a boatGUI, add in this class a selectedBoat,
		// TODO allow moving the boat by giving it a processedPosition
		// this.selectedBoat.setPosition(processedPosition);
	}


	@Override
	public ActionType getCurrentAction() {
		return this.actionType;
	}
}
