package com.lsj.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsj.demo.repository.ArticleRepository;
import com.lsj.demo.util.Ut;
import com.lsj.demo.vo.Article;
import com.lsj.demo.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public ResultData writeArticle(String boardId, int loginedMemberId, String title, String body) {

		articleRepository.writeArticle(boardId, loginedMemberId, title, body);

		int id = articleRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 게시글이 작성되었습니다.", id), "이번에 쓰여진 글의 id", id);
	}

	public ResultData userCanModify(int loginedMemberId, Article article) {

		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A2", Ut.f("%d번 게시글에 대한 수정 권한이 존재하지 않습니다.", article.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글을 수정 할 수 있습니다.", article.getId()));
	}

	public ResultData userCanDelete(int loginedMemberId, Article article) {
		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-A2", Ut.f("%d번 게시글에 대한 삭제 권한이 존재하지 않습니다.", article.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글을 삭제 할 수 있습니다.", article.getId()));
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public Article getForPrintArticle(int loginedMemberId, int id) {

		Article article = articleRepository.getForPrintArticle(id);

		controlForPrintData(loginedMemberId, article);

		return article;
	}

	private void controlForPrintData(int loginedMemberId, Article article) {
		if (article == null) {
			return;
		}

		ResultData userCanModifyRd = userCanModify(loginedMemberId, article);
		article.setUserCanModify(userCanModifyRd.isSuccess());

		ResultData userCanDeleteRd = userCanDelete(loginedMemberId, article);
		article.setUserCanDelete(userCanDeleteRd.isSuccess());
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);

	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public List<Article> getForPrintArticles(int boardId, int itemsInAPage, int page, String searchKeywordTypeCode,
			String searchKeyword) {
	// SELECT * FROM article WHERE boardId = 1 ORDER BY id DESC LIMIT 0, 10;
		// -> 1page
		// SELECT * FROM article WHERE boardId = 1 ORDER BY id DESC LIMIT 10, 10;
		// -> 2page

		int limitFrom = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;

		return articleRepository.getForPrintArticles(boardId, limitFrom, limitTake, searchKeywordTypeCode,
				searchKeyword);
	}

	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword) {
		return articleRepository.getArticlesCount(boardId, searchKeywordTypeCode, searchKeyword);
	}
	
	public void increaseHitCount(int id) {
	    articleRepository.increaseHitCount(id);
	}

}