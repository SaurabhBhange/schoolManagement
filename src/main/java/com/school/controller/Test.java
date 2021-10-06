package com.school.controller;

public class Test {

	public static void main(String[] args) {
		String ss="rajat<img\r\n" + 
				"\r\n" + 
				" style=\"margin-left: 0px; margin-top: 0px;\" src=\"https://lh5.googleusercontent.com/dvF14gmUemj_4cjrNhN9fYxMmDTznhHhtUzXtCZjEGgM1cH4A1Y_jdJsGf5G6Tc6lhzTZJ6PsRcQLBlzfK6g6GNJuDerX_ixtlJ3B4o0cvjx0vzL3TbySr12xnGC1MPcwiUL1IUl\" width=\"624\" height=\"441\"\r\n" + 
				"\r\n" + 
				" />saurabh";
			
		int start=ss.indexOf("<img");
		
		int end=ss.indexOf("/>",ss.indexOf("<img"));		
		
		System.out.println(ss.indexOf("/>",ss.indexOf("<img")));
		
		String subString=ss.substring(ss.indexOf(">",ss.indexOf("img")));
		
		System.out.println(subString);

	}

}
