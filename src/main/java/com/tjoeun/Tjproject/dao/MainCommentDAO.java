package com.tjoeun.Tjproject.dao;

import java.util.ArrayList;

import com.tjoeun.Tjproject.vo.MainCommentVO;

public interface MainCommentDAO {

//	comment - reply 목록
	ArrayList<MainCommentVO> selectCommentList(int idx);

//	comment -> insert
	void insertComment(MainCommentVO mainCommentVO);

//	comment -> delete 'no' -> 'Yes'
	void deleteCheck(int idx);

//	comment -> 수정 전 글
	MainCommentVO beforeUpdateContent(int idx);

//	comment -> update 
	void updateComment(MainCommentVO originComment);


}
