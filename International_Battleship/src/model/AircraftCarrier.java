package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;

@objid ("b0f6e843-5b59-4e8c-8233-5a897083772c")
public class AircraftCarrier extends AbstractBoat {
	final int size = 5;
	TargetAction mySpecialAction;

    @objid ("d7f1e9b2-c61d-4f7a-8a81-a52d2389f794")
    public AircraftCarrier(Coord coord) {
    	super(BoatName.AircraftCarrier, coord);
    	mySpecialAction = new TargetAction(){
    		
    		@Override
    		public void doAction(){
    			//TODO
    			System.out.println("Target action dans le porte avion"); //to test
    		}
    	};
    }

    @objid ("3222a6aa-93ed-428b-a289-90062504d72b")
    public void move() {
    }

    @objid ("40ff6d2b-b949-4bad-9f34-a102728a0beb")
    public void hourlyRotation() {
    }

    @objid ("1ed4e00e-e755-4fd5-8fe1-124173c4521c")
    public void antiHourlyRotation() {
    }

    public int getSize() {
    	return this.size;
    }

	@Override
	public void actionSpecial(Coord target) {
		if(this.mySpecialAction.getTarget() == null){
			this.mySpecialAction.setTarget(target);
		}
		else{
			System.out.println("Attention : l'attribut target de TargetAction n'est pas null");
		}
		this.mySpecialAction.doAction();
		
	}
}
