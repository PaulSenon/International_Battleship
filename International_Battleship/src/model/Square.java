package model;


import tools.Coord;

public class Square {
        private boolean isInternational;

        private boolean isPort;

        private boolean isMined;
    
    public boolean isDestroyed;

        private String isOccupied;

        public Coord coord;

        public void isMined() {
    }

        public void isInternational() {
    }

        public void isPort() {
    }

        public Square() {
    }

	public void destroy() {
		this.isDestroyed = true;
	}
    
}
