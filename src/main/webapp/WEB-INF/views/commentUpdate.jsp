<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	
	String updatedCommentContent = (String) request.getAttribute("updatedCommentContent");


	if(updatedCommentContent != null && !updatedCommentContent.trim().equals("")) {
		int idx = (int) request.getAttribute("idx");
		int currentPage = (int) request.getAttribute("currentPage");
		out.println("<script>");
		out.println("alert('댓글 수정되었습니다.')");
		out.println("location.href='selectByIdx?idx=" + idx + "&currentPage=" + currentPage + "'");
		out.println("</script>");
		
	} else {
		int idx = (int) request.getAttribute("idx");
		int comidx = (int) request.getAttribute("comidx");
		int currentPage = (int) request.getAttribute("currentPage");
		out.println("<script>");
		out.println("alert('" + comidx + " 번 댓글 수정 실패')");
		out.println("location.href='selectByIdx?idx=" + idx + "&currentPage=" + currentPage + "'");
		out.println("</script>");
	
	}
%>

</body>
</html>