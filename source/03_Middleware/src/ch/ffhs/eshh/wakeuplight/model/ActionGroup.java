/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Züger & Markus Schenk
 * ActionGroup.java
 * POJO-Objekt für ActionGroups
 */
package ch.ffhs.eshh.wakeuplight.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ActionGroup
{
	private int groupId;
	private List<ActionGroupMember> members;
	private boolean inTime;

	public ActionGroup()
	{

	}

	public ActionGroup(int groupId, List<ActionGroupMember> members)
	{
		this.groupId = groupId;
		this.members = members;
		this.inTime = false;
	}

	public int getGroupId()
	{
		return groupId;
	}

	public void setGroupId(int groupId)
	{
		this.groupId = groupId;
	}

	public List<ActionGroupMember> getMembers()
	{
		return members;
	}

	public void setMembers(List<ActionGroupMember> members)
	{
		this.members = members;
	}

	public void addMember(ActionGroupMember member)
	{
		this.members.add(member);
	}

	public void removeMember(ActionGroupMember member)
	{
		this.members.remove(member);
	}

	public void removeMember(int member)
	{
		this.members.remove(member);
	}

	public boolean isInTime()
	{
		return this.inTime;
	}

	public void setInTime(boolean inTime)
	{
		this.inTime = inTime;
	}

}
