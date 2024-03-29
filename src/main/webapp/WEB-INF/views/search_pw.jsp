<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="./js/mainjs.js"></script>
<link rel="stylesheet" href="./css/write.css"/>


</head>
<body>
<div class="container">

	<div class="container bg-light">
		<nav class="navbar navbar-light bg-light static-top justify-content-center">
			<div class="row">
				<div class="col-lg-2 d-flex align-items-center justify-content-center">
					<input class="btn btn-warning" type="button" value="Main으로" onclick="location.href='list'"
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
						<input class="btn btn-primary" type="button" value="Logout"
							style="width: 100%; height: 100%; max-height: 3em;" onclick="location.href='./logout?backPage=1&currentPage=${currentPage}'" />
					</c:if>
				</div>
			</div>
		</nav>
	</div>
	
	<div class="container bg-light">
		<div class="row justify-content-center">
		
			<div class="col-lg-2 bg-light justify-content-center align-items-center">
				<h4 align="center">메뉴 목록</h4><hr/>
			
				<div id="div2_1" class="d-flex flex-column justify-content-center align-items-center">
				<span style="display: inline-block;">제목/ID 검색<br/></span>
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
			<form id="categoryOptionValueSubmit" action="categorySort">
				<div id="div2_2" class="d-flex flex-column justify-content-center align-items-center">카테고리 검색
					<select id="category" 
	                  class="form-control form-control-sm" 
	                  style="width: 100%; text-align: center;"
	                  onchange="categorySortFn()"> 
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
						<input id="categoryOptionValue" name="categoryOptionValue" type="text" style="visibility: hidden;">
						<input  type="submit" style="visibility: hidden;">
			</form>
		</div>

		<div class="col-lg-8">
			
			<div class="d-flex align-items-center justify-content-center">
				<table>
					<tr>
						<td style="width: 36em; height: 4em;"></td>
					</tr>
					<tr>
						<td style="height: 29em">
							<!-- -->
							<div class="login-wrap p-4 p-md-5">
								<div
									class="icon d-flex align-items-center justify-content-center">
									<span class="fa fa-user-o"></span>
								</div>
								<h3 class="text-center mb-4">Search Password</h3>
								<form action="./search_pw_OK?backPage=${param.backPage}" id="forgotWindow" class="forgot-form"
									method="post">
									<div class="form-group">
										<input name="id" type="text" class="form-control rounded-left"
											placeholder="id를 입력해주세요." required="required"><br/>
									</div>
									<div class="form-group">
										<input name="name" type="text" class="form-control rounded-left"
											placeholder="이름을 입력해주세요" required="required"><br/>
									</div>
									<div class="form-group d-flex">
										<input name="email" type="email"
											class="form-control rounded-left" placeholder="이메일을 입력해주세요."
											required="required">
									</div>
									<div class="form-group">
										<br/><button type="submit"
											class="form-control btn btn-secondary rounded submit px-3">찾기</button>
									</div>
									<div class="form-group d-md-flex">
									
									</div>
								</form>
								<!-- -->
							</div>
						</td>
					</tr>
					<tr>
						<td style=" height: 13em"></td>
					</tr>

				</table>

			</div>
		</div>

		<div class="col-lg-2 bg-light justify-content-center align-items-center">
				<h4 align="center">추천 소설 목록</h4><hr/>
					<div class="rankHyper2" style="color: #198754;">
					조회수 TOP 5<br/>
					</div>
					<ol>
						<c:set var="list" value="${selectHit.list }" />
						<c:forEach var="vo" items="${list}">
							<c:if test="${vo.deleted != 'yes'}">
								<li style="color: #198754;">
									<div class="rankHyper">
										<a class="link-success link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
											href="increment?idx=${vo.idx}&currentPage=${currentPage}">
										${vo.getSubject()}(${vo.getHit()})</a>
									</div>
								</li>
							</c:if>
							<c:if test="${vo.deleted == 'yes'}">
								<li style="color: #198754;">
									<div class="rankHyper">
										<a href="">삭제된 글입니다</a>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ol><hr/>
					<div class="rankHyper2" style="color: #dc3545;">
					추천수 TOP 5<br/>
					</div>
					<ol>
						<c:set var="list" value="${selectGood.list }" />
						<c:forEach var="vo" items="${list}">
							<c:if test="${vo.deleted == 'no'}">
							<li style="color: #dc3545;">
								<div class="rankHyper">
									<a class="link-danger link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
										href="increment?idx=${vo.idx}&currentPage=${currentPage}">
									${vo.getSubject()}(${vo.getGood()})</a>
								</div>
							</li>
							</c:if>
							<c:if test="${vo.deleted == 'yes'}">
							<li style="color: #dc3545;">
								<div class="rankHyper">
									삭제된 글입니다
								</div>
							</li>
							</c:if>
						</c:forEach>
					</ol><hr/>
					<div class="rankHyper2" style="color: #0d6efd;">
					Today NEW 5<br/>
					</div>
					<ol>
						<c:set var="list" value="${ selectNew.list}" />
						<c:forEach var="vo" items="${list}">
							<fmt:formatDate var="writeDate" value="${vo.getWriteDate()}" pattern="MM/dd HH:mm:ss"/>
							<c:if test="${vo.deleted == 'no'}">
								<li style="color: #0d6efd;">
									<div class="rankHyper">
										<a class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover"
											href="increment?idx=${vo.idx}&currentPage=${currentPage}">
										${vo.getSubject()}</a>
									</div>
								</li>
							</c:if>
							<c:if test="${vo.deleted == 'yes'}">
								<li style="color: #0d6efd;">
									<div class="rankHyper">
										삭제된 글입니다
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ol><hr/>
				</div>
				
			</div>
		</div>

	</div>

</body>
</html>