package com.example.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Category;
import com.example.course.entities.Order;
import com.example.course.entities.OrderItem;
import com.example.course.entities.Payment;
import com.example.course.entities.Product;
import com.example.course.entities.User;
import com.example.course.entities.enums.OrderStatus;
import com.example.course.repositories.CategoryRepository;
import com.example.course.repositories.OrderItemRepository;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.ProductRepository;
import com.example.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of he Rings", "Lorem ipsum dolor sit amet, consectetur ", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Vestibulum quis risus varius purus sagittis eleifend", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Aenean consectetur velit at quam auctor aliquam non quis nibh. ", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "raesent lacinia quam nec lorem rutrum lobortis.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", " Nullam eros leo, viverra sit amet vulputate et, congue sed felis.", 100.99, "");
				
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		User u1 = new User(null,"Maria Brown","maria@gmail.com","988888888","123456");
		User u2 = new User(null,"Alex Green","alex@gmail.com","977777777","123456");	
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:08Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
		
		userRepository.saveAll(Arrays.asList(u1,u2));
		
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));
		
		OrderItem oi1 = new OrderItem(2, p1.getPrice(), p1, o1);
		OrderItem oi2 = new OrderItem(1, p4.getPrice(), p4, o1);
		OrderItem oi3 = new OrderItem(2, p1.getPrice(), p1, o2);
		OrderItem oi4 = new OrderItem(2, p5.getPrice(), p5, o3);
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:08Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);
		
	}
}
