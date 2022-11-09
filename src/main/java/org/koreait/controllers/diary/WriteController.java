package org.koreait.controllers.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.koreait.commons.JsonData;
import org.koreait.entity.*;
import org.koreait.service.WriteDiaryService;

@RestController
@RequestMapping("/diary/write")
public class WriteController {
	
	@Autowired
	private WriteDiaryService service;
	
	@PostMapping
	public JsonData<Diary> process(@Valid DiaryRequest request, Errors errors) {
		if (errors.hasErrors()) {
			List<ObjectError> errs = errors.getAllErrors();
			String message = errs.stream().map(e -> e.getDefaultMessage())
											.collect(Collectors.joining("||"));
			
			throw new RuntimeException(message);
		}
 		
		Diary diary = service.process(request);
		
		JsonData<Diary> data = new JsonData<>();
		data.setSuccess(true);
		data.setData(diary);
		
		return data;
	}
}
