package com.vaidya.request;
import java.time.LocalDate;
import java.time.LocalTime;

public class SlotRequest {
    private LocalTime startTime;
    private LocalTime endTime;
    private String slotRange;
    private Long userId;
    private LocalDate date;
    
    
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public String getSlotRange() {
		return slotRange;
	}
	public void setSlotRange(String slotRange) {
		this.slotRange = slotRange;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "SlotRequest [startTime=" + startTime + ", endTime=" + endTime + ", slotRange=" + slotRange + ", userId="
				+ userId + ", date=" + date + "]";
	}
	public SlotRequest(LocalTime startTime, LocalTime endTime, String slotRange, Long userId, LocalDate date) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.slotRange = slotRange;
		this.userId = userId;
		this.date = date;
	}
	public SlotRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
