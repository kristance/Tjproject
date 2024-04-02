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

	
//	id
	public MainList search(HashMap<String, Object> hmap, MainDAO mapper) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainList mainList = ctx.getBean("mainList", MainList.class);
		int currentPage = (int) hmap.get("currentPage");
		String searchVal = (String) hmap.get("searchVal");
		String searchTag = (String) hmap.get("searchTag");
		logger.info("searchService -> searchVal -> {}, searchTag -> {}", searchVal, searchTag);
		Param param = ctx.getBean("paramC", Param.class);
		int pageSize = 10;
		
		if(searchTag != null && searchTag.trim().equalsIgnoreCase("id")) {
			int totalCount = mapper.searchIDCount(searchVal);
			mainList.setMainList(pageSize, totalCount, currentPage);
			param.setStartNo(mainList.getStartNo());
			param.setEndNo(mainList.getEndNo());
			param.setSearchVal(searchVal);
			mainList.setList(mapper.searchIDList(param));
			logger.info("searchService1 -> searchVal -> {}, searchTag -> {}", searchVal, searchTag);
		} else if (searchTag != null && searchTag.trim().equalsIgnoreCase("subject")) {
			int totalCount = mapper.searchSubjectCount(searchVal);
			mainList.setMainList(pageSize, totalCount, currentPage);
			param.setStartNo(mainList.getStartNo());
			param.setEndNo(mainList.getEndNo());
			param.setSearchVal(searchVal);
			mainList.setList(mapper.searchSubjectList(param));
			logger.info("searchService2 -> searchVal -> {}, searchTag -> {}", searchVal, searchTag);
			
		}
		
		logger.info("@@@@@ searchService -> {}", mainList);
		return mainList;
	}

	
//	keyword - no
	public MainList searchNone(int currentPage, MainDAO mapper) {
		int pageSize = 10;
		int totalCount = mapper.totalCount();
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainList mainList = ctx.getBean("mainList", MainList.class);
		mainList.setMainList(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", mainList.getStartNo());
		hmap.put("endNo", mainList.getEndNo());
		mainList.setList(mapper.selectList(hmap));
		
		return mainList;
	}







	

}
