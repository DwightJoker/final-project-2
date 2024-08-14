package by.edu.academy.model.history;

import java.util.Objects;

import by.edu.academy.model.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpurchase_history")
	private int id;

	@Column(name = "product_name", nullable = false)
	private String productNameHistory;

	@Column(name = "product_picture", nullable = false)
	private String productPictureHistory;

	@Column(name = "product_quantity", nullable = false)
	private int productQuantityHistory;

	@ManyToOne
	@JoinColumn(name = "users_idusers", nullable = false)
	private User user;

	public PurchaseHistory() {
	}

	public PurchaseHistory(int id, String productNameHistory, String productPictureHistory, int productQuantityHistory,
			User user) {
		this.id = id;
		this.productNameHistory = productNameHistory;
		this.productPictureHistory = productPictureHistory;
		this.productQuantityHistory = productQuantityHistory;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductNameHistory() {
		return productNameHistory;
	}

	public void setProductNameHistory(String productNameHistory) {
		this.productNameHistory = productNameHistory;
	}

	public String getProductPictureHistory() {
		return productPictureHistory;
	}

	public void setProductPictureHistory(String productPictureHistory) {
		this.productPictureHistory = productPictureHistory;
	}

	public int getProductQuantityHistory() {
		return productQuantityHistory;
	}

	public void setProductQuantityHistory(int productQuantityHistory) {
		this.productQuantityHistory = productQuantityHistory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, productNameHistory, productPictureHistory, productQuantityHistory, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseHistory other = (PurchaseHistory) obj;
		return id == other.id && Objects.equals(productNameHistory, other.productNameHistory)
				&& Objects.equals(productPictureHistory, other.productPictureHistory)
				&& productQuantityHistory == other.productQuantityHistory && Objects.equals(user, other.user);
	}

}
