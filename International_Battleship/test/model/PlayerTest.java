package model;

import org.junit.Before;
import org.junit.Test;

import testTools.BaseTests;
import tools.GameConfig;
import tools.PersonnalException;

import static tools.GameConfig.getMaxActionPoint;

public class PlayerTest extends BaseTests {

	@Before
	public void setUp() {
		GameConfig.newInstance(
				25, // gameGridWidth
				25, // gameGridHeight
				20 //maxActionPoint
		);
	}

	@Test
	public void ActionPoint() throws PersonnalException {
		Player player = new Player("Player1", "Port1");
		int testActionPoint = 17;
		player.setActionPoint(17);
		//Test of setActionPoint method
		if (testActionPoint != player.getActionPoint()) {
			throw new PersonnalException("Test failed !!");
		}
		//Test of creditActionPoint method
		player.setActionPoint(player.getMaxActionPoint() - 2);
		player.creditActionPoint(1);
		if (player.getActionPoint() != player.getMaxActionPoint() - 1) {
			throw new PersonnalException("Test failed !!");
		}
		player.creditActionPoint(3);
		if (player.getActionPoint() != player.getMaxActionPoint()) {
			throw new PersonnalException("Test failed !!");
		}
		//Test of debitActionPoint method
		player.setActionPoint(2);
		player.debitActionPoint(1);
		if (player.getActionPoint() != 1) {
			throw new PersonnalException("Test failed !!");
		}
	}

}
