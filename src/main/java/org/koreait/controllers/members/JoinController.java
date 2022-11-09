package org.koreait.controllers.members;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.koreait.commons.JsonData;
import org.koreait.entity.Member;
import org.koreait.service.MemberJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/join")
public class JoinController {
	
	@Autowired
	private JoinValidator validator;
	
	@Autowired
	private MemberJoinService memberJoinService;
	
	@PostMapping
	public JsonData<Member> process(@Valid MemberRequest memberRequest, Errors errors) {
		
		validator.validate(memberRequest, errors);
	
		if (errors.hasErrors()) {
			List<ObjectError> errs = errors.getAllErrors();
			String message = errs.stream().map(e -> e.getDefaultMessage())
											.collect(Collectors.joining("||"));
			
			throw new RuntimeException(message);
		}
		
		Member member = memberJoinService.process(memberRequest);
		
		JsonData<Member> jsonData = new JsonData<>();
		jsonData.setSuccess(true);
		jsonData.setData(member);
		
		return jsonData;
	}
}
