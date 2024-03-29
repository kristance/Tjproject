<%@page import="com.tjoeun.Tjproject.vo.MemberVO"%>
<%@page import="com.tjoeun.Tjproject.vo.MainVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저장하기 1</title>
<script type="text/javascript" src="./js/mainjs.js" defer="defer"></script>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");
		
	MainVO mainVO = (MainVO) request.getAttribute("mainVO");

	if (mainVO.getCategory().equals("카테고리 입력")) {
		out.println("<script>");
		out.println("alert('카테고리를 입력하세요')");
		out.println("history.go(-1)");
		out.println("</script>");

	} else if (mainVO.getSubject() == null || mainVO.getSubject().trim().equals("")) {
		out.println("<script>");
		out.println("alert('제목을 입력하세요')");
		out.println("history.go(-1)");
		out.println("</script>");

	} else if (mainVO.getContent() == null || mainVO.getContent().trim().equals("")) {
		out.println("<script>");
		out.println("alert('내용을 입력하세요')");
		out.println("history.go(-1)");
		out.println("</script>");
		out.println("<script>");
		out.println("alert('내용을 입력하세요')");
		out.println("history.go(-1)");
		out.println("</script>");

	} else {
		response.sendRedirect("./Main");
	}
%>
		
		



</body>
</html>
