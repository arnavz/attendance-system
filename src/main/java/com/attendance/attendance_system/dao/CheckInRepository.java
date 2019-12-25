package com.attendance.attendance_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository  extends JpaRepository<CheckIn, Integer> {

	
}