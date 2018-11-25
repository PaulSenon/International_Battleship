package view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import controler.ControllerModelViewInterface;
import tools.ActionType;
import tools.Coord;
import tools.ProcessedPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

@objid ("7f86e906-82ca-4124-8c92-c6f6305ed941")
public class GameGUI extends JFrame implements GameGUIInterface{
    private static final long serialVersionUID = 7636412061294453620L;

    private GridGUI gridGUI;
    private ButtonGUI buttonGUITirer;
    private ButtonGUI buttonGUIDéplacer;
    private ButtonGUI buttonGUIActionSpéciale;

    @objid ("dc33ec30-907d-43b8-8b28-bbc7f57950c3")
    public GridGUI battleShipGridGUI;

    @objid ("a6ff4313-0cc1-4317-8f8f-cad40722f639")
    public ButtonGUI battleShipButtonGUI;

    public List<ButtonGUI> listOfButtons;

    public ActionType actionType = null;


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
			JPanel actionPlaceholder = new JPanel();
			actionPlaceholder.setBackground(Color.GRAY);

            // Create of Button in the "actions" panel
            this.buttonGUITirer =  new ButtonGUI("Tirer");
    		//this.buttonGUIDéplacer = new ButtonGUI("Déplacer");
    		//this.buttonGUIActionSpéciale = new ButtonGUI("Action Spéciale");
            actionPlaceholder.add(this.buttonGUITirer);
    		//actionPlaceholder.add(this.buttonGUIDéplacer);
    		//actionPlaceholder.add(this.buttonGUIActionSpéciale);

            //Store buttons in a list
            this.listOfButtons = new ArrayList<>();
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

		this.pack();
    }

    public void initListeners(ControllerModelViewInterface gameController){
		// Create and attach event listener on grid GUI
		EventListener mouseEventListener = new GridGUIListener( this.gridGUI, gameController);
		this.gridGUI.addMouseListener((MouseListener) mouseEventListener);

		//Create and attach event listener on "actions" button
		EventListener mouseEventListenerShoot = new ButtonGUIListener(this.buttonGUITirer, gameController);
		this.buttonGUITirer.addMouseListener((MouseListener) mouseEventListenerShoot);
	}

	@Override
	public void setCurrentAction(ActionType actionType) {
		this.actionType = actionType;
		this.gridGUI.setCurrentAction(actionType);
	}

	@Override
	public ActionType getCurrentAction() {
		return this.actionType;
	}

	@Override
	public void setSelectedBoatByCoord(int x, int y) {
		this.gridGUI.setSelectedBoat(new Coord(x, y));
	}

	@Override
	public void setProcessedPotion(ProcessedPosition processedPosition) {
		this.gridGUI.setProcessedPosition(processedPosition);
	}
}
