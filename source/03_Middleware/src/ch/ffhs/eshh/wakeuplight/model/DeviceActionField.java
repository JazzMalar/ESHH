package ch.ffhs.eshh.wakeuplight.model;

public class DeviceActionField<T>
{
	private String fieldName;
	private T fieldValue;

	public DeviceActionField(String fieldName, T fieldValue)
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

	public T getFieldValue()
	{
		return fieldValue;
	}

	public void setFieldValue(T fieldValue)
	{
		this.fieldValue = fieldValue;
	}

}
