package com.attendance.attendance_system.service;

import java.text.ParseException;
import java.util.List;

import com.attendance.attendance_system.dao.UserLocation;

public interface PunchInService {
	
	public void punchInRequest(Integer userId)  throws ParseException ;
	
	public void updateStatus(Integer userId)  ;
	
	public void userRegistration(String name,String email);
	
	public List<UserLocation> fetchGeoLocationRecord(Integer userId, String date) throws ParseException;
}
