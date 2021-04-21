package com.ideas2it.employeeManagementSystem.employee.model;

/**
 * To store the address details of employee 
 * @author Tamilarasi Shanmugasundaram
 * created 02-03-2021
 */
public class Address {
    public String doorNumber;
    public String streetNumber;
    public String city;
    public String district;
    public String state;
    public String country;
    public long pincode;
    int employeeId;
    int id;
    boolean isDelete = false;
    boolean isPermanent = false;

    public Address() {
    }

    public Address(String doorNumber, String streetNumber, String city, String district, String state, String country, long pincode, boolean isPermanent) {
        this.doorNumber = doorNumber;
        this.streetNumber = streetNumber;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
	this.isPermanent = isPermanent;
    }

    public boolean getIsDelete() {
        return isDelete;
    }
	
	public boolean getIsPermanent() {
        return isPermanent;
    }
	
    public String getDoorNumber() {
        return doorNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public long getPincode() {
        return pincode;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPincode(long pincode) {
        this.pincode = pincode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployeeId(int id) {
        this.employeeId = id;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
	
    public void setIsPermanent(boolean isPermanent) {
        this.isPermanent = isPermanent;
    }
	
    @Override
  
    public String toString() {
     
        return "Address : "  + this.doorNumber +"  "  + this.streetNumber +"  "  + this.city  +"  "  + this.district  +"  " + 
                this.state +"  " + this.country  +"  " + this.pincode;
 
    }
}


