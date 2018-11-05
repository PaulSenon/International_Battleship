package Model;

import java.util.ArrayList;
import java.util.List;

import Model.Boat;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import tools.BattleshipBoatFactory;



@objid ("dcf26cb5-3322-4d9d-98af-5b54a0f09632")
public class BoatsImplementor implements BattleshipGameImplementor {
	
    @objid ("6eaf860c-9a4a-4dab-888f-d3e4f31d9e77")
    public Boat boat;
    
    @objid ("5ec8880b-f75d-4bee-8ed0-ecea6a4d4930")
    public BoatsImplementor(String boatType) {
    	boat = BattleshipBoatFactory.newBoat(boatType);
    	//test
    	System.out.println("L'implementor a générer un bateau de type "+ boat);
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

	public Boat getSelectedPiece(){
		return this.boat;
	}
	public void setSelectedPiece(Boat boat){
		this.boat = boat;
	}
	
	public Boat findPiece(int x, int y) {
		List<Boat> boats = null;
		Boat boat = null;


		//recherche de la piÃ¨ce en fonction de sa position
		for(Boat p : boats){
			if(p.getX() == x && p.getY() == y){
				boat = p;
				break;
			}
		}
		return boat;
	}
	
	public Coord checkReachable(List<Coord> listCoord, Boat boat){
		Coord coord = null;
		if(listCoord != null)
			coord = listCoord.get(listCoord.size()-1);
	
		for(Coord c : listCoord){
			Boat p = findPiece(c.getX(),c.getY());
		}
		return coord;		
	}
	
	public String manageCatch(int xFinal, int yFinal) {
		String ret = null;
		Boat toCatch = findPiece(xFinal,yFinal);
		if(toCatch != null){
			if(toCatch.catchPiece())
				ret = "J'ai pris la pièce";
		}
		else{
			ret = "J'ai bougé la pièce";
		}
		return ret;
	}
}