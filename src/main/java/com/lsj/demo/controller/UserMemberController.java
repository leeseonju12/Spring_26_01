package com.lsj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.demo.service.MemberService;
import com.lsj.demo.util.Ut;
import com.lsj.demo.vo.Member;

@Controller
public class UserMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		
		if (Ut.isEmptyOrNull(loginId)) {
			return "loginId 입력해";
		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return "loginPw 입력해";
		}
		if (Ut.isEmptyOrNull(name)) {
			return "name 입력해";
		}
		if (Ut.isEmptyOrNull(nickname)) {
			return "nickname 입력해";
		}
		if (Ut.isEmptyOrNull(cellphoneNum)) {
			return "cellphoneNum 입력해";
		}
		if (Ut.isEmptyOrNull(email)) {
			return "email 입력해";
		}
		
		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		if (id == -1) {
			return "이미 사용중인 loginId입니다";
		}
		
		if (id == -2) {
			return "이미 사용중인 name과 email 입니다";
		}

		Member member = memberService.getMemberById(id);

		return member;
	}
}