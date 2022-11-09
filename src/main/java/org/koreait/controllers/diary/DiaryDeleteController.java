package org.koreait.controllers.diary;

import org.koreait.commons.JsonData;
import org.koreait.service.DiaryDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryDeleteController {
	
	@Autowired
	private DiaryDeleteService service;
	
	@GetMapping("/diary/delete/{id}")
	public JsonData<Object> process(@PathVariable Long id) {
		boolean result = service.process(id);
		
		JsonData<Object> data = new JsonData<>();
		data.setSuccess(result);
		
		return data;
	}
}
