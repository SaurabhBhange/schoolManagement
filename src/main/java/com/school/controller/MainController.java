package com.school.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import com.school.config.ApplicationConstant;
import com.school.model.FileItemDTO;
import com.school.model.UploadFile;
import com.school.repository.AuthorizationService;
import com.school.repository.DriveService;

@Controller
@RequestMapping("/auth")
public class MainController {

	private Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	AuthorizationService authorizationService;

	@Autowired
	DriveService driveService;

	private Drive driverService;

	public static final String USER_IDENTIFIER_KEY = "MY_TEST_USER";
	private GoogleAuthorizationCodeFlow flow;

	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	@GetMapping("/drive")
	public ResponseEntity<?> showHomePage() throws Exception {
		if (authorizationService.isUserAuthenticated()) {
			logger.debug("User is authenticated. Redirecting to home...");
			System.out.println("In home dashboard");
			return new ResponseEntity<String>("home",HttpStatus.OK);
		} else {
			logger.debug("User is not authenticated. Redirecting to sso...");
			System.out.println("In else part login again");
			return new ResponseEntity<String>("index",HttpStatus.OK);
		}
	}

	

	@GetMapping("/googlesignin")
	public void doGoogleSignIn(HttpServletResponse response) throws Exception {
		logger.debug("SSO Called...");
		response.sendRedirect(authorizationService.authenticateUserViaGoogle());
	}

	@GetMapping("/oauth/callback")
	public ResponseEntity<?> saveAuthorizationCode(HttpServletRequest request) throws Exception {
		logger.debug("SSO Callback invoked...");
		String code = request.getParameter("code");
		logger.debug("SSO Callback Code Value..., " + code);

		if (code != null) {
			authorizationService.exchangeCodeForTokens(code);
			System.out.println();
			return new ResponseEntity<String>("home",HttpStatus.OK);
			//return "redirect:/home";
		}
		return new ResponseEntity<String>("index",HttpStatus.OK);
		//return "redirect:/login";
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) throws Exception {
		logger.debug("Logout invoked...");
		authorizationService.removeUserSession(request);
		return new ResponseEntity<String>("index",HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(HttpServletRequest request, @ModelAttribute UploadFile uploadedFile) throws Exception {
		System.out.println("upload is calling");
		MultipartFile multipartFile = uploadedFile.getMultipartFile();
		driveService.uploadFile(multipartFile);
		return new ResponseEntity<String>("successfully upload",HttpStatus.OK);
				
	}

	@GetMapping(value = { "/listfiles" }, produces = { "application/json" })
	public @ResponseBody List<FileItemDTO> listFiles() throws Exception {

		Credential credential = authorizationService.getCredentials();
		driverService = new Drive.Builder(ApplicationConstant.HTTP_TRANSPORT, ApplicationConstant.JSON_FACTORY,
				credential).setApplicationName(ApplicationConstant.APPLICATION_NAME).build();
//  Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY,cred).setApplicationName("SSD OAuth Spring App").build();
		List<FileItemDTO> responseList = new ArrayList<>();
		FileList fileList = driverService.files().list().setFields("files(id,name,thumbnailLink)").execute();
		for (File file : fileList.getFiles()) {
			FileItemDTO item = new FileItemDTO();
			item.setId(file.getId());
			item.setName(file.getName());
			item.setThumbnailLink(file.getThumbnailLink());
			responseList.add(item);
		}
		return responseList;
	}

	@PostMapping(value = { "/makepublic/{fileId}" }, produces = { "application/json" })
	public @ResponseBody Message makePublic(@PathVariable(name = "fileId") String fileId) throws Exception {
		Credential credential = authorizationService.getCredentials();
		driverService = new Drive.Builder(ApplicationConstant.HTTP_TRANSPORT, ApplicationConstant.JSON_FACTORY,
				credential).setApplicationName(ApplicationConstant.APPLICATION_NAME).build();
//		Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, cred).setApplicationName("SSD OAuth Spring App").build();

		Permission permission = new Permission();
		permission.setType("anyone");
		permission.setRole("reader");

		driverService.permissions().create(fileId, permission).execute();

		Message message = new Message();
		message.setMessage("Permission has been successfully granted.");
		return message;
	}

	@DeleteMapping(value = { "/deletefile/{fileId}" }, produces = "application/json")
	public @ResponseBody Message deleteFile(@PathVariable(name = "fileId") String fileId) throws Exception {
		Credential credential = authorizationService.getCredentials();
		driverService = new Drive.Builder(ApplicationConstant.HTTP_TRANSPORT, ApplicationConstant.JSON_FACTORY,
				credential).setApplicationName(ApplicationConstant.APPLICATION_NAME).build();
//		Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, cred).setApplicationName("SSD OAuth Spring App").build();
		driverService.files().delete(fileId).execute();

		Message message = new Message();
		message.setMessage("File has been deleted.");
		return message;
	}

	@GetMapping(value = { "/createfolder/{folderName}" }, produces = "application/json")
	public @ResponseBody Message createFolder(@PathVariable(name = "folderName") String folder) throws Exception {
		Credential credential = authorizationService.getCredentials();
		driverService = new Drive.Builder(ApplicationConstant.HTTP_TRANSPORT, ApplicationConstant.JSON_FACTORY,
				credential).setApplicationName(ApplicationConstant.APPLICATION_NAME).build();
//		Drive drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, cred)
//				.setApplicationName("SSD OAuth Spring App").build();

		File file = new File();
		file.setName(folder);
		file.setMimeType("application/vnd.google-apps.folder");
		driverService.files().create(file).execute();
		Message message = new Message();
		message.setMessage("Folder has been created successfully.");
		return message;
	}

	class Message {
		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

}
