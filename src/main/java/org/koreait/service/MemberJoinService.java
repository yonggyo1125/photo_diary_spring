package org.koreait.service;

import org.koreait.controllers.members.MemberRequest;
import org.koreait.entity.Member;
import org.koreait.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.mindrot.bcrypt.BCrypt;

@Service
public class MemberJoinService {
		
		@Autowired
		private MemberRepository repository;
		
		public Member process(MemberRequest params) {
			
			String hash = BCrypt.hashpw(params.getMemPw(), BCrypt.gensalt(12));
			
			Member member = new Member();
			member.setMemId(params.getMemId());
			member.setMemNm(params.getMemNm());
			member.setMemPw(hash);
			member.setEmail(params.getEmail());
			member.setMobile(params.getMobile());
			
			Member newMember = repository.save(member);
			
			return newMember;
		}
}
