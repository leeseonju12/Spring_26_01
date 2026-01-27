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

	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "/usr/member/login";
	}

	// 로그인
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpSession session, String loginId, String loginPw) {

		boolean isLogined = false;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}

		if (isLogined) {
//			return ResultData.from("F-A", "이미 로그인되어 있습니다.");
			return Ut.jsHistoryBack("F-A", "이미 로그인되어 있습니다.");
		}

		if (Ut.isEmptyOrNull(loginId)) {
//			return ResultData.from("F-1", "loginId는 필수 입력 항목입니다.");
			return Ut.jsHistoryBack("F-1", "loginId는 필수 입력 항목입니다.");
		}
		if (Ut.isEmptyOrNull(loginPw)) {
//			return ResultData.from("F-2", "loginPw는 필수 입력 항목입니다.");
			return Ut.jsHistoryBack("F-2", "loginPw는 필수 입력 항목입니다.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
//			return ResultData.from("F-3", Ut.f("%s 는 잘못된 아이디입니다.", loginId));
			return Ut.jsHistoryBack("F-3", Ut.f("%s 는 존재하지 않는 아이디입니다.", loginId));
		}

		if (member.getLoginPw().equals(loginPw) == false) {
//			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
			return Ut.jsHistoryBack("F-4", "비밀번호가 일치하지 않습니다.");
		}

		session.setAttribute("loginedMemberId", member.getId());

//		return ResultData.from("S-1", Ut.f("%s님 환영합니다", member.getNickname()), "이번에 로그인 한 회원", member);
		return Ut.jsReplace("S-1", Ut.f("%s님 환영합니다", member.getNickname()), "/");
	}

	// 로그아웃
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {

		boolean isLogined = false;

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}
		
//		NeedLoginInterceptor에서 이미 처리됨
//		if (!isLogined) {
//			return ResultData.from("F-A", "이미 로그아웃 되어 있습니다.");
//		}

		session.removeAttribute("loginedMemberId");

//		return ResultData.from("S-1", "로그아웃 되었습니다.");
		return Ut.jsReplace("S-1", Ut.f("로그아웃 되었습니다."), "/");

	}

	// 회원 가입
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(HttpSession session, String loginId, String loginPw, String name, String nickname,
			String cellphoneNum, String email) {

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

		return ResultData.newData(doJoinRd, "이번에 가입한 회원 / 새로 INSERT 된 member", member);
	}
}