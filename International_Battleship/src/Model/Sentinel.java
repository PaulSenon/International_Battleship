package Model;


import java.util.ArrayList;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6b6d0859-e740-493d-98e4-4636c5ba6350")
public class Sentinel extends AbstractBoat {
    final int size = 1;

	@objid ("62b7f0e5-4f57-4a8c-bc19-9993b91246d0")
    public Sentinel(Coord coord) {
		super(BoatName.SENTINEL, coord);
    }

	@objid ("64194498-4526-4adf-9189-6c2873dbc42f")
    @Override
    public void shoot() {
        // TODO Auto-generated method stub
    }

    @objid ("e3a0f19d-f496-4b18-8b34-c7953806c406")
    @Override
    public void move() {
        // TODO Auto-generated method stub
    }

    @objid ("90d46db7-7bd1-4864-9e37-5bed7c4b3c76")
    @Override
    public void hourlyRotation() {
        // TODO Auto-generated method stub
    }

    @objid ("81499637-eb3b-401e-8916-4e434b256ce6")
    @Override
    public void antiHourlyRotation() {
        // TODO Auto-generated method stub
    }

    public int getSize () {
    	return this.size;
    }

	@Override
	public boolean isAlgoMoveOk(int xFinal, int yFinal) {
		boolean deplacement = false;
		int deltamin = 0;
		int deltasup = 0;
		if (xFinal == x && yFinal-y <= deltasup && yFinal-y >= deltamin){
			deplacement = true;
		}
		return deplacement;
	}

	@Override
	public List<Coord> getMoveItinerary(int xFinal, int yFinal) {
		List<Coord> coord = new ArrayList<Coord>();
		int y = super.y;
		int deltaY = yFinal-y;
		if(deltaY > 1){
			coord.add(new Coord(xFinal,yFinal-1));
		}else if(deltaY < -1){
			coord.add(new Coord(xFinal,yFinal+1));
		}
		coord.add(new Coord(xFinal,yFinal));
		return coord;
	}

	@Override
	public boolean isAlgoMoveOk(int xFinal, int yFinal, String type) {
		// TODO Auto-generated method stub
		return false;
	}

}
