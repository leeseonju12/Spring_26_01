package com.lsj.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.demo.service.ArticleService;
import com.lsj.demo.vo.Article;

@Controller
public class UserArticleController {

	@Autowired
	private ArticleService articleService;

	public UserArticleController() {
//		articleService = new ArticleService(); --> @Autowired로 대체 됨
	}
	
	// 액션 메서드
	// 상세보기 - 특정 게시글 하나만 보기 
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id) {
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		
		return article;
	}
	
	// 액션메서드
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}

		articleService.deleteArticle(id);
		
		return id + "번 글이 삭제되었습니다";
	}
	
//쿼리 파라미터 사용 - http://localhost:8081/usr/article/doModify?id=10&title=제목10수정&body=내용10수정
// 액션메서드
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		
		article.setTitle(title);
		article.setBody(body);

//		return id + "번 글이 수정되었습니다" + article;
		return article;
	}
	
//쿼리 파라미터 사용 - http://localhost:8081/usr/article/doAdd?title=제목11&body=내용11
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = articleService.writeArticle(title, body);
		return article;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articleService.getArticles();
	}

}
