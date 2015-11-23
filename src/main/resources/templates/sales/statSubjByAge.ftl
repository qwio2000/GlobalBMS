<#include "/include/header.ftl">

<!-- Main Content -->		
<div class="content">
	<h2 class="conTit">연령별 과목 수</h2>
	<ul class="memSearch">
		<li>
			<label for="">조직찾기</label>
			<input type="text" class="searchInput" style="width:230px" />
		</li>
		<li>
			<label for="">검색기간</label>

			<select name="" id="" style="width:120px">
				<option value="">2015</option>
			</select>
			년 &nbsp;
			<select name="" id="" style="width:90px">
				<option value="">03</option>
			</select>
			월
		</li>
		<li>
			<label for="">과목</label>
			<select name="" id="" style="width:240px">
				<option value="">전체</option>
			</select>
		</li>
	</ul>
	<div class="btnArea">
		<a href=""><span>검색</span></a>
	</div>
	<div class="tbl01">
		<table>
			<colgroup>
				<col style="width:100px" />
				<col />
				<col style="width:230px" />
				<col style="width:230px" />
			</colgroup>
			<thead>
				<tr class="line">
					<th rowspan="2" class="no_line">순번</th>
					<th rowspan="2">등급</th>
					<th colspan="2">과목</th>
				</tr>
				<tr class="line">
					<th>수</th>
					<th>%</th>
				</tr>
			</thead>
			<tbody>
				<tr class="total line2">
					<td class="no_line"></td>
					<td><a href="javascript:$.openStatSubjByAge('08','','');">합계</a></td>
					<td>3,266</td>
					<td>100</td>
				</tr>
				<tr class="line2">
					<td class="no_line">1</td>
					<td>4세이하</td>
					<td><a href="">162</a></td>
					<td>5</td>
				</tr>
				<tr class="line2">
					<td class="no_line">2</td>
					<td>5세</td>
					<td><a href="">375</a></td>
					<td>5</td>
				</tr>
				<tr class="line2">
					<td class="no_line">3</td>
					<td>6세</td>
					<td><a href="">375</a></td>
					<td>5</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>		

<!--// Main Content -->
<#include "/include/footer.ftl">		