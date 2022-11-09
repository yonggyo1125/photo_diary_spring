package org.koreait.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
public class Diary extends BaseEntity {
	@Id @GeneratedValue
	private Long id;
	
	@Column(length=100, nullable=false)
	private String title;
	
	@Lob
	@Column(nullable=false)
	private String content;
	
	@Column(length=100)
	private String photoUrl;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="memNo")
	private Member member;
}
