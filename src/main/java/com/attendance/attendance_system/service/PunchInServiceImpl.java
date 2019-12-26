package com.attendance.attendance_system.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.attendance_system.dao.PunchIn;
import com.attendance.attendance_system.dao.PunchInRepository;
import com.attendance.attendance_system.dao.User;
import com.attendance.attendance_system.dao.UserLocation;
import com.attendance.attendance_system.dao.UserLocationRepository;
import com.attendance.attendance_system.dao.UserRepository;


@Service
public class PunchInServiceImpl implements PunchInService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PunchInRepository punchInRepository;
	
	@Autowired
	private UserLocationRepository locationRepository;
	

	private static final Logger logger = LoggerFactory.getLogger(PunchInServiceImpl.class);
	
	List<PunchIn> punchedInUsers;

	@Override
	public void punchInRequest(Integer userId) throws ParseException
	{
		logger.info("Request received  for User with UserId : {} ", userId);
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()==false)
		{
			
			throw new RuntimeException("User not registered or valid to Punch In");
		}
		punchedInUsers =punchInRepository.findByUserId(userId,"Punched In");
		if(punchedInUsers!=null && punchedInUsers.isEmpty()==false)
		{
			throw new RuntimeException("User Status already marked as Punched In ");
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
	    String s=dateFormat.format(calendar.getTime());
	    String[] dateAndTime=s.split(" ");
	    punchedInUsers=punchInRepository.findAllByCreatedAtAndUserId(new SimpleDateFormat("yyyy-MM-dd").parse(dateAndTime[0]), userId);
	    if(punchedInUsers!=null && punchedInUsers.isEmpty()==false)
		{
			throw new RuntimeException("Currently, we do not allow to let a user punch In twice a day!");
		} 
		
		PunchIn punchIn=new PunchIn();
		punchIn.setUserId(userId);
		punchIn.setStatus("Punched In");
		punchInRepository.saveAndFlush(punchIn);
	}
	
	@Override
	public void updateStatus(Integer userId) {
		
		logger.info("Request received to CheckOut User for UserId : {} ", userId);
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()==false)
		{
			throw new RuntimeException("User does not exists");
		}
		 punchedInUsers =punchInRepository.findByUserId(userId,"Punched In");
		if(punchedInUsers==null || punchedInUsers.isEmpty()==true)
		{
			throw new RuntimeException("User cannot punch out without punching in!");
		}
		PunchIn checkIn=punchedInUsers.get(0);
		punchInRepository.updatePunchInStatus(checkIn.getId(), "Punched Out");
	}
	
	
	@Override
	public void userRegistration(String name,String email) {
		
		logger.info("Request received to register user with email Id : {} ", email);
		List<User> userList=userRepository.findByEmail(email);
		if(userList!=null && userList.isEmpty()==false)
		{
			throw new RuntimeException("User Already registered with this email Id");
		}
		User user =new User();
		user.setEmail(email);
		user.setUserName(name);
		
		userRepository.saveAndFlush(user);
		
	}
    @Override
	public List<UserLocation> fetchGeoLocationRecord(Integer userId, String dateInString) throws ParseException {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = formatter.parse(dateInString);
    	List<UserLocation> userLocationList = locationRepository.findByUserIdAndDate(userId, date);
    	
    	return userLocationList;
    	
    	
    	
    	
    	
	}
	
}
