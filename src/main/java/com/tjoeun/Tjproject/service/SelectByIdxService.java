package com.tjoeun.Tjproject.service;

import java.util.ArrayList;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.Tjproject.dao.MainCommentDAO;
import com.tjoeun.Tjproject.dao.MainDAO;
import com.tjoeun.Tjproject.vo.MainCommentList;
import com.tjoeun.Tjproject.vo.MainCommentVO;
import com.tjoeun.Tjproject.vo.MainList;
import com.tjoeun.Tjproject.vo.MainVO;

public class SelectByIdxService implements PrimitiveService {
	

	@Override
	public void execute(Model model, MainDAO mapper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
	}


	

	public void delete(int idx, MainDAO mapper) {
		mapper.delete(idx);
	}

	public MainVO selectByIdx(int idx, MainDAO mapper) {
		MainVO mainVO = mapper.selectByIdx(idx); 
		return mainVO;
	}

	public void good(int idx, MainDAO mapper) {
		mapper.good(idx);
	}

	public void update(MainVO vo, MainDAO mapper) {
		mapper.update(vo);
	}

	public MainCommentList selectList(int idx, MainCommentDAO mapperComment) {
		ArrayList<MainCommentVO> list = new ArrayList<MainCommentVO>();
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainCommentList commentList = ctx.getBean("mainCommentList", MainCommentList.class);
		list = mapperComment.selectCommentList(idx);
		commentList.setList(list);
		return commentList;
	}





}
