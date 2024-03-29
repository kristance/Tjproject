package com.tjoeun.Tjproject.dao;

import java.util.ArrayList;

import com.tjoeun.Tjproject.vo.MainCommentVO;

public interface MainCommentDAO {

//	comment - reply 목록
	ArrayList<MainCommentVO> selectCommentList(int idx);

}
