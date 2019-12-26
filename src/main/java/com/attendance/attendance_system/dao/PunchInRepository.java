package com.attendance.attendance_system.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PunchInRepository  extends JpaRepository<PunchIn, Integer> {

	@Query("SELECT u FROM PunchIn u WHERE u.userId = :userId AND u.status= :status")
	public List<PunchIn> findByUserId(@Param("userId") Integer userId,@Param("status") String status);
	
	@Transactional
	@Modifying
	@Query("UPDATE PunchIn SET status = :status WHERE id = :id")
	public int updatePunchInStatus(@Param("id") Integer id, @Param("status") String status);
	
	@Query("SELECT u FROM PunchIn u WHERE u.createdAt = :createdDate AND u.userId= :userId")
	List<PunchIn> findAllByCreatedAtAndUserId(Date createdDate,Integer userId);
	 
	 @Query("SELECT u FROM PunchIn u where u.id = :id and u.status= :status")
	 public List<PunchIn> findByPunchInIdAndStatus(@Param("id") Integer id,@Param("status") String status);
	
	 @Query("SELECT u FROM PunchIn u WHERE u.status = :status")
	 public List<PunchIn> findByPunchInStatus(@Param("status") String status);
	 
}