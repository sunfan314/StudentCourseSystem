package edu.cn.model;

import java.io.Serializable;

/**
 *CourseStudent主键类
 */
public class CourseStudentPK  implements Serializable{
	
	private int courseId;
	
	private int studentId;
	
	public CourseStudentPK(){
		super();
	}

	public CourseStudentPK(int courseId,int studentId){
		super();
		this.courseId=courseId;
		this.studentId=studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseId;
		result = prime * result + studentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseStudentPK other = (CourseStudentPK) obj;
		if (courseId != other.courseId)
			return false;
		if (studentId != other.studentId)
			return false;
		return true;
	}
	
	
}
