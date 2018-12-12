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

	private List<BoatInterface> fleet;

	private String PortName;

	private int ActionPoint;

	private AbstractBoat selectedBoat;

    public Player(String name, String port) {
        this.name = name;
        this.PortName = port;
        this.fleet = new LinkedList<BoatInterface>();
        this.maxActionPoint = GameConfig.getMaxActionPoint();
		this.ActionPoint = GameConfig.getMaxActionPoint();
    }

	private void isPlay() {
	}

	public List<BoatInterface> getFleet() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.fleet;
	}

    /**
     * Add a boat in the fleet of the player
     * @param boatName
     * @param coord
     */
	public void addBoatInFleet(BoatName boatName, Coord coord, int id){
        BoatInterface boat = BoatFactory.newBoat(boatName, coord, id);
        this.fleet.add(boat);
	}

	public void ValidTurn() {
	}

	public void SkipTurn() {
	}

	public AbstractBoat getSelectedBoat() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.selectedBoat;
	}

	@Override
	public BoatInterface getBoat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBoat(BoatInterface value) {
		// TODO Auto-generated method stub
	}

	public String getPortName() {
		// Automatically generated method. Please delete this comment before entering specific code.
		return this.PortName;
	}

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
