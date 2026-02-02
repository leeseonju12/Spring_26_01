<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.code} LIST"></c:set>

<%@ include file="../common/head.jspf"%>

<hr />
<%-- <div>${board }</div> --%>
<section class="mt-24 text-xl px-4">
	<div class="mx-auto">

		<!-- 검색 폼 -->
		<form action="list" method="get" class="mb-4">
			<input type="hidden" name="boardId" value="${board.id}" />
			<div class="flex gap-2 justify-center items-center">
				<select class="select select-bordered select-sm" name="searchType">
					<option value="" disabled selected>검색 기준</option>
					<option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
					<option value="body" ${searchType == 'body' ? 'selected' : ''}>내용</option>
					<option value="writer" ${searchType == 'writer' ? 'selected' : ''}>작성자</option>
				</select>

				<input class="input input-bordered input-sm w-64" name="searchKeyword" value="${searchKeyword}" autocomplete="off"
					type="text" placeholder="검색어를 입력하세요" />

				<button type="submit" class="btn btn-sm btn-success">검색</button>

				<!-- 검색 초기화 버튼 (검색어가 있을 때만 표시) -->
				<c:if test="${not empty searchKeyword}">
					<a href="list?boardId=${board.id}" class="btn btn-sm btn-ghost">초기화</a>
				</c:if>
			</div>
		</form>

		<!-- 검색 결과 정보 (검색어가 있을 때만 표시) -->
		<c:if test="${not empty searchKeyword}">
			<div class="alert alert-ghost mb-4">
				<span>"${searchKeyword}"에 대한 검색 결과 입니다.</span>
			</div>
		</c:if>
		
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
			</tbody>
		</table>

		<!-- 	동적 페이징 -->
		<div class="flex justify-center mt-4">
			<div class="btn-group join">
				<c:set var="paginationLen" value="3" />
				<c:set var="startPage" value="${page - paginationLen >= 1 ? page - paginationLen : 1}" />
				<c:set var="endPage" value="${page + paginationLen <= pagesCount ?  page + paginationLen : pagesCount}" />

				<c:if test="${startPage > 1}">
					<a class="join-item btn btn-sm" href="?page=1&boardId=${boardId}">1</a>
				</c:if>

				<c:if test="${startPage > 2}">
					<button class="join-item btn btn-sm btn-disabled">...</button>
				</c:if>

				<c:forEach begin="${startPage }" end="${endPage }" var="i">
					<a class="join-item btn btn-sm ${param.page == i ? 'btn-active' : ''}" href="?page=${i }&boardId=${param.boardId}">${i }</a>
				</c:forEach>

				<c:if test="${endPage < pagesCount - 1}">
					<button class="join-item btn btn-sm btn-disabled">...</button>
				</c:if>

				<c:if test="${endPage < pagesCount}">

					<a class="join-item btn btn-sm" href="?page=${pagesCount }&boardId=${boardId}">${pagesCount }</a>
				</c:if>

			</div>
		</div>

		<!-- 내 페이지네이션 -->
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

				<!-- 페이지 번호 [앞뒤로 2개씩, 최대 5개의 페이지만 처리] -->
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