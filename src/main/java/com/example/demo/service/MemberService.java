package com.example.demo.service;


import com.example.demo.domain.Role;
import com.example.demo.domain.entity.Member;
import com.example.demo.domain.repository.MemberRepository;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;


@RequiredArgsConstructor
@Service
@AllArgsConstructor
@Slf4j

@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private void makeLowerCaseEmail(MemberDto dto) {
		log.info("회원가입 시 email을 소문자로 변경");
		dto.setEmail(dto.getEmail().toLowerCase());
	}

	private boolean validateDuplicateMember(String email) {
		List<Member> findMembers = memberRepository.findAllByEmail(email);
		if (!findMembers.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}

	@Transactional
	public Long save(MemberDto memberDto){

		makeLowerCaseEmail(memberDto);

		Member member = memberDto.toEntity();

		// 중복된 회원 있으면 회원가입 실패
		if(!validateDuplicateMember(memberDto.getEmail())) {
			return (long)-1;
		}

		member.encodingPassword(passwordEncoder.encode(member.getPassword()));

		return memberRepository.save(member).getId();
	}

	@Transactional
	public boolean login(MemberDto memberDto) {
		Optional<Member> member = memberRepository.findByEmail(memberDto.getEmail());

		// 로그인 이메일과 일치하는 회원 없으면 로그인 실패
		if(member == null) {
			return false;
		}

		// 암호화된 비밀번호 비교해서 다르면 로그인 실패
		if(!passwordEncoder.matches(memberDto.getPassword(), member.get().getPassword())) {
			return false;
		}

		return true;
	}

	private Collection<? extends GrantedAuthority> authorities(Role role) {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.toString()));
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email));

		return new User(member.getEmail(), member.getPassword(), authorities(member.getRole()));
	}
}



