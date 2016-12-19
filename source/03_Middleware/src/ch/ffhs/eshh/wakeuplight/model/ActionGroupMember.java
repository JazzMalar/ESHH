package ch.ffhs.eshh.wakeuplight.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ActionGroupMember
{
	private int actionGroupMemberId;
	private int groupId;
	private int deviceId;
	private int actionId;
	private int offset;

	public ActionGroupMember()
	{

	}

	public ActionGroupMember(int groupId, int deviceId, int actionId, int offset)
	{
		this.groupId = groupId;
		this.deviceId = deviceId;
		this.actionId = actionId;
		this.offset = offset;
	}

	public int getActionGroupMemberId()
	{
		return actionGroupMemberId;
	}

	public void setActionGroupMemberId(int actionGroupMemberId)
	{
		this.actionGroupMemberId = actionGroupMemberId;
	}

	public int getGroupId()
	{
		return groupId;
	}

	public void setGroupId(int groupId)
	{
		this.groupId = groupId;
	}

	public int getDeviceId()
	{
		return deviceId;
	}

	public void setDeviceId(int deviceId)
	{
		this.deviceId = deviceId;
	}

	public int getActionId()
	{
		return actionId;
	}

	public void setActionId(int actionId)
	{
		this.actionId = actionId;
	}

	public int getOffset()
	{
		return offset;
	}

	public void setOffset(int offset)
	{
		this.offset = offset;
	}

}
