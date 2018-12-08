package model;

import tools.Coord;


public abstract class SpecialAction implements SpecialActionInterface {
        private String cost;

        public String getCost() {
        return this.cost;
    }

        public void setCost(final String value) {
        this.cost = value;
    }

        public abstract void doAction(Coord target);

}
