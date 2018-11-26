package view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import model.BoatName;
import tools.Coord;
import tools.Direction;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

@objid ("3b04865d-c08b-43d3-8e93-24b16ee484b7")
public class BoatGUI {
    private Direction direction;
    private BoatName name;
    private List<JLabel> listOfFragmentBoat;

    @objid ("ebfb1aca-aff8-46b5-b450-22e1f24d47a0")
    public BoatGUI(BoatName name, List<JLabel> listOfFragment, Direction direction){
        this.name = name;
        this.listOfFragmentBoat = listOfFragment;
        this.direction = direction;
    }

    public List<JLabel> getListOfFragmentBoat() {
        return listOfFragmentBoat;
    }
}
