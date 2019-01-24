package model;


import tools.Coord;

public class TorpedoBoat extends AbstractBoat {

    public TorpedoBoat(int id, Coord pivot, int playerId) {
        super(BoatType.TorpedoBoat, id, pivot, playerId);
        this.mySpecialAction = new SpecialActionMine();
//                new InstantAction(){
//            @Override
//            public void doAction(Coord target) {
//                // TODO Auto-generated method stub
//                System.out.println("Action spécial torpilleur");
//                return null;
//            }
//        };
    }

    @Override
    public void getVisibleArea() {

    }

    public int getSize() {
    	return this.size;
    }
}
