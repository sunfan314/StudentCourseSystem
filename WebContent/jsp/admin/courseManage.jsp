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
		style="width: 950px; height: 475px" url="getCourses.action"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="courseId" width="30">课程号</th>
				<th field="courseName" width="50">课程名</th>
				<th field="optional" width="50">是否选选修</th>
				<th field="maxNum" width="70">课程最大人数</th>
				<th field="time" width="70">上课时间</th>
				<th field="place" width="70">上课地点</th>
				<th field="hour" width="50">学时</th>
				<th field="credit" width="50">学分</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newCourse()">新建课程</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editCourse()">编辑课程</a> <a
			href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyCourse()">删除课程</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 350px; height: 380px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellpadding="5">
				<tr>
					<td><label style="font-size: 15px;">课程信息</label></td>
				</tr>
				<tr style="display: none">
					<td>课程号：</td>
					<td><input id="courseId" name="course.courseId"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>课程名：</td>
					<td><input id="courseName" name="course.courseName"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>是否选修：</td>
					<td><select id="optional" name="course.optional"
						class="easyui-combobox" panelHeight="auto"">
							<option value="选修">选修</option>
							<option value="必修">必修</option>
					</select></td>
				</tr>
				<tr>
					<td>课程最大人数：</td>
					<td><input id="maxNum" name="course.maxNum"
						class="easyui-numberspinner" data-options="min:1,increment:1"></td>
				</tr>
				<tr>
					<td>上课时间：</td>
					<td><input id="time" name="course.time"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>上课地点：</td>
					<td><input id="place" name="course.place"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>学时：</td>
					<td><input id="hour" name="course.hour"
						class="easyui-numberspinner" value="2"
						data-options="min:1,max:6,increment:1"></td>
				</tr>
				<tr>
					<td>学分：</td>
					<td><input id="credit" name="course.credit"
						class="easyui-numberspinner" value="2"
						data-options="min:1,max:6,increment:1"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveCourse()">保存</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>

	<div id="dlg1" class="easyui-dialog"
		style="width: 300px; height: 140px; padding: 10px 20px" closed="true"
		buttons="#dlg1-buttons">
		<label id="dialogInfo" style="font-size: 15px;"></label>
	</div>

	<div id="dlg1-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="javascript:$('#dlg1').dialog('close')">确定</a>
	</div>

	<script type="text/javascript">
		function newCourse() {
			$('#dlg').dialog('open').dialog('setTitle', '新建课程');
			$('#fm').form('clear');
			url = 'addCourse.action';
		}

		function editCourse() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑课程');
				$("#courseId").val(row.courseId);
				$('#courseName').val(row.courseName);
				$('#optional').val(row.optional);
				$('#maxNum').numberspinner('setValue', row.maxNum);
				$('#time').val(row.time);
				$('#place').val(row.place);
				$("#hour").numberspinner('setValue', row.hour);
				$('#credit').numberspinner('setValue', row.credit);
				url = 'updateCourse.action';
			}
		}

		function saveCourse() {
			$('#fm').form('submit', {
				url : url,
				success : function(result) {
					result = JSON.parse(result);
					if (result.success) {
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
					}
				}
			});
		}

		function destroyCourse() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '确定删除该课程吗？', function(r) {
					if (r) {
						$.post('deleteCourse.action', {
							id : row.courseId
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