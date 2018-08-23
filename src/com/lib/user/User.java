package com.lib.user;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name;
	String id;
	String password;
	String mobile;
	String dept;
	String course;
	String email;
	String address;
	int role;
	String isbn;
	Boolean records=new Boolean(false);
	int flength=0;
	/*Profile*/
	ArrayList<String> title= new ArrayList<String>();
	ArrayList<String> doi= new ArrayList<String>();
	ArrayList<String> dor= new ArrayList<String>();
	ArrayList<String> amt= new ArrayList<String>();
	int length;
	/*Booking*/
	ArrayList<String> bid= new ArrayList<String>();
	ArrayList<String> btitle= new ArrayList<String>();
	ArrayList<String> avail= new ArrayList<String>();
	ArrayList<String> bauthor= new ArrayList<String>();
	ArrayList<String> bpublisher= new ArrayList<String>();
	ArrayList<String> bedition= new ArrayList<String>();
	int blength=0;
	Boolean brecords=new Boolean(false);
	int flag;
	boolean mem;
	int tflag=0;
	Boolean sflag=new Boolean(false);
	String tempId;
	String tempName;
	String tempMobile;
	ArrayList<String> famt= new ArrayList<String>();
	String regid;
	int issue;
	int rtn;
	public int getRtn() {
		return rtn;
	}
	public void setRtn(int rtn) {
		this.rtn = rtn;
	}
	public int getIssue() {
		return issue;
	}
	public void setIssue(int issue) {
		this.issue = issue;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public boolean isMem() {
		return mem;
	}
	public void setMem(boolean mem) {
		this.mem = mem;
	}
	public Boolean getSflag() {
		return sflag;
	}
	public void setSflag(Boolean sflag) {
		this.sflag = sflag;
	}
	public int getTflag() {
		return tflag;
	}
	public void setTflag(int tflag) {
		this.tflag = tflag;
	}
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public String getTempMobile() {
		return tempMobile;
	}
	public void setTempMobile(String tempMobile) {
		this.tempMobile = tempMobile;
	}
	public String getFamt(int i) {
		return famt.get(i);
	}
	public void setFamt(String string) {
		famt.add(string);
	}
	public int getFlength() {
		return flength;
	}
	public void setFlength(int flength) {
		this.flength = flength;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
		
	}
	public Boolean getRecords() {
		return records;
	}
	public void setRecords(Boolean records) {
		this.records = records;
		
	}

	public Boolean getBrecords() {
		return brecords;
	}
	public void setBrecords(Boolean brecords) {
		this.brecords = brecords;
	}
	public int getBlength() {
		return blength;
	}
	public void setBlength(int blength) {
		this.blength = blength;
	}
	
	public String getBid(int i) {
		return bid.get(i);
	}
	public void setBid(String string) {
		bid.add(string);
	
	}
	public String getBauthor(int i) {
		return bauthor.get(i);
	}
	public void setBauthor(String string) {
		bauthor.add(string);

	}
	public String getBpublisher(int i) {
		return bpublisher.get(i);
	}
	public void setBpublisher(String string) {
		bpublisher.add(string);
	}
	public String getBedition(int i) {
		return bedition.get(i);
	}
	public void setBedition(String string) {
		bedition.add(string);
	}
	public String getBtitle(int i) {
		return btitle.get(i);
	}
	public void setBtitle(String string) {
		btitle.add(string);
	}
	public String getAvail(int i) {
		return avail.get(i);
	}
	public void setAvail(String string) {
		avail.add(string);
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getTitle(int i) {
		return title.get(i);
		
	}
	public void setTitle(String string) {
		title.add(string);
	}
	public String getDoi(int i) {
		return doi.get(i);
	}
	public void setDoi(String string) {
		doi.add(string);
	}
	public String getDor(int i) {
		return dor.get(i);
	}
	public void setDor(String string) {
		dor.add(string);
	}
	public String getAmt(int i) {
		return amt.get(i);
	}
	public void setAmt(String string) {
		amt.add(string);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
