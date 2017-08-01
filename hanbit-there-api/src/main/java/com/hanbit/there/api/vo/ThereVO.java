package com.hanbit.there.api.vo;

import java.util.List;

public class ThereVO {

	private String id;
	private String groupId;
	private String name;
	private String nameEn;
	private String background;
	private String summary;
	private String timezone;
	
	private List<InfoVO> areaInfo;
	private LocationVO location;
	private List<TrafficVO> traffics;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public List<InfoVO> getAreaInfo() {
		return areaInfo;
	}
	public void setAreaInfo(List<InfoVO> areaInfo) {
		this.areaInfo = areaInfo;
	}
	public LocationVO getLocation() {
		return location;
	}
	public void setLocation(LocationVO location) {
		this.location = location;
	}
	public List<TrafficVO> getTraffics() {
		return traffics;
	}
	public void setTraffics(List<TrafficVO> traffics) {
		this.traffics = traffics;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}