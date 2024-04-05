<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>소설 내용 PAGE</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<script type="text/javascript" src="./js/memberInfo.js" defer="defer"></script>
<link rel="stylesheet" href="./css/read.css"/>

</head>
<body>
<c:set var="mainvo" value="${Mainboard}" />

<div id="container" class="container">

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
							style="width: 100%; height: 100%; max-height: 3em;" onclick="location.href='./login?backPage=3&idx=${mainvo.idx}&currentPage=${currentPage}'" />
						<input class="btn btn-dark" type="button" value="Register"
							style="width: 100%; height: 100%; max-height: 3em;" onclick="location.href='./register'"/>
					</c:if>
					<!-- 로그인한 상태 -->
					<c:if test="${loginCheck == 1}">
						<div class="overflow-auto d-flex align-items-center justify-content-center" style="width: 100%; height: 100%; max-height: 5em;">
							<div class="loginInfo"><strong>${loginInfoID}</strong></div>님<br/></div>
						<input class="btn btn-primary" type="button" value="Logout"
							style="width: 100%; height: 100%; max-height: 3em;" onclick="location.href='./logout?backPage=3&idx=${mainvo.idx}&currentPage=${currentPage}'" />
					</c:if>
				</div>
			</div>	
		</nav>
		
	<div class="container bg-light">
		<div class="row justify-content-center">
			<div class="col-lg-2 bg-light justify-content-center align-items-center">
				<div style="position: sticky; top:0rem;">
					<h4 align="center">정보 및 메뉴</h4><hr/>
					
					<div id="div2_1" class="d-flex flex-column justify-content-center align-items-center">
						<div id="imageUpload" >
							<img id = "imageTag" src="./images/defaultMember.jpg"  style="background-size: cover; width: 210px; height: 300px; object-fit:contain">
						</div>
					<br/>
					</div>
						<hr/>
					<div id="div2_2" align="center">
						<c:if test="${loginCheck == 1 }">
							<button class="btn btn-primary memberInfoRewriteBtn" type="button" onclick="location.href='./memberInfoModify?id=${memberVO.id}'">회원 정보 수정</button>
							<button class="btn btn-danger signOutBtn" type="button" onclick="memberSignOut(${memberVO.id})">회원 탈퇴</button>
						</c:if>
						
					</div>
					<div id="div2_3" align="center">
						
					</div><hr/>
					
					<div id="div2_2" align="center">
						<input class="btn btn-sm btn-outline-success" type="button" value="TOP"
							 style="width: 100%; height: 80%; font-size: 20px;" onclick="location.href='#container'"/>
					</div><hr/>
				
				</div>
			</div>
			
			<div class="col-lg-8 bg-light">
				<div class="center">
					<h4 align="center">Member Profile</h4><hr/>
					<table id="table1" class="table table-light" style="table-layout: fixed;">
						<tr>
						</tr>
						<tr style="height: 5em;">
							<th style="width: 15%"><h5><b>ID</b></h5></th>
							<td style="width: 85%"><h5>
							${memberVO.id}
							<input id="memberInfoID" type="hidden" value="${memberVO.id }">
							</h5></td>
						</tr>
						<tr style="height: 5em;">
							<th><h5><b>이름</b></h5></th>
							<td><h5>${memberVO.name}</h5></td>
						</tr>
						<tr style="height: 5em;">
							<th><h5><b>닉네임</b></h5></th>
							<td><h5>${memberVO.nick}</h5></td>
						</tr>
						<tr style="height: 5em;">
							<th><h5><b>이메일</b></h5></th>
							<td><h5>${memberVO.email}</h5></td>
						</tr>
						<tr style="height: 5em;">
						</tr>
					</table>
						
					<div id="div5">
						<!-- 댓글 입력 -->
						<div id="div5_2">
							
							
						</div>
					
						<div id="div5_1" class="item">
							
						</div>
					</div>
					
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
	</div>
	
</div>	

</body>
</html>