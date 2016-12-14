package ch.ffhs.eshh.wakeuplight.model;

import java.time.LocalTime;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Alarm
{
	private LocalTime startTime;
	private int offset;
	private String repeatPatter;
	private Boolean enabled;
	private int actionGroup;

	public Alarm()
	{

	}

	public Alarm(LocalTime startTime, int offset, String repeatPatter, Boolean enabled, int actionGroup)
	{
		this.startTime = startTime;
		this.offset = offset;
		this.repeatPatter = repeatPatter;
		this.enabled = enabled;
		this.actionGroup = actionGroup;
	}

	public LocalTime getStartTime()
	{
		return startTime;
	}

	public void setStartTime(LocalTime startTime)
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

	public String getRepeatPatter()
	{
		return repeatPatter;
	}

	public void setRepeatPatter(String repeatPatter)
	{
		this.repeatPatter = repeatPatter;
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
