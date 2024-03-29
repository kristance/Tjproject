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
	
	public MainList search(Model model, MainDAO mapper) {
		System.out.println("SearchService -> search() -> main -> 검색 결과 글 ");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpSession session = request.getSession();
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt( request.getParameter("currentPage") );
		} catch (Exception e) {
		}
		
			String searchVal = "";
			String searchTag = "";
		try {
			searchVal = request.getParameter("searchVal");
			searchTag = request.getParameter("searchTag");
			model.addAttribute("searchVal", searchVal);
			model.addAttribute("searchTag", searchTag);
		} catch (Exception e) {
			searchVal = (String) session.getAttribute("searchVal");
			searchTag = (String) session.getAttribute("searchTag");
			model.addAttribute("searchVal", searchVal);
			model.addAttribute("searchTag", searchTag);

		}
		logger.info("searchVal -> {}, searchTag -> {}", searchVal, searchTag);
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("searchVal", searchVal);
		hmap.put("searchTag", searchTag);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainList mainList = ctx.getBean("mainList", MainList.class);
		Param param = ctx.getBean("paramC", Param.class);

		if (hmap.get("searchTag").equals("id")) {
			int pageSize = 10;
			int totalCount = mapper.totalCountSearchedID(hmap);
			mainList.setMainList(pageSize, totalCount, currentPage);
			param.setSearchTag(searchTag);
			param.setSearchVal(searchVal);
			param.setStartNo(mainList.getStartNo());
			param.setEndNo(mainList.getEndNo());
			mainList.setList(mapper.searchedListID(param));
			model.addAttribute("searchTag",searchTag);
			model.addAttribute("searchVal",searchVal);
			
			
		} else if (hmap.get("searchTag").equals("subject")) {
			int pageSize = 10;
			int totalCount = mapper.totalCountSearchedSubject(hmap);
			mainList.setMainList(pageSize, totalCount, currentPage);
			param.setSearchTag(searchTag);
			param.setSearchVal(searchVal);
			param.setStartNo(mainList.getStartNo());
			param.setEndNo(mainList.getEndNo());
			mainList.setList(mapper.searchedListSubject(param));
			model.addAttribute("searchTag",searchTag);
			model.addAttribute("searchVal",searchVal);
		}
		
		
		return mainList;
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



	

}
