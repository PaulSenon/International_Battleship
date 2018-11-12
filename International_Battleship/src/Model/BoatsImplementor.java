package Model;

import java.util.LinkedList;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.BattleshipBoatFactory;

@objid ("dcf26cb5-3322-4d9d-98af-5b54a0f09632")
public class BoatsImplementor implements BattleshipGameImplementor {
	
    @objid ("6eaf860c-9a4a-4dab-888f-d3e4f31d9e77")
    public Boat boat;
    
    @objid ("5ec8880b-f75d-4bee-8ed0-ecea6a4d4930")
    public BoatsImplementor(List<Player> players, List<BoatName> linkedList) {
    	for (Player p : players) {
    		int i=0;
        	for (BoatName boatName  : linkedList) {
        		p.getFleet().add(BattleshipBoatFactory.newBoat(boatName,new Coord(0,i)));
        		i++;
        	}
        //test
       	System.out.println("L'implementor a générer un bateau de type "+ p.getFleet());
		}
    }

	@objid ("f561c936-de20-43ae-a170-a9290c7f975c")
    public void shoot() {
    }

    @objid ("262ccb08-0aa5-49fd-9237-4805c3304fb9")
    public void move() {
    }

    @objid ("4643b543-6571-4c67-bf46-c267384eea71")
    public void findBoatByCoord() {
    }

}