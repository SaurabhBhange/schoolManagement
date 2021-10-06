package com.school.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class AWSSliderBucketImpl implements AWSSliderBucket {

	private static final Logger LOGGER = LoggerFactory.getLogger(AWSSliderBucketImpl.class);

	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${aws.s3.bucket2}")
	private String bucketName2;

	@Value("${aws.s3.region}")
	private String region;

	@Override
	@Async
	public void uploadFiles(final MultipartFile multipartFile, String fileName) {
		LOGGER.info("File upload in progress.");
		try {
			final File file = convertMultiPartFileToFile(multipartFile);
			uploadFileToS3Bucket(bucketName2, file, fileName);
			LOGGER.info("File upload is completed.");
			file.delete(); // To remove the file locally created in the project folder.
		} catch (final AmazonServiceException ex) {
			LOGGER.info("File upload is failed.");
			LOGGER.error("Error= {} while uploading file.", ex.getMessage());
		}
	}

	private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex) {
			LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
		}
		return file;
	}

	private void uploadFileToS3Bucket(final String bucketName2, final File file, String fileName) {
		final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName2, fileName, file);
		amazonS3.putObject(putObjectRequest);
	}

	@Override
	@Async
	public byte[] downloadFiles(final String keyName) {
		byte[] content = null;
		LOGGER.info("Downloading an object with key= " + keyName);
		final S3Object s3Object = amazonS3.getObject(bucketName2, keyName);
		final S3ObjectInputStream stream = s3Object.getObjectContent();
		try {
			content = IOUtils.toByteArray(stream);
			LOGGER.info("File downloaded successfully.");
			s3Object.close();
		} catch (final IOException ex) {
			LOGGER.info("IO Error Message= " + ex.getMessage());
		}
		return content;
	}

	@Async
	@Override
	public void DeleteFiles(final String keyName) {
		try {
			final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName2, keyName);
			amazonS3.deleteObject(deleteObjectRequest);
			LOGGER.info("File deleted successfully.");
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			LOGGER.error("Delete file from s3 exception " + e);
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			LOGGER.error("Delete file from s3 exception " + e);
		}
	}

}
