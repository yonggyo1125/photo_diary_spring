package org.koreait.controllers.diary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.koreait.commons.JsonData;
import org.koreait.entity.Diary;
import org.koreait.service.DiaryListService;

@RestController
public class DiaryListController {
	
	@Autowired
	private DiaryListService service;
	
	@GetMapping("/diaries/{memId}")
	public JsonData<List<Diary>> gets(@PathVariable String memId) {
		
		List<Diary> diaries = service.gets(memId);
		
		JsonData<List<Diary>> data = new JsonData<>();
		data.setSuccess(true);
		data.setData(diaries);
		
		return data;
	}
}
