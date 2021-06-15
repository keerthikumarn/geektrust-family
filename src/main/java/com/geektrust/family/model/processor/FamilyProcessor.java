package com.geektrust.family.model.processor;

import static com.geektrust.family.constants.RelationShipConstants.FEMALE;

import com.geektrust.family.constants.MessageConstants;
import com.geektrust.family.enumeration.Gender;
import com.geektrust.family.model.util.FamilyUtils;

public class FamilyProcessor {

	private FamilyMemberProcessor familyHead;

	public void addFamilyHead(String name, String gender) {
		Gender g = (FEMALE.equals(gender)) ? Gender.MALE : Gender.FEMALE;
		familyHead = new FamilyMemberProcessor(name, g, null, null);
	}

	/**
	 * 
	 * @param name
	 * @param spouseName
	 * @param gender
	 */
	public void addSpouse(String name, String spouseName, String gender) {
		FamilyMemberProcessor member = FamilyUtils.searchMember(familyHead, name);
		if (member != null && member.spouse == null) {
			Gender g = (FEMALE.equals(gender)) ? Gender.MALE : Gender.FEMALE;
			FamilyMemberProcessor sp = new FamilyMemberProcessor(spouseName, g, null, null);
			sp.addSpouse(member);
			member.addSpouse(sp);
		}
	}

	/**
	 * to add a child
	 * 
	 * @param motherName
	 * @param childName
	 * @param gender
	 * @return
	 */
	public String addChild(String motherName, String childName, String gender) {
		String message = "";
		FamilyMemberProcessor member = FamilyUtils.searchMember(familyHead, motherName);
		if (member == null) {
			message = MessageConstants.PERSON_NOT_FOUND;
		} else if (childName == null || gender == null) {
			message = MessageConstants.CHILD_ADDITION_FAILED;
		} else if (member.gender == Gender.FEMALE) {
			Gender g = (FEMALE.equals(gender)) ? Gender.FEMALE : Gender.MALE;
			FamilyMemberProcessor child = new FamilyMemberProcessor(childName, g, member.spouse, member);
			member.addChild(child);
			message = MessageConstants.CHILD_ADDITION_SUCCEEDED;
		} else {
			message = MessageConstants.CHILD_ADDITION_FAILED;
		}
		return message;
	}

	/**
	 * 
	 * @param person
	 * @param relationship
	 * @return
	 */
	public String getRelationship(String person, String relationship) {
		String relations;
		FamilyMemberProcessor member = FamilyUtils.searchMember(familyHead, person);
		if (member == null) {
			relations = MessageConstants.PERSON_NOT_FOUND;
		} else if (relationship == null) {
			relations = MessageConstants.PROVIDE_VALID_RELATION;
		} else {
			relations = FamilyUtils.getRelationship(member, relationship);
		}
		return relations;
	}

}
