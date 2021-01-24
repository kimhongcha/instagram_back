package com.example.demo.controller;


import com.example.demo.domain.TokenMemberEmail;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class MemberController {

	private MemberService memberService;

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity errorHandler(IllegalArgumentException e){
		return ResponseEntity.badRequest().body(e);
	}
	// 회원가입
	@PostMapping("/join")
	public ResponseEntity joinMember(@RequestBody @Valid MemberDto dto, Errors erros){

		if(erros.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}

		memberService.save(dto);

		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity queryMemberRole(@TokenMemberEmail String email){
		MemberResponseDto dto = memberService.findByEmail(email);
		return ResponseEntity.ok(dto);
	}




}
