package com.example.tests;

public class ContactData implements Comparable<ContactData>{

	public ContactData setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public ContactData setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public ContactData setPhone(String phone) {
		this.home = phone;
		return this;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public void setNewGroup(String newGroup) {
		this.newGroup = newGroup;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getAddress() {
		return address;
	}

	public String getHome() {
		return home;
	}

	public String getMobile() {
		return mobile;
	}

	public String getWork() {
		return work;
	}

	public String getEmail() {
		return email;
	}

	public String getEmail2() {
		return email2;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public String getNewGroup() {
		return newGroup;
	}

	public String getAddress2() {
		return address2;
	}

	public String getPhone2() {
		return phone2;
	}

	public String id;
	public String firstname;
	public String lastname;
	public String address;
	public String home;
	public String mobile;
	public String work;
	public String email;
	public String email2;
	public String birthDay;
	public String birthMonth;
	public String birthYear;
	public String newGroup;
	public String address2;
	public String phone2;

	public ContactData() {
	  }

	@Override
	public String toString() {
		return "ContactData [firstname=" + firstname + ", lastname=" + lastname + ", phone=" + home + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
/*
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((home == null) ? 0 : home.hashCode());
		result = prime * result
				+ ((lastname == null) ? 0 : lastname.hashCode());
*/
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactData other = (ContactData) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (home == null) {
			if (other.home != null)
				return false;
		} else if (!home.equals(other.home))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		return true;
	}

	@Override
	public int compareTo(ContactData other) {
		int compareLastName = lastname.toLowerCase().compareTo(other.lastname.toLowerCase());
		int compareFirstName = firstname.toLowerCase().compareTo(other.firstname.toLowerCase());
		int comparePhone = home.toLowerCase().compareTo(other.home.toLowerCase());
		if (compareLastName != 0){
			return compareLastName;
		} else {
			if (compareFirstName != 0) {
				return compareFirstName;
			} else {
				return comparePhone;
			}
		}
	}

}