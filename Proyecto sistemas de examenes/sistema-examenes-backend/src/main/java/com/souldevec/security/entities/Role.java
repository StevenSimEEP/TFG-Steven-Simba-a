package com.souldevec.security.entities;

import com.souldevec.security.enums.RoleList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private RoleList name;

	public RoleList getName() {
		return name;
	}
	
	public Role(RoleList name) {
        this.name = name;
    }
	
	public Role() {
	}

	public void setName(RoleList name) {
		this.name = name;
	}
}
