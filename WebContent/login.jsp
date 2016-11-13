<%@ page pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

</head>
<body>
	<form action="UserJudge" method="post">
		<link href="css/default.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css"href="js/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
		<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="js/jQuery.easyui.js"></script>
		<!--修改密码窗口-->
		<div id="w" class="easyui-window" title="登录" collapsible="false"
			minimizable="false" maximizable="false" icon="icon-save"
			style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false"
					style="padding: 10px; background: #fff; border: 1px solid #ccc;">
					<table cellpadding=3>
						<tr>
							<td>用户名：</td>
							<td><input id="txtNewPass" type="password" name="user"
								class="txt01" /></td>
						</tr>
						<tr>
							<td>确认密码：</td>
							<td><input id="txtRePass" type="password" name="pass"
								class="txt01" /></td>
						</tr>
					</table>
				</div>
				<div region="south" border="false"
					style="text-align: right; height: 30px; line-height: 30px;">
					<input type="submit" value="submit" />
				</div>
			</div>
		</div>
	</form>
	${desc}
</body>
</html>
