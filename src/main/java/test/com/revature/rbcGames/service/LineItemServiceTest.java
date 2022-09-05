package test.com.revature.rbcGames.service;

import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import com.revature.rbcGames.DAO.LineItemDAO;
import com.revature.rbcGames.Service.LineItemService;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.models.StoreFront;

public class LineItemServiceTest {
	
	private LineItemDAO mockLineItemDAO = Mockito.mock(LineItemDAO.class);
	private ArrayList<LineItem> mockLineItems = null;
	private LineItemService lineItemService = new LineItemService(mockLineItemDAO, mockLineItems);
	
	
	@Test
	public void GetAll_Pass() {
		LineItem lineItem1 = new LineItem();
		lineItem1.setQuantity(2);
		lineItem1.setProduct(new Product());
		lineItem1.setId(0);
		LineItem lineItem2 = new LineItem();
		lineItem2.setQuantity(1);
		lineItem2.setProduct(new Product());
		lineItem2.setId(3);
		ArrayList<LineItem> lineItems = new ArrayList<>(Arrays.asList(lineItem1, lineItem2));
		
		when(mockLineItemDAO.GetAllInstances()).thenReturn(lineItems);
		
		ArrayList<LineItem> actual = lineItemService.GetAllLineItemsAll();
		
		Assertions.assertEquals(lineItems, actual);
	}
	
	@Test
	public void GetAll_Fail() {
		LineItem lineItem1 = new LineItem();
		lineItem1.setQuantity(2);
		lineItem1.setProduct(new Product());
		lineItem1.setId(0);
		LineItem lineItem2 = new LineItem();
		lineItem2.setQuantity(1);
		lineItem2.setProduct(new Product());
		lineItem2.setId(3);
		ArrayList<LineItem> lineItems1 = new ArrayList<>(Arrays.asList(lineItem1, lineItem2));
		ArrayList<LineItem> lineItems2 = new ArrayList<>(Arrays.asList(lineItem1));
		
		when(mockLineItemDAO.GetAllInstances()).thenReturn(lineItems1);
		
		ArrayList<LineItem> actual = lineItemService.GetAllLineItemsAll();
		
		Assertions.assertNotEquals(lineItems2, actual);
	}
	
	@Test 
	public void GetFromStore_Pass() {
		StoreFront store1 = new StoreFront();
		store1.setId(0);
		StoreFront store2 = new StoreFront();
		store2.setId(1);
		LineItem lineItem1 = new LineItem();
		lineItem1.setQuantity(2);
		lineItem1.setProduct(new Product());
		lineItem1.setId(0);
		lineItem1.setStoreFront(store1);
		LineItem lineItem2 = new LineItem();
		lineItem2.setQuantity(1);
		lineItem2.setProduct(new Product());
		lineItem2.setId(3);
		lineItem2.setStoreFront(store2);
		ArrayList<LineItem> lineItems = new ArrayList<>(Arrays.asList(lineItem1, lineItem2));
		
		when(mockLineItemDAO.GetAllInstances()).thenReturn(lineItems);
		
		ArrayList<LineItem> actual = lineItemService.GetAllLineItemsFromStoreFront(store1);
		ArrayList<LineItem> expected = new ArrayList<>(Arrays.asList(lineItem1));
		
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void UpdateLineItems_Pass() {
		StoreFront store1 = new StoreFront();
		store1.setId(0);
		StoreFront store2 = new StoreFront();
		store2.setId(1);
		LineItem lineItem1 = new LineItem();
		lineItem1.setQuantity(2);
		lineItem1.setProduct(new Product());
		lineItem1.setId(0);
		lineItem1.setStoreFront(store1);
		LineItem lineItem2 = new LineItem();
		lineItem2.setQuantity(1);
		lineItem2.setProduct(new Product());
		lineItem2.setId(3);
		lineItem2.setStoreFront(store2);
		ArrayList<LineItem> lineItems1 = new ArrayList<>(Arrays.asList(lineItem1, lineItem2));
		ArrayList<LineItem> lineItems2 = new ArrayList<>(Arrays.asList(lineItem1));
		
		when(mockLineItemDAO.UpdateExistingInstances(lineItems2)).thenReturn(lineItems2);
		when(mockLineItemDAO.GetAllInstances()).thenReturn(lineItems1);
		
		ArrayList<LineItem> actual = lineItemService.UpdateLineItems(lineItems2);
		
		Assertions.assertEquals(actual, lineItems1);
	}
	
	// add updateLineItemFromOrder test
}
