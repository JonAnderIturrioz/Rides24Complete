import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Driver;
import testOperations.TestDataAccess;

public class GauzatuEragiketaBDBlackTest {
	//sut:system under test
		 static DataAccess sut=new DataAccess();
		 
		 //additional operations needed to execute the test 
		 static TestDataAccess testDA=new TestDataAccess();

		private Driver driver; 
		
		@Test
		public void positiveTest1() {
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
				testDA.close();
				
				// Get expected money, current + amount to add
				// Should be 0 + 10 if new driver
				double expected = driver.getMoney() + amount;
				
				sut.open();
				// Starting amount of money is 0 because that's how new users are created
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
		public void positiveTest2() {
			String username ="testuser";
			String pass ="a";
			double amount = 5;
			boolean deposit = false;
			boolean driverCreated = false;
			
			
			try {
				// Add testuser to database and save User object as "driver"
				testDA.open();
				if (!testDA.existDriver(username)) {
					driver = testDA.createDriver(username,pass);
				    driverCreated = true;
				} else driver = sut.getDriver(username);
				testDA.close();
				
				// Get expected money, current + amount to add
				
				double expected = 10 - amount;
				
				sut.open();
				// Set money bigger than amount				
				sut.gauzatuEragiketa(username, 10 , true);
				
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
		public void positiveTest3() {
			String username = "testuser";
			String pass ="a";
			double amount = 15;
			boolean deposit = false;
			boolean driverCreated = false;
			
			try {
				
				// Add testuser to database and save User object as "driver"
				testDA.open();
				if (!testDA.existDriver(username)) {
					driver = testDA.createDriver(username,pass);
				    driverCreated = true;
				} else driver = sut.getDriver(username);
				testDA.close();
				
				// Expected money should be negative, which should be corrected to 0
				double expected = 0;				

				sut.open();
				
				// Set up quantity smaller than amount to run test
				sut.gauzatuEragiketa(username, 10 , true);
				
				// Run test
				boolean u=sut.gauzatuEragiketa(username, amount , deposit);
						
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
		public void positiveTest4() {
			
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
				testDA.close();
				
				sut.open();
				
				// Add money to driver for result zero 
				sut.gauzatuEragiketa(username, amount , true);
				
				// Set expected result, which should be 0
				double expected = 0;
				
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
		public void negativeTest1() {
			String username = "testuser"; //no object of this username in the DB
			double amount = 10;
			boolean deposit = true;
			
			try {
				
							
				sut.open();
				// Run test with empty DB
				boolean u=sut.gauzatuEragiketa(username, amount, deposit);
						
				// Check function success and correct result
				assertFalse(u);
				
			}catch(Exception e) {
				e.printStackTrace();
				fail();
			} finally{
				// Cleanup
				sut.close();
			}
		}
		
		@Test
		public void negativeTest3() {
			String username = "";
			String pass ="a";
			double amount = 10;
			boolean deposit = false;
			boolean driverCreated = false;
			
			try {
				
				// Add testuser to database and save User object as "driver"
				testDA.open();
				if (!testDA.existDriver(username)) {
					driver = testDA.createDriver(username,pass); // even this fuction probably shouldn't accept empty strings...
				    driverCreated = true;
				} else driver = sut.getDriver(username);
				testDA.close();
				
				sut.open();
				
				// Run test
				boolean u=sut.gauzatuEragiketa(username, amount, deposit);
						
						
				// Should likely either fail or throw an exception, does neither.
				assertFalse(u);
				
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
		public void negativeTest5() {
			String username = "testuser";
			String pass ="a";
			double amount = -10;
			boolean deposit = true;
			boolean driverCreated = false;
			
			try {
				
				// Add testuser to database and save User object as "driver"
				testDA.open();
				if (!testDA.existDriver(username)) {
					driver = testDA.createDriver(username,pass);
				    driverCreated = true;
				} else driver = sut.getDriver(username);
				testDA.close();
				
				sut.open();
				// Set baseline amount
				sut.gauzatuEragiketa(username, amount, true);
				
				
				// Run test
				boolean u=sut.gauzatuEragiketa(username, amount, deposit);
				
				// Check if operation is done with non-valid numbers
				// If operation goes through, baseline amount should change and lead to failure
				assertEquals(10, sut.getActualMoney(username), 0.001);
				
				// Negative numbers should not be accepted.
				// It defeats the point of the deposit parameter.
				assertFalse(u);
				
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
		public void negativeTest6() {
			String username = "testuser";
			String pass ="a";
			double amount = -10;
			boolean deposit = true;
			boolean driverCreated = false;
			
			try {
				
				// Add testuser to database and save User object as "driver"
				testDA.open();
				if (!testDA.existDriver(username)) {
					driver = testDA.createDriver(username,pass);
				    driverCreated = true;
				} else driver = sut.getDriver(username);
				testDA.close();
				
				sut.open();

				// Run test
				boolean u=sut.gauzatuEragiketa(username, amount, deposit);
				
				// Operation with amount 0 is redundant and should not be accepted.
				assertFalse(u);
				
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
		public void negativeTest8() {
			String username = null;
			//String pass = "a";
			double amount = 10;
			boolean deposit = true;
			//boolean driverCreated = false;
			
			try {
				// Add testuser to database and save User object as "driver"
				/*testDA.open();
				if (!testDA.existDriver(username)) {
					driver = testDA.createDriver(username,pass);
				    driverCreated = true;
				} else driver = sut.getDriver(username);
				testDA.close();*/
				sut.open();
				
				// Run test
				boolean u=sut.gauzatuEragiketa(username, amount, deposit);
						
				// Check function success and correct result
				assertFalse(u);
				
			}catch(NullPointerException e){
				// Uncaught exception
				fail();
			}catch(Exception e) {
				e.printStackTrace();
				fail();
			} finally{
				// Cleanup
				/*testDA.open();
				if (driverCreated)
					testDA.removeDriver(username);
				testDA.close();*/
				sut.close();
			}
		}
		
		@Test
		public void negativeTest9() {
			String username = "testuser";
			String pass ="a";
			Double amount = null;
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
				
				sut.open();
				
				// Run test
				@SuppressWarnings("null")
				boolean u=sut.gauzatuEragiketa(username, amount, deposit);
						
				// Should catch null pointer if it happens and return false
				assertFalse(u);
				
			}catch(NullPointerException e) {
				// Uncaught exception
				fail();
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
		public void negativeTest10() {
			String username = "testuser";
			String pass ="a";
			double amount = 10;
			Boolean deposit = null;
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
				
				// Run test deposit = null
				@SuppressWarnings("null")
				boolean u=sut.gauzatuEragiketa(username, amount, deposit);
						
				// Correct if function
				assertFalse(u);
				
			}catch(NullPointerException e) {
				// Uncaught Exception is thrown, program failed. No checks are done for this error.
				fail();
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
		
		
}
