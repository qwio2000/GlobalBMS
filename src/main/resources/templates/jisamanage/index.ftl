<#include "/include/header.ftl">
<div class="content">
	<h2 class="conTit">지사 관리</h2>
	<div class="tbl01">
		<table>
			<colgroup>
				<col width="*">			
				<col width="20%">
				<col width="20%">
				<col width="10%">
				<col width="10%">
				<col width="10%">
				<col width="10%">				
			</colgroup>
			<thead>
				<tr>
					<th>지사</th>					
					<th>지사장</th>
					<th>연락처</th>
					<th>가맹점수</th>
					<th>회원수</th>
					<th>과목수</th>					
					<th>Login</th>
				</tr>
			</thead>
			<tbody id=mainContent>
				<#list jisaList as jisa>
				<tr>
					<td>
						<#if jisa.jisaCD=="08">
							<a href="javascript:;" onClick="$.goJisaView('${jisa.jisaCD }');">${jisa.deptName }</a>
						<#else>
							${jisa.deptName }
						</#if>			
					</td>					
					<td>${jisa.empName }</td>
					<td>${jisa.phone }</td>
					<td>${jisa.deptCnt }</td>
					<td>${jisa.memCnt }</td>
					<td>${jisa.memSubjCnt }</td>					
					<td>
						<#if jisa.jisaCD=="08">
						<a href="/ma/jisamanage/login?memberId=${jisa.userId }">Login</a>
						<#else>
						<a href="${jisa.linkUrl }" target="global">Login</a>
						</#if>
					</td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</div>
<#include "/include/footer.ftl">