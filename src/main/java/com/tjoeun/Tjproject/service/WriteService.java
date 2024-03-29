package com.tjoeun.Tjproject.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.tjoeun.Tjproject.dao.MainDAO;
import com.tjoeun.Tjproject.vo.MainVO;

public class WriteService implements PrimitiveService {

	private static final Logger logger = LoggerFactory.getLogger(WriteService.class);
	
	@Override
	public void execute(Model model, MainDAO mapper) {
		Map<String, Object> map = model.asMap();
		MainVO mainVO = (MainVO) map.get("mainVO");
		logger.info("writeService -> execute() -> mainVO {}", mainVO);
	}

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
	}

}


