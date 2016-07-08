<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生选课界面</title>
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
		style="width: 950px; height: 475px" url="getCourseSelectStatus.action?id=${id}"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="courseId" width="30">课程号</th>
				<th field="courseName" width="50">课程名</th>
				<th field="courseTeachers" width="100">课程教师</th>
				<th field="optional" width="50">是否选选修</th>
				<th field="maxNum" width="70">课程最大人数</th>
				<th field="studentNum" width="70">课程已选人数</th>
				<th field="time" width="70">上课时间</th>
				<th field="place" width="70">上课地点</th>
				<th field="hour" width="50">学时</th>
				<th field="credit" width="50">学分</th>
				<th field="isChosen" width="50">课程状态</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="selectCourse()">选择课程</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="dropCourse()">退选课程</a>
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
	 	
	 	$(function(){
	 		$('#dg').datagrid({
				onDblClickRow : function() {
					var row = $('#dg').datagrid('getSelected');
					if (row) {
						var text = row.courseName;
						var url = "courseTeachers.action?id=" + row.courseId;
						window.parent.Open(text, url);
					}
				}
			});
	 	});
	 	
		function selectCourse() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				if(row.isChosen=="已选课"){
					$('#dialogInformation').text("选课失败，课程已经选择!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return;
				}
				if(row.studentNum>=row.maxNum){
					$('#dialogInformation').text("选课失败，选课人数已满!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return;
				}
				$.messager.confirm('提示', '确定选择该课程吗？', function(r) {
					if (r) {
						$.post('addStudentCourse.action', {
							studentId : userId,
							courseId : row.courseId
						}, function(result) {
							if (result.success) {
								$('#dg').datagrid('reload');
							}
						}, 'json');
					}
				});
			}
		}

		function dropCourse() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				if(row.isChosen=="未选课"){
					$('#dialogInformation').text("退选失败，课程尚未选择!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return;
				}
				$.messager.confirm('提示', '确定退选该课程吗？', function(r) {
					if (r) {
						$.post('deleteStudentCourse.action', {
							studentId : userId,
							courseId : row.courseId
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