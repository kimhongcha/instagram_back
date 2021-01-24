package com.example.demo.domain.entity;

import com.example.demo.domain.BaseTimeEntity;
import com.example.demo.domain.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.ArrayList;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

@Entity
public class Member extends BaseTimeEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	private Role role;

//	@JsonIgnore
//	@OneToMany(mappedBy = "member")
//	private List<Board> boards = new ArrayList<>();
//
//	@JsonIgnore
//	@OneToMany(mappedBy = "member")
//	private List<Comment> comments = new ArrayList<>();
//
//	@JsonIgnore
//	@OneToMany(mappedBy = "member")
//	private List<Event> events = new ArrayList<>();

	@Builder
	public Member(String email, String password, String name, Role role){
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	public void encodingPassword(String password){
		this.password = password;
	}

}
