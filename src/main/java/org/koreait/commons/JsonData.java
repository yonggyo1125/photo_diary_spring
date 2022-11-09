package org.koreait.commons;

import lombok.*;

@Getter @Setter @ToString
public class JsonData<T> {
	private boolean success;
	private String message;
	private T data;
}
