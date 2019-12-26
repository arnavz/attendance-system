package com.attendance.attendance_system.controller;

import java.text.ParseException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.attendance_system.dao.UserLocation;
import com.attendance.attendance_system.service.PunchInServiceImpl;

@RestController
public class AttendanceController {
	
	@Autowired
	private PunchInServiceImpl punchInServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);
	
	 
	 @PostMapping(value = "/remoteAttendance/punchIn/{userId}")
		public ResponseEntity<Void> punchInRequest(@PathVariable("userId") Integer userId) throws ParseException {
			logger.info("PunchIn request received  for userId : {} ", userId);
			punchInServiceImpl.punchInRequest(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	 
	 @PostMapping(value = "/remoteAttendance/punchOut/{userId}")
		public ResponseEntity<Void> updateStatus(@PathVariable("userId") Integer userId) throws ParseException {
			logger.info("PunchOut request received  for userId  : {} ", userId);
			punchInServiceImpl.updateStatus(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	 
	 
	 @PostMapping(value = "/remoteAttendance/registration/{email}/{name}")
		public ResponseEntity<Void> userRegistration(@PathVariable("email") String email,@PathVariable("name") String name) throws ParseException {
			logger.info("Request received to register user for email : {} ", email);
			punchInServiceImpl.userRegistration(name,email);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	 
	 @PostMapping(value = "/remoteAttendance/track/{userId}/{date}")
		public List<UserLocation> fetchGeoLocationRecord(@PathVariable("userId") Integer userId,@PathVariable("date") String date) throws ParseException {
			logger.info("Request received to fetch records on {} for userId : {} ", date, userId);
			return punchInServiceImpl.fetchGeoLocationRecord(userId, date);
		}
		
}
