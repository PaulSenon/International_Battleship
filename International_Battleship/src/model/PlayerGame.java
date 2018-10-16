package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("e18fc35c-5b26-4137-a3ef-3863e6e3f46a")
public interface PlayerGame {
    @objid ("b31194db-0d16-49e3-836e-01c937a0eb0b")
    Boat getBoat();

    @objid ("6d661e57-8fae-4018-a1b5-70963b1dc0b3")
    void setBoat(final Boat value);

}
