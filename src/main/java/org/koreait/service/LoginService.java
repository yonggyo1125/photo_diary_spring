package org.koreait.service;

import org.koreait.controllers.members.LoginRequest;
import org.koreait.entity.Member;
import org.koreait.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.mindrot.bcrypt.BCrypt;

@Service
public class LoginService {
	
	@Autowired
	private MemberRepository repository;
	
	public Member process(LoginRequest req) {
		String memId = req.getMemId();
		String memPw = req.getMemPw();
		
		Member member = repository.findByMemId(memId);
		if (member == null) {
			throw new RuntimeException("가입하지 않은 회원입니다.");
		}
		
		boolean matched = BCrypt.checkpw(memPw, member.getMemPw());
		if (!matched) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		
		return member;
	}
}



