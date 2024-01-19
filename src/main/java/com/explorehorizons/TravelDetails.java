package com.explorehorizons;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class TravelDetails {
String travelDescription ;
Date startDate; 
Date endDate;
int travelId; 

String startDateDisplay ; 

String endDateDisplay ; 

public TravelDetails(int travelId, String travelDescription, Date startDate, Date endDate) {
	super();
	this.travelId = travelId;
	this.travelDescription = travelDescription;
	this.startDate = startDate;
	this.endDate = endDate;
}
public int getTravelId() {
	return travelId;
}
public void setTravelId(int travelId) {
	this.travelId = travelId;
}
public String getTravelDescription() {
	return travelDescription;
}
public void setTravelDescription(String travelDescription) {
	this.travelDescription = travelDescription;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
} 

public String getStartDateDisplay() 
{
	java.time.LocalDate localDateStart = startDate.toLocalDate();
	//System.out.println(tempFromDate);
	    
	DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String formattedDate = localDateStart.format(pattern);
	this.startDateDisplay = formattedDate ; 
	System.out.println("startDateDisplay" + startDateDisplay);
	return this.startDateDisplay;
}

public void setStartDateDisplay(String startDateDisplay) {
	this.startDateDisplay = startDateDisplay;
}

public String getEndDateDisplay() {
	
	java.time.LocalDate localDateEnd = endDate.toLocalDate();
	
	DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String formattedDate = localDateEnd.format(pattern);
	this.endDateDisplay = formattedDate ; 
	System.out.println("endDateDisplay" + endDateDisplay);
	    
	return endDateDisplay;
}

public void setEndDateDisplay(String endDateDisplay) {
	this.endDateDisplay = endDateDisplay;
}


}
