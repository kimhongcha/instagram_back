package com.example.demo.service;


import com.example.demo.domain.Role;
import com.example.demo.domain.entity.Member;
import com.example.demo.domain.repository.MemberRepository;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@Service
@AllArgsConstructor
@Slf4j

@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

	private MemberRepository memberRepository;
	private PasswordEncoder passwordEncoder;

	@Transactional
	public Long save(MemberDto dto){
		makeLowerCaseEmail(dto);
		validateDuplicateMember(dto.getEmail());
		Member member = dto.toEntity();
		member.encodingPassword(passwordEncoder.encode(member.getPassword()));
		return memberRepository.save(member).getId();
	}

	private void makeLowerCaseEmail(MemberDto dto) {
		log.info("회원가입 시 email을 소문자로 변경");
		dto.setEmail(dto.getEmail().toLowerCase());
	}

	private void validateDuplicateMember(String email) {
		List<Member> findMembers = memberRepository.findAllByEmail(email);
		if (!findMembers.isEmpty()) {
			throw new IllegalArgumentException("이미 존재하는 회원입니다.");
		}
	}

	public MemberResponseDto findOne(Long id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

		return new MemberResponseDto(member);
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email));

		return new User(member.getEmail(), member.getPassword(), authorities(member.getRole()));
	}

	private Collection<? extends GrantedAuthority> authorities(Role role) {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.toString()));
	}


	public MemberResponseDto findByEmail(String email) {
		Member findMember = memberRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

		return new MemberResponseDto(findMember);
	}

}

