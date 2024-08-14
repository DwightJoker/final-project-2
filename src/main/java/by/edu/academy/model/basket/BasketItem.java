package by.edu.academy.model.basket;

import java.util.Objects;

import by.edu.academy.model.product.Product;
import jakarta.persistence.*;

@Entity
@Table(name = "basket_item")
public class BasketItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbasket_item")
	private int idbasket_item;

	@ManyToOne
	@JoinColumn(name = "basket_id", nullable = false)
	private Basket basket;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	public BasketItem() {
	}

	public BasketItem(Basket basket, Product product, int quantity) {
		this.basket = basket;
		this.product = product;
		this.quantity = quantity;

	}

	public int getIdbasket_item() {
		return idbasket_item;
	}

	public void setIdbasket_item(int idbasket_item) {
		this.idbasket_item = idbasket_item;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(basket, idbasket_item, product, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasketItem other = (BasketItem) obj;
		return Objects.equals(basket, other.basket) && idbasket_item == other.idbasket_item
				&& Objects.equals(product, other.product) && quantity == other.quantity;
	}

}
