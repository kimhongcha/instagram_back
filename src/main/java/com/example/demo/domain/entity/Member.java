package com.example.demo.domain.entity;

import com.example.demo.domain.BaseTimeEntity;
import com.example.demo.domain.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String email;

	@NotNull
	@Column(nullable = false)
	private String password;

	@NotNull
	@Column(nullable = false)
	private String name;

	@Column(length = 32, columnDefinition = "varchar(32) default 'UNKNOWN'")
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Feed> boards = new ArrayList<>();

	@OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Comment> comments = new ArrayList<>();

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
