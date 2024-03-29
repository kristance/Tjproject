package com.tjoeun.Tjproject.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tjoeun.Tjproject.vo.MainVO;
import com.tjoeun.Tjproject.vo.MemberVO;
import com.tjoeun.Tjproject.vo.Param;

public interface MainDAO {

//	login
	int login(MemberVO memberVO);

//	조회수 +1
	void increment(int idx);
	
//	우측바 글 5개
	ArrayList<MainVO> selectHit();
	ArrayList<MainVO> selectGood();
	ArrayList<MainVO> selectNew();

//	main -- delete
	void delete(int idx);

//	main - 글 1건 얻어옴
	MainVO selectByIdx(int idx);

//	main - 추천 수 +1
	void good(int idx);

//	main - 글 수정
	void update(MainVO vo);

//	password check
	int search_pw_check(MemberVO vo);

//	find password 
	MemberVO search_pw(MemberVO vo);

//	id 중복 체크
	int registerIdCheck(String id);

//	회원가입
	int register(MemberVO memberVO);

//	전체 글의 갯수
	int totalCount();

//	main -> 1 page 글
	ArrayList<MainVO> selectList(HashMap<String, Integer> hmap);



//	검색된 글의 갯수 - id
	int totalCountSearchedID(HashMap<String, String> hmap);

//	검색된 글의 갯수 - subject
	int totalCountSearchedSubject(HashMap<String, String> hmap);


//	검색된 글의 목록 - id
	ArrayList<MainVO> searchedListID(Param param);

//	검색된 글의 목록 - subject
	ArrayList<MainVO> searchedListSubject(Param param);

	
//	category Sorted List Count
	int categorySortedListCount(String categoryOptionValue);

//	category Sorted List
	ArrayList<MainVO> categorySortedList(Param param);



	
	
	
	

	
	
	
	

}
