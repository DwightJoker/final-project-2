package by.edu.academy.model.role;

import java.util.List;
import java.util.Objects;

import by.edu.academy.model.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idroles")
	private int id;

	@Column(name = "title", nullable = false)
	private String title;

	@ManyToMany(mappedBy = "roles")
	private List<User> user;

	public Role() {
	}

	public Role(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return id == other.id && Objects.equals(title, other.title) && Objects.equals(user, other.user);
	}

}
