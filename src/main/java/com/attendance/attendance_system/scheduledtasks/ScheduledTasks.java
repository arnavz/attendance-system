package com.attendance.attendance_system.scheduledtasks;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.attendance.attendance_system.dao.PunchIn;
import com.attendance.attendance_system.dao.PunchInRepository;
import com.attendance.attendance_system.dao.UserLocation;
import com.attendance.attendance_system.dao.UserLocationRepository;

@Component
public class ScheduledTasks {

	@Autowired
	private PunchInRepository punchInRepository;
	
	@Autowired
	private UserLocationRepository locationRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	List<PunchIn> punchedInUsers;
	
	@Scheduled(fixedRate = 1*5*1000) 
	private void fetchAndSaveLocation() {
		  try {
		  punchedInUsers = punchInRepository.findByPunchInStatus("Punched In");
			  int index = 0;
			  while(punchedInUsers.iterator().hasNext()&&index!=punchedInUsers.size()) {
				     UserLocation userLocation = new UserLocation();
					 double latitude = Math.random();
					 double longitude = Math.random();
					 userLocation.setUserId(punchedInUsers.get(index).getUserId());
					 userLocation.setLatitude(latitude);
					 userLocation.setLongitude(longitude);
					 locationRepository.saveAndFlush(userLocation);
					 System.out.println("Location is printed for userId : "+ punchedInUsers.get(index).getUserId() ); 
				   index ++;
			  }
		  }
		  catch (Exception e) {
		  logger.error("No Checked In User to keep track of!");
		  }
		  }
}
