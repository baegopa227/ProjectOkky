package com.okky.vo;

import java.util.Date;

public class UserInfoVO {

	private int id;
	private String name;
	private String nickname;
	private int sex;
	private String uno;
	private String phone;
	private String email;
	private String address;
	private String emailConfirmed;
	private Date cdate;
	private String emailConfirmKey;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getUno() {
		return uno;
	}
	public void setUno(String uno) {
		this.uno = uno;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getEmailConfirmed() {
		return emailConfirmed;
	}
	public void setEmailConfirmed(String emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getEmailConfirmKey() {
		return emailConfirmKey;
	}
	public void setEmailConfirmKey(String emailConfirmKey) {
		this.emailConfirmKey = emailConfirmKey;
	}
	@Override
	public String toString() {
		return "UserInfoVO [id=" + id + ", name=" + name + ", nickname=" + nickname + ", sex=" + sex + ", uno=" + uno
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", emailConfirmed="
				+ emailConfirmed + ", cdate=" + cdate + ", emailConfirmKey=" + emailConfirmKey + "]";
	}
	
	
	
}
