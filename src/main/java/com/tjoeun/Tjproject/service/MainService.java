package com.tjoeun.Tjproject.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.Tjproject.HomeController;
import com.tjoeun.Tjproject.dao.MainDAO;
import com.tjoeun.Tjproject.vo.MainList;
import com.tjoeun.Tjproject.vo.MainVO;
import com.tjoeun.Tjproject.vo.MemberVO;
import com.tjoeun.Tjproject.vo.Param;

public class MainService  implements PrimitiveService{

	private static final Logger logger = LoggerFactory.getLogger(MainService.class);
	
	
	@Override
	public void execute(Model model, MainDAO mapper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public MainList selectList(int currentPage, MainDAO mapper) {
		System.out.println("MainService -> selectList() -> main -> 1 page 글 ");
		int pageSize = 10;
		int totalCount = mapper.totalCount();
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainList mainList = ctx.getBean("mainList", MainList.class);
		mainList.setMainList(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", mainList.getStartNo());
		hmap.put("endNo", mainList.getEndNo());
		mainList.setList(mapper.selectList(hmap));
		logger.info("mainService -> {}", mainList);
		return mainList;
	}

	public MainList selectHit(MainDAO mapper) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath://appCTX.xml");
		MainList selectHit = ctx.getBean("mainList", MainList.class);
		ArrayList<MainVO> list = mapper.selectHit();
		selectHit.setList(list);
		return selectHit;
	}

	public MainList selectGood(MainDAO mapper) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath://appCTX.xml");
		MainList selectGood = ctx.getBean("mainList", MainList.class);
		ArrayList<MainVO> list = mapper.selectGood();
		selectGood.setList(list);
		return selectGood;
	}

	public MainList selectNew(MainDAO mapper) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath://appCTX.xml");
		MainList selectNew = ctx.getBean("mainList", MainList.class);
		ArrayList<MainVO> list = mapper.selectNew();
		selectNew.setList(list);
		return selectNew;
	}


	








}
