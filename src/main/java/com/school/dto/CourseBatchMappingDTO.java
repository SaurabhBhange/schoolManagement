package com.school.dto;
public class CourseBatchMappingDTO {
	
	private int id;
		
	private int course_id;
		
	private int batch_id;
		
			public int getCourse_id() {
			return course_id;
		}
		
		// TODO Auto-generated constructor stub
		public CourseBatchMappingDTO( int course_id, int batch_id) {
			this.id = id;
			this.course_id = course_id;
			this.batch_id = batch_id;

		}

		public void setCourse_id(int course_id) {
			this.course_id = course_id;
		}

		public int getBatch_id() {
			return batch_id;
		}

		public void setBatch_id(int batch_id) {
			this.batch_id = batch_id;
		}


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
	}