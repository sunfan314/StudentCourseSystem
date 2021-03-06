<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师课程界面</title>
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
		style="width: 950px; height: 475px" url="getTeacherCourses.action?id=${id}" 
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="courseId" width="30">课程号</th>
				<th field="courseName" width="50">课程名</th>
				<th field="optional" width="50">是否选修</th>
				<th field="maxNum" width="70">课程最大人数</th>
				<th field="studentNum" width="70">选课人数</th>
				<th field="time" width="70">上课时间</th>
				<th field="place" width="70">上课地点</th>
				<th field="hour" width="50">学时</th>
				<th field="credit" width="50">学分</th>
			</tr>
		</thead>
	</table>
	<script>
		
	</script>
</body>
</html>