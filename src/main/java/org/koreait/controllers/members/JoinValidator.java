package org.koreait.controllers.members;

import org.koreait.entity.Member;
import org.koreait.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class JoinValidator implements Validator {

	@Autowired
	private MemberRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if (errors.hasErrors()) {
			return;
		}
		
		MemberRequest req = (MemberRequest)target;
		
		/**
		 * 1. 아이디가 이미 가입되어 있는지?
		 * 2. 비밀번호, 비밀번호 확인 일치하는지?
		 * 3. 휴대전화번호 형식 체크
		 */
		
		String memId = req.getMemId();
		Member member = repository.findByMemId(memId);
		if (member != null) {
			errors.reject("MemberDuplidated", "이미 가입된 회원입니다.");
		}
		
		String memPw = req.getMemPw();
		String memPwRe = req.getMemPwRe();
		if (!memPw.equals(memPwRe)) {
			errors.reject("PasswordIncorrect", "비밀번호가 일치하지 않습니다.");
		}
		
		String mobile = req.getMobile();
		if (mobile != null && !mobile.isBlank()) {
			mobile = mobile.replaceAll("\\D", "");
			String pattern = "01[016]\\d{3,4}\\d{4}";
			if (!mobile.matches(pattern)) {
				errors.reject("WrongMobilePattern", "휴대전화 번호 형식이 아닙니다.");
			}
		}
	}
}
