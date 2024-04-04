<%@page import="com.tjoeun.Tjproject.vo.MainList"%>
<%@page import="com.tjoeun.Tjproject.vo.MainCommentList"%>
<%@page import="com.tjoeun.Tjproject.vo.MainCommentVO"%>
<%@page import="com.tjoeun.Tjproject.vo.MainVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 랜딩(selectByIdx)</title>
</head>
<body>

<%
	int idx = (int) request.getAttribute("idx");
		String job = ""; 
	try {
		job = (String) request.getParameter("job");
	} catch (Exception e) {
		
	}
	int currentPage = Integer.parseInt( request.getParameter("currentPage") );
	MainVO mainVO = (MainVO) request.getAttribute("vo");
	MainCommentVO co = (MainCommentVO) request.getAttribute("commentList");
	int errorCheckFirst = 1110;
	int errorCheck = 1111;
	try {
		errorCheckFirst = (int) request.getAttribute("errorCheckFirst");
		errorCheck = (int) request.getAttribute("errorCheck");
	} catch (Exception e) {
		
	}
//	out.println(errorCheck);
//	out.println(errorCheckFirst);
	
if(errorCheckFirst == 1110) {
	if (errorCheck == 1111) {
		out.println("<script>");
		out.println("alert('카테고리를 입력하세요')");
		out.println("location.href='./readUpdate?idx=" + idx + "&currentPage=" + currentPage + "'");
		out.println("</script>");
	} else if (errorCheck == 1112) {
		out.println("<script>");
		out.println("alert('제목을 입력하세요')");
		out.println("location.href='./readUpdate?idx=" + idx + "&currentPage=" + currentPage + "'");
		out.println("</script>");
	} else if (errorCheck == 1113) {
		out.println("<script>");
		out.println("alert('내용을 입력하세요')");
		out.println("location.href='./readUpdate?idx=" + idx + "&currentPage=" + currentPage + "'");
		out.println("</script>");
	} else if (errorCheck == 1114){
		out.println("<script>");
		out.println("alert('정상적으로 수정되었습니다')");
		out.println("location.href='./selectByIdx?idx=" + idx + "&currentPage=" + currentPage + "'");
		out.println("</script>");
	}
}


%>


</body>
</html>