import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Driver;
import domain.User;
import testOperations.TestDataAccess;

public class GauzatuEragiketaBDWhiteTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	@SuppressWarnings("unused")
	private Driver driver; 
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test1() {
		
		String username = "testuser";//testuser is not in DB
		
		double amount = 10;
		boolean deposit = true;
		
		
		try {
			sut.open();
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			// Looking for a non-existent user is the only way I can think of but this throws an exception rather than returning null.
			// The test takes an unintended path so it's technically a failure.
			assertFalse(u);
			
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally{
			sut.close();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test2() {
		String username ="testuser";
		String pass ="a";
		double amount = 10;
		boolean deposit = true;
		boolean driverCreated = false;
		
		
		try {
			// Add testuser to database and save User object as "driver"
			testDA.open();
			if (!testDA.existDriver(username)) {
				driver = testDA.createDriver(username,pass);
			    driverCreated = true;
			} else driver = sut.getDriver(username);
			testDA.open();
			
			// Get expected money, current + amount to add
			double expected = driver.getMoney() + amount;
			
			sut.open();
			// Run test
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			// Get new money amount
			double current= sut.getDriver(username).getMoney();
			
			// Check function success and correct result
			assertTrue(u);
			assertEquals(expected , current, 0.001);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally{
			// Cleanup
			testDA.open();
			if (driverCreated)
				testDA.removeDriver(username);
			testDA.close();
			sut.close();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test3() {
		String username = "testuser";
		String pass ="a";
		double amount;
		boolean deposit = false;
		boolean driverCreated = false;
		
		try {
			
			// Add testuser to database and save User object as "driver"
			testDA.open();
			if (!testDA.existDriver(username)) {
				driver = testDA.createDriver(username,pass);
			    driverCreated = true;
			} else driver = sut.getDriver(username);
			testDA.open();
			
			// Get expected money, which should be 0
			double expected = 0;
			
			// Set amount to be bigger than current money for negative result
			amount = driver.getMoney() + 10;
			
			sut.open();
			// Run test
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
					
			// Get new money amount
			double current= sut.getDriver(username).getMoney();
					
			// Check function success and correct result
			assertTrue(u);
			assertEquals(expected , current, 0.001);
						
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally{
			// Cleanup
			testDA.open();
			if (driverCreated)
				testDA.removeDriver(username);
			testDA.close();
			sut.close();
		}

	}
	
	@Test
	public void test4() {
		
		String username = "testuser";
		String pass ="a";
		double amount = 10;
		boolean deposit = false;
		boolean driverCreated = false;
		
		try {
			
			// Add testuser to database and save User object as "driver"
			testDA.open();
			if (!testDA.existDriver(username)) {
				driver = testDA.createDriver(username,pass);
			    driverCreated = true;
			} else driver = sut.getDriver(username);
			testDA.open();
			
			sut.open();
			
			// Add money to driver for positive result 
			sut.gauzatuEragiketa(username, 100, true);
			
			// Set expected result, which should be current - amount
			double expected = 100 - amount;
			
			// Run test
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
					
			// Get new money amount
			double current= sut.getDriver(username).getMoney();
					
			// Check function success and correct result
			assertTrue(u);
			assertEquals(expected , current, 0.001);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally{
			// Cleanup
			testDA.open();
			if (driverCreated)
				testDA.removeDriver(username);
			testDA.close();
			sut.close();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test5() {
		String username = null;
		double amount = 10;
		boolean deposit = false;
		
		try {
			
			sut.open();
			// Run test
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			// null username makes db.getUser(String) throw a NoResultException
			// gauzatuEragiketa catches the exception properly and returns false
			
			assertFalse(u);
		
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		} finally{
			// Cleanup
			sut.close();
		}

	}
	
}
