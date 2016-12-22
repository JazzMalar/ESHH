package ch.ffhs.eshh.wakeuplight.model;

public class DeviceActionField
{
	private String fieldName;
	private String fieldValue;

	public DeviceActionField(String fieldName, String fieldValue)
	{
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	public String getFieldValue()
	{
		return fieldValue;
	}

	public void setFieldValue(String fieldValue)
	{
		this.fieldValue = fieldValue;
	}

}