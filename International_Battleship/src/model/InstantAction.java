package model;

import tools.Coord;


public abstract class InstantAction extends SpecialAction {

    @Override
    public abstract void doAction(Coord target);

}
