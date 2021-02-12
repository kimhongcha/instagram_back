package com.example.demo.controller;


import com.example.demo.domain.Role;
import com.example.demo.domain.TokenMemberEmail;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MemberResponseDto;
import com.example.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class MemberController{

	@Autowired
	private MemberService memberService;

	// 회원가입
	@PostMapping("/join")
	public ResponseEntity joinMember(@RequestBody @Valid MemberDto memberDto, Errors erros){

		if(erros.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if(memberService.save(memberDto) == -1) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// 로그인
	@PostMapping("login")
	public ResponseEntity login(@RequestBody MemberDto memberDto, Errors erros) {

		if(erros.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if(!memberService.login(memberDto)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
