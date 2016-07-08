<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/color.css">
<script
	src="${pageContext.request.contextPath}/jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>

</head>
<body>
	<table id="dg" class="easyui-datagrid"
		style="width: 950px; height: 475px" url="getTeachers.action"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="30">用户Id</th>
				<th field="username" width="70">用户名</th>
				<th field="password" width="70">密码</th>
				<th field="name" width="50">姓名</th>
				<th field="sex" with="40">性别</th>
				<th field="department" with="50">院系</th>
				<th field="phone" width="70">电话号码</th>
				<th field="email" width="70">邮箱</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">新建用户</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a> <a
			href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyUser()">删除用户</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 350px; height: 400px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellpadding="5">
				<tr>
					<td><label style="font-size: 15px;">账号信息</label></td>
				</tr>
				<tr style="display: none;">
					<td>用户Id:</td>
					<td><input id="id" name="user.id" class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><input id="username" name="user.username"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input id="password" name="user.password"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td><label style="font-size: 15px;">教师信息</label></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input id="name" name="user.name"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><select id="sex" name="user.sex" class="easyui-combobox"
						panelHeight="auto"">
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
				</tr>
				<tr>
					<td>院系：</td>
					<td><input id="department" name="user.department"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>电话号码：</td>
					<td><input id="phone" name="user.phone"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td><input id="email" name="user.email"
						class="easyui-validatebox"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveUser()">保存</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	
	<div id="dialog" class="easyui-dialog"
		style="width: 300px; height: 140px; padding: 10px 20px" closed="true"
		buttons="#dialog-buttons">
		<label id="dialogInformation" style="font-size: 15px;"></label>
	</div>

	<div id="dialog-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="javascript:$('#dialog').dialog('close')">确定</a>
	</div>
	

	<script>
		function newUser() {
			$('#dlg').dialog('open').dialog('setTitle', '新建用户');
			$('#fm').form('clear');
			url = 'addTeacher.action';
		}
	</script>

	<script>
		function editUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑用户');
				$("#id").val(row.id);
				$("#username").val(row.username);
				$("#password").val(row.password);
				$("#name").val(row.name);
				$("#sex").val(row.sex);
				$("#department").val(row.department);
				$("#phone").val(row.phone);
				$("#email").val(row.email);
				url = 'updateUser.action';
			}
		}
	</script>

	<script>
		function saveUser() {
			/*用户名检验*/
			var str = document.getElementById('username').value.trim();
			if (str.length == 0) {
				$('#dialogInformation').text("请输入用户名！");
				$('#dialog').dialog('open').dialog('setTitle', '警告');
				return;
			}
			/* 密码格式检验：6-20位包含数字字母特殊字符 */
			str = document.getElementById('password').value.trim();
			if (str.length == 0) {
				$('#dialogInformation').text("请输入用户密码！");
				$('#dialog').dialog('open').dialog('setTitle', '警告');
				return;
			}
			reg = /^[a-zA-Z0-9\.@#\$%\^&\*\(\)\[\]\\?\\\/\|\-~`\+\=\,\r\n\:\'\"]{6,20}$/;
			if (!reg.test(str)) {
				$('#dialogInformation').text("密码格式错误，请输入6至20位由数组字母或特殊字符组成的密码!");
				$('#dialog').dialog('open').dialog('setTitle', '警告');
				return;
			}
			/*检验电话号码格式*/
			str = document.getElementById('phone').value.trim();
			if (str.length != 0) {
				reg = /^[0-9]{11}$/;
				if (!reg.test(str)) {
					$('#dialogInformation').text("电话号码格式错误，请输入11位数字组成的电话号码!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return;
				}
			}
			/*检验邮箱格式*/
			str = document.getElementById('email').value.trim();
			if(str.length != 0){
				reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				if (!reg.test(str)) {
					$('#dialogInformation').text(
							"邮箱地址格式错误，请输入正确的邮箱地址!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return;
				}
			}
			/*提交数据操作*/
			$('#fm').form('submit', {
				url : url,
				success : function() {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			});
		}
	</script>

	<script>
		function destroyUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '确定删除该用户吗?', function(r) {
					if (r) {
						$.post('deleteUser.action', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$('#dg').datagrid('reload');
							}

						}, 'json');

					}

				});
			}
		}
	</script>

</body>
</html>