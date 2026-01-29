<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE WRITE"></c:set>

<%@ include file="../common/head.jspf"%>

<hr />

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../article/doWrite" method="POST">
			<input type="hidden" name="boardId" id="boardIdInput" value="" />
			<table class="table" border="1" style="width: 100%; border-collapse: collapse;">
				<tbody>
					<tr>
						<th style="width: 20%;">게시판</th>
						<td style="padding: 10px;">
							<div class="dropdown dropdown-right dropdown-end">
								<div tabindex="0" role="button" class="btn btn-sm m-1" id="boardDropdownBtn">게시판을 선택하세요. 👉🏻</div>
								<ul tabindex="-1" class="dropdown-content menu bg-base-100 rounded-box z-[1] w-52 p-2 shadow">
									<li>
										<a href="#" onclick="selectBoard(1, 'NOTICE'); return false;">NOTICE</a>
									</li>
									<li>
										<a href="#" onclick="selectBoard(2, 'FREE'); return false;">FREE</a>
									</li>
									<li>
										<a href="#" onclick="selectBoard(3, 'QnA'); return false;">QnA</a>
									</li>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td style="padding: 10px;">
							<input class="input input-neutral input-sm" name="title" autocomplete="off" type="text" placeholder="제목을 입력하세요." />
						</td>
					</tr>
					<tr>
						<th style="width: 50%;">내용</th>
						<td style="padding: 10px;">
							<input class="input input-neutral input-sm" name="body" type="text" autocomplete="off" placeholder="내용을 입력하세요." />
						</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<input class="btn btn-soft btn-success" type="submit" value="작성 완료" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="btns">
			<button class="btn btn-soft" type="button" onClick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>

<script>
	function selectBoard(boardId, boardName) {
		document.getElementById('boardIdInput').value = boardId;
		document.getElementById('boardDropdownBtn').innerText = boardName + ' 👉🏻';
		document.activeElement.blur();
	}
</script>

<%@ include file="../common/foot.jspf"%>