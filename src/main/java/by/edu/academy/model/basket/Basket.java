package by.edu.academy.model.basket;

import java.util.List;
import java.util.Objects;

import by.edu.academy.model.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "basket")

public class Basket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbasket")
	private int id;

	@ManyToOne
	@JoinColumn(name = "users_idusers", nullable = false)
	private User user;

	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BasketItem> basketItems;

	@Column(name = "total_price")
	private Double totalPrice;

	public Basket() {
	}

	public Basket(User user, List<BasketItem> basketItems, Double totalPrice) {
		this.user = user;
		this.basketItems = basketItems;
		this.totalPrice = totalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BasketItem> getBasketItems() {
		return basketItems;
	}

	public void setBasketItems(List<BasketItem> basketItems) {
		this.basketItems = basketItems;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(basketItems, id, totalPrice, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Basket other = (Basket) obj;
		return Objects.equals(basketItems, other.basketItems) && id == other.id
				&& Objects.equals(totalPrice, other.totalPrice) && Objects.equals(user, other.user);
	}

}
