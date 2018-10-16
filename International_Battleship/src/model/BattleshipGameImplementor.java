package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("75a7604f-f615-4013-af67-b5d5bd901738")
public interface BattleshipGameImplementor {
    @objid ("292fef80-59e0-4f72-99c4-35036676c47b")
    void shoot();

    @objid ("37d29d69-fc60-40fa-81d7-2d4a1000d1e2")
    void move();

    @objid ("5216da63-aac6-4fdd-8ebb-95e7c49e22e8")
    void findBoatByCoord();

}
