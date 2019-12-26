package com.attendance.attendance_system.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Integer>{
	
	@Query("SELECT u FROM UserLocation u WHERE u.createdAt =:date AND u.userId = :userId")
	public List<UserLocation> findByUserIdAndDate(@Param("userId") Integer userId, @Param("date") Date date);
	
}
