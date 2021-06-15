package com.geektrust.family.model.processor;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.family.enumeration.Gender;

/**
 * 
 * @author kenarayan
 *
 */
public class FamilyMemberProcessor {

	Gender gender;
	String name;
	FamilyMemberProcessor father;
	FamilyMemberProcessor mother;
	FamilyMemberProcessor spouse;

	List<FamilyMemberProcessor> children;

	public FamilyMemberProcessor(String name, Gender gender, FamilyMemberProcessor father,
			FamilyMemberProcessor mother) {
		this.spouse = null;
		this.name = name;
		this.gender = gender;
		this.mother = mother;
		this.father = father;
		this.children = new ArrayList<FamilyMemberProcessor>();
	}

	/**
	 * To add a child member to the family
	 */
	public void addChild(FamilyMemberProcessor child) {
		children.add(child);
	}

	/**
	 * To add a child member to the family
	 */
	public void addSpouse(FamilyMemberProcessor spouse) {
		this.spouse = spouse;
	}

	/**
	 * Search for a child (based on gender)
	 */
	public String searchChild(Gender gender) {
		StringBuilder sb = new StringBuilder("");
		for (FamilyMemberProcessor member : this.children) {
			if (member.gender.equals(gender)) {
				sb.append(member.getName()).append(" ");
			}
		}
		return sb.toString();
	}

	/**
	 * search for siblings
	 * 
	 * @return
	 */
	public String searchSiblings() {
		StringBuilder sb = new StringBuilder("");
		if (this.mother != null) {
			for (FamilyMemberProcessor member : this.mother.children) {
				if (!this.name.equals(member.getName())) {
					sb.append(member.getName()).append("");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * search children with gender & name
	 * 
	 * @return
	 */
	public String searchChildren(Gender gender, String name) {
		StringBuilder sb = new StringBuilder(" ");
		for (FamilyMemberProcessor member : this.children) {
			if (!name.equals(member.getName()) && member.gender == gender) {
				sb.append(member.name).append(" ");
			}
		}
		return sb.toString().trim();
	}

	/**
	 * Search relatives (Uncle/aunt)
	 * 
	 * @return
	 */
	public String searchRelatives(Gender gender) {
		StringBuilder sb = new StringBuilder("");
		if (this.mother != null) {
			for (FamilyMemberProcessor member : this.mother.children) {
				if (!this.getName().equals(member.getName()) && member.gender == gender) {
					sb.append(member.getName()).append(" ");
				}
			}
		}
		return sb.toString().trim();
	}
	
	/**
	 * 
	 * @param member
	 * @param gender
	 * @return
	 */
	public String searchInLaws(FamilyMemberProcessor member, Gender gender) {
		String name = member.getName();
		StringBuilder sb = new StringBuilder("");
		String result = "";
		if (member.getSpouse() != null && member.getSpouse().getMother() != null) {
			result = member.getSpouse().getMother().searchChildren(gender, member.getSpouse().getName());
		}
		sb.append(result);

		result = "";
		if (member.getMother() != null) {
			result = member.getMother().searchChildren(gender, name);
		}
		sb.append(result);

		return sb.toString().trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FamilyMemberProcessor> getChildren() {
		return children;
	}

	public void setChildren(List<FamilyMemberProcessor> children) {
		this.children = children;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public FamilyMemberProcessor getFather() {
		return father;
	}

	public void setFather(FamilyMemberProcessor father) {
		this.father = father;
	}

	public FamilyMemberProcessor getMother() {
		return mother;
	}

	public void setMother(FamilyMemberProcessor mother) {
		this.mother = mother;
	}

	public FamilyMemberProcessor getSpouse() {
		return spouse;
	}

	public void setSpouse(FamilyMemberProcessor spouse) {
		this.spouse = spouse;
	}

}
