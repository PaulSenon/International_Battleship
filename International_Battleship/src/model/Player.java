package model;

import tools.Coord;
import tools.GameConfig;
import tools.PersonnalException;
import tools.UniqueIdGenerator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements PlayerInterface {
	private int id;

    private final String name;
	private String PortName;
	private List<Coord> visibleCoords;

	private static final long serialVersionUID = 5950874519310163575L;

	//TODO : modifier cet attribut pour le faire varier en fonction du nombre de bateau.
	private int maxActionPoint;

	private Map<Integer, BoatType> fleet;
	private List<Coord> port;

	private int nbActionPoint;
	private int lastNbActionPoints;
	private boolean eliminate;

    public Player(int id, String name, String portName) {
    	this.id = id;
        this.name = name;
        this.PortName = portName;
        this.fleet = new HashMap<>();
        this.port = new ArrayList<>();
        this.maxActionPoint = GameConfig.getMaxActionPoint();
		this.nbActionPoint = GameConfig.getMaxActionPoint();
		this.lastNbActionPoints = this.nbActionPoint;
		this.visibleCoords = null;
		this.eliminate = false;
    }

	public boolean isEliminate() {
		return eliminate;
	}

	public void setEliminate(boolean eliminate) {
		this.eliminate = eliminate;
	}

	private void isPlay() {
	}

	public Map<Integer, BoatType> getFleet() {
		return this.fleet;
	}

	public void ValidTurn() {
	}

	public void SkipTurn() {
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

	public boolean isInPort(Coord coord){
//    	return this.port.contains(coord); // TODO
		return true;
	}

	/**
	 * This method returns the number of Action Point
	 * @return
	 */
	public int getNbActionPoint() {
		return this.nbActionPoint;
	}

	/**
	 * This method is used to set the value of Action Point
	 * @param value
	 * @throws PersonnalException
	 */
	public void setNbActionPoint(final int value) throws PersonnalException {
		if (value > maxActionPoint) {
			throw new PersonnalException("The value is higher than expected");
		}
		this.nbActionPoint = value;
	}

	/**
	 * This method is used to credit the value of Action Point
	 * @param value
	 * @throws PersonnalException
	 */
	public void creditActionPoint (final int value) {
		// save last nb AP
		this.lastNbActionPoints = this.nbActionPoint;

		this.nbActionPoint += value;
		if (this.nbActionPoint > maxActionPoint) {
			this.nbActionPoint = maxActionPoint;
		}
	}

	/**
	 *
	 * @param value
	 */
	public boolean debitActionPoint (final int value) {
		// save last nb AP
		this.lastNbActionPoints = this.nbActionPoint;

		int verifDebitPossible = this.nbActionPoint - value;
		if (verifDebitPossible < 0) {
			// TODO remove this, not the responsibility of the game model
			JOptionPane.showMessageDialog(null, "Pas assez de PA", null , JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		else {
			this.nbActionPoint -= value;
			return true;
		}
	}

	/**
	 * This method returns the maxActionPoint
	 * @return
	 */
	public int getMaxActionPoint() {
		return this.maxActionPoint;
	}

	public void generateFleet(BoatType[] fleet){
		for(BoatType boatType : fleet){
			this.fleet.put(UniqueIdGenerator.getNextId(), boatType);
		}
	}

	@Override
	public int getId() {
		return id;
	}

	public void undoLastAction(){
		this.nbActionPoint = this.lastNbActionPoints;
	}

	/**
     *
     * @return the type of the player
     */
    public String getName() {
        return this.name;
    }

    public List<Coord> getVisibleCoords (){ return this.visibleCoords; }

	public void setVisibleCoords(List<Coord> visibleCoords) {
		this.visibleCoords = visibleCoords;
	}


    @Override
	public String toString(){
    	return "Player : " + getName() + " | Port : " + getPortName();
	}

}
