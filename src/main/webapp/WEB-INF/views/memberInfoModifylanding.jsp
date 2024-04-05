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
	request.setCharacterEncoding("UTF-8");

	int processValue = (int) request.getAttribute("processValue");
	String id = (String) request.getAttribute("id");
	
	if (processValue >= 1) {
		out.println("<script>");
		out.println("alert('회원 정보 수정에 성공하였습니다.')");
		out.println("location.href='./memberInfo?id=" + id + "'");
		out.println("console.log('확인')");
		out.println("</script>");
	} else {
		out.println("<script>");
		out.println("alert('수정에 실패하였습니다.')");
		out.println("location.href='history.go(-1)'");
		out.println("</script>");
		
	}
%>


</body>
</html>