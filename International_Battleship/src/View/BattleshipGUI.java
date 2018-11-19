package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controler.BattleshipGameControlerModelView;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import tools.ActionType;
import tools.Coord;
import tools.Direction;


@objid ("7f86e906-82ca-4124-8c92-c6f6305ed941")
public class BattleshipGUI extends JFrame implements GameGUIInterface{
	private BattleShipGridGUI gridGUI;
	
    @objid ("dc33ec30-907d-43b8-8b28-bbc7f57950c3")
    public BattleShipGridGUI battleShipGridGUI;

    @objid ("a6ff4313-0cc1-4317-8f8f-cad40722f639")
    public BattleShipButtonGUI battleShipButtonGUI;

    @objid ("81916075-5be2-4b77-b7e6-32f83af649a3")
    public BattleshipGameControlerModelView controller;
    
    public ActionType actionType = null;
     
    
    // Constructor
    public BattleshipGUI(BattleshipGameControlerModelView controller) {
    	super();
    	
    	Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridBagLayout());
    	
        this.controller = controller;
        
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
			
			// tweak GridBagConstraints for "action" panel 
				c.fill = GridBagConstraints.BOTH;
		        c.gridx = 1;
		        c.gridy = 0;
		        c.weightx = 1.0;
		        c.weighty = 1.0;
			contentPane.add(actionPlaceholder, c);
			
			
				
		// Create and attach event listener on grid GUI
			EventListener mouseEventListener = 
					new BattleShipGridGUIListener( this.gridGUI, this.controller);
			
			this.gridGUI.addMouseListener((MouseListener) mouseEventListener);
			this.pack();
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
	public void setBoatDirection(Direction direction) {
		// TODO rotate the selectedBoat in wanted Direction
	}


	@Override
	public ActionType getCurrentAction() {
		return this.actionType;
	}
}
