package com.school.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Id", "FullName", "Mobile", "Email", "Interest", "Created Time","comment" };
	static String SHEET = "Tutorials";

	public static ByteArrayInputStream tutorialsToExcel(List<EnrollmentInterest> tutorials) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (EnrollmentInterest tutorial : tutorials) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(tutorial.getId());
				row.createCell(1).setCellValue(tutorial.getFullName());
				row.createCell(2).setCellValue(tutorial.getMobile());
				row.createCell(3).setCellValue(tutorial.getEmail());
				row.createCell(4).setCellValue(tutorial.getInterest());
				row.createCell(5).setCellValue(tutorial.getCreated());
				row.createCell(6).setCellValue(tutorial.getComment());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
	
	public static ByteArrayInputStream itToExcel(List<ITEnrollmentInterest> tutorials) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (ITEnrollmentInterest tutorial : tutorials) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(tutorial.getId());
				row.createCell(1).setCellValue(tutorial.getFullName());
				row.createCell(2).setCellValue(tutorial.getMobile());
				row.createCell(3).setCellValue(tutorial.getEmail());
				row.createCell(4).setCellValue(tutorial.getItEnrollmentInterest());
				row.createCell(5).setCellValue(tutorial.getCreated());
				row.createCell(6).setCellValue(tutorial.getComment());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
	

	public static ByteArrayInputStream webinarToExcel(List<WebinarEnrollment> tutorials) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (WebinarEnrollment tutorial : tutorials) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(tutorial.getId());
				row.createCell(1).setCellValue(tutorial.getFullName());
				row.createCell(2).setCellValue(tutorial.getMobile());
				row.createCell(3).setCellValue(tutorial.getEmail());
				row.createCell(4).setCellValue(tutorial.getInterest());
				row.createCell(5).setCellValue(tutorial.getCreated());
				row.createCell(6).setCellValue(tutorial.getComment());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream internToExcel(List<InternEnrollemnt> tutorials) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (InternEnrollemnt tutorial : tutorials) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(tutorial.getId());
				row.createCell(1).setCellValue(tutorial.getFullName());
				row.createCell(2).setCellValue(tutorial.getMobile());
				row.createCell(3).setCellValue(tutorial.getEmail());
				row.createCell(4).setCellValue(tutorial.getInterest());
				row.createCell(5).setCellValue(tutorial.getCreated());
				row.createCell(6).setCellValue(tutorial.getComment());
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}


	public static ByteArrayInputStream graphicToExcel(List<GraphicEnrollment> tutorials) {

		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			// Header
			Row headerRow = sheet.createRow(0);

			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}

			int rowIdx = 1;
			for (GraphicEnrollment tutorial : tutorials) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(tutorial.getId());
				row.createCell(1).setCellValue(tutorial.getFullName());
				row.createCell(2).setCellValue(tutorial.getMobile());
				row.createCell(3).setCellValue(tutorial.getEmail());
				row.createCell(4).setCellValue(tutorial.getGraphicEnrollmentInterest());
				row.createCell(5).setCellValue(tutorial.getCreated());
				row.createCell(6).setCellValue(tutorial.getComment());
				

			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}

}
