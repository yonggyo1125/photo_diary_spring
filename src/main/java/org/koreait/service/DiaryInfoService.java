package org.koreait.service;

import org.koreait.entity.Diary;
import org.koreait.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryInfoService {
	
	@Autowired
	private DiaryRepository repository;
	
	public Diary get(Long id) {
		
		Diary diary = repository.findById(id).orElse(null);
		
		return diary;
	}
}
