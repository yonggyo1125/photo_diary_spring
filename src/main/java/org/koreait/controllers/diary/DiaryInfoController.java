package org.koreait.controllers.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.koreait.commons.JsonData;
import org.koreait.entity.Diary;
import org.koreait.service.DiaryInfoService;

@RestController
public class DiaryInfoController {
	
	@Autowired
	private DiaryInfoService service;
	
	@GetMapping("/diary/{id}")
	public JsonData<Diary> get(@PathVariable Long id) {
		
		Diary diary = service.get(id);
		if (diary == null) {
			throw new RuntimeException("등록된 일기가 없습니다.");
		}
		
		JsonData<Diary> data = new JsonData<>();
		data.setSuccess(true);
		data.setData(diary);
		
		return data;
	}
}
