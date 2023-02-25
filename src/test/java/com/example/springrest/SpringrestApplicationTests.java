package com.example.springrest;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springrest.controller.MyController;
import com.example.springrest.method.meetingMethod;

@SpringBootTest
class SpringrestApplicationTests {

//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	void json2pdftest() {
//		String json = "{\r\n" + "  \"seller\": \"XYZ Pvt. Ltd.\",\r\n" + "  \"sellerGstin\": \"29AABBCCDD121ZD\",\r\n"
//				+ "  \"sellerAddress\": \"New Delhi, India\",\r\n" + "  \"buyer\": \"Vedant Computers\",\r\n"
//				+ "  \"buyerGstin\": \"29AABBCCDD131ZD\",\r\n" + "  \"buyerAddress\": \"New Delhi, India\",\r\n"
//				+ "  \"items\": [\r\n" + "    {\r\n" + "      \"name\": \"Product 1\",\r\n"
//				+ "      \"quantity\": \"12 Nos\",\r\n" + "      \"rate\": 123,\r\n" + "      \"amount\": 1476\r\n"
//				+ "    }\r\n" + "  ]\r\n" + "}";
//		String status = json2pdfTable.json2pdf(json, "someName");
//		assertEquals(status, "someName.pdf");
//	}
//
//	@Test
//	void json2pdftestFailEmptyJSON() {
//		String json = "";
//		String status = json2pdfTable.json2pdf(json, "someName");
//		assertThatExceptionOfType(org.json.JSONException.class);
//		assertEquals(status, "End of input at character 0 of ");
//
//	}
//
//	@Test
//	void json2pdftestFailInvalidJSON() {
//		String json = "{\n" + "  \"seller\" \"XYZ Pvt. Ltd.\"\n" + "  \"sellerGstin\"\"29AABBCCDD121ZD\"\n" + "}";
//		String status = json2pdfTable.json2pdf(json, "someName");
//		assertThatExceptionOfType(org.json.JSONException.class);
//		assertEquals(status, "Expected ':' after seller at character 14 of {\n" + "  \"seller\" \"XYZ Pvt. Ltd.\"\n"
//				+ "  \"sellerGstin\"\"29AABBCCDD121ZD\"\n" + "}");
//
//	}
//
//	@Test
//	void homePageCheck() {
//		MyController MyController = new MyController();
//		String[] arr = new String[] { "http://localhost:8080", "http://localhost:8080/json2pdf",
//				"http://localhost:8080/json2pdf?fileName=" };
//
//		String[] status = MyController.homepage();
//		assertTrue(Arrays.equals(arr, status));
//
//	}
//
//	@Test
//	void getAPICheck() throws Exception {
//		MyController MyController = new MyController();
//		String json = "{\r\n" + "  \"seller\": \"XYZ Pvt. Ltd.\",\r\n" + "  \"sellerGstin\": \"29AABBCCDD121ZD\",\r\n"
//				+ "  \"sellerAddress\": \"New Delhi, India\",\r\n" + "  \"buyer\": \"Vedant Computers\",\r\n"
//				+ "  \"buyerGstin\": \"29AABBCCDD131ZD\",\r\n" + "  \"buyerAddress\": \"New Delhi, India\",\r\n"
//				+ "  \"items\": [\r\n" + "    {\r\n" + "      \"name\": \"Product 1\",\r\n"
//				+ "      \"quantity\": \"12 Nos\",\r\n" + "      \"rate\": 123,\r\n" + "      \"amount\": 1476\r\n"
//				+ "    }\r\n" + "  ]\r\n" + "}";
//		String status = MyController.config(json, "testPDF");
//		assertEquals(status, "File name : testPDF.pdf");
//
//	}
//	@Test
//	void postAPICheck() throws Exception {
//		MyController MyController = new MyController();
//		String json = "{\r\n" + "  \"seller\": \"XYZ Pvt. Ltd.\",\r\n" + "  \"sellerGstin\": \"29AABBCCDD121ZD\",\r\n"
//				+ "  \"sellerAddress\": \"New Delhi, India\",\r\n" + "  \"buyer\": \"Vedant Computers\",\r\n"
//				+ "  \"buyerGstin\": \"29AABBCCDD131ZD\",\r\n" + "  \"buyerAddress\": \"New Delhi, India\",\r\n"
//				+ "  \"items\": [\r\n" + "    {\r\n" + "      \"name\": \"Product 1\",\r\n"
//				+ "      \"quantity\": \"12 Nos\",\r\n" + "      \"rate\": 123,\r\n" + "      \"amount\": 1476\r\n"
//				+ "    }\r\n" + "  ]\r\n" + "}";
//		String status = MyController.config2(json, "testPDF");
//		assertEquals(status, "File name : testPDF.pdf");
//
//	}
}
