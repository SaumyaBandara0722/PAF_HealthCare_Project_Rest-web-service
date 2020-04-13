package com;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import model.OnlineAppointment;

import java.sql.Date;
import java.sql.Time;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

//for json
import com.google.gson.*;
@Path("/Appointment")

public class OnlineAppointmentService {
	OnlineAppointment appObj = new OnlineAppointment();

	@GET
	@Path("/appointment-doctor")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return  appObj.readDetails();
	}
	
	@POST
	@Path("/appointment-doctor")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDetails(@FormParam("patientId") int PatientID,
	 @FormParam("dueDate") String DueDate,
	 @FormParam("scheduleId") int ScheduleId,
	 @FormParam("status") boolean Status) {
		
	String result = null;
	 
	// ==================convert util time to sql time=====================
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			DateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

			try {
				java.util.Date date = (java.util.Date) format.parse(DueDate);
				java.sql.Date sDate = convertUtilToSql(date);

				String output = appObj.insertDetails(PatientID, DueDate, ScheduleId);
				
				result=output;

			} catch (ParseException e) {
				e.printStackTrace();
				result = e.toString();
			}
			return result;
		}
	
	@PUT
	@Path("/appointment-doctor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDetails(String details)
	{
	 String result = null;
	//Convert the input string to a JSON object
	 JsonObject detailsObject = new JsonParser().parse(details).getAsJsonObject();
	//Read the values from the JSON object
	 String AppointmentID = detailsObject.get("AppointmentID").getAsString();
	 String PatientID = detailsObject.get("PatientID").getAsString();
	 String DueDate = detailsObject.get("DueDate").getAsString();
	 String ScheduleId = detailsObject.get("ScheduleId").getAsString();
			 
	 // ==================convert util time to sql time=====================
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			DateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

			try {
				java.util.Date date = (java.util.Date) format.parse(DueDate);
				java.sql.Date sDate = convertUtilToSql(date);

				 String output = appObj.updateDetails(AppointmentID, PatientID, DueDate, ScheduleId);
				
				result=output;

			} catch (ParseException e) {
				e.printStackTrace();
				result = e.toString();
			}
			return result;
		}
	// =================== return sql date and time======================
			private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
				java.sql.Date sDate = new java.sql.Date(uDate.getTime());
				return sDate;
			}
	
	@DELETE
	@Path("/appointment-doctor")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDetails(String details)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(details, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String AppointmentID = doc.select("appointmentId").text();
	 String output = appObj.deleteDetails(AppointmentID);
	return output;
	}
	
	
	//view doctors' schedules
	// get all Schedules
	@GET
	@Path("/schedule-doctor")
	@Produces(MediaType.TEXT_HTML)
	public String readAllSchedules() {
		return appObj.viewAllSchedule();
	}
}
