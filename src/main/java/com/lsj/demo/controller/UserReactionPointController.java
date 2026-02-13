package com.lsj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsj.demo.service.ReactionPointService;
import com.lsj.demo.util.Ut;
import com.lsj.demo.vo.ResultData;
import com.lsj.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserReactionPointController {

	private final UserArticleController usrArticleController;

	@Autowired
	private Rq rq;

	@Autowired
	private ReactionPointService reactionPointService;

	UserReactionPointController(UserArticleController usrArticleController) {
		this.usrArticleController = usrArticleController;
	}

	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	public String doGoodReaction(HttpServletRequest req, String relTypeCode, int relId, String replaceUri) {

		int usersReaction = reactionPointService.userCanReaction(rq.getLoginedMemberId(), relTypeCode, relId);

		if (usersReaction == 1) {
			return Ut.jsHistoryBack("F-1", "이미 함");
		}

		ResultData reactionRd = reactionPointService.increaseReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		return Ut.jsReplace(reactionRd.getResultCode(), reactionRd.getMsg(), replaceUri);
	}

}