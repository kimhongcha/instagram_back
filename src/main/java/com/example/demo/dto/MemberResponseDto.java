package com.example.demo.dto;


import com.example.demo.domain.Role;
import com.example.demo.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {

	private  Long id;
	private String email;
	private  String name;
	private Role role;

	public MemberResponseDto(Member member){

		this.id = member.getId();
		this.email = member.getEmail();
		this.name = member.getName();
		this.role = member.getRole();

	}
}
