<%@page import="com.tjoeun.Tjproject.vo.MemberVO"%>
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
		
		String pw2= "";
	try {
		pw2 = (String) request.getAttribute("pw2");
	} catch (Exception e) {
	}
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	int registerIdCheck = (int) request.getAttribute("registerIdCheck");
	int registerSuccess;
	try {
		registerSuccess = (int) request.getAttribute("registerSuccess") ;
		
	} catch (Exception e) {
		registerSuccess = -1;
	}
	
	
	
	if (registerIdCheck > 0) {
		out.println("<script>");
		out.println("alert('중복된 아이디가 있습니다.')");
		out.println("location.href=history.go(-1)");		
		out.println("</script>");
	
	}
	
	if (!memberVO.getPw().equals(pw2)) {
		out.println("<script>");
		out.println("alert('비밀번호가 일치하지 않습니다.')");
		out.println("location.href=history.go(-1)");		
		out.println("</script>");
	}
	
	if (memberVO.getId() == null || memberVO.getId().equals("") || 
            memberVO.getPw() == null || memberVO.getPw().equals("") ||
            pw2 == null || pw2.equals("") ||
            memberVO.getName() == null || memberVO.getName().equals("") ||
            memberVO.getNick() == null || memberVO.getNick().equals("") ||
            memberVO.getEmail() == null || memberVO.getEmail().equals("")) {
		out.println("<script>");
		out.println("alert('입력하지 않은 데이터값이 있습니다.')");
		out.println("location.href=history.go(-1)");		
		out.println("</script>");
    	   
	} 
	
	if (registerSuccess == 1) {
		session.removeAttribute("messageType");
		session.removeAttribute("messageContent");
		out.println("<script>");
		out.println("alert('회원가입 성공')");
		out.println("location.href='Main'");
		out.println("</script>");
	}
	
%>


</body>
</html>