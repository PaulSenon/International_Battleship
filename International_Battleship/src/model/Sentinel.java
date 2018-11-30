package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import tools.Coord;

@objid ("6b6d0859-e740-493d-98e4-4636c5ba6350")
public class Sentinel extends AbstractBoat {
    final int size = 1;
	TargetAction mySpecialAction;    

	@objid ("62b7f0e5-4f57-4a8c-bc19-9993b91246d0")
    public Sentinel(Coord coord) {
		super(BoatName.Sentinel, coord);
    	mySpecialAction = new TargetAction(){
    		
    		@Override
    		public void doAction(){
    			//TODO
    			System.out.println("Target action dans le sentinel"); //to test
    		}
    	};
    }

    public int getSize () {
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
