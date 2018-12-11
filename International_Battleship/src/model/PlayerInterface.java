package model;


import java.util.List;


import tools.Coord;
import tools.PersonnalException;

public interface PlayerInterface {
	
	List<BoatInterface> getFleet();
	
	    BoatInterface getBoat();

        void setBoat(final BoatInterface value);

    String getName();

    void addBoatInFleet(BoatName boatName, Coord coord, int id);

    int getActionPoint();

    String getPortName();

    void setPortName(final String value);

    void setActionPoint(final int value) throws PersonnalException;

    void creditActionPoint (final int value);

    void debitActionPoint (final int value) throws PersonnalException;

    int getMaxActionPoint ();

}