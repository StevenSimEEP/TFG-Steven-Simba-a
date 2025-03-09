package com.sistemas_examenes.entities;

import com.sistemas_examenes.enums.RoleList;

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

    @Enumerated(EnumType.STRING)
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
