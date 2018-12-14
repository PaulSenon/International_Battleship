package model;



public abstract class SpecialAction implements SpecialActionInterface {
        private String cost;

        public String getCost() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.cost;
    }

        public void setCost(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.cost = value;
    }

        public abstract void doAction();

}
