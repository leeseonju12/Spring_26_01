package com.lsj.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Controller
public class UserHomeController {

	@RequestMapping("/user/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article article = new Article(1, "제목1", "내용1");
		return article;
	}


	
	@RequestMapping("/user/home/getList")
	@ResponseBody
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		list.add("철수 나이");
		list.add("영수 나이");

		return list;
	}

	@RequestMapping("/user/home/getMap")
	@ResponseBody
	public Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("철수 나이", 12);
		map.put("영수 나이", 13);

		return map;
	}

	@RequestMapping("/user/home/getDouble")
	@ResponseBody
	public double getDouble() {
		return 3.14;
	}

	@RequestMapping("/user/home/getBoolean")
	@ResponseBody
	public boolean getBoolean() {
		return true;
	}

	@RequestMapping("/user/home/getString")
	@ResponseBody
	public String getString() {
		return "abc";
	}

	@RequestMapping("/user/home/getInt")
	@ResponseBody
	public int getInt() {
		return 100;
	}
	
}
@AllArgsConstructor
@Getter
@Setter
class Article {
	int id;
	String title;
	String body;
}