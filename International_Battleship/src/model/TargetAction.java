package model;

import tools.Coord;

public abstract class TargetAction extends SpecialAction {
    private Coord target;

    /**
    * @return the target
    */
    public Coord getTarget() {
    	return target;
    }

    /**
    * @param target the target to set
    */
    public void setTarget(Coord target) {
    	this.target = target;
    }

	@Override
	public abstract void doAction(Coord target);

}
