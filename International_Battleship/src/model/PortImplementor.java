package model;

import tools.Coord;
import tools.GameConfig;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortImplementor implements PortImplementorInterface {

	private List<Port> ports;

	public PortImplementor(List<PlayerInterface> players) {
		int i = 0;
		this.ports = new ArrayList<>();
		for (PlayerInterface player: players){
			Port port = new Port(player.getId(), GameConfig.getColors()[i], i);
			this.ports.add(port);
			i++;
		}
	}

	public Map <Coord, Color> getVisibleCoords(PlayerInterface player) {
		Map <Coord, Color> visibleCoords = new HashMap<Coord, Color>();
		for(Port port: this.ports){
			if (port.getPlayerIdInModel() == player.getId())
				visibleCoords = port.getVisibleCoords();
		}
		return visibleCoords;
	}

	public Map <Coord, Color> getColorOfCoord(List<Coord> visibleCoordsBoat) {
		Map <Coord, Color> visibleCoords = new HashMap<Coord, Color>();
		for(Coord coord: visibleCoordsBoat){
			Color color = getColorOfCoord(coord);
			visibleCoords.put(coord, color);
		}
		return visibleCoords;
	}

	private Color getColorOfCoord(Coord coord) {
		for(Port port: this.ports){
			List<Coord> coords = port.getCoords();
			if (coords.contains(coord)){
				return port.getColor();
			}
		}
		return null;
	}

	public boolean isInPort(Coord coord) {
		for (Port port: this.ports){
			if (port.getCoords().contains(coord))
				return true;
		}
		return false;
	}

	public boolean checkIfCoordInEnnemyPort(Coord coord, int playerId) {
		for(Port port: this.ports){
			if (port.getPlayerIdInModel() == playerId)
				continue;
			if (port.getCoords().contains(coord))
				return true;
		}
		return false;
	}

}
