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
<title>페이지 랜딩</title>
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
	String selectByIdxButton = (String) request.getAttribute("selectByIdxButton");
	
	
if (selectByIdxButton != null && selectByIdxButton.equals("delete")) {
	response.sendRedirect("Main?currentPage=" + currentPage);
// read.jsp에서 수정 버튼 클릭 시
} else if(selectByIdxButton != null && selectByIdxButton.equals("update")) {
	response.sendRedirect("readUpdate?idx=" + idx + "&currentPage=" + currentPage);
// read.jsp에서 추천 버튼 클릭 시
} else if(selectByIdxButton != null && selectByIdxButton.equals("good")) {
	session.setAttribute("goodidxcheck" + idx, "checked");
	response.sendRedirect("selectByIdx?idx=" + idx + "&currentPage=" + currentPage);
// readUpdate.jsp에서 수정 완료 버튼 클릭 시
} else if(job != null && job.equals("updateOK")) {
	if (mainVO.getCategory() == null || mainVO.getCategory().equals("카테고리 입력") || mainVO.getCategory().trim().equals("")) {
		out.println("<script>");
		out.println("alert('카테고리를 입력하세요')");
		out.println("location.href='readUpdate?idx=" + idx + "&currentPage=" + currentPage + "'");
		
	} else if (mainVO.getSubject() == null || mainVO.getSubject().trim().equals("")) {
		out.println("<script>");
		out.println("alert('제목을 입력하세요')");
		out.println("location.href='readUpdate?idx=" + idx + "&currentPage=" + currentPage + "'");
		
	} else if (mainVO.getContent() == null || mainVO.getContent().trim().equals("")) {
		out.println("<script>");
		out.println("alert('내용을 입력하세요')");
		out.println("location.href='readUpdate?idx=" + idx + "&currentPage=" + currentPage + "'");
		
	} else {
		out.println("<script>");
		out.println("alert('정상적으로 수정되었습니다')");
		out.println("location.href='selectByIdx?idx=" + idx + "&currentPage=" + currentPage + "'");
		
	}
	out.println("</script>");

} else {
	MainVO mainvo = (MainVO) request.getAttribute("vo");
	MainCommentList list = (MainCommentList) request.getAttribute("commentList");
	
	
	
	request.setAttribute("idx", idx);
	request.setAttribute("currentPage", currentPage);
	request.setAttribute("Mainboard", mainVO);
	request.setAttribute("enter", "\r\n");
	
	request.setAttribute("commentList", list);
	
	try {
		session.getAttribute("goodidxcheck" + idx);
	} catch (NullPointerException e) {
		session.setAttribute("goodidxcheck" + idx, "un");
	}
	
	//	read.jsp로 넘겨준다.
	pageContext.forward("read.jsp");
}


%>


</body>
</html>