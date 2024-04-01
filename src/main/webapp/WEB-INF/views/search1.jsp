<%@page import="com.tjoeun.Tjproject.service.MainService"%>
<%@page import="com.tjoeun.Tjproject.vo.MainList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");

	int currentPage = 1;
	try {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	} catch (NumberFormatException e) {
		
	}

//	검색창에서 넘어오는 글 제목을 받음

	String searchTag = request.getParameter("searchTag"); // 태그(작가,제목)
	String category = request.getParameter("category"); // 카테고리
	String searchVal = request.getParameter("searchVal"); // 검색어
//	out.println("searchTag: "+ searchTag);  
//	out.println("searchVal: "+ searchVal);
	
	if (searchVal != null) { // 넘어온 검색어가 있으면 세션에 태그와 검색어를 저장
		session.setAttribute("searchTag", searchTag);
		session.setAttribute("category", category);
		searchVal = searchVal.trim().length() == 0 ? "" : searchVal;
		session.setAttribute("searchVal", searchVal);
	} else { // 검색어가 없으면 세션에 저장된 검색어와 카테고리를 읽어온다.
		searchTag = (String) session.getAttribute("searchTag");
		category = (String) session.getAttribute("category");
		searchVal = (String) session.getAttribute("searchVal");
	}
	
	MainList mainList = null;
	
//	검색어가 없는 경우와 검색어가 있는 경우를 각각 메소드로 구현한다.
	if (searchVal == null || searchVal.trim().length() == 0) {
		// 검색어가 입력되지 않은 경우 => 1 페이지로 돌아감 
		mainList = MainService.getInstance().selectSearchList(currentPage);
	} else {
		// 검색어가 입력된 경우 위에서 받은 인수로 메소드 실행
		mainList = MainService.getInstance().selectSearchList(currentPage, searchTag, category, searchVal);
	}

	
	
// 찾은 글을 request 영역에 저장해서 메인글을 브라우저에 표시하는 페이지(Main.jsp)로 넘겨준다.
	request.setAttribute("mainList", mainList);
	request.setAttribute("currentPage", currentPage);
	request.setAttribute("selectHit", selectHit);
	request.setAttribute("selectGood", selectGood);
	request.setAttribute("selectNew", selectNew);
	
	pageContext.forward("Main.jsp");
	
%>

</body>
</html>