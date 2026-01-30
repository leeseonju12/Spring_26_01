<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.code} LIST"></c:set>

<%@ include file="../common/head.jspf"%>

<hr />
<%-- <div>${board }</div> --%>
<section class="mt-24 text-xl px-4">
	<div class="mx-auto">
		<div>${articlesCount }개</div>
		<div class="mb-3 text-2xl font-bold">${board.code}게시판</div>
		<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<thead>
				<tr>
					<th style="text-align: center;">Board</th>
					<th style="text-align: center;">ID</th>
					<th style="text-align: center;">Registration date</th>
					<th style="text-align: center;">TITLE</th>
					<th style="text-align: center;">Writer</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles }">
					<tr class="hover:bg-base-300">
						<td style="text-align: center;">${board.code}</td>
						<td style="text-align: center;">${article.id }</td>
						<td style="text-align: center;">${article.regDate.substring(0,10) }</td>
						<td style="text-align: center;">
							<a href="detail?id=${article.id } ">${article.title }</a>
						</td>
						<td style="text-align: center;">${article.extra__writer }</td>
					</tr>
				</c:forEach>
				<c:if test="${empty articles }">
					<tr>
						<td colspan="4" style="text-align: center;">게시글이 없습니다</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		
		<!-- 페이지네이션 -->
		<div class="flex justify-center mt-8">
			<div class="join">
				<!-- 처음 -->
				<c:if test="${page > 1}">
					<a href="list?boardId=${board.id}&page=1" class="join-item btn btn-square">««</a>
				</c:if>

				<!-- 이전 -->
				<c:if test="${page > 1}">
					<a href="list?boardId=${board.id}&page=${page - 1}" class="join-item btn btn-square">«</a>
				</c:if>

				<!-- 페이지 번호 [5개의 페이지만 처리] -->
				<c:set var="startPage" value="${page - 2 > 1 ? page - 2 : 1}" />
				<c:set var="endPage" value="${page + 2 < pagesCount ? page + 2 : pagesCount}" />

				<c:forEach begin="${startPage}" end="${endPage}" var="i">
					<c:choose>
						<c:when test="${i == page}">
							<a class="join-item btn btn-square btn-active">${i}</a>
						</c:when>
						<c:otherwise>
							<a href="list?boardId=${board.id}&page=${i}" class="join-item btn btn-square">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<!-- 다음 -->
				<c:if test="${page < pagesCount}">
					<a href="list?boardId=${board.id}&page=${page + 1}" class="join-item btn btn-square">»</a>
				</c:if>

				<!-- 마지막 -->
				<c:if test="${page < pagesCount}">
					<a href="list?boardId=${board.id}&page=${pagesCount}" class="join-item btn btn-square">»»</a>
				</c:if>
			</div>
		</div>

		<!-- 게시글 수 표시 -->
		<div class="text-center mt-4 text-sm text-gray-600">전체 게시글: ${articlesCount}개 | 현재 페이지: ${page}/${pagesCount}</div>
	</div>
</section>


<%@ include file="../common/foot.jspf"%>