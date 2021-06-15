package com.geektrust.family.model.util;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.family.enumeration.Gender;
import com.geektrust.family.model.processor.FamilyMemberProcessor;
import static com.geektrust.family.constants.RelationShipConstants.*;
import static com.geektrust.family.constants.MessageConstants.*;

public class FamilyUtils {

	public static FamilyMemberProcessor searchMember(FamilyMemberProcessor head, String memberName) {
		if (memberName == null || head == null) {
			return null;
		}

		FamilyMemberProcessor member = null;
		if (memberName.equals(head.getName())) {
			return head;
		} else if (head.getSpouse() != null && memberName.equals(head.getSpouse().getName())) {
			return head.getSpouse();
		}

		List<FamilyMemberProcessor> childlist = new ArrayList<FamilyMemberProcessor>();
		if (head.getGender() == Gender.FEMALE) {
			childlist = head.getChildren();
		} else if (head.getSpouse() != null) {
			childlist = head.getSpouse().getChildren();
		}

		for (FamilyMemberProcessor m : childlist) {
			member = searchMember(m, memberName);
			if (member != null) {
				break;
			}
		}
		return member;
	}

	public static String getRelationship(FamilyMemberProcessor member, String relationship) {
		String relation = "";
		switch (relationship) {
		case SON:
			relation = member.searchChild(Gender.MALE);
			break;

		case DAUGHTER:
			relation = member.searchChild(Gender.FEMALE);
			break;
		
		case SIBLINGS:
			relation = member.searchSiblings();
			break;
			
		case BROTHER_IN_LAW:
			relation = member.searchInLaws(member, Gender.MALE);
			break;
			
		case SISTER_IN_LAW:
			relation = member.searchInLaws(member, Gender.FEMALE);
			break;
			
		case PATERNAL_AUNT:
			if (member.getFather()!= null) {
				relation = member.getFather().searchRelatives(Gender.FEMALE);
			}
			break;

		case MATERNAL_UNCLE:
			if (member.getMother() != null) {
				relation = member.getMother().searchRelatives(Gender.MALE);
			}
			break;

		case PATERNAL_UNCLE:
			if (member.getFather() != null) {
				relation = member.getFather().searchRelatives(Gender.MALE);
			}
			break;
			
		default:
			relation = NOT_YET_IMPLEMENTED;
			break;
		}

		return relation;
	}

}
