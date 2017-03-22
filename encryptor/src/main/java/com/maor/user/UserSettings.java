package com.maor.user;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.maor.tools.CipherType;

import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "UserSettings")
@XmlAccessorType (XmlAccessType.FIELD)
public class UserSettings {
	
	
	private CipherType type , subType1, subType2;
	
	public UserSettings( )
	{
		this.setType(null);
	}
	public UserSettings(CipherType type )
	{
		this.setType(type);
	}
	
	public UserSettings(CipherType type , CipherType subtype)
	{
		this.setType(type);
		this.setSubType1(subtype);
	}
	
	public UserSettings(CipherType type , CipherType subtype , CipherType subtype2)
	{
		this.setType(type);
		this.setSubType1(subtype);
		this.setSubType2(subtype2);
	}

	public CipherType getType() {
		return type;
	}

	public void setType(CipherType type) {
		this.type = type;
	}

	public CipherType getSubType1() {
		return subType1;
	}

	public void setSubType1(CipherType subType1) {
		this.subType1 = subType1;
	}

	public CipherType getSubType2() {
		return subType2;
	}

	public void setSubType2(CipherType subType2) {
		this.subType2 = subType2;
	}

}
