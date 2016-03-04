<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body bgcolor="#FFFACD">
	<h3 align="center">欢迎登录学生作业查询网址</h3>
	<s:form method='post' action='/Student/login'>
		<table align="center" border="0">
			<tr>
				<td><s:textfield name="studentId" label="用户名:"/></td>
			</tr>
			<tr>
				<td><s:password name="password" label="密 码 :"/></td>
			</tr>
			<tr>
          		<td colspan="2" align="center">
         		<s:submit value="登录"/>
         		<s:reset value="重置"/>
          		</td>
        	</tr>
		</table>
	</s:form>
</body>
</html>