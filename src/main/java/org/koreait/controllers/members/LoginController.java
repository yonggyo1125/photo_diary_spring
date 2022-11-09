package org.koreait.controllers.members;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.koreait.commons.JsonData;
import org.koreait.entity.Member;
import org.koreait.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/login")
public class LoginController {
	
	@Autowired
	private LoginService service;
	
	@PostMapping
	public JsonData<Member> process(@Valid LoginRequest loginRequest, Errors errors) {
		
		if (errors.hasErrors()) {
			List<ObjectError> errs = errors.getAllErrors();
			String message = errs.stream().map(e -> e.getDefaultMessage())
											.collect(Collectors.joining("||"));
			
			throw new RuntimeException(message);
		}
		
		Member member = service.process(loginRequest);
		
		JsonData<Member> data = new JsonData<>();
		data.setSuccess(true);
		data.setData(member);
		return data;
	}
}
