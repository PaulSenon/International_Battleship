package Model;

import java.util.List;

import Model.Coord;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c1e77788-c863-45cf-bace-9f86e3b2f0cc")
public interface Boat {
    @objid ("83fb710c-9713-4424-ae50-2f2fa0c5e14b")
    void shoot();

    @objid ("40a00f44-2a87-4bdf-b47b-15f693d433c0")
    void move();

    @objid ("c10f1d8a-c319-4f3f-9875-2a11226fb3d8")
    void hourlyRotation();

    @objid ("b621313c-9077-4c05-abd7-160fac2282a7")
    void antiHourlyRotation();

	int getSize();

	int getX();

	int getY();
	
	public List<Coord> getMoveItinerary(int xFinal, int yFinal);
	
	public boolean isAlgoMoveOk(int xFinal, int yFinal);

	boolean catchPiece();
	
	public String doMove(int xFinal, int yFinal);




}
