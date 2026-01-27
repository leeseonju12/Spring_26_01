package com.lsj.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.demo.DemoApplication;
import com.lsj.demo.service.ArticleService;
import com.lsj.demo.util.Ut;
import com.lsj.demo.vo.Article;
import com.lsj.demo.vo.ResultData;
import com.lsj.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserArticleController {

	private final DemoApplication demoApplication;

	@Autowired
	private ArticleService articleService;

	UserArticleController(DemoApplication demoApplication) {
		this.demoApplication = demoApplication;
	}

	// 액션메서드
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {

		Rq rq = new Rq(req);

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		model.addAttribute("article", article);
		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpServletRequest req, int id, String title, String body) {

		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {
			return ResultData.from("F-A", "게시글 수정은 로그인 후 가능합니다.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 글이 존재하지 않습니다.", id));
		}

		ResultData userCanModifyRd = articleService.userCanModify(rq.getLoginedMemberId(), article);

		if (userCanModifyRd.isFail()) {
			return userCanModifyRd;
		}

		if (userCanModifyRd.isSuccess()) {
			articleService.modifyArticle(id, title, body);
		}
		article = articleService.getArticleById(id);

		return ResultData.from(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg(), "이번에 수정된 글 ", article);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {

		Rq rq = new Rq(req);
		
		if (rq.isLogined() == false) {
			return Ut.jsReplace("F-A", "게시글 삭제는 로그인 후 가능합니다.", "../member/login");
		}
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 글이 존재하지 않습니다.", id));
		}

		ResultData userCanDeleteRd = articleService.userCanDelete(rq.getLoginedMemberId(), article);

		if (userCanDeleteRd.isFail()) {
			return Ut.jsHistoryBack(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg());
		}

		if (userCanDeleteRd.isSuccess()) {
			articleService.deleteArticle(id);
		}
		return Ut.jsReplace(userCanDeleteRd.getResultCode(), userCanDeleteRd.getMsg(), "../article/list");
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model)	 {
		List<Article> articles = articleService.getArticles();

		model.addAttribute("articles", articles);
		return "/usr/article/list";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpServletRequest req, String title, String body) {

		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {
			return ResultData.from("F-A", "게시글 작성은 로그인 후 가능합니다.");
		}

		if (Ut.isEmptyOrNull(title)) {
			return ResultData.from("F-1", "제목을 작성하세요.");
		}
		if (Ut.isEmptyOrNull(body)) {
			return ResultData.from("F-2", "내용을 작성하세요.");
		}

		ResultData doWriteRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body);

		int id = (int) doWriteRd.getData1();

		Article article = articleService.getArticleById(id);

		return ResultData.newData(doWriteRd, "이번에 쓰여진 글 / 새로 INSERT 된 article", article);
	}

}
