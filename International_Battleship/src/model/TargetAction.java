package model;

import tools.Coord;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c4a43324-1b6c-40b9-8f03-4506aaee6a8a")
public class TargetAction extends SpecialAction {

	private Coord target;
	
	@objid ("5763df9b-ec15-4555-9f5f-e413d42b83ac")
    @Override
    public void doAction() {
        System.out.println("Je suis Target action");
    }

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

}
