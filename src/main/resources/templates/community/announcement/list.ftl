<#include "/include/header.ftl">
<body>
<a href="#primary_content" class="skip-nav">본문 컨텐츠 바로가기</a>
<div id="wrapper">
	<#include "/include/headerMenu.ftl">
	<!-- container -->
	<div id="container">
		<div id="content">
			<div class="side-content">
				<#include "/include/leftContents.ftl">
				<#include "/include/leftMenu.ftl">
				<div class="banner-section">
					<a href="#none"><img src="/public/img/common/bn_side_02.gif" alt="" /></a>
					<a href="#none"><img src="/public/img/common/bn_side_03.gif" alt="" /></a>
				</div>
			</div>
			<div id="primary_content" class="primary-content">
				<div class="page-path">
					<ul>
						<li class="home"><a href="#none">Home</a> &gt; </li>
						<li><a href="#none">Community</a> &gt; </li>
						<li class="current"><a href="/fa/community/announcements">Announcements</a></li>
					</ul>
				</div>
				<div class="tab-A">
					<ul>
						<li class="first active"><a href="/fa/community/announcements">Announce</a></li>
					</ul>
				</div>
				<div class="mgt-20 search-class">
					<input name='pageNum' id="pageNum" type='hidden' value='${pageNum }'/>
					<fieldset>
						<legend></legend>
						<p>
							<select name='searchField' id="searchField" style="width:75px;" title="검색조건선택">
								<option value='boardSubject'>제목</option>
								<option value='boardContent'>내용</option>
								<option value='memberId'>작성자</option>
							</select>
							<input name='searchValue' id="searchValue" type="text" class="text" style="width:300px;" title="" value='' />
							<span class="button"><input type="button" id="searchBtn" title="검색버튼" value="검색" /></span>
						</p>
					</fieldset>
				</div>
				<h1 class="mgt-20"></h1>
				<p>* 총 <strong id="totalCnt">0</strong>건</p>
				<div class="tbl-type-D">
					<table style="width:100%;border: 0;">
						<colgroup>
							<col width="5%">
							<col width="*%">
							<col width="20%">
							<col width="15%">
							<col width="5%">	
						</colgroup>
						<thead>
							<tr>
								<th>NO</th>
								<th>제목</th>
								<th>작성자</th>
								<th>등록일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody id=mainContent></tbody>
					</table>
				</div>
				<div class="paging">
					<span id="pageNavi"></span>
				</div>
				<div class="btn-box float-r">
					<span class="button btn-type-I"><a href="/fa/community/announcements/new">글쓰기</a></span>
				</div> 
			</div>
		</div>
	</div>
	<!-- //container -->
	<!-- footer -->
	<#include "/include/footer.ftl">
	<!-- //footer -->
</div>
<script id="announcementsTemplate" type="text/x-handlebars-template">
	{{#each articles}}
		<tr>
			<td>{{boardNo @index}}</td>
			<td>
				<p class="tb_1">
					<a href='/fa/community/announcements/{{boardIdx}}?pageNum={{../pageInfo.pageNum}}'>{{{boardSubject}}}</a>
				</p>
			</td>
			<td>{{memberId}}</td>
			<td>{{boardRegDate}}</td>
			<td>{{boardReadCount}}</td>
		</tr>
	{{else}}
		<tr>
			<td colspan="5">데이터가 없습니다.</td>
		</tr>
	{{/each}}
</script>
</body>
</html>
