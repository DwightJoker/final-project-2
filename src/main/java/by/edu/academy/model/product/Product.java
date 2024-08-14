package by.edu.academy.model.product;

import java.util.List;
import java.util.Objects;

import by.edu.academy.model.basket.BasketItem;
import by.edu.academy.model.category.Category;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproducts", nullable = false)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "discription", length = 2550, nullable = false)
	private String discription;

	@Column(name = "picture", nullable = false)
	private String picture;

	@Column(name = "price", nullable = false)
	private String price;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "category_idcategory", nullable = false)
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<BasketItem> basketItems;

	public Product(int id, String name, String type, String discription, String picture, String price, int quantity,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.discription = discription;
		this.picture = picture;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}

	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<BasketItem> getBasketItems() {
		return basketItems;
	}

	public void setBasketItems(List<BasketItem> basketItems) {
		this.basketItems = basketItems;
	}

	@Override
	public int hashCode() {
		return Objects.hash(basketItems, category, discription, id, name, picture, price, quantity, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(basketItems, other.basketItems) && Objects.equals(category, other.category)
				&& Objects.equals(discription, other.discription) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(picture, other.picture) && Objects.equals(price, other.price)
				&& quantity == other.quantity && Objects.equals(type, other.type);
	}

}
