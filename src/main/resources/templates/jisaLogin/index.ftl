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
						<li class="home"><a href="/">Home</a> &gt; </li>
						<li class="current"><a href="/ma/jisalogin">지사 Log in</a></li>
					</ul>
				</div>
				<div class="tab-A">
					<ul>
						<li class="first active"><a href="/ma/jisalogin">지사 Login</a></li>
					</ul>
				</div>
				<div class="tbl-type-D" style="border:none">
					<table style="width:50%;border: 0;margin-top: 50px;">
						<colgroup>
							<col width="50%">
							<col width="50%">
						</colgroup>
						<thead>
							<tr>
								<th>구분</th>
								<th></th>
							</tr>
						</thead>
						<tbody id=mainContent>
							<tr>
								<td>홍콩</td>
								<td><a href="/ma/jisalogin/login?memberId=daekim">Login</a></td>
							</tr>
							<tr>
								<td>북경</td>
								<td><a href="#">Login</a></td>
							</tr>
							<tr>
								<td>호주</td>
								<td><a href="#">Login</a></td>
							</tr>
							<tr>
								<td>뉴질랜드</td>
								<td><a href="#">Login</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- //container -->
	<!-- footer -->
	<#include "/include/footer.ftl">
	<!-- //footer -->
</div>
</body>
</html>
