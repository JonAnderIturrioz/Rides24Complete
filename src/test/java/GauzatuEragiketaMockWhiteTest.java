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

public class GauzatuEragiketaMockWhiteTest {
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
	public void test1() {
		String username="testuser";
		double amount= 10;
		boolean deposit= true;
		
		
		try {
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
	public void test2() {
		String username ="testuser";
		String pass ="a";
		String mota ="admin";
		double amount = 10;
		boolean deposit = true;
		
		
		try {
			User user = new User(username, pass, mota);
			Double money = user.getMoney();
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			assertTrue(u);
			assertEquals(money+amount , user.getMoney(), 0.001);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test3() {
		String username = "testuser";
		String pass ="a";
		String mota ="admin";
		double amount = 10;
		boolean deposit = false;
		
		try {
			
			User user = new User(username, pass, mota);
			user.setMoney(5);
			
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(typedQuery);
			Mockito.when(typedQuery.getSingleResult()).thenReturn(user);
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			assertTrue(u);
			assertEquals(0 , user.getMoney(), 0.001);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test4() {
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
			
			assertTrue(u);
			assertEquals(10 , user.getMoney(), 0.001);
			
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void test5() {
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
			Mockito.doThrow(new RuntimeException("Commit failed")).when(et).commit();
			
			boolean u=sut.gauzatuEragiketa(username, amount, deposit);
			
			assertFalse(u);
		
		}catch(Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	*/
}
