package test.com.revature.rbcGames.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import com.revature.rbcGames.DAO.StoreFrontDAO;
import com.revature.rbcGames.Service.StoreFrontService;
import com.revature.rbcGames.models.StoreFront;

public class StoreFrontServiceTest {
	private StoreFrontDAO mockStoreFrontDAO = Mockito.mock(StoreFrontDAO.class);
	private ArrayList<StoreFront> mockStoreFronts = null;
	private StoreFront mockStoreFront = null;
	private StoreFrontService storeFrontService = new StoreFrontService(mockStoreFrontDAO, mockStoreFronts, mockStoreFront);
	
	@Test
	public void AddStoreFront_Pass() {
		StoreFront store1 = new StoreFront();
		store1.setId(0);
		store1.setName("34");
		
		when(mockStoreFrontDAO.AddInstance(store1)).thenReturn(store1);
		
		StoreFront actual = storeFrontService.AddStoreFront(store1);
		
		Assertions.assertEquals(store1, actual);
	}
	
	@Test
	public void GetAll_Pass() {
		StoreFront store1 = new StoreFront();
		store1.setId(0);
		store1.setName("34");
		StoreFront store2 = new StoreFront();
		store2.setId(1);
		store2.setName("56");
		StoreFront store3 = new StoreFront();
		store3.setId(2);
		store3.setName("78");
		ArrayList<StoreFront> storeFronts = new ArrayList<>(Arrays.asList(store1, store2, store3));
		
		when(mockStoreFrontDAO.GetAllInstances()).thenReturn(storeFronts);
		
		ArrayList<StoreFront> actual = storeFrontService.GetAllStoreFronts();
		
		Assertions.assertEquals(storeFronts, actual);
	}
	
	@Test
	public void GetStoreById_Pass() {
		StoreFront store1 = new StoreFront();
		store1.setId(0);
		store1.setName("34");
		StoreFront store2 = new StoreFront();
		store2.setId(1);
		store2.setName("56");
		StoreFront store3 = new StoreFront();
		store3.setId(2);
		store3.setName("78");
		ArrayList<StoreFront> storeFronts = new ArrayList<>(Arrays.asList(store1, store2, store3));
		
		when(mockStoreFrontDAO.GetAllInstances()).thenReturn(storeFronts);
		
		StoreFront actual =  storeFrontService.GetAStoreFront("1");
		
		Assertions.assertEquals(store2, actual);
	}
}
