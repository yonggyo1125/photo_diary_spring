package org.koreait.controllers.members;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @ToString
public class MemberRequest {
	@NotBlank(message="아이디를 입력하세요.")
	private String memId;
	
	@NotBlank(message="회원명을 입력하세요.")
	private String memNm;
	
	@NotBlank(message="비밀번호를 입력하세요.")
	private String memPw;
	
	@NotBlank(message="비밀번호확인을 해주세요.")
	private String memPwRe;
	
	@Email(message="이메일 형식이 아닙니다.")
	private String email;
	
	private String mobile;
}




