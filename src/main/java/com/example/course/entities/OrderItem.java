package com.example.course.entities;

import java.io.Serializable;
import java.util.Objects;

import com.example.course.entities.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OrderItemPK id;
	private Integer quantity;
	private Double price;

	
	public OrderItem() {
		super();
	}

	public OrderItem (Integer quantity, Double price, Product product, Order order) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.id.setProduct(product);
		this.id.setOrder(order);
	}

	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		this.id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		this.id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, price, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id) && Objects.equals(price, other.price)
				&& Objects.equals(quantity, other.quantity);
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
	}

	
	/*
	 * public double subTotal(){
	 * 	quantity * price;
	 * }
	 */

}
