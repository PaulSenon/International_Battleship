package controler;

import model.BoatInterface;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import tools.Coord;

@objid ("0ba7d808-0e11-42c8-9e3f-0f056a59efa6")
public interface ControllerModelInterface {


    // TODO deprecated, this class will probably be deleted.
    // never used

    @objid ("b916c4ea-3f0d-4a34-bb89-f59aecdd6ca1")
    void ActionWhenBoatIsSelectedOnGUI();

    @objid ("3b3496e7-3c7b-4770-8043-d0674ed20fc7")
    void ActionWhenBoatIsMovedOnGUI();

    void ActionWhenAction(BoatInterface boat, Coord target);

}
