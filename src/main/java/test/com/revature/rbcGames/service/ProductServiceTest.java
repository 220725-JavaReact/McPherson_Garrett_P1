package test.com.revature.rbcGames.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import com.revature.rbcGames.DAO.ProductDAO;
import com.revature.rbcGames.Service.LineItemService;
import com.revature.rbcGames.Service.ProductService;
import com.revature.rbcGames.models.LineItem;
import com.revature.rbcGames.models.Product;
import com.revature.rbcGames.models.StoreFront;

public class ProductServiceTest {

	private ProductDAO mockProductDAO = Mockito.mock(ProductDAO.class);
	private LineItemService mockLineItemService = Mockito.mock(LineItemService.class);
	private ArrayList<Product> mockProducts = null;
	private ProductService productService = new ProductService(mockProductDAO, mockLineItemService, mockProducts);
	
	
	@Test
	public void GetAllProducts_Pass() {
		Product product1 = new Product();
		product1.setId(0);
		product1.setName("Crash");
		Product product2 = new Product();
		product2.setId(1);
		product2.setName("Spyro");
		ArrayList<Product> products = new ArrayList<>(Arrays.asList(product1, product2));
		
		when(mockProductDAO.GetAllInstances()).thenReturn(products);
		
		ArrayList<Product> actual = productService.GetAllProducts();
		
		Assertions.assertEquals(products, actual);
		
	}
	
	@Test
	public void GetUnlistedProducts() {
		StoreFront store1 = new StoreFront();
		store1.setId(0);
		LineItem lineItem1 = new LineItem();
		LineItem lineItem2 = new LineItem();
		Product product1 = new Product();
		product1.setId(0);
		product1.setName("Crash");
		Product product2 = new Product();
		product2.setId(1);
		product2.setName("Spyro");
		Product product3 = new Product();
		product3.setId(2);
		product3.setName("Mario");
		lineItem1.setId(3);
		lineItem1.setProduct(product3);
		lineItem1.setStoreFront(store1);
		lineItem2.setId(0);
		lineItem2.setProduct(product1);
		lineItem2.setStoreFront(store1);
		
		ArrayList<LineItem> lineItems = new ArrayList<>(Arrays.asList(lineItem1, lineItem2));
		ArrayList<Product> products = new ArrayList<>(Arrays.asList(product1, product2, product3));

		when(mockLineItemService.GetAllLineItemsFromStoreFront(store1)).thenReturn(lineItems);
		when(mockProductDAO.GetAllInstances()).thenReturn(products);
		
		ArrayList<Product> expected = new ArrayList<>(Arrays.asList(product1, product3));
		ArrayList<Product> actual = productService.GetStoreUnlistedProducts(store1);
		

		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void AddProduct_Pass(){
		Product product1 = new Product();
		product1.setId(0);
		product1.setName("Crash");
		product1.setPrice(15);
		
		when(mockProductDAO.AddInstance(product1)).thenReturn(product1);
		
		Product actual = productService.AddProduct(product1);
		
		Assertions.assertEquals(product1, actual);
		
	}
	
	@Test
	public void AddProduct_Fail() {
		Product product1 = new Product();
		product1.setId(0);
		product1.setName("Crash");
		product1.setPrice(0);
		
		when(mockProductDAO.AddInstance(product1)).thenReturn(product1);
		
		Product actual = productService.AddProduct(product1);
		
		Assertions.assertNotEquals(product1, actual);
	}
	
	@Test
	public void AddProduct_Null() {
		Product product1 = new Product();
		product1.setId(0);
		product1.setName("Crash");
		product1.setPrice(0);
		
		when(mockProductDAO.AddInstance(product1)).thenReturn(product1);
		
		Product actual = productService.AddProduct(product1);
		
		Assertions.assertEquals(null, actual);
	}
}
