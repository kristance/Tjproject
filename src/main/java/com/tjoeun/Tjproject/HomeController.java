package com.tjoeun.Tjproject;

import java.util.HashMap;

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
import com.tjoeun.Tjproject.service.CommentService;
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
		
		return "redirect:list";
	}
	@RequestMapping("/list")
	public String list (HttpServletRequest request, Model model) {
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (Exception e) {
			
		}
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainService service = ctx.getBean("main", MainService.class);
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		HttpSession session = request.getSession();
		MainList mainList = service.selectList(currentPage, mapper);
		logger.info("####list -> mainList -> {}", mainList);
		
		model.addAttribute("mainList",mainList);
		model.addAttribute("currentPage", currentPage);
		session.setAttribute("mainList", mainList);
		session.setAttribute("currentPage", currentPage);
		
		return "Main";
	}
	
	
	@RequestMapping ("/Main") 
	public String main (HttpServletRequest request, Model model) {
		logger.info("homecontroller -> main()");
		
		return "Main";
		
	}
	
	@RequestMapping("/search")
	public String search (HttpServletRequest request, Model model) {
		
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt( request.getParameter("currentPage") );
		} catch (Exception e) {
		}
		String searchVal = request.getParameter("searchVal");
		String searchTag = request.getParameter("searchTag");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainList mainList = ctx.getBean("mainList", MainList.class);
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		SearchService service  = ctx.getBean("search", SearchService.class);
		HttpSession session = request.getSession();
		
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("searchVal", searchVal);
		hmap.put("searchTag", searchTag);
		hmap.put("currentPage", currentPage);
		logger.info("1111searchVal -> {}, searchTag -> {}", searchVal, searchTag);
		if (searchVal != null || searchTag != null || searchTag.trim().length() != 0) {
			if (searchVal.trim().length() != 0) {
				mainList = service.search(hmap, mapper);
				System.out.println("### search () ### + " + mainList );
				model.addAttribute("mainList", mainList);
				model.addAttribute("currentPage", currentPage);
				session.setAttribute("searchVal", searchVal);
				session.setAttribute("searchTag", searchTag);
			} else {
				mainList = service.searchNone(currentPage, mapper);
				session.removeAttribute("searchVal");
				session.removeAttribute("searchTag");
				model.addAttribute("mainList", mainList);
				model.addAttribute("currentPage", currentPage);
			}
		}else {
//		none	
		}
		
					
		
		return "Main";
	}
	
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
		int backPage = 1;
		int idx = 1;
		int currentPage = 1;
		try {
			backPage = Integer.parseInt( request.getParameter("backPage") );
			idx = Integer.parseInt( request.getParameter("idx"));
			currentPage = Integer.parseInt( request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		
		model.addAttribute("backPage", backPage);
		model.addAttribute("idx",idx);
		model.addAttribute("currentPage", currentPage);
		return "login";
	}
	
	@RequestMapping("/loginOK")
	public String loginOK (HttpServletRequest request, Model model) {
		logger.info("homecontroller -> loginOK()");
		
		String id = request.getParameter("id");
		String password = request.getParameter("pw");
		
		int backPage = 1;
		int idx = 1;
		int currentPage = 1;
		try {
			backPage = Integer.parseInt( request.getParameter("backPage") );
			idx = Integer.parseInt( request.getParameter("idx"));
			currentPage = Integer.parseInt( request.getParameter("currentPage"));
		} catch (Exception e) {
		}
		
		
		
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
		model.addAttribute("idx",idx);
		model.addAttribute("currentPage", currentPage);
		
		
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
		
		return "redirect:selectByIdx";	
		
	}
	
	@RequestMapping("/selectByIdx")
	public String selectByIdx (HttpServletRequest request, Model model, MainVO mainVO, MainCommentVO co) {
		logger.info("homecontroller -> selectByIdx()");
		String job = "";
		job = request.getParameter("job");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		logger.info("selectByIdx -> idx -> {}", idx);
		
		try {
			idx = (int) request.getAttribute("idx");
			currentPage = (int) request.getAttribute("currentPage");
		} catch (Exception e) {
			
		}
		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("job", job);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		SelectByIdxService service = ctx.getBean("selectByidx", SelectByIdxService.class);
		MainDAO mapper = sqlSession.getMapper(MainDAO.class);
		MainCommentDAO mapperComment = sqlSession.getMapper(MainCommentDAO.class);
		MainVO vo = service.selectByIdx(idx, mapper);
		HttpSession session = request.getSession();
		logger.info("selectByidx -> mainVO {}",vo);
		logger.info("selectByidx -> job -> {}",job);
		MainCommentList commentList = service.selectList(idx, mapperComment);
		
		if (job != null && job.equals("delete")) {
//			pressed delete main button
				service.delete(idx, mapper);
				model.addAttribute("currentPage", currentPage);
				return "redirect:list";
			} else if(job != null && job.equals("update")) {
//				pressed update main button
				model.addAttribute("idx", idx);
				model.addAttribute("currentPage", currentPage);
				return "redirect:readUpdate";
			} else if(job != null && job.equals("good")) {
//				pressed recommend button
				service.good(idx, mapper);
				session.setAttribute("goodidxcheck" + idx, "checked");
				model.addAttribute("commentList", commentList);
				model.addAttribute("idx", idx);
				model.addAttribute("currentPage", currentPage);
				model.addAttribute("enter", "\r\n");
				model.addAttribute("Mainboard", vo);
				return "read";
				
			} else if(job != null && job.equals("updateOK")) {
				model.addAttribute("errorCheck", 1110);
//				pressed Update complete button
					if (mainVO.getCategory() != null || !mainVO.getCategory().equals("카테고리 입력")) {
						model.addAttribute("errorCheck", 1111);
						model.addAttribute("idx", idx);
						model.addAttribute("currentPage", currentPage);
						return "readUpdate";
					} else if (mainVO.getSubject() != null || !mainVO.getSubject().trim().equals("") ) {
						model.addAttribute("errorCheck", 1112);
						model.addAttribute("idx", idx);
						model.addAttribute("currentPage", currentPage);
						model.addAttribute("idx", idx);
						model.addAttribute("currentPage", currentPage);
						return "readUpdate";
					} else if (mainVO.getContent() != null || !mainVO.getContent().trim().equals("") ) {
						model.addAttribute("errorCheck", 1113);
						model.addAttribute("idx", idx);
						model.addAttribute("currentPage", currentPage);
						return "readUpdate";
					}else {
						model.addAttribute("errorCheck", 1114);
						model.addAttribute("idx", idx);
						model.addAttribute("currentPage", currentPage);
						service.update(mainVO, mapper);
						return "selectByIdx";
					}
			} else {
//				게시물 글 선택, 수정 취소 버튼 입력
				model.addAttribute("commentList", commentList);
				model.addAttribute("idx", idx);
				model.addAttribute("currentPage", currentPage);
				model.addAttribute("enter", "\r\n");
				model.addAttribute("Mainboard", vo);
				
				try {
					session.getAttribute("goodidxcheck" + idx);
				} catch (NullPointerException e) {
					session.setAttribute("goodidxcheck" + idx, "un");
				}
				
				return "read";
			}
	}
	
	@RequestMapping("/commentInsert")
	public String commentInsert(HttpServletRequest request, Model model, MainCommentVO mainCommentVO) {
		int currentPage = Integer.parseInt( request.getParameter("currentPage") );
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		logger.info("commentInsert -> mainCommentVO -> {}", mainCommentVO);
		MainCommentDAO mapperComment = sqlSession.getMapper(MainCommentDAO.class);
		mapperComment.insertComment(mainCommentVO);
		model.addAttribute("idx", mainCommentVO.getIdx());
		model.addAttribute("currentPage", currentPage);
		return "redirect:selectByIdx";
	}
	
	@RequestMapping("/commentDelete")
	public String commentDelete( HttpServletRequest request, Model model, MainCommentVO mainCommentVO) {
		int currentPage = Integer.parseInt( request.getParameter("currentPage") );
		int mainIdx = Integer.parseInt( request.getParameter("idx"));
//		idx = commentIdx
		int idx = Integer.parseInt( request.getParameter("commentidx"));
		mainCommentVO.setGup(mainIdx);
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainCommentDAO mapperComment = sqlSession.getMapper(MainCommentDAO.class);
		CommentService service = ctx.getBean("comment", CommentService.class);
		logger.info("maincommentVO -> {}", mainCommentVO);
		logger.info("commentIdx -> {}", idx);
		logger.info("mainCommentVO -> idx -> {},  main -> idx -> {}", mainCommentVO.getGup(), idx);
		
		mapperComment.deleteCheck(idx);
		
		model.addAttribute("idx", idx);
		model.addAttribute("currentPage", currentPage);
		
		return "redirect:selectByIdx";
	}
	
	@RequestMapping("/commentUpdate")
	public String commentUpdate(HttpServletRequest request, Model model) {
		String updatedCommentContent = request.getParameter("upcomment");
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:/appCTX.xml");
		MainCommentVO originComment = ctx.getBean("mainCommentVO", MainCommentVO.class);
		MainCommentVO afterUpdateComment = ctx.getBean("mainCommentVO", MainCommentVO.class);
		MainCommentDAO mapperComment = sqlSession.getMapper(MainCommentDAO.class);
//		comment - idx
		
		if (updatedCommentContent != null && !updatedCommentContent.equals("")) {
			int idx = Integer.parseInt( request.getParameter("comidx"));
			int currentPage = Integer.parseInt( request.getParameter("currentPage"));
			int mainIdx = Integer.parseInt( request.getParameter("voidx") );
			
			originComment = mapperComment.beforeUpdateContent(idx);
			updatedCommentContent.replace(">", "%gt;");
			updatedCommentContent.replace("<", "%lt;");
			updatedCommentContent.replace("<br/>", "\r\n");
			originComment.setContent(updatedCommentContent.trim());
			
			mapperComment.updateComment(originComment);
			
			model.addAttribute("idx", mainIdx);
			model.addAttribute("comidx", idx);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("updatedCommentContent", updatedCommentContent);
			return "commentUpdate";
			
		} else {
			int idx = Integer.parseInt( request.getParameter("comidx"));
			int currentPage = Integer.parseInt( request.getParameter("currentPage"));
			int mainIdx = Integer.parseInt( request.getParameter("voidx") );
			
			model.addAttribute("idx", mainIdx);
			model.addAttribute("comidx", idx);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("updatedCommentContent", updatedCommentContent);
			return "commentUpdate";
		}
		
	}
	
	
	
	@RequestMapping("/logout")
	public String logout (HttpServletRequest request, Model model) {
		logger.info("homecontroller -> logout()");
		int backPage = 1;
		int currentPage = 1;
		int idx = 1;
		try {
			backPage = Integer.parseInt( request.getParameter("backPage"));
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
			return "redirect:list";
		case 2 : // write.jsp
			return "write";
		case 3 : // read.jsp
			return "redirect:selectByIdx";
		default : // main.jsp
			return "redirect:list";
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
			return "redirect:list";
		case 2 : // write.jsp
			return "redirect:write";
		case 3 : // read.jsp
			return "redirect:selectByIdx";
		default : // main.jsp
			return "redirect:list";
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
