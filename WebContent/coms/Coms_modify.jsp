<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
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
.list input{
	display: inline-block;
	width: 100%;
	height:100%;
}
</style>
<body>
<script type="text/javascript" src="../js/Calendar3.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/provinceandcity.js"></script>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;单位管理<span>&nbsp;
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;<a href="<%=path%>/coms/Com_comquery.action">单位列表</a><span>&nbsp;
	</div>
</div>
<div id="tips">
</div>
<div id="mainContainer">
<strong>修改单位资料</strong>
<br>
<br>
<form name="modifyForm" action="<%=path%>/coms/Com_update.action" method="post" style="margin-bottom: 20px">
<h3>基本信息</h3>
<table width="400" style="padding: 10px">
   <input type="text" name="cid" value='<s:property value="#request.com_info.cid"/>' hidden>
   <tr>
    <td width="30%">单位名称：</td>
    <td><input type="text" name="cname" value='<s:property value="#request.com_info.cname"/>'/></td>
  </tr>
  <tr>
    <td width="30%">所属行业：</td>
    <td><input type="text" name="cfield" value='<s:property value="#request.com_info.cfield"/>'/></td>
  </tr>
  <tr>
  	<td>单位性质:</td>
  	<td>
  		<select name="ctype">
  			<option selected value='<s:property value="#request.com_info.ctype"/>'><s:property value="#request.com_info.ctype"/></option>
  			<option value="事业单位">事业单位</option>
  			<option value="国企">国企</option>
  			<option value="外企">外企</option>
  			<option value="民企">民企</option>
  			<option value="研究机构">研究机构</option>
  			<option value="其他">其他</option>
  		</select>
  	</td>
  </tr>
  
  <tr>
    <td>省  份：</td>
    <td>
    	<select id="selProvince" onchange="provinceChange();" name="cprov">
    		<option selected value='<s:property value="#request.com_info.cname"/>'><s:property value="#request.com_info.cprov"/></option>
    	</select>
    </td>
  </tr>
  <tr>
  	<td>市(区)：</td>
  	<td>
  		<select id="selCity" name="ccity" onclick="provinceChange();">
  			<option selected value='<s:property value="#request.com_info.ccity"/>'><s:property value="#request.com_info.ccity"/></option>
  		</select>
  	</td>
  </tr>
  
  <tr>
    <td>招聘起始：</td>
    <td><input name="startdate" type="text" id="control_date" size="20"
      maxlength="10" onclick="new Calendar().show(this);" readonly="readonly"
      value='<s:date name="#request.com_info.startdate" format="yyyy-MM-dd" />' />
    </td>
  </tr>
  <tr>
    <td>招聘结束：</td>
     <td><input name="enddate" type="text" id="control_date" size="20"
      maxlength="10" onclick="new Calendar().show(this);" readonly="readonly"
      value='<s:date name="#request.com_info.enddate" format="yyyy-MM-dd" />' />
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input class="button" type="submit" value="修改"></td>
  </tr>
</table>
</form>

</div>
<script type="text/javascript" src="../js/addschool.js"></script>
</body>
</html>