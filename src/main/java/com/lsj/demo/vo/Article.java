package com.lsj.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
	public Article(String title, String body) {
		this.title = title;
		this.body = body;
	}

	int id;
	LocalDateTime regDate;
	LocalDateTime updateDate;
	String title;
	String body;
}