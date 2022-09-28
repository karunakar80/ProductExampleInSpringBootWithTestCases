package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductServiceimpl;



@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootCrudApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@MockBean
	private ProductRepository repository;
	
	@Autowired
	private ProductServiceimpl service;
	
	
	@Test
	public void getProductsTest() {
		when(repository.findAll()).thenReturn(Stream.of(new Product(1,"bags","80","100"),new Product(2,"nokia","90","10000")).collect(Collectors.toList()));
		assertEquals(2,service.getProducts().size());
		
		}
	
	@Test
	public void getProductByIdTest() {
		 int id=76;
		
		when(repository.findById(id).orElse(null))
		.thenReturn((Product) Stream.of(new Product(76,"bags","80","100")).collect(Collectors.toList()));
		assertEquals(1,service.getProductById(id));
	}
	
	@Test
	public void getProductbyNameTest() {
		String name="bagss";
		
		when(repository.findByName(name))
		.thenReturn((Product) Stream.of(new Product(1,"bags","80","100")).collect(Collectors.toList()));
		assertEquals(1,service.getProductByName(name));
	}
	
	@Test
	public void saveProductTest() {
		Product products=new Product(1,"bags","80","100");
		when(repository.save(products)).thenReturn(products);
		assertEquals(products,service.saveProduct(products));
	}
	
	
	
	
	
	
	@Test
	public void deleteProductTest() {
		Product products=new Product(1,"mobile","80","10000");
		service.deleteProduct(products);
		verify(repository,times(1)).delete(products);
		
	}
	
	
	
	

}
