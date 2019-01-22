package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.Coord;
import tools.GameConfig;

public class Port implements PortInterface{
	
	private int playerIdInModel;
	private int areaVisible;
	private Color color;
	private int playerPosition;
	private List<Coord> coords;

	public Port(int playerId, Color color, int playerIdPosition){
		this.playerIdInModel = playerId; //This id is the id of the object player in the Model
		this.playerPosition = playerIdPosition; //This id is the id of the player from 0 to 3
		this.color = color;
		this.areaVisible = GameConfig.getPortSize();
		this.coords = new ArrayList<Coord>(getVisibleCoords().keySet());
	}

	public int getPlayerIdInModel() {
		return playerIdInModel;
	}

	public Color getColor() {
		return color;
	}

	public Map <Coord, Color> getVisibleCoords() {
		Coord origin;
        switch (this.playerPosition){
            case 0:
                return getPortCoordsFromOrigin(new Coord(0,0));
            case 1:
                origin = new Coord(GameConfig.getGameGridWidth() - this.areaVisible, 0);
                return getPortCoordsFromOrigin(origin);
            case 2:
            	origin = new Coord(GameConfig.getGameGridWidth() - this.areaVisible, GameConfig.getGameGridHeight() - this.areaVisible);
                return getPortCoordsFromOrigin(origin);
            case 3:
                origin = new Coord(0, GameConfig.getGameGridHeight() - this.areaVisible);
                return getPortCoordsFromOrigin(origin);
            default:
                System.out.println("Error PortManager, there is ony 4 ports (from 0 to 3)");
        }
        return null;
	}
	
	private Map <Coord, Color> getPortCoordsFromOrigin(Coord origin){
		Map <Coord, Color> coords = new HashMap<Coord, Color>();
        for(int y = 0; y < this.areaVisible; y++){
            for(int x = 0; x < this.areaVisible; x++){
            	coords.put(origin.add(new Coord(x, y)), this.color);
            }
        }
        return coords;
    }
	
	public List<Coord> getCoords(){
		return this.coords;
	}
}
