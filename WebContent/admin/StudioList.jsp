<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>LayOut</title>
 <script src="<%=request.getContextPath()%>/css2/easyui/jquery.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/css2/easyui/jquery.easyui.min.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/css2/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css2/easyui/themes/icon.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript">
	$(function() {
	$('#dg').datagrid({
			title : '结果列表',
			pageSize : 5,//默认选择的分页是每页10行数据
			pageList : [ 5, 10, 15, 20 ],//可以选择的分页集合
			nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取
			striped : true,//设置为true将交替显示行背景。
			collapsible : true,//显示可折叠按钮
			 
			url : '/ttms/StudioServlet2',//url调用Action方法
			loadMsg : '数据装载中......',
			singleSelect : false,//为true时只能选择单行
			fitColumns : false,
			remoteSort : false,
			onClickRow : function(rowIndex, rowData) {//单击事件
			},
			onDblClickRow : function(rowIndex, rowData) {//双击事件
			},
			
			toolbar : '#toolbar',
	  
			frozenColumns : [ [ {
				field : 'id',
				checkbox : true
			},  {
				field : "studio_id",
				align : 'center',
				title : '厅号',
				width : 200
			}, {
				field : 'studio_name',
				align : 'center',
				title : '厅名',
				width : 200
			} ] ],
			columns : [ [ {
				field : 'studio_row_count',
				align : 'center',
				title : '行数',
				width : 200
			}, {
				field : 'studio_col_count',
				align : 'center',
				title : '列数',
				width : 200
			},{
				field : 'studio_introduction',
				align : 'center',
				title : '介绍',
				width : 200
			},{
				field : 'studio_flag',
				align : 'center',
				title : '是否安排座位',
				width : 220
			}] ],
			pagination : true,//分页
			rownumbers : true
		//行数
		});
		var p = $('#dg').datagrid('getPager');  
		
		$(p).pagination({  
			pageSize: 5,//每页显示的记录条数，默认为10   
	        pageList: [5,10,15,20],//可以设置每页记录条数的列表   
	        beforePageText: '第',//页数文本框前显示的汉字   
	        afterPageText: '页    共 {pages} 页',   
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',   
		});
		

		//在datagrid实例化之后调用这个方法。
		$("#dg").datagrid({}).datagrid("page");

	});
	function openUserModifyDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑用户信息");
		$("#fm").form("load", row);
		url = "/tccp/studio/update";
	}

	  
	
	//添加用户的函数
	function openUserAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加用户信息");
		url = "/tccp/studio/add";
		}
	
	function saveUser() {
		$("#fm").form("submit", {
		url :url,
		onSubmit : function() {
		return $(this).form("validate");
		},
		success : function(result) {
		//var result = eval('(' + result + ')');
		alert(result)
		$("#dg").datagrid("reload");
		$("#dlg").dialog("close");
		$.messager.alert("系统提示", "保存成功！");
		 
		 
		 
		}
		});
		}
	//删除用户
	function deleteUser() {
		var selectedRows = $("#dg").datagrid("getSelections");
		
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			
			alert(selectedRows[i].studio_id);
			strIds.push(selectedRows[i].studio_id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示", "您确定要删除这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("/tccp/studio/delete", {
					ids :ids
				}, function(result) {
					if (result.success) {
						$.messager.alert("系统提示", "数据已成功删除！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert("系统提示", "数据删除失败，请联系系统管理员！");
					}
				}, "json");
			}
		});
	}
	//查找用户
	function searchUser() {
		$("#dg").datagrid('load', {
			"studio_name" : $("#studio_name").val()
		});
	}
</script>
</head>

<body class="easyui-layout" id="cc">
<div id="toolbar">
<a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>      
  <a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="">编辑</a>
        <a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="">删除</a>
 		 <a href="javascript:" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="">座位管理</a>
 </div>

    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
         <form id="fm" method="post" novalidate>
        &nbsp;  &nbsp;
        <div class="fitem">
                <label>演出厅id号:</label>
              <input name="studio_id" class="easyui-validatebox" required="true" />
            </div>
            &nbsp;  &nbsp;
            <div class="fitem">
                <label>演出厅名称:</label>
              <input name="studio_name" class="easyui-validatebox" required="true" />
            </div>
            &nbsp;  &nbsp;
            <div class="fitem">
                <label>演出厅行号:</label>
                <input name="row" class="easyui-validatebox" required="true" />
            </div>  
            &nbsp;  &nbsp;      
            <div class="fitem"><label for="">演出厅列号:</label>
                <input name="col" />
            </div>
            &nbsp;  &nbsp;
            <div class="fitem">
                <label for="">演出厅介绍:</label>
                <input type=""  name="introduction" class="easyui-validatebox" required="true" />
            </div>
            &nbsp;  &nbsp;
        </form>
    </div>
    
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" icon-Cls="icon-ok" onclick="">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" icon-Cls="icon-cancel" onclick="javascript:$('#dlg').dialog('close');">取消</a>
    </div>

<div region="north" border="true" split="true"
		style="overflow: hidden; height: 65px;">
		<form id="searchForm" name="searchForm">
			<table id="searchTable" width=100%>
				<tr>	
					<td>当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">影厅管理</a> &gt; 内容列表<!--   <input class="easyui-textbox" type="text" name="bh"  id="bh">--></td>
							&nbsp;&nbsp;&nbsp;		
					<td>厅名：<input class="easyui-textbox" type="text" name="studio_name"  id="studio_name" onkeydown=" if(event.keyCode == 13)searchUser()">&nbsp;<a href="javascript:searchUser()" class="easyui-linkbutton" icon="icon-search"
						>查询</a>
					</td>
					<td>
					&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
                      <td><a href="#" class="easyui-linkbutton" icon="icon-search"
						onclick="doSearch()">退出</a>
					</td>						
				</tr>
			</table>

		</form>
	</div>
	<div region="center" border="true" split="true"
		style="overflow: hidden;">
		<table id="dg" title="My Users" class="easyui-datagrid" width="100%"
			height="99%" toolbar="#toolbar" rownumbers="true" fitColumns="true"
			singleSelect="true">

		</table>
		

	</div>




 
</body>
</html>