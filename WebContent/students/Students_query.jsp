<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8" charset="utf-8">
	<link rel="stylesheet" type="text/css" href="../css/default.css" />
<style type="text/css">
* {
    background: none repeat scroll 0 0 transparent;
    border: 1 none;
    margin: 0;
    padding: 0;
    vertical-align: baseline;
	font-family:微软雅黑;
}
#navi{
	width:100%;
	position:relative;
	word-wrap:break-word;
	border-bottom:1px solid #065FB9;
	margin:0;
	padding:0;
	height:40px;
	line-height:40px;
	vertical-align:middle;
    background-image: -moz-linear-gradient(top,#EBEBEB, #BFBFBF);
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),color-stop(1, 
#BFBFBF));
}
#naviDiv{
	font-size:14px;
	color:#333;
	padding-left:10px;
}
#tips{
	margin-top:10px;
	width:100%;
	height:40px;
}
#buttonGroup{
	padding-left:10px;
	float:left;
	height:35px;
}
.button{
	margin-top:20px;
	padding-left:10px;
	padding-right:10px;
	font-size:14px;
	width:70px;
	height:30px;
	line-height:30px;
	vertical-align:middle;
	text-align:center;
	cursor:pointer;
    border-color: #77D1F6;
    border-width: 1px;
    border-style: solid;
    border-radius: 6px 6px;
    -moz-box-shadow: 2px 2px 4px #282828;
    -webkit-box-shadow: 2px 2px 4px #282828;
    background-image: -moz-linear-gradient(top,#EBEBEB, #BFBFBF);
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),color-stop(1, #BFBFBF));
}
#mainContainer{
	padding-left:10px;
	padding-right:10px;
	text-align:left;
	width:98%;
	font-size:16px;
}
select
{
    width: 120px;
    margin: 3px 0px;
}
form{
	margin-bottom: 8px;
}
</style>
<body>
<script type="text/javascript" src="../js/Calendar3.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/provinceandcity.js"></script>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;学生管理<span>&nbsp;
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;<a href="<%=path%>/students/Students_query.action">学生列表</a><span>&nbsp;
	</div>
</div>
<div id="tips">
</div>
<div id="mainContainer">
<!-- 从session中获取学生集合 -->
<strong>查找学生</strong>
<br>
<br>
<div>
	<s:fielderror/>
</div>
<form name="addForm" action="<%=path%>/students/Student_queryByInfo.action" method="post">
<fieldset>
	<legend>根据基本信息查询</legend>
	<table width="400" style="padding: 10px">
	  <tr>
	    <td width="30%">学号：</td>
	    <td><input type="text" name="sid" /></td>
	  </tr>
	  <tr>
	    <td width="30%">姓名：</td>
	    <td><input type="text" name="sname" /></td>
	  </tr>
	  <tr>
	    <td>性别：</td>
	    <td><input type="radio" name="gender" value="男"/>男<input type="radio" name="gender" value="女"/>女</td>
	  </tr>
	  <tr>
	  	<td>政治面貌:</td>
	  	<td>
	  		<select name="political">
	  			<option value="" selected></option>
	  			<option value="无">无</option>
	  			<option value="共青团员">共青团员</option>
	  			<option value="共产党员">共产党员</option>
	  		</select>
	  	</td>
	  </tr>
	  <tr>
	    <!--<input type="text" name="address" /> -->
	    <td>省  份：</td>
	    <td>
	    	<select id="selProvince" onchange="provinceChange();" name="sprov">
	    		<option value="" selected></option>
	    	</select>
	    </td>
	  </tr>
	  <tr>
	  	<td>市(区)：</td>
	  	<td>
	  		<select id="selCity" name="scity" onclick="provinceChange();">
	  			<option value="" selected></option>
	  		</select>
	  	</td>
	  </tr>
	  <tr>
	  	<td>专业:</td>
	  	<td><input type="text" name="major"></td>
	  </tr>
	  <tr>
	  	<td>电话:</td>
	  	<td><input type="tel" name="tel"></td>
	  </tr>
	  <tr>
	  	<td>qq:</td>
	  	<td><input type="text" name="sqq"></td>
	  </tr>
	  <tr>
	    <td colspan="2" align="center"><input class="button" type="submit" value="查询"></td>
	  </tr>
	</table>
	</fieldset>
</form>

<form name="addForm" action="<%=path%>/students/Student_queryBySchool.action" method="post">
	<fieldset>
	<legend>根据大学生涯查询</legend>
	<table width="400" style="padding: 10px">
	  <tr>
	    <td width="30%">参加活动：</td>
	    <td><input type="text" name="activity" /></td>
	  </tr>
	  <tr>
	    <td width="30%">所获荣誉：</td>
	    <td><input type="text" name="honor" /></td>
	  </tr>
	   <tr>
	    <td width="30%">活动起始时间：</td>
	    <td><input type="text" name="startyear" /></td>
	  </tr>
	   <tr>
	    <td width="30%">活动结束时间：</td>
	    <td><input type="text" name="endyear" /></td>
	  </tr>
	  <tr>
	    <td colspan="2" align="center"><input class="button" type="submit" value="查询"></td>
	  </tr>
	</table>
</fieldset>
</form>

<form name="addForm" action="<%=path%>/students/Student_queryByJob.action" method="post">
	<fieldset>
	<legend>根据职业发展查询</legend>
	<table width="400" style="padding: 10px">
	  <tr>
	    <td width="30%">开始时间：</td>
	    <td><input type="text" name="time" /></td>
	  </tr>
	  <tr>
	    <td width="30%">学生状态：</td>
	    <td><input type="text" name="type" /></td>
	  </tr>
	   <tr>
	    <td width="30%">单位名称：</td>
	    <td><input type="text" name="cname" /></td>
	  </tr>
	   <tr>
	    <td width="30%">岗位名称：</td>
	    <td><input type="text" name="job" /></td>
	  </tr>
	  <tr>
	    <td width="30%">备注：</td>
	    <td><input type="text" name="comment" /></td>
	  </tr>
	  <tr>
	    <td colspan="2" align="center"><input class="button" type="submit" value="查询"></td>
	  </tr>
	</table>
</fieldset>
</form>

</div>
</body>
</html>