<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改密码</title>
</head>
<style type="text/css" >
    label, input{
        margin: 3px 1px;
        padding: 3px;
        min-width: 40px;
        min-height: 20px;
        font-size: 18px;
    }
</style>
<body>
<form action="<%=path %>/users/User_changepwd.action">
    <label>请输入原密码:</label>
    <input type="text" name="prevpwd"/><br>
    <label>请输入新密码:</label>
    <input type="password" name="newpwd1" placeholder="密码长度不少于3位"><br>
    <label>再输入新密码:</label>
    <input type="password" name="newpwd2" placeholder="密码长度不少于3位"><br>
    <input type="button" value="提交" onclick="checkpassword();">
    <div>
	  <s:fielderror/>
	</div>
</form>
<script type="text/javascript">
function checkpassword(){
    var newpwd1 = document.getElementsByName("newpwd1")[0];
    var newpwd2 = document.getElementsByName("newpwd2")[0];
    if(newpwd1.value != newpwd2.value){
        alert("两次密码输入不一致");
        return false;
    }
    if(newpwd1.value.length < 3){
    	alert("密码长度小于3位");
    	return false;
    }
    var form = document.getElementsByTagName("form")[0];
    form.submit();
}
</script>
</body>
</html>