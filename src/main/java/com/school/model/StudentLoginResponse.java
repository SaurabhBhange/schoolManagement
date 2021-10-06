package com.school.model;

import org.springframework.stereotype.Component;

@Component
public class StudentLoginResponse {

		private String token;
		private StudentPojo user;
		private String data;
			
		public StudentLoginResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
				
		public StudentPojo getUser() {
			return user;
		}
		public void setUser(StudentPojo user) {
			this.user = user;
		}
	
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		
		
		@Override
		public String toString() {
			return "StudentLoginResponse [token=" + token + ", user=" + user + ", data=" + data + "]";
		}
		
		
	}



