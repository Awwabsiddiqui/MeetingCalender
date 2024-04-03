package com.example.springrest.method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.example.springrest.entity.Ent;

@Component
public class meetingMethod {

	public static Connection DBconn() throws Exception {
//replace meetingcalender with b6bmryp3uei8fahjfmde.meetingcalender
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mainer", "root", "16200913");
//		Connection conn = DriverManager.getConnection("jdbc:mysql://b6bmryp3uei8fahjfmde-mysql.services.clever-cloud.com/b6bmryp3uei8fahjfmde", "usiodlmzha0y5xb9","AAYuYbhQMSp3ruuRxysA");
		return conn;
	}

	public static String register(String name) throws Exception {
		Connection conn = null;
		try {
			conn = DBconn();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("Insert into meetingcalender(name,meeting) values ('" + name + "' , '')");
		} catch (Exception e) {
//			e.printStackTrace();
			return e.getLocalizedMessage();
		} finally {
			conn.close();
		}

		return "Registered Successfully";
	}

	public static String registerHibernate(String name) {
		try {
			new DOMeeting().doRegister(name);
		} catch (Exception e) {
			e.printStackTrace();
			return "Some Error";
		}
		return "Registered Successfully";
	}
	
	public static String registerHibernateEntity(Ent Ent) {
		String status="";
		try {
			 status = new DOMeeting().doRegisterEntity(Ent);
		} catch (Exception e) {
			e.printStackTrace();
			return "Some Error";
		}
		return status;
	}

	public static String updateColumnByName(String name) {
		try {
			new DOMeeting().updateEnt(name);
		} catch (Exception e) {
			e.printStackTrace();
			return "Some Error";
		}
		return "Updated Successfully";
	}

	public static String bookMeeting(String from, String to, String Date, String Time) throws Exception {

		String meetingTime = Date + "|" + Time;

		Connection conn = DBconn();
		Statement stmt = conn.createStatement();

		String meetingValTo = "";
		String[] meetingArrTo = null;

		String meetingValFrom = "";
		String[] meetingArrFrom = null;
		try {
			ResultSet rsTo = stmt.executeQuery("SELECT * FROM  meetingcalender WHERE name='" + to + "'");
			if (rsTo.next()) {
				meetingValTo = rsTo.getString("meeting");
				meetingArrTo = meetingValTo.split("\\,");
			}

			ResultSet rsFrom = stmt.executeQuery("SELECT * FROM  meetingcalender WHERE name='" + from + "'");
			if (rsFrom.next()) {
				meetingValFrom = rsFrom.getString("meeting");
				meetingArrFrom = meetingValFrom.split("\\,");
			}

			if (meetingArrFrom == null) {
				return "User does not exist , Please register : http://localhost:8080/register?name=";
			} else if (meetingArrTo == null) {
				return "User does not exist";
			} else {

				for (int i = 0; i < meetingArrTo.length; i++) {
					if (meetingArrTo[i].contains(meetingTime)) {
						return to + " has a meeting already booked with : " + meetingArrTo[i].split("@")[0];
					}
				}
				for (int i = 0; i < meetingArrFrom.length; i++) {
					if (meetingArrFrom[i].contains(meetingTime)) {
						return "you have a meeting already booked with : " + meetingArrFrom[i].split("@")[0];
					}
				}
				stmt.executeUpdate("UPDATE meetingcalender SET meeting = '" + meetingValTo + from + "@" + meetingTime
						+ ",' WHERE name = '" + to + "'");
				stmt.executeUpdate("UPDATE meetingcalender SET meeting = '" + meetingValFrom + to + "@" + meetingTime
						+ ",' WHERE name = '" + from + "'");

			}

			return "meeting booked successfully";
		} finally {
			conn.close();
		}
	}
//	public static void main(String args[]) throws Exception {
////		System.out.println(bookMeeting("awwab", "faizan", "25-02-2023|08"));
////		System.out.println(register("awwab"));
//	}
}
