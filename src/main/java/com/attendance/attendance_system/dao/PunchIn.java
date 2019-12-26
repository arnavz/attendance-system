package com.attendance.attendance_system.dao;



import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="punch_in")
public class PunchIn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "status")
	private String status;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "created_at")
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at")
	private Date updatedAt;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Override
	public String toString() {
		return "CheckIn [id=" + id + ", status=" + status + ", userId=" + userId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}


	

}
