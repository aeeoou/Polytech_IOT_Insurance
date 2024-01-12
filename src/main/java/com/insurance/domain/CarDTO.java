package com.insurance.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO
{
	private Long idx;                // 글 번호??(PK)
	
	private String carInsco;         // 보험회사
	private String carInstype;       // 보험종목
	private String carNumber;        // 차량번호
	private String carContractor;    // 계약자
	private String carInsured;       // 피보험자

	private String carName;          // 차량명
	private String carCode;          // 차명코드
	private String modelYear;        // 차량년식
	private String drivingRes;       // 운전자&연령한정
	private String drivingExp;       // 가입경력자
	private String drivingLow;       // 최저연령자
	private String specialAgr;       // 특약
	private String identicalSec;     // 동일증권
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getCarInsco() {
		return carInsco;
	}
	public void setCarInsco(String carInsco) {
		this.carInsco = carInsco;
	}
	public String getCarInstype() {
		return carInstype;
	}
	public void setCarInstype(String carInstype) {
		this.carInstype = carInstype;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getCarContractor() {
		return carContractor;
	}
	public void setCarContractor(String carContractor) {
		this.carContractor = carContractor;
	}
	public String getCarInsured() {
		return carInsured;
	}
	public void setCarInsured(String carInsured) {
		this.carInsured = carInsured;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getModelYear() {
		return modelYear;
	}
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}
	public String getDrivingRes() {
		return drivingRes;
	}
	public void setDrivingRes(String drivingRes) {
		this.drivingRes = drivingRes;
	}
	public String getDrivingExp() {
		return drivingExp;
	}
	public void setDrivingExp(String drivingExp) {
		this.drivingExp = drivingExp;
	}
	public String getDrivingLow() {
		return drivingLow;
	}
	public void setDrivingLow(String drivingLow) {
		this.drivingLow = drivingLow;
	}
	public String getSpecialAgr() {
		return specialAgr;
	}
	public void setSpecialAgr(String specialAgr) {
		this.specialAgr = specialAgr;
	}
	public String getIdenticalSec() {
		return identicalSec;
	}
	public void setIdenticalSec(String identicalSec) {
		this.identicalSec = identicalSec;
	}
	
	@Override
	public String toString() {
		return "CarDTO [idx=" + idx + ", carInsco=" + carInsco + ", carInstype=" + carInstype + ", carNumber="
				+ carNumber + ", carContractor=" + carContractor + ", carInsured=" + carInsured + ", carName=" + carName
				+ ", carCode=" + carCode + ", modelYear=" + modelYear + ", drivingRes=" + drivingRes + ", drivingExp="
				+ drivingExp + ", drivingLow=" + drivingLow + ", specialAgr=" + specialAgr + ", identicalSec="
				+ identicalSec + "]";
	}
	
}
