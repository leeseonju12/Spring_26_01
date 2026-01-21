package com.lsj.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class UserHomeController {

//	@RequestMapping("/user/home/main")
//	@ResponseBody
//	public String showMain() {
//		return "안녕하세요";
//	}
//
//	@RequestMapping("/user/home/main2")
//	@ResponseBody
//	public String bye() {
//		return "잘가";
//	}
//
//	@RequestMapping("/user/home/main3")
//	@ResponseBody
//	public int math() {
//		int x = 1;
//		int y = 2;
//		return x + y;
//	}

	int cnt;
	
	public UserHomeController() {
		cnt = 0;
	}

	@RequestMapping("/user/home/getCount")
	@ResponseBody
	public int getCount() {
		return cnt++;
	}

	@RequestMapping("/user/home/setCount")
	@ResponseBody
	public String setCount() {
		cnt = 0;
		return "count 값: 0 으로 초기화";
	}
	
//	쿼리 파라미터 사용 - http://localhost:8081/user/home/setCountValue?value=230
	@RequestMapping("/user/home/setCountValue")
	@ResponseBody
	public String setCountValue(int value) {
		this.cnt = value;
		return "count 값: " + value + " 으로 초기화";
	}
}