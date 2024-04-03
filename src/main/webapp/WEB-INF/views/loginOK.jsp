<%@page import="java.net.URLEncoder"%>
<%@page import="com.tjoeun.Tjproject.vo.MemberVO"%>
<%@page import="com.tjoeun.Tjproject.service.MainService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");
	MemberVO vo = (MemberVO) request.getAttribute("memberVO");
	String id = vo.getId();
	String password = vo.getPw();
	String encodedId = URLEncoder.encode(id, "UTF-8");
	int loginCheck = (int) request.getAttribute("loginCheck");
	String saveID = request.getParameter("saveID");
	String autoLogin = request.getParameter("autoLogin");
	int backPage = (int) request.getAttribute("backPage");
	
	out.println("<script>");
	if (loginCheck != 1) {
		out.println("alert('로그인 불가')");
		out.println("location.href='./login'");
	} else {
		if (saveID != null && autoLogin != null) {
			session.setAttribute("loginInfoID", id);
			session.setAttribute("loginCheck", loginCheck);
			Cookie cookie = new Cookie("saveID", encodedId);
			cookie.setMaxAge(100000);
			response.addCookie(cookie);
			Cookie cookie2 = new Cookie("autoLogin", encodedId);
			cookie2.setMaxAge(100000);
			response.addCookie(cookie2);
			out.println("alert('로그인 성공 - 1')");
		} else if (saveID != null) {
			session.setAttribute("loginInfoID", id);
			session.setAttribute("loginCheck", loginCheck);
			Cookie cookie = new Cookie("saveID", encodedId);
			cookie.setMaxAge(100000);
			response.addCookie(cookie);
			out.println("alert('로그인 성공 - 2')");
	//		out.println("alert('"+ backPage +"')");
		} else {
			session.setAttribute("loginInfoID", id);
			session.setAttribute("loginCheck", loginCheck);
			out.println("alert('로그인 성공' - 3)");
	//		out.println("alert('"+ backPage +"')");
		}


		if (backPage == 3) {
			int idx;
			int currentPage;
			try {
				idx = Integer.parseInt(request.getParameter("idx"));
				currentPage = Integer.parseInt(request.getParameter("currentPage"));		
			} catch (NumberFormatException e){
				idx = 1;
				currentPage = 1;
			}
			session.setAttribute("idx", idx);
			session.setAttribute("currentPage", currentPage);
			out.println("location.href='./goBack?backPage=" + backPage + "&idx=" + idx + "&currentPage=" + currentPage + "'");
		} else {
			out.println("location.href='./goBack?backPage=" + backPage +"'");		
		}
//		out.println("location.href='./goBack?backPage="+ backPage +"'");
	}
	out.println("</script>");
%>

</body>
</html>