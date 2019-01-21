package model;


import java.io.Serializable;
import java.util.List;

import tools.Coord;

import java.util.List;
import java.util.Map;

public interface PlayerInterface extends Serializable{
	
	Map<Integer, BoatType> getFleet();

    BoatInterface getBoat();

    void setBoat(final BoatInterface value);

    String getName();

    int getNbActionPoint();

    void creditActionPoint (final int value);

    boolean debitActionPoint (final int value);

    int getMaxActionPoint();

    void generateFleet(BoatType[] fleet);

    boolean isInPort(Coord coord);

    void undoLastAction();

    int getId();

    List<Coord> getVisibleCoords();

    void setVisibleCoords(List<Coord> visibleCoords);

    boolean isEliminate();

    void setEliminate(boolean eliminate);

}