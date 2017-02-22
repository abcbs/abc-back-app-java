package com.ndlan.cwwarm.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ndlan.framework.core.web.domain.Result;

public class FileUploadRestfulTest {
	 FileUploadRestful  fileUploadRestful=
			new  FileUploadRestful();
	@Test
	public void selectAll() {
		Result result= fileUploadRestful.selectAll();
		assertNotNull(result);
		
	}
}
