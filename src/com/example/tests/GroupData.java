package com.example.tests;

public class GroupData implements Comparable<GroupData>{
	public String nameGroup;
	public String header;
	public String footer;

	public GroupData(String nameGroup, String header, String footer) {
		this.nameGroup = nameGroup;
		this.header = header;
		this.footer = footer;
	}

	public GroupData() {
	}

	@Override
	public String toString() {
		return "GroupData [nameGroup=" + nameGroup + ", header=" + header
				+ ", footer=" + footer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
/*		result = prime * result
				+ ((nameGroup == null) ? 0 : nameGroup.hashCode());
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
		GroupData other = (GroupData) obj;
		if (nameGroup == null) {
			if (other.nameGroup != null)
				return false;
		} else if (!nameGroup.equals(other.nameGroup))
			return false;
		return true;
	}

	@Override
	public int compareTo(GroupData other) {
		return nameGroup.toLowerCase().compareTo(other.nameGroup.toLowerCase());
	}



}