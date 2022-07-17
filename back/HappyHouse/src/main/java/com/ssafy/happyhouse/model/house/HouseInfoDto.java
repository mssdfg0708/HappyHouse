package com.ssafy.happyhouse.model.house;

public class HouseInfoDto {

	private long aptCode;
	private String apartmentName;
	private String dong;
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private long buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String recentPrice;

	public long getAptCode() {
		return aptCode;
	}


	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}


	public String getApartmentName() {
		return apartmentName;
	}


	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}


	public String getDong() {
		return dong;
	}


	public void setDong(String dong) {
		this.dong = dong;
	}


	public String getDongCode() {
		return dongCode;
	}


	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}


	public String getSidoName() {
		return sidoName;
	}


	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}


	public String getGugunName() {
		return gugunName;
	}


	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}


	public long getBuildYear() {
		return buildYear;
	}


	public void setBuildYear(long buildYear) {
		this.buildYear = buildYear;
	}


	public String getJibun() {
		return jibun;
	}


	public void setJibun(String jibun) {
		this.jibun = jibun;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getLng() {
		return lng;
	}


	public void setLng(String lng) {
		this.lng = lng;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getRecentPrice() {
		return recentPrice;
	}


	public void setRecentPrice(String recentPrice) {
		this.recentPrice = recentPrice;
	}


	@Override
	public String toString() {
		return "HouseInfoDto [aptCode=" + aptCode + ", apartmentName=" + apartmentName + ", dong=" + dong
				+ ", dongCode=" + dongCode + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", buildYear="
				+ buildYear + ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng + ", img=" + img + ", recentPrice="
				+ recentPrice + "]";
	}
	
}
