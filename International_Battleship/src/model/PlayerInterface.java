package model;


import tools.Coord;

import java.util.Map;

public interface PlayerInterface {
	
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

}