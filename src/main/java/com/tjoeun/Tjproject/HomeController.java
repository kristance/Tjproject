package com.tjoeun.Tjproject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.Tjproject.dao.MainCommentDAO;
import com.tjoeun.Tjproject.dao.MainDAO;
import com.tjoeun.Tjproject.service.LoginService;
import com.tjoeun.Tjproject.service.MainService;
import com.tjoeun.Tjproject.service.RegisterService;
import com.tjoeun.Tjproject.service.SearchService;
import com.tjoeun.Tjproject.service.SelectByIdxService;
import com.tjoeun.Tjproject.service.WriteService;
import com.tjoeun.Tjproject.vo.MainCommentList;
import com.tjoeun.Tjproject.vo.MainCommentVO;
import com.tjoeun.Tjproject.vo.MainList;
import com.tjoeun.Tjproject.vo.MainVO;
import com.tjoeun.Tjproject.vo.MemberVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	

	@RequestMapping("/")
	public String home(HttpServletRequest request , Model model) {
		logger.info("homecontroller -> root() ");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainList selectHit = ctx.getBean("mainList", MainList.class);
		MainList selectGood = ctx.getBean("mainList", MainList.class);
		MainList selectNew = ctx.getBean("mainList", MainList.class);
		MainService service = ctx.getBean("main", MainService.class);
		
		
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		selectHit = service.selectHit(mapper);
		selectGood = service.selectGood(mapper);
		selectNew = service.selectNew(mapper);
		
		HttpSession session = request.getSession();
		session.setAttribute("selectHit", selectHit);
		session.setAttribute("selectGood", selectGood);
		session.setAttribute("selectNew", selectNew);
		
		return "redirect:Main";
	}
	
	@RequestMapping ("/Main") 
	public String list (HttpServletRequest request, Model model) {
		logger.info("homecontroller -> main()");
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
			
		}
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainService service = ctx.getBean("main", MainService.class);
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		MainList mainList = service.selectList(currentPage, mapper);
		
		model.addAttribute("mainList",mainList);
		model.addAttribute("currentPage", currentPage);
		return "Main";
	}
	
//	@RequestMapping("/search")
//	public String search (HttpServletRequest request, Model model) {
//		int currentPage = 1;
//		try {
//			currentPage = Integer.parseInt(request.getParameter("currentPage"));
//		} catch (NumberFormatException e) {
//			
//		}
//		HttpSession session = request.getSession();
//		
//		String searchTag = ""; 
//		String searchVal = "";
//		try {
//			searchTag = request.getParameter("searchTag");
//			searchVal = request.getParameter("searchVal").trim();
//		} catch (Exception e) {
//		}
//		
//		
//		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
//		MainList mainList = ctx.getBean("mainList", MainList.class);
//		SearchService service = ctx.getBean("search", SearchService.class);
//		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
//		
//		if (searchVal != null || searchVal.length() != 0) {
//			session.setAttribute("searchTag", searchTag);
//			session.setAttribute("searchVal", searchVal);
//			
//			model.addAttribute("request", request);
//			mainList = service.search(model, mapper);
//			
//			session.setAttribute("mainList", mainList);
//			model.addAttribute("mainList", mainList);
//			model.addAttribute("currentPage", currentPage);
//			model.addAttribute("searchTag", searchTag);
//			model.addAttribute("searchVal", searchVal);
//			
//			return "Main";
//			
//		} else if (searchVal == null || searchVal.trim().length() == 0) { 
//			return "redirect:Main";
//		} else {
//			return "redirect:Main";
//		}
//	}
	
	@RequestMapping("/categorySort")
	public String categorySort (HttpServletRequest request, Model model) {
		String categoryOptionValue = request.getParameter("categoryOptionValue");
		HttpSession session = request.getSession();
		
		logger.info("categoryOptionValue -> {}", categoryOptionValue);
		
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		SearchService service = ctx.getBean("search", SearchService.class);
		MainList mainList = ctx.getBean("mainList", MainList.class);
		mainList = service.categorySort(categoryOptionValue, mapper);
		logger.info("homecontroller -> categorySort -> categorySortedList -> {}", mainList);
		session.setAttribute("mainList", mainList);
		
		return "Main";
	}
	
	@RequestMapping("/login")
	public String login (HttpServletRequest request, Model model) {
		logger.info("homecontroller -> login()");
		int backPage = Integer.parseInt( request.getParameter("backPage") );
		model.addAttribute("backPage", backPage);
		return "login";
	}
	
	@RequestMapping("/loginOK")
	public String loginOK (HttpServletRequest request, Model model) {
		logger.info("homecontroller -> loginOK()");
		int backPage = 1;
		try{
			backPage = Integer.parseInt(request.getParameter("backPage") );
		} catch (NumberFormatException e){
		}
		
		String id = request.getParameter("id");
		String password = request.getParameter("pw");
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MemberVO memberVO = ctx.getBean("memberVO", MemberVO.class);
		memberVO.setId(id);
		memberVO.setPw(password);
		LoginService service = ctx.getBean("login", LoginService.class);
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		
		int loginCheck = service.login(memberVO, mapper);
		
		model.addAttribute("loginCheck", loginCheck);
		model.addAttribute("memberVO", memberVO);
		model.addAttribute("backPage", backPage);
		
		
		return "loginOK";
	}
	
	@RequestMapping("/increment")
	public String increment (HttpServletRequest request, Model model) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
		}
		
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		mapper.increment(idx);
		
		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		
		return "selectByIdx";	
		
	}
	
	@RequestMapping("/selectByIdx")
	public String selectByIdx (HttpServletRequest request, Model model, MainVO mainVO, MainCommentVO co) {
		logger.info("homecontroller -> selectByIdx()");
		int idx = Integer.parseInt(request.getParameter("idx"));
		logger.info("selectByIdx -> idx -> {}", idx);
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		String job = "";
		try {
			job = request.getParameter("job");
		} catch (Exception e) {
			if (job == null) {
				job = "";
			}
		}
		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("job", job);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		SelectByIdxService service = ctx.getBean("selectByidx", SelectByIdxService.class);
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		MainCommentDAO mapperComment = sqlSession.getMapper(MainCommentDAO.class);
		MainVO vo = service.selectByIdx(idx, mapper);
		logger.info("selectByidx -> mainVO {}",vo);
		logger.info("selectByidx -> job -> {}",job);
		MainCommentList commentList = service.selectList(idx, mapperComment);
		model.addAttribute("vo", vo);
		model.addAttribute("commentList", commentList);
		
		if (job.equals("delete")) {
			service.delete(idx, mapper);
			model.addAttribute("selectByIdxButton", "delete");
		} else if(job.equals("update")) {
			model.addAttribute("selectByIdxButton", "update");
		} else if(job.equals("good")) {
			service.good(idx, mapper);
			model.addAttribute("selectByIdxButton", "good");
		}  else if(job.equals("updateOK")) {
			if (mainVO.getCategory() != null || !mainVO.getCategory().equals("카테고리 입력") ||
					mainVO.getSubject() != null || !mainVO.getSubject().trim().equals("") ||
							mainVO.getContent() != null || !mainVO.getContent().trim().equals("") ) {
				service.update(mainVO, mapper);
			}
		}
		
		
		return "selectByIdx";
	}
	
	@RequestMapping("/logout")
	public String logout (HttpServletRequest request, Model model) {
		logger.info("homecontroller -> logout()");
		int backPage = Integer.parseInt( request.getParameter("backPage"));
		int currentPage = 1;
		int idx = 1;
		try {
			currentPage = Integer.parseInt( request.getParameter("currentPage"));
			idx = Integer.parseInt(request.getParameter("idx"));
		} catch (Exception e) {

		}
		model.addAttribute("backPage", backPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("idx", idx);
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginCheck");
		session.removeAttribute("loginInfoID");
		
		switch (backPage) {
		case 1 : // main.jsp
			return "redirect:Main";
		case 2 : // write.jsp
			return "write";
		case 3 : // read.jsp
			return "selectByIdx?idx=" + idx + "&currentPage=" + currentPage + "'";
		default : // main.jsp
			return "redirect:Main";
		}
	}
	
	@RequestMapping("/goBack")
	public String goBack(HttpServletRequest request, Model model) {
		logger.info("homecontroller -> goback()");
		int backPage = Integer.parseInt( request.getParameter("backPage") );
		int idx = 1;
		int currentPage = 1;
		try {
			idx = Integer.parseInt( request.getParameter("idx") );
			currentPage = Integer.parseInt( request.getParameter("currentPage") );
		} catch (Exception e) {
		}
		
		model.addAttribute("backpage", backPage);
		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		
		switch (backPage) {
		case 1 : // main.jsp
			return "redirect:Main";
		case 2 : // write.jsp
			return "write";
		case 3 : // read.jsp
			return "selectByIdx";
		default : // main.jsp
			return "redirect:Main";
		}
	}
	
	@RequestMapping("/search_pw")
	public String search_pw (HttpServletRequest request, Model model) {
		return "search_pw";
	}
	
	@RequestMapping("/search_pw_OK")
	public String search_pw_OK (HttpServletRequest request, Model model) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		LoginService service = ctx.getBean("login", LoginService.class);
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email").trim();
		
		MemberVO vo = ctx.getBean("memberVO", MemberVO.class);
		vo.setId(id);
		vo.setName(name);
		vo.setEmail(email);
		
		int search_pw_check = service.search_pw_check(vo, mapper);
		MemberVO search_pw = service.search_pw(vo, mapper);
		
		model.addAttribute("search_pw_check", search_pw_check);
		model.addAttribute("search_pw", search_pw);
		
		return "search_pw_OK";
	}
	
	@RequestMapping("/register")
	public String register (HttpServletRequest request, Model model) {
		logger.info("homecontroller -> register()");
		return "register";
	}
	
	@RequestMapping("/registerProcess")
	public String registerProcess(HttpServletRequest request, Model model) {
		String id = request.getParameter("id").trim();
        String pw = request.getParameter("pw").trim();
        String pw2 = request.getParameter("pw2").trim();
        String name = request.getParameter("name").trim();
        String nick = request.getParameter("nick").trim();
        String email = request.getParameter("email").trim();
        
        AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
        MemberVO memberVO = ctx.getBean("memberVO", MemberVO.class);
        memberVO.setId(id);
        memberVO.setPw(pw);
        memberVO.setName(name);
        memberVO.setNick(nick);
        memberVO.setEmail(email);
        MainDAO mapper = sqlSession.getMapper(MainDAO.class);
        RegisterService service = ctx.getBean("register", RegisterService.class);
		model.addAttribute("pw2", pw2);
		model.addAttribute("memberVO", memberVO);
		
		HttpSession session = request.getSession();
		
		int registerIdCheck = service.registerIdCheck(id, mapper);
		
		model.addAttribute("registerIdCheck", registerIdCheck);
		logger.info("registerIdCheck ->  {}", registerIdCheck);
		
		if (registerIdCheck > 0) {
			session.setAttribute("messageContent", "이미 존재하는 아이디입니다.");
		}
		
		if (!memberVO.getPw().equals(pw2)) {
			session.setAttribute("messageContent", "비밀번호가 일치하지 않습니다.");
			
		}
		
		if (memberVO.getId() == null || memberVO.getId().equals("") || 
	            memberVO.getPw() == null || memberVO.getPw().equals("") ||
	            pw2 == null || pw2.equals("") ||
	            memberVO.getName() == null || memberVO.getName().equals("") ||
	            memberVO.getNick() == null || memberVO.getNick().equals("") ||
	            memberVO.getEmail() == null || memberVO.getEmail().equals("")) {
	        session.setAttribute("messageContent", "모든 내용을 입력하세요.");
	        
		} else if (memberVO.getId() != null && !memberVO.getId().equals("") && 
	            memberVO.getPw() != null && !memberVO.getPw().equals("") &&
	            pw2 != null && !pw2.equals("") && memberVO.getPw().equals(pw2) &&
	            memberVO.getName() != null && !memberVO.getName().equals("") &&
	            memberVO.getNick() != null && !memberVO.getNick().equals("") &&
	            memberVO.getEmail() != null && !memberVO.getEmail().equals("")) {
			  int registerSuccess =  service.register(memberVO, mapper);
			  
			  logger.info("registerSuccess -> {}", registerSuccess);
			  model.addAttribute("registerSuccess", registerSuccess);
		}
		
		
		return "registerProcess";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		return "write";
	}
	
	@RequestMapping("/writeOK")
	public String writeOK(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String category = request.getParameter("category");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String id = (String) session.getAttribute("loginInfoID");
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainVO mainVO = ctx.getBean("mainVO",MainVO.class);
		WriteService service = ctx.getBean("write", WriteService.class);
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		mainVO.setId(id);
		mainVO.setSubject(subject);
		mainVO.setCategory(category);
		mainVO.setContent(content);
		
		model.addAttribute("mainVO", mainVO);
		
		if (category != null || category.trim().length() != 0 ||
			subject != null || subject.trim().length() != 0 ||
			content != null || content.trim().length() != 0 ||
			!category.equals("카테고리 입력")) {
			service.execute(model, mapper);
		}
		
		return "writeOK";
	}
	
}