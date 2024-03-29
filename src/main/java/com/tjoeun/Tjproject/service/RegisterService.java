package com.tjoeun.Tjproject.service;

import org.springframework.ui.Model;

import com.tjoeun.Tjproject.dao.MainDAO;
import com.tjoeun.Tjproject.vo.MainList;
import com.tjoeun.Tjproject.vo.MemberVO;

public class RegisterService implements PrimitiveService {
	

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void execute(Model model, MainDAO mapper) {
		// TODO Auto-generated method stub
		
	}

	public int registerIdCheck(String id, MainDAO mapper) {
		int registerIdCheck = mapper.registerIdCheck(id);
		return registerIdCheck;
	}

	public int register(MemberVO memberVO, MainDAO mapper) {
		int registerSuccess = (int) mapper.register(memberVO);
		return registerSuccess;
	}




	



}
