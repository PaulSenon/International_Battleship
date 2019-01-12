package model;

import tools.Coord;

import java.util.ArrayList;
import java.util.List;

public class SpecialZoneAOE extends TargetAction{
    private int radius;

    public SpecialZoneAOE(int radius) {
        super();
        this.radius = radius;
    }

    @Override
    public void doAction(Coord target) {

    }

    public List<Coord> getEffectZone(Coord coord){
        List<Coord> areaOfEffect = new ArrayList<>();
        int lineBeginning = coord.getY() - radius;
        int lineEnd = coord.getY() + radius + 1;
        int columnBeginning = coord.getX() - radius;
        int columnEnd = coord.getX() + radius + 1;
        for (int line = lineBeginning; line<lineEnd; line ++){
            for(int column = columnBeginning; column<columnEnd; column ++) {
                if (!((line==lineBeginning && column==columnBeginning) || (line==lineBeginning && column==columnEnd - 1) || (line==lineEnd - 1 && column==columnBeginning) || (line==lineEnd - 1 && column==columnEnd - 1))){
                    Coord visibleCoord = new Coord(column, line);
                    areaOfEffect.add(visibleCoord);
                }
            }
        }
        return areaOfEffect;
    }

}
