package com.lsj.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsj.demo.repository.BoardRepository;
import com.lsj.demo.vo.Article;
import com.lsj.demo.vo.Board;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}

	public Board getBoardById(int boardId) {
		return boardRepository.getBoardById(boardId);
	}

	public List<Article> getForListupBoard(int loginedMemberId, int boardId) {
		List<Article> articles = boardRepository.getForListupBoard(boardId);

		// 필요시 각 글에 대한 권한 체크
		for (Article article : articles) {
			controlForPrintData(loginedMemberId, article);
		}

		return articles;
	}

	private void controlForPrintData(int loginedMemberId, Article article) {
		// 권한 체크 로직 구현
	}

}