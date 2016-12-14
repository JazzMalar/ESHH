package ch.ffhs.eshh.wakeuplight.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeviceAction
{
	private String stringId;
	private int id;
	private int n;
	private List<DeviceActionField> fields;

	public DeviceAction()
	{

	}

	public DeviceAction(String stringId, int id, int n, List<DeviceActionField> fields)
	{
		this.stringId = stringId;
		this.id = id;
		this.n = n;
		this.fields = fields;
	}

	public String getStringId()
	{
		return stringId;
	}

	public void setStringId(String stringId)
	{
		this.stringId = stringId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getN()
	{
		return n;
	}

	public void setN(int n)
	{
		this.n = n;
	}

	public List<DeviceActionField> getFields()
	{
		return fields;
	}

	public void setFields(List<DeviceActionField> fields)
	{
		this.fields = fields;
	}

}
