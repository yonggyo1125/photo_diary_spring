package org.koreait.controllers.diary;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter @Setter @ToString
public class DiaryRequest {
	
	@NotBlank(message="제목을 입력하세요.")
	private String title;
	@NotBlank(message="내용을 입력하세요.")
	private String content;
	@NotBlank(message="잘못된 접근입니다.")
	private String memId;
	
	private String imageData;
	
}
