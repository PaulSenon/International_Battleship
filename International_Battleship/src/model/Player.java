package model;

import java.util.LinkedList;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import tools.BoatFactory;
import tools.Coord;
import tools.GameConfig;
import tools.PersonnalException;

@objid ("f238657d-bdf0-4378-bd4d-31cf69f544c3")
public class Player implements PlayerInterface {
    private final String name;

	//TODO : modifier cet attribut pour le faire varier en fonction du nombre de bateau.
	int maxActionPoint;

	@objid ("3f33dd87-0c02-456e-9147-4d36c1941913")
	private List<BoatInterface> fleet;

	@objid ("88768db5-cf22-41e6-9f3f-6b54186cf01c")
	private String PortName;

	@objid ("f900ed4b-76f2-4b50-a670-5d8767e93379")
	private int ActionPoint;

	@objid ("34e61417-a489-4dd3-a9bd-7d7edf60a067")
	private AbstractBoat selectedBoat;

    @objid ("f51afe71-7f94-42e7-a536-e386c3048b7c")
    public Player(String name, String port) {
        this.name = name;
        this.PortName = port;
        this.fleet = new LinkedList<BoatInterface>();
        this.maxActionPoint = GameConfig.getMaxActionPoint();
		this.ActionPoint = GameConfig.getMaxActionPoint();
    }

	@objid ("1d956215-4939-4f01-984b-461bfa06531f")
	private void isPlay() {
	}

	@objid ("5f3747e3-599d-4f3a-9a47-2d5e9c1e68e6")
	public List<BoatInterface> getFleet() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.fleet;
	}

    /**
     * Add a boat in the fleet of the player
     * @param boatName
     * @param coord
     */
	@objid ("f71f0f18-99aa-4b0d-a697-41e75baa6f7e")
	public void addBoatInFleet(BoatName boatName, Coord coord, int id){
        BoatInterface boat = BoatFactory.newBoat(boatName, coord, id);
        this.fleet.add(boat);
	}

	@objid ("30029abf-d2e3-49ed-bd95-f43be5fa5c17")
	public void ValidTurn() {
	}

	@objid ("85c75097-8c2d-446b-bb9e-762d1faeea35")
	public void SkipTurn() {
	}

	@objid ("74af210d-5fd6-4e20-b79e-f0e1a4e24550")
	public AbstractBoat getSelectedBoat() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.selectedBoat;
	}

	@objid ("a5e853b4-d905-439e-9751-fa18148121b4")
	@Override
	public BoatInterface getBoat() {
		// TODO Auto-generated method stub
		return null;
	}

	@objid ("3a452fcc-08c9-4245-b719-bb8ee3854eba")
	@Override
	public void setBoat(BoatInterface value) {
		// TODO Auto-generated method stub
	}

	@objid ("4f02c9da-499f-420b-85c7-af611928bff1")
	public String getPortName() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.PortName;
	}

	@objid ("c1a05358-3927-425a-a6dd-e0348309cd6c")
	public void setPortName(final String value) {
		// Automatically generated method. Please delete this comment before entering specific code.
		this.PortName = value;
	}

	/**
	 * This method returns the number of Action Point
	 * @return
	 */
	@objid ("bef56e38-69ff-4e7b-91cd-8a16a206e12b")
	public int getActionPoint() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.ActionPoint;
	}

	/**
	 * This method is used to set the value of Action Point
	 * @param value
	 * @throws PersonnalException 
	 */
	@objid ("b3fb1af7-f52b-4fbb-83f0-5be19d8970eb")
	public void setActionPoint(final int value) throws PersonnalException {
		if (value > maxActionPoint) {
			throw new PersonnalException("The value is higher than expected");
		}
		this.ActionPoint = value;
	}

	/**
	 * This method is used to credit the value of Action Point
	 * @param value
	 * @throws PersonnalException 
	 */
	public void creditActionPoint (final int value) {
		this.ActionPoint += value;
		if (this.ActionPoint > maxActionPoint) {
			this.ActionPoint = maxActionPoint;
		}
	}

	/**
	 * This method is used to debit the value of Action Point
	 * @param value
	 * @throws PersonnalException
	 */
	public void debitActionPoint (final int value) throws PersonnalException {
		int verifDebitPossible = this.ActionPoint - value;
		if (verifDebitPossible < 0) {
			throw new PersonnalException("Not enough Action Point");
		}
		else {
			this.ActionPoint -= value;
		}
	}

	/**
	 * This method returns the maxActionPoint
	 * @return
	 */
	public int getMaxActionPoint () {
		return this.maxActionPoint;
	}

	/**
	 * This method calculates the MaxActionPoint
	 */
	public void calculationOfMaxActionPoint () {
		this.maxActionPoint = 0;
		if (fleet != null) {
			for (BoatInterface boat : fleet) {
				this.maxActionPoint += boat.getSize();
			}
		}
	}

    /**
     *
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

}
