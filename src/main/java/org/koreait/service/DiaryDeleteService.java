package org.koreait.service;

import org.koreait.entity.Diary;
import org.koreait.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryDeleteService {
	
	@Autowired
	private DiaryRepository repository;
	
	public boolean process(Long id) {
		Diary diary = repository.findById(id).orElse(null);
		
		if (diary == null) {
			return false;
		}
		
		repository.delete(diary);
		
		return true;
	}
}
