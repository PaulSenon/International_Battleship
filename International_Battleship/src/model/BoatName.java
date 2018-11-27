package model;

public enum BoatName {
    AircraftCarrier(5),
    Cruiser(4),
    Submarin(3),
    TorpedoBoat(2),
    Sentinel(1);

    private final int size;
    BoatName(int size) { this.size = size; }
    public int getValue() { return size; }
}