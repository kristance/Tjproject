package com.tjoeun.Tjproject.service;

import org.springframework.ui.Model;

import com.tjoeun.Tjproject.dao.MainDAO;
import com.tjoeun.Tjproject.vo.MainList;

public interface PrimitiveService {

	
	public abstract void execute(Model model, MainDAO mapper);

	void execute(Model model);
	

}
