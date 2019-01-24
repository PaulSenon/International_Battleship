package model;


import tools.Coord;

public class Sentinel extends AbstractBoat {

    public Sentinel(int id, Coord pivot, int playerId) {
        super(BoatType.Sentinel, id, pivot, playerId);
        this.mySpecialAction = new SpecialActionMine();
//                new TargetAction(){
//            @Override
//            public void doAction(Coord target) {
//                // TODO Auto-generated method stub
//                System.out.println("Action spéciale sentinel");
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
        return 3;
    }
}
