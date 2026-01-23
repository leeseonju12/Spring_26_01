package com.lsj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.demo.service.MemberService;
import com.lsj.demo.util.Ut;
import com.lsj.demo.vo.Member;
import com.lsj.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserMemberController {

	@Autowired
	private MemberService memberService;

	// 로그인
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData<Member> doLogin(HttpSession session, String loginId, String loginPw) {

		boolean isLogined = false;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}

		if (isLogined) {
			return ResultData.from("F-A", "이미 로그인되어 있습니다.");
		}

		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", "loginId는 필수 입력 항목입니다.");
		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", "loginPw는 필수 입력 항목입니다.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return ResultData.from("F-3", Ut.f("%s는 잘못된 아이디입니다.", loginId));
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}

		session.setAttribute("loginedMemberId", member.getId());

		return ResultData.from("S-1", Ut.f("%s님 환영합니다", member.getNickname()), member);
	}

	// 회원 가입
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", "loginId는 필수 입력 항목입니다.");
		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", "loginPw는 필수 입력 항목입니다.");
		}
		if (Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-3", "이름을 입력해 주세요.");
		}
		if (Ut.isEmptyOrNull(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해 주세요.");
		}
		if (Ut.isEmptyOrNull(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해 주세요.");
		}
		if (Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-6", "email을 입력해 주세요.");
		}

		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (doJoinRd.isFail()) {
			return doJoinRd;
		}

		Member member = memberService.getMemberById((int) doJoinRd.getData1());

		return ResultData.newData(doJoinRd, member);
	}
}