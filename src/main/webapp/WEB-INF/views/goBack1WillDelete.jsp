<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 돌아갈 때 쓰는 페이지</title>
</head>
<body>


<%
	request.setCharacterEncoding("UTF-8");
	
//	out.println(request.getParameter("backPage")); // ok
		int backPage = (int) request.getAttribute("backPage");
		
		int idx = 1;
		try {
			idx = (int) request.getAttribute("idx");
		} catch (NumberFormatException e) {
		}
		
		int currentPage = 1;
		try {
			currentPage = (int) request.getAttribute("currentPage");			
		} catch (NumberFormatException e) {
		}
		
		
		out.println("<script>");

		switch (backPage) {
			case 1 : // main.jsp
				out.println("location.href='./main'");
				break;
			case 2 : // write.jsp
				out.println("location.href='./write'");
				break;
			case 3 : // read.jsp
				out.println("location.href='./selectByIdx?idx=" + idx + "&currentPage=" + currentPage + "'");
				break;
			default : // main.jsp
				out.println("location.href='./list'");
				break;
		}
		
		out.println("</script>");
%>



</body>
</html>