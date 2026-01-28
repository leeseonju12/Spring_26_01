<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE MODIFY"></c:set>

<%@ include file="../common/head.jspf"%>

<hr />

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../article/doModify" method="POST">
			<input type="hidden" name="id" value="${article.id}" />
			<table border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th>새 제목</th>
						<td style="padding: 10px;">
							<input name="title" value="${article.title}" autocomplete="off" type="text" placeholder="수정 제목 입력" />
						</td>
					</tr>
					<tr>
						<th style="width: 50%;">새 내용</th>
						<td style="padding: 10px;">
							<textarea class="input-field" name="body" rows="5" placeholder="수정 내용 입력">${article.body}</textarea>
						</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<input type="submit" value="완료" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="btns">
			<button type="button" onClick="history.back();">뒤로가기</button>


		</div>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>