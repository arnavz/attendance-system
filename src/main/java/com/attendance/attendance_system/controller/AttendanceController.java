package com.attendance.attendance_system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {
	
	 @RequestMapping("/hello")
	   public String UserPunchIn() {
	       return "User Punched In";
	   }


}
