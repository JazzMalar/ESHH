package ch.ffhs.eshh.wakeuplight.model;

import java.sql.Time;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Alarm
{
	private int alarmId;
	private Time startTime;
	private int offset;
	private String repeatPattern;
	private Boolean enabled;
	private int actionGroup;

	public Alarm()
	{

	}

	public Alarm(Time startTime, int offset, String repeatPattern, Boolean enabled, int actionGroup)
	{
		this.startTime = startTime;
		this.offset = offset;
		this.repeatPattern = repeatPattern;
		this.enabled = enabled;
		this.actionGroup = actionGroup;
	}

	public int getAlarmId()
	{
		return alarmId;
	}

	public void setAlarmId(int alarmId)
	{
		this.alarmId = alarmId;
	}

	public Time getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Time startTime)
	{
		this.startTime = startTime;
	}

	public int getOffset()
	{
		return offset;
	}

	public void setOffset(int offset)
	{
		this.offset = offset;
	}

	public String getRepeatPattern()
	{
		return repeatPattern;
	}

	public void setRepeatPattern(String repeatPattern)
	{
		this.repeatPattern = repeatPattern;
	}

	public Boolean getEnabled()
	{
		return enabled;
	}

	public void setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
	}

	public int getActionGroup()
	{
		return actionGroup;
	}

	public void setActionGroup(int actionGroup)
	{
		this.actionGroup = actionGroup;
	}

}
