package model;

import tools.Coord;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c4a43324-1b6c-40b9-8f03-4506aaee6a8a")
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
