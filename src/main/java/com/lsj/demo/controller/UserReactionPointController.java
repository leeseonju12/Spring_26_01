package com.lsj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.demo.service.ReactionPointService;
import com.lsj.demo.util.Ut;
import com.lsj.demo.vo.ResultData;
import com.lsj.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserReactionPointController {

	private final UserArticleController userArticleController;

	@Autowired
	private Rq rq;

	@Autowired
	private ReactionPointService reactionPointService;

	UserReactionPointController(UserArticleController usrArticleController) {
		this.userArticleController = usrArticleController;
	}

	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	@ResponseBody
	public String doGoodReaction(HttpServletRequest req, String relTypeCode, int relId, String replaceUri) {

		int usersReaction = reactionPointService.usersReaction(rq.getLoginedMemberId(), relTypeCode, relId);
		
		if (usersReaction == 1) {
			return Ut.jsHistoryBack("F-1", "이미 함");
		}

		ResultData reactionRd = reactionPointService.increaseReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		return Ut.jsReplace(reactionRd.getResultCode(), reactionRd.getMsg(), replaceUri);
	}

}