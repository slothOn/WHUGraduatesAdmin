<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String parameterurl = (String)request.getAttribute("parameterurl");
parameterurl = (parameterurl == null ? "" : parameterurl);
String methodurl = (String)request.getAttribute("methodurl");
%>
<!DOCTYPE html>
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8" charset="utf-8">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css" />
<style type="text/css">
* {
    background: none repeat scroll 0 0 transparent;
    border: 0 none;
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
	float:left;
	margin-right:10px;
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
	text-align:center;
	width:98%;
	font-size:12px;
}
</style>
<body>
<div id="navi">
	<div id='naviDiv'>
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;单位管理<span>&nbsp;
		<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;<a href="<%=path%>/coms/Com_comquery.action">单位列表</a><span>&nbsp;
	</div>
</div>
<div id="tips">
	<div id="buttonGroup">
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a href="<%=path%>/coms/Coms_add.jsp">添加单位</a>
		</div>
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a href="<%=path%>/coms/Coms_query.jsp">查找单位</a>
		</div>
		<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
			<a href="<%=path%>/coms/Com_<%=methodurl%>.action?casetype=1&<%=parameterurl%>" target="_blank"">导出excel</a>
		</div>
	</div>
</div>
<div id="mainContainer">

<table class="default" width="100%">
	<col width="15%">
	<col width="10%">
	<col width="10%">
	<col width="10%">
	<col width="15%">
	<col width="10">
	<col width="10">
	<col width="20%">
	<tr class="title">
		<td>单位名称</td>
		<td>所属行业</td>
		<td>单位性质</td>
		<td>所在省</td>
		<td>所在市</td>
		<td>起始日期</td>
		<td>结束日期</td>
		<td>操作</td>
	</tr>

	<s:iterator value="#request.coms_list" var="com">
		<tr class="list">
			<td><s:property value="#com.cname"/></td>
			<td><s:property value="#com.cfield"/></td>
			<td><s:property value="#com.ctype"/></td>
			<td><s:property value="#com.cprov"/></td>
			<td><s:property value="#com.ccity"/></td>
			<td><s:property value="#com.startdate"/></td>
			<td><s:property value="#com.enddate"/></td>
			<td>
			<a href="<%=path%>/coms/Com_modify.action?cid=<s:property value="#com.cid"/>">详情</a>
			<a href="<%=path%>/coms/Com_modify.action?cid=<s:property value="#com.cid"/>">修改</a>
			<a href="<%=path%>/coms/Com_delete.action?cid=<s:property value="#com.cid"/>" onclick="javascript:return confirm('真的要删除吗？');">删除</a>
			</td>
		</tr>
	</s:iterator>
</table>
   <div class="page_list">
        <ul>
            <li><a href="coms/Com_<%=methodurl%>.action?page=1&<%=parameterurl%>">首页</a></li>
            <li><a href='coms/Com_<%=methodurl%>.action?page=<s:property value="#request.beforepage"/>&<%=parameterurl%>'>&lt;</a></li>
            <li><a href="#">当前第<s:property value="#request.pagenum"/>页</a></li>
            <li><a href='coms/Com_<%=methodurl%>.action?page=<s:property value="#request.afterpage"/>&<%=parameterurl%>'>&gt;</a></li>
            <li><a href='coms/Com_<%=methodurl%>.action?page=<s:property value="#request.pagesize"/>&<%=parameterurl%>'>末页</a></li>
            <li>共<s:property value="#request.pagesize"/>页</li>
            <li>&nbsp;&nbsp;</li>
	        <li>转至:</li>
	        <li>
	            <form action="coms/Com_<%=methodurl%>.action?<%=parameterurl%>" id="pageform">
	                <span  style="margin: 5px;padding: 10px;"><input id="page" type="text" name="page" id="page" style="background: #e1e1e1; width:50px; height:20px"/></span>页
	                <span  style="padding: 10px; margin: 5px"><input type="button" value="跳转" font-size="20px" onclick="postparamform();"/></span>
	            </form>
	        </li>
	     </ul>
   </div>
</div>
<script>
function postparamform(){
    var pageform = document.getElementById("pageform");
    var pageval = document.getElementById("page").value;
    var formurl = pageform.action + "&page=" + pageval;
    window.location = formurl;
    return false;
}
</script>
</body>
</html>