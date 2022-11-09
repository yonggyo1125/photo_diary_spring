package org.koreait.service;

import java.time.LocalDateTime;
import java.util.List;

import org.koreait.entity.Diary;
import org.koreait.entity.QDiary;
import org.koreait.entity.QMember;
import org.koreait.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;

@Service
public class DiaryListService {
	
	@Autowired
	private DiaryRepository respository;
	
	public List<Diary> gets(String memId) {
		// BooleanBuilder -> and, or
		// 회원 ID, 7일 이내 일기 
		QMember member = QMember.member;
		QDiary diary = QDiary.diary;
		
		LocalDateTime date = LocalDateTime.now();
		date = date.minusWeeks(1);
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(diary.member.memId.eq(memId))
							.and(diary.regDt.goe(date));
		
		
		 List<Diary> diaries =  (List<Diary>)respository.findAll(booleanBuilder, Sort.by(Direction.DESC, "id"));
		 
		 return diaries;
	}
}
