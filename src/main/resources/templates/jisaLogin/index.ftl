<#include "/include/header.ftl">
<div class="content">
	<h2 class="conTit">지사 로그인</h2>
	<div class="tbl01">
		<table>
			<colgroup>
				<col width="33%">
				<col width="33%">
				<col width="34%">
			</colgroup>
			<thead>
				<tr>
					<th>구분</th>
					<th>지사장</th>
					<th>Login</th>
				</tr>
			</thead>
			<tbody id=mainContent>
				<#list jisaLogins as jisa>
				<tr>
					<td>${jisa.deptName }</td>
					<td>${jisa.userName }</td>
					<td><a href="/ma/jisalogin/login?memberId=${jisa.userId }">Login</a></td>
				</tr>
				</#list>
				<tr>
					<td>북경</td>
					<td></td>
					<td>준비중</td>
				</tr>
				<tr>
					<td>호주</td>
					<td></td>
					<td>준비중</td>
				</tr>
				<tr>
					<td>뉴질랜드</td>
					<td></td>
					<td>준비중</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<#include "/include/footer.ftl">