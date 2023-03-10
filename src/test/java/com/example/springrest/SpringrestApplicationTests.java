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

	@Test
	void contextLoads() {
	}

	@Test
	void registerTest() throws Exception {
		String status = meetingMethod.register("someName");
		assertEquals("Duplicate entry 'someName' for key 'meetingcalender.PRIMARY'", status);
	}

	@Test
	void bookMeeting() throws Exception {
		String status = meetingMethod.bookMeeting("awwab", "faizan", "25-02-2023", "07");
		assertEquals("faizan has a meeting already booked with : awwab", status);
	}

	@Test
	void bookMeetingPleaseRegister() throws Exception {
		String status = meetingMethod.bookMeeting("person", "faizan", "25-02-2023", "07");
		assertEquals("User does not exist , Please register : http://localhost:8080/register?name=", status);
	}

	@Test
	void bookMeetingUserDoesNotExist() throws Exception {
		String status = meetingMethod.bookMeeting("awwab", "person", "25-02-2023", "07");
		assertEquals("User does not exist", status);
	}
}
