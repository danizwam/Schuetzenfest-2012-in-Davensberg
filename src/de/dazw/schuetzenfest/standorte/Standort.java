package de.dazw.schuetzenfest.standorte;

import com.google.android.maps.GeoPoint;


public abstract class Standort {

	private int drawableMarker = -1;
	private String text1 = "";
	private String text2 = "";
	private Double latitude = 0.0;
	private Double longitude = 0.0;
	private String ort = "";

	public Standort(int drawableMarker, String ort, String text1, String text2, Double latitude,
			Double longitude) {
		this.drawableMarker = drawableMarker;
		this.ort = ort;
		this.text1 = text1;
		this.text2 = text2;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public GeoPoint getGeoPoint(){
		return new GeoPoint ((int)(getLatitude() * 1E6), (int)(getLongitude() * 1E6));
	}
	
	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getDrawableMarker() {
		return drawableMarker;
	}

	public void setDrawableMarker(int drawableMarker) {
		this.drawableMarker = drawableMarker;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return ort;
	}
	
}
