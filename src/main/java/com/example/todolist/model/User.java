package com.example.todolist.model;

import jakarta.persistence.*;

@Entity                  // ✅ Required to tell JPA this is an entity
@Table(name = "users")   // optional: sets the DB table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    // ✅ Always include a no-arg constructor
    public User() {}

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

	 public User orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
