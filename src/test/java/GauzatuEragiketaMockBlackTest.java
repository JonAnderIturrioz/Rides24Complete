import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dataAccess.DataAccess;
import domain.User;

public class GauzatuEragiketaMockBlackTest {
/*static DataAccess sut;
	
	protected MockedStatic<Persistence> persistenceMock;

	@Mock
	protected  EntityManagerFactory entityManagerFactory;
	@Mock
	protected  EntityManager db;
	@Mock
    protected  EntityTransaction  et;
	
	@Mock
	TypedQuery<User> typedQuery;

	@Before
    public  void init() {
        MockitoAnnotations.openMocks(this);
        persistenceMock = Mockito.mockStatic(Persistence.class);
		persistenceMock.when(() -> Persistence.createEntityManagerFactory(Mockito.any()))
        .thenReturn(entityManagerFactory);
        
        Mockito.doReturn(db).when(entityManagerFactory).createEntityManager();
		Mockito.doReturn(et).when(db).getTransaction();
	    sut=new DataAccess(db);
    }
	@After
    public  void tearDown() {
		persistenceMock.close();
    }
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void positiveTest1() {
		String username = "testuser";
		double amount = 10;
		boolean deposit = true;
		
		String mota = "admin";
		String pass = "a";
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(10);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);

			double expected = user.getMoney()+amount;

			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			double current = user.getMoney(); 
			
			assertTrue(u);
			assertEquals(expected, current, 0.001);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void positiveTest2() {
		String username ="testuser";
		double amount = 5;
		boolean deposit = false;
		
		String pass ="a";
		String mota ="admin";
		
		
		try {
			User user = new User(username, pass, mota);
			user.setMoney(10);
			
			double expected = user.getMoney() - amount;
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			double current = sut.getUser(username).getMoney();
			
			assertTrue(u);
			assertEquals(expected , current, 0.001);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void positiveTest3() {
		String username = "testuser";
		String pass ="a";
		String mota ="admin";
		double amount = 15;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(10);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			assertTrue(u);
			// Expected 0 because amount to deduct is bigger than money set, and wallet cannot be negative
			assertEquals(0 , user.getMoney(), 0.001);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void positiveTest4() {
		String username = "testuser";
		String pass ="a";
		String mota ="admin";
		double amount = 10;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(10);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			assertTrue(u);
			// Expected 0 because amount to deduct is the same as amount in wallet, result should be 0
			assertEquals(0 , user.getMoney(), 0.001);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void negtiveTest1() {
		String username = "testuser";
		String pass ="a";
		String mota ="admin";
		double amount = 10;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(20);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(null);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			assertFalse(u);
		
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void negtiveTest2() {
		String username = "12345";
		String pass ="a";
		String mota ="admin";
		double amount = 10;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(20);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			// Numeric string usernames being accepted might be unintended
			assertFalse(u);
		
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void negtiveTest3() {
		String username = "";
		String pass ="a";
		String mota ="admin";
		double amount = 10;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(20);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			// Empty username should not be accepted
			assertFalse(u);
		
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void negtiveTest5() {
		String username = "testuser";
		String pass ="a";
		String mota ="admin";
		double amount = -10;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(20);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
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
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void negtiveTest6() {
		String username = "testuser";
		String pass ="a";
		String mota ="admin";
		double amount = 10;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(20);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			// Operation with amount 0 is redundant and should not be accepted.
			assertFalse(u);
		
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void negtiveTest8() {
		String username = null;
		String pass ="a";
		String mota ="admin";
		double amount = 10;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(20);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			// Possible NullPointerException caught and/or handled
			assertFalse(u);
		}catch(NullPointerException e) {
			// Uncaught Exception
			fail();
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void negtiveTest9() {
		String username = "testuser";
		String pass ="a";
		String mota ="admin";
		Double amount = null;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			@SuppressWarnings("null")
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			// Possible NullPointerException caught and/or handled
			assertFalse(u);
			
		}catch (NullPointerException e){
			// Uncaught Exception
			fail();	
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void negtiveTest10() {
		String username = "testuser";
		String pass ="a";
		String mota ="admin";
		double amount = 10;
		Boolean deposit = null;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(20);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			@SuppressWarnings("null")
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			// Possible NullPointerException caught and/or handled
			assertFalse(u);
		}catch (NullPointerException e){
			// Uncaught Exception
			fail();	
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	*/
}
