package com.lsj.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.lsj.demo.vo.Board;

@Mapper
public interface BoardRepository {

	public Board getBoardById(int id);

}