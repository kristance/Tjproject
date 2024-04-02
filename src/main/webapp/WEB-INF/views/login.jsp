<%@page import="com.tjoeun.Tjproject.service.MainService"%>
<%@page import="com.tjoeun.Tjproject.vo.MainList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저장하기</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script type="text/javascript" src="./js/login.js"></script>
<link rel="stylesheet" href="./css/write.css"/>
</head>
<body>
	

<div class="container">

		<nav class="navbar navbar-light bg-light static-top justify-content-center">
		<div class="row">
			<div class="col-lg-2 d-flex align-items-center justify-content-center">
				<input class="btn btn-warning" type="button" value="Main으로" onclick="location.href='Main'"
					style="width: 100%; height: 100%; max-height: 5em;"/>
			</div>
	
			<div class="col-lg-8 justify-content-center" class="text-center">
				<span>
					<img alt="소설 투고 사이트" src="./images/logo.jpg" class="img-fluid" width="100%">
				</span>
			</div>
	
			<div class="col-lg-2 d-flex align-items-center justify-content-center">
				<!-- 로그인하지 않은 상태 -->
				<c:if test="${loginCheck != 1}">
					<input class="btn btn-primary" type="button" value="Login"
						style="width: 100%; height: 100%; max-height: 3em;" onclick="location.href='./login?backPage=1&currentPage=${currentPage}'" />
					<input class="btn btn-dark" type="button" value="Register"
						style="width: 100%; height: 100%; max-height: 3em;" onclick="location.href='./register'"/>
				</c:if>
				<!-- 로그인한 상태 -->
				<c:if test="${loginCheck == 1}">
					<div class="overflow-auto d-flex align-items-center justify-content-center" style="width: 100%; height: 100%; max-height: 5em;">
						<div class="loginInfo"><strong>${loginInfoID}</strong></div>님<br/></div>
					<input class="btn btn-primary" type="button" value="logout"
						style="width: 100%; height: 100%; max-height: 3em;" onclick="location.href='./logout?backPage=1&currentPage=${currentPage}'" />
				</c:if>
			</div>
		</div>
		</nav>
		
		<main class="container-fluid bg-light flex-fill">
			<div class="row">
		
			<!-- 왼쪽 검색바 -->
			<div id="div2" class="col-lg-2 left">
				<h4 align="center">메뉴 목록</h4><hr/>
				
				<div id="div2_1">제목/ID 검색
					<form id="search" action="search" method="post">
						<select name="searchTag" class="form-control form-control-sm">
							<option value="subject">subject</option>
							<option value="id">id</option>
						</select>
						<input class="form-control form-control-sm" size="12" name="searchVal" type="text" placeholder="검색어 입력"> 
						<input type="submit" class="btn btn-outline-primary btm-sm" value="검색" maxlength="10"
							style="width: 100%;">
					</form>
				</div><hr/>
				
				<div id="div2_2">카테고리 검색
						<select id="category" 
	                  class="form-control form-control-sm" 
	                  style="width: 100%; text-align: center;"
	                  onchange="categorySearch()"> 
							<option>장르</option>
							<option value="공포">공포</option>
							<option value="스릴러">스릴러</option>
							<option value="미스터리">미스터리</option>
							<option value="순정">순정</option>
							<option value="코미디">코미디</option>
							<option value="역사">역사</option>
							<option value="판타지">판타지</option>
							<option value="무협">무협</option>
						</select>
				</div><hr/>
			</div>
			
	
			<div id="div3" class="col-lg-8">
				<div class="d-flex align-items-center justify-content-center">
					<table>
						<tr>
							<td style="width: 36em; height: 4em;"></td>
						</tr>
						<tr>
							<td style="height: 29em">
								<!-- 로그인 폼-->
								<div class="login-wrap p-4 p-md-5">
									<div
										class="icon d-flex align-items-center justify-content-center">
										<span class="fa fa-user-o"></span>
									</div>
									
									<h3 class="text-center mb-4"><b>로그인</b></h3>
									
									<form action="loginOK?backPage=${param.backPage}&idx=${idx}&currentPage=${currentPage}" id="loginWindow" class="login-form"
										method="post">
										<div class="form-group">
											<input id="id" name="id" type="text" class="form-control rounded-left"
												placeholder="id" required="required"><br/>
										</div>
										<div class="form-group d-flex">
											<input name="pw" type="password"
												class="form-control rounded-left" placeholder="Password"
												required="required">
										</div><br/>
										<div class="form-group">
											<button type="submit"
												class="form-control btn btn-secondary rounded submit px-3">Login</button>
										</div>
										<div class="form-group d-md-flex">
											<div class="w-100 text-md-start">
												<input id= "saveID" name="saveID" type="checkbox" />
												 아이디 저장
											</div>
											<div class="w-100 text-md-start">
												<input id= "autoLogin" name="autoLogin" type="checkbox" />
												 자동 로그인
											</div>
											<div class="w-100 text-md-end">
												<a href="./search_pw.jsp?backPage=${param.backPage}" style="color: gray;">Forgot Password</a>
											</div>
										</div>
									</form>
									
								</div>
							</td>
						</tr>
						<tr>
							<td style="height: 13em"></td>
						</tr>
	
					</table>
	
				</div>
			</div>

			<!-- 오른 쪽 새글, 추천 글 -->
				<div class="col-lg-2 bg-light justify-content-center align-items-center">
					<h4 align="center">추천 소설 목록</h4><hr/>
						<div class="dropdown">
							<a class="btn btn-outline-success dropdown-toggle" href="" role="button" 
								  id="dropdownMenuLinkHit"
								  data-bs-toggle="dropdown"
								  aria-expanded="false"
								  style="width: 100%; height: 80%;">
									조회수 TOP 5<br/>
							</a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuLinkHit">
								<c:set var="list" value="${selectHit.list}" />
								<c:forEach var="vo" items="${list}">
									<c:if test="${vo.deleted != 'yes'}">
										<li>
											<div class="rankHyper">
												<a class="link-success link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
													href="increment?idx=${vo.idx}&currentPage=${currentPage}">
												${vo.getSubject()}(${vo.getHit()})</a>
											</div>
										</li>
									</c:if>
									<c:if test="${vo.deleted == 'yes'}">
										<li>
											<div class="rankHyper">
												<a href="">삭제된 글입니다</a>
											</div>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</div><hr/>
					
						<div class="dropdown">
								<a class="btn btn-outline-danger dropdown-toggle form-control" href="" role="button" 
								   id="dropdownMenuLinkGood"
								   data-bs-toggle="dropdown"
								   aria-expanded="false"
								   style="width: 100%; height: 80%;">
								   추천수 TOP 5<br/>
								</a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuLinkGood">
								<c:set var="list" value="${selectGood.list}" />
								<c:forEach var="vo" items="${list}">
									<c:if test="${vo.deleted == 'no'}">
										<li>
											<div class="rankHyper">
												<a class="link-danger link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
													href="increment?idx=${vo.idx}&currentPage=${currentPage}">
												${vo.getSubject()}(${vo.getGood()})</a>
											</div>
										</li>
										</c:if>
										<c:if test="${vo.deleted == 'yes'}">
										<li>
											<div class="rankHyper">
												삭제된 글입니다
											</div>
										</li>
									</c:if>
							</c:forEach>
							</ul><hr/>
						</div>
						<div class="dropdown">
							<a class="btn btn-outline-primary dropdown-toggle" href="" role="button" 
							   id="dropdownMenuLinkNew"
							   data-bs-toggle="dropdown"
							   aria-expanded="false"
							   style="width: 100%; height: 80%;">
								Today NEW 5<br/>
							</a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuLinkNew">
								<c:set var="list" value="${selectNew.list}" />
								<c:forEach var="vo" items="${list}">
									<c:if test="${vo.deleted == 'no'}">
										<li>
											<div class="rankHyper">
												<a class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
													href="increment?idx=${vo.idx}&currentPage=${currentPage}">
												${vo.getSubject()}</a>
											</div>
										</li>
									</c:if>
									<c:if test="${vo.deleted == 'yes'}">
										<li>
											<div class="rankHyper">삭제된 글입니다</div>
										</li>
										</c:if>
								</c:forEach>
							</ul><hr/>
						</div>
					</div>
		</div>
	</main>

</div>
	
</body>
</html>