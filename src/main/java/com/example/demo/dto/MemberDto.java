package com.example.demo.dto;


import com.example.demo.domain.Role;
import com.example.demo.domain.entity.Member;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {

	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String name;

	@NotNull
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
