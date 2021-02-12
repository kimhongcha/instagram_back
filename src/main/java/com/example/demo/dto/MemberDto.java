package com.example.demo.dto;

import com.example.demo.domain.Role;
import com.example.demo.domain.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

	@Email
	private String email;

	@NotNull
	private String password;

	@NotEmpty
	private String name;

	private Role role;

	@Builder
	public MemberDto(String email, String password, String name, Role role){

		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	public Member toEntity(){
		return Member.builder()
				.email(email)
				.password(password)
				.name(name)
				.role(role)
				.build();
	}

}


