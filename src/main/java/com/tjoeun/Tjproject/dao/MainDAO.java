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

//	category Sorted List Count
	int categorySortedListCount(String categoryOptionValue);

//	category Sorted List
	ArrayList<MainVO> categorySortedList(Param param);


// id - count
	int searchIDCount(String searchVal);

//	id - list
	ArrayList<MainVO> searchIDList(Param param);

//	subject - count
	int searchSubjectCount(String searchVal);

//	subject - list
	ArrayList<MainVO> searchSubjectList(Param param);

//	save -> article
	void insert(MainVO mainVO);

//	memberInfo
	MemberVO memberInfo(String id);

//	삭제할 대상 확인
	int coincideCheck(String id);

//	delete Account
	void deleteAccount(String id);

//	memberInfo update
	int updateInfo(MemberVO memberVO);

	
//	save name of profile images 
	void saveMemberProfileImageName(HashMap<String, String> hmap);
	
//	get profile images file name
	String getProfileImageName(String id);



	
	
	
	

	
	
	
	

}
