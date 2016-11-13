<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%
    String path=request.getContextPath();
    String basePath=request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
    
    String login=(String)request.getSession().getAttribute("loginflag");
    if(login == null || !login.equals("yes"))
    {
        request.setAttribute("desc", "请输入用户名、密码");
        RequestDispatcher rd=request.getRequestDispatcher("/error.jsp");
        rd.forward(request, response);
    }
    session.invalidate();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>嘻哈影院</title>
    <link href="css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/jQuery.easyui.js"></script>

	<script type="text/javascript" src="js/outlook2.js"> </script>

    <script type="text/javascript">
	 var _menus = {"menus":[
						{"menuid":"1","icon":"icon-sys","menuname":"系统管理",
							"menus":[{"menuname":"系统管理","icon":"icon-nav","url":" "},
									{"menuname":"添加用户","icon":"icon-add","url":" "},
									{"menuname":"用户管理","icon":"icon-users","url":" "},
									{"menuname":"角色管理","icon":"icon-role","url":" "},
									{"menuname":"权限设置","icon":"icon-set","url":" "},
									{"menuname":"系统日志","icon":"icon-log","url":" "}
								]
						},{"menuid":"8","icon":"icon-sys","menuname":"演出厅管理",
							"menus":[{"menuname":"演出厅列表","icon":"icon-nav","url":"admin/StudioList.jsp"}
									
								]
						},{"menuid":"56","icon":"icon-sys","menuname":"影片管理",
							"menus":[{"menuname":"影片列表","icon":"icon-nav","url":"admin/PlayList.jsp"}]
						},{"menuid":"28","icon":"icon-sys","menuname":"员工管理",
							"menus":[{"menuname":"员工列表","icon":"icon-nav","url":"admin/EmployeeList.jsp"}
									
								]
						},{"menuid":"39","icon":"icon-sys","menuname":"设置",
							"menus":[{"menuname":"","icon":"icon-nav","url":""},
									{"menuname":"财务统计","icon":"icon-nav","url":""},
									{"menuname":"订单管理","icon":"icon-nav","url":""}
								]
						}
				]};
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function close() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
            
        }

        $(function() {

            openPwd();
            //
            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        location.href = 'login.jsp';
                    }
                });

            })
			
			
			
        });
		
		

    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    
</div></noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 XXXXXXX <a href="changePassword.jsp" >修改密码</a> <a href="#" id="loginOut">安全退出

</a></span>
        
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">By Uniquc @copy-2016.12</div>
    </div>
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
<div class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
				
			</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
				
			<h1>Welcome to 嘻哈影院</h1>

			</div>
		</div>
    </div>
    
    
   

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>