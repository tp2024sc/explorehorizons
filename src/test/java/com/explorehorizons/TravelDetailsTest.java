package com.explorehorizons;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
class TravelDetailsTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetTravelDescription() {
		TravelDetails details = new TravelDetails(0, "TEST1", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()) ) ; 
		assertEquals("TEST1", details.getTravelDescription());
	}

	@Test
	void testSetTravelDescription() {
		TravelDetails details = new TravelDetails(0, "TEST1", new java.sql.Date(System.currentTimeMillis()), new java.sql.Date(System.currentTimeMillis()) ) ; 
		details.setTravelDescription("TEST2" );
		assertNotNull(details.getTravelDescription()) ;  
		assertEquals("TEST2", details.getTravelDescription());
	}

	@Test
	void testGetStartDate() {
		
		LocalDate localDate = LocalDate.now();
		java.util.Date  date = (java.util.Date) java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		
		System.out.println("Result Date is : "+date);
		TravelDetails details = new TravelDetails(0, "TEST1",  new java.sql.Date(date.getTime())  ,  new java.sql.Date(date.getTime()) ) ; 
		assertNotNull(details.getTravelDescription()) ;  
		assertEquals("TEST1", details.getTravelDescription());
	}

	@Test
	void testSetStartDate() {
		//fail("Not yet implemented");
		LocalDate localDate = LocalDate.now();
		java.util.Date  date = (java.util.Date) java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		TravelDetails details = new TravelDetails(0, "TEST1",  new java.sql.Date(date.getTime())  ,  new java.sql.Date(date.getTime()) ) ; 

		details.setStartDate(new java.sql.Date(date.getTime()));
	
		//System.out.println( " HELOO!!! formatdate.getHours()tedDate " +  date.getHours()  + "  details.getStartDate().getHours  "+  details.getStartDate().getHours() );
		
		assertEquals(date.getYear(), details.getStartDate().getYear());
		assertEquals(date.getMonth(), details.getStartDate().getMonth());
		assertEquals(date.getDay(), details.getStartDate().getDay());
		//assertEquals(date.getHours(), details.getStartDate().getHours());
		//assertEquals(date.getMinutes(), details.getStartDate().getMinutes());
	}

	/*
	 * @Test void testGetEndDate() { fail("Not yet implemented"); }
	 */

	@Test
	void testSetEndDate() {
		LocalDate localDate = LocalDate.now();
		java.util.Date  date = (java.util.Date) java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		
		java.util.Date  date_end = (java.util.Date) java.util.Date.from(localDate.plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant());
		TravelDetails details = new TravelDetails(0, "TEST1",  new java.sql.Date(date.getTime())  ,  new java.sql.Date(date.getTime()) ) ; 

		
		
		
		details.setEndDate(new java.sql.Date(date_end.getTime()));
	
		
		
		assertEquals(date_end.getYear(), details.getEndDate().getYear());
		assertEquals(date_end.getMonth(), details.getEndDate().getMonth());
		assertEquals(date_end.getDay(), details.getEndDate().getDay());
		//assertEquals(date_end.getHours(), details.getEndDate().getHours());
		//assertEquals(date_end.getMinutes(), details.getEndDate().getMinutes());

	}

	@Test
	void testGetStartDateDisplay() {
		//returns the startDate in dd/MM/yyyy 
		//fail("Not yet implemented");
		
		LocalDate localDate = LocalDate.now();
		java.util.Date  date = (java.util.Date) java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		TravelDetails details = new TravelDetails(0, "TEST1",  new java.sql.Date(date.getTime())  ,  new java.sql.Date(date.getTime()) ) ; 
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = localDate.format(pattern);
		
		System.out.println( " formattedDate " +  formattedDate  + "  getStartDateDisplay "+  details.getStartDateDisplay() );
		assertEquals(formattedDate, details.getStartDateDisplay());
	}

	/*
	 * @Test void testSetStartDateDisplay() { fail("Not yet implemented"); }
	 */

	@Test
	void testGetEndDateDisplay() {
		//returns the startDate in dd/MM/yyyy 
		//fail("Not yet implemented");
		
		LocalDate localDate = LocalDate.now();
		java.util.Date  date = (java.util.Date) java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.util.Date  date_end = (java.util.Date) java.util.Date.from(localDate.plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		
		TravelDetails details = new TravelDetails(0, "TEST1",  new java.sql.Date(date.getTime())  ,  new java.sql.Date(date_end.getTime()) ) ; 
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDateEnd = localDate.plusDays(5).format(pattern);
		
		System.out.println( " formattedDateEnd " +  formattedDateEnd  + "  getEndDateDisplay "+  details.getEndDateDisplay() );
		assertEquals(formattedDateEnd, details.getEndDateDisplay());
	}

	/*
	 * @Test void testSetEndDateDisplay() { fail("Not yet implemented"); }
	 */
	
	@Test
	void testSetTraveld() {
		LocalDate localDate = LocalDate.now();
		java.util.Date  date = (java.util.Date) java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		TravelDetails details = new TravelDetails(0, "TEST1",  new java.sql.Date(date.getTime())  ,  new java.sql.Date(date.getTime()) ) ; 
		details.setTravelId(0);
		assertEquals(0, details.getTravelId());
	}
	

}
