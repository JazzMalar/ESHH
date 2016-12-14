package ch.ffhs.eshh.wakeuplight.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Device
{
	private String name;
	private String stringId;
	private Boolean gpio;

	public Device()
	{

	}

	public Device(String name, String stringId, Boolean gpio)
	{
		this.name = name;
		this.stringId = stringId;
		this.gpio = gpio;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStringId()
	{
		return stringId;
	}

	public void setStringId(String stringId)
	{
		this.stringId = stringId;
	}

	public Boolean getGpio()
	{
		return gpio;
	}

	public void setGpio(Boolean gpio)
	{
		this.gpio = gpio;
	}

}
