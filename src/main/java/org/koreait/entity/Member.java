package org.koreait.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Getter @Setter
public class Member extends BaseEntity {
	@Id @GeneratedValue
	private Long memNo;
	
	@Column(length=40, nullable=false, unique=true)
	private String memId;
	
	@Column(length=65, nullable=false)
	@JsonIgnore
	private String memPw;
	
	@Column(length=40, nullable=false)
	private String memNm;
	
	@Column(length=100)
	private String email;
	
	@Column(length=11)
	private String mobile;
}
