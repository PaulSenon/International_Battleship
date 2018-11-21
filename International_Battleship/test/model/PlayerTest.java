package model;

import org.junit.Test;

import tools.PersonnalException;

public class PlayerTest extends Player{

	@Test
	public void ActionPoint() throws PersonnalException {
		int testActionPoint = 17;
		setActionPoint(17);
		//Test of setActionPoint method
		if (testActionPoint != getActionPoint()) {
			throw new PersonnalException("Test failed !!");
		}
		//Test of creditActionPoint method
		setActionPoint(getMaxActionPoint() - 2);
		creditActionPoint(1);
		if (getActionPoint() != getMaxActionPoint() - 1) {
			throw new PersonnalException("Test failed !!");
		}
		creditActionPoint(3);
		if (getActionPoint() != getMaxActionPoint()) {
			throw new PersonnalException("Test failed !!");
		}
		//Test of debitActionPoint method
		setActionPoint(2);
		debitActionPoint(1);
		if (getActionPoint() != 1) {
			throw new PersonnalException("Test failed !!");
		}
	}

}
