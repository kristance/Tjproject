package com.tjoeun.Tjproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.tjoeun.Tjproject.dao.MainDAO;
import com.tjoeun.Tjproject.vo.MainList;
import com.tjoeun.Tjproject.vo.MemberVO;

public class LoginService implements PrimitiveService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Override
	public void execute(Model model, MainDAO mapper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
	}
	
	
// login check
	public int login(MemberVO memberVO, MainDAO mapper) {
		logger.info("LoginService -> login()");
		logger.info("{}", mapper);
		int loginCheck = mapper.login(memberVO);
		
		return loginCheck;
	}

//	password check
	public int search_pw_check(MemberVO vo, MainDAO mapper) {
		logger.info("LoginService -> search_pw_check()");
		int search_pw_check = mapper.search_pw_check(vo);
		return search_pw_check;
	}

//	find password 
	public MemberVO search_pw(MemberVO vo, MainDAO mapper) {
		logger.info("LoginService -> search_pw()");
		MemberVO memberVO = mapper.search_pw(vo);
		return memberVO;
	}

	





}
