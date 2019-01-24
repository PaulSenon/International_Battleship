package model;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import testTools.BaseTests;
import tools.GameConfig;
import tools.PersonnalException;

public class PlayerTest extends BaseTests {

	@Before
	public void setUp() {
		// setup game config :
		GameConfig.forceNewInstance(
				25, // gameGridWidth
				25, // gameGridHeight
				20, // maxActionPoint
				7, // portSize
				4, // nbMaxPlayer
				new String[]{
						"José",
						"Théodule",
						"Yvonne",
						"Titouan"
				},
				new BoatType[]{
						BoatType.Cruiser,
						BoatType.Submarine,
						BoatType.AircraftCarrier,
						BoatType.Sentinel,
						BoatType.TorpedoBoat
				},
				0,
				0,
				0,
				new Color(0,0,0,0),new Color(0,0,0,0),new Color(0,0,0,0),new Color(0,0,0,0)

		);
	}

	@Test
	public void ActionPoint() throws PersonnalException {
		Player player = new Player(1,"Player1", "Port1");
		int testActionPoint = 17;
		player.setNbActionPoint(17);
		//Test of setNbActionPoint method
		if (testActionPoint != player.getNbActionPoint()) {
			throw new PersonnalException("Test failed !!");
		}
		//Test of creditActionPoint method
		player.setNbActionPoint(player.getMaxActionPoint() - 2);
		player.creditActionPoint(1);
		if (player.getNbActionPoint() != player.getMaxActionPoint() - 1) {
			throw new PersonnalException("Test failed !!");
		}
		player.creditActionPoint(3);
		if (player.getNbActionPoint() != player.getMaxActionPoint()) {
			throw new PersonnalException("Test failed !!");
		}
		//Test of debitActionPoint method
		player.setNbActionPoint(2);
		player.debitActionPoint(1);
		if (player.getNbActionPoint() != 1) {
			throw new PersonnalException("Test failed !!");
		}
	}

}
