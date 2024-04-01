package com.tjoeun.Tjproject.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.tjoeun.Tjproject.dao.MainDAO;
import com.tjoeun.Tjproject.vo.MainList;
import com.tjoeun.Tjproject.vo.Param;

public class SearchService implements PrimitiveService {

	private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

	@Override
	public void execute(Model model, MainDAO mapper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
	}
	
	
// Category Sorted List
	public MainList categorySort(String categoryOptionValue, MainDAO mapper) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainList mainList = ctx.getBean("mainList", MainList.class);
		Param param = ctx.getBean("paramC", Param.class);
		int pageSize = 10;
		int currentPage = 1;
		int totalCount = mapper.categorySortedListCount(categoryOptionValue);
		logger.info("searchService -> {}", totalCount);
		mainList.setMainList(pageSize, totalCount, currentPage);
		param.setCategory(categoryOptionValue);
		param.setStartNo(mainList.getStartNo());
		param.setEndNo(mainList.getEndNo());
		mainList.setList(mapper.categorySortedList(param));
		logger.info("searchService -> {}", mainList.toString());
		return mainList;
	}

	public MainList selectSearchList(int currentPage, MainDAO mapper) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainList mainList = ctx.getBean("mainList", MainList.class);
		int pageSize = 10;
		int totalCount = mapper.totalCount();		
		mainList.setMainList(currentPage, currentPage, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", mainList.getStartNo());
		hmap.put("endNo", mainList.getEndNo());
		mainList.setList(MainDAO.getInstance().selectList(mapper, hmap));
		return null;
	}



	

}
