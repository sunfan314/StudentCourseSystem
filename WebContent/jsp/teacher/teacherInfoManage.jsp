<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息界面</title>
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

	<div data-options="title:'教师信息'">
		<div>
			<table id="pg" class="easyui-propertygrid"
				data-options="
				url:'getTeacherInfo.action?id=${id}',
				toolbar:'#toolbar',
				method:'get',
				showGroup:true,
				scrollbarSize:0
			">
			</table>
		</div>
	</div>

	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editTeacherInfo()">编辑个人信息</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 350px; height: 320px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td><label style="font-size: 15px;">账号信息</label></td>
				</tr>
				<tr>
					<td>用户Id:</td>
					<td><input id="id" name="user.id" class="easyui-validatebox"
						readonly></td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><input id="username" name="user.username"
						class="easyui-validatebox" readonly></td>
				</tr>
				<tr style="display: none;">
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
			onclick="updateTeacherInfo()">保存</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">取消</a>
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

	<script type="text/javascript">
		var userId = ${id};
		function editTeacherInfo() {
			$('#dlg').dialog('open').dialog('setTitle', '编辑个人信息');
			$.post('getJsonTeacherInfo.action', {
				id : userId
			}, function(result) {
				$('#id').val(result.data.id);
				$('#username').val(result.data.username);
				$('#password').val(result.data.password);
				$('#name').val(result.data.name);
				$('#sex').val(result.data.sex);
				$('#department').val(result.data.department);
				$('#phone').val(result.data.phone);
				$('#email').val(result.data.email);
			}, 'json');
			url = 'updateTeacherInfo.action';
		}

		function updateTeacherInfo() {
			/*检验电话号码格式*/
			var str = document.getElementById('phone').value.trim();
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
			if (str.length != 0) {
				reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				if (!reg.test(str)) {
					$('#dialogInformation').text("邮箱地址格式错误，请输入正确的邮箱地址!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return;
				}
			}
			$('#fm').form('submit', {
				url : url,
				success : function() {
					$('#dlg').dialog('close'); // close the dialog
					$('#pg').datagrid('reload'); // reload the user data
				}
			});
		}
	</script>
</body>
</html>