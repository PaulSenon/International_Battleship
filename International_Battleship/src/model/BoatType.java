package model;

public enum BoatType {
    AircraftCarrier(5),
    Cruiser(4),
    Submarine(3),
    TorpedoBoat(2),
    Sentinel(1);

    private final int size;
    BoatType(int size) { this.size = size; }
    public int getSize() { return size; }
}