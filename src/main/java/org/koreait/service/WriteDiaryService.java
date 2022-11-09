package org.koreait.service;

import java.util.Base64;
import java.io.*;
import org.koreait.controllers.diary.DiaryRequest;
import org.koreait.entity.Diary;
import org.koreait.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.koreait.repository.*;

@Service
public class WriteDiaryService {
	
	@Value("${uploadPath}")
	private String uploadPath;
	
	@Autowired
	private DiaryRepository diaryRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public Diary process(DiaryRequest request) {
		
		boolean isSuccess = false;
		String fileName =  null;
		String imageData = request.getImageData();
		if (imageData != null && uploadPath != null) {
			byte[] bytes = Base64.getDecoder().decode(imageData);
			
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdir();
			}
			fileName = System.currentTimeMillis() + ".png";
			String filePath = uploadPath + "/" + fileName;
			
			try(FileOutputStream fos = new FileOutputStream(filePath)) {
				fos.write(bytes);
				isSuccess = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Member member = memberRepository.findByMemId(request.getMemId());
		Diary diary = new Diary();
		diary.setTitle(request.getTitle());
		diary.setContent(request.getContent());
		diary.setMember(member);
		if (isSuccess) {
			diary.setPhotoUrl("/images/" + fileName);
		}
		
		Diary newDiary = diaryRepository.save(diary);
		
		return newDiary;
	}
}
