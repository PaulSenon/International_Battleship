package model;


import tools.Coord;

public class Submarine extends AbstractBoat {

    public Submarine(int id, Coord pivot, int playerId) {
        super(BoatType.Submarine, id, pivot, playerId);
        this.mySpecialAction = new SpecialZoneAOE(1);
//                new InstantAction(){
//            @Override
//            public void doAction(Coord target) {
//                // TODO Auto-generated method stub
//                System.out.println("Action spéciale sous marin");
//                return null;
//            }
//        };
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize () {
    	return this.size;
    }

    @Override
    public int getSpecialActionCost() {
        return 4;
    }
}
