<#include "/include/header.ftl">
<script type="text/javascript">
<!--
	if ("${chk}"=="N") {
		alert("정보가 없습니다.");
		history.back();
		//location.href = "/ja/centers/centerSearch";
	}
//-->
</script>
<!-- Main Content -->		
<div class="content">
	<div class="clearfix">
		<div class="conLeft">
			<h2 class="conTit">Information</h2>
			<div class="tbl01">
				<table>
					<colgroup>
						<col width="155px" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th class="left">지사코드</th>
							<td class="left">${jisaInfo.jisaCD?default('')}</td>
						</tr>
						<tr>
							<th class="left">지사명</th>
							<td class="left">${jisaInfo.deptName?default('')}</td>
						</tr>
						<tr>
							<th class="left">Director</th>
							<td class="left">${jisaInfo.empName?default('')}</td>
						</tr>
						<tr>
							<th class="left">Address</th>
							<td class="left">${jisaInfo.addr?default('')}</td>
						</tr>
						<tr>
							<th>Email Address</th>
							<td class="left">${jisaInfo.email?default('')}</td>
						</tr>
						<tr>
							<th>Phone Number</th>
							<td class="left">${jisaInfo.phone?default('')}</td>
						</tr>
						<tr>
							<th>Fax Number</th>
							<td class="left">${jisaInfo.fax?default('')}</td>
						</tr>
						<tr>
							<th>Contact Date</th>
							<td class="left">${jisaInfo.contractDate?default('')}</td>
						</tr>
						<tr>
							<th>Open Date</th>
							<td class="left">${jisaInfo.openDate?default('')}</td>
						</tr>
						<tr>
							<th>Status</th>
							<td class="left">${jisaInfo.statusName?default('')}</td>
						</tr>						
					</tbody>
				</table>
			</div>
		</div>
			
		<div class="conRight">
			<h2 class="conTit">Set</h2>
			<div class="btnArea_txt">
				<a href="javascript:$.openSetSubjPreference('${jisaInfo.jisaCD}');" class="btn btn_set">Set Subject Preference - subject(s) that this center offers</a>
				<a href="javascript:$.openSetTuitionMatrix('${jisaInfo.jisaCD}');" class="btn btn_set">Set Tuition Matrix</a>				
			</div>
		</div>			
					
	</div>
</div>
		
<!--// Main Content -->
<#include "/include/footer.ftl">		