package com.geektrust.family.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.geektrust.family.model.processor.FamilyProcessor;
import com.geektrust.family.service.ProcessFileService;
import static com.geektrust.family.constants.CommandsConstants.*;
import static com.geektrust.family.constants.MessageConstants.INVALID_COMMAND;


public class ProcessFileServiceImpl implements ProcessFileService {

	public void processInputFile(FamilyProcessor family, File file, boolean isInputFile) throws FileNotFoundException {
		// Reading the file from the command line args
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			String command = scan.nextLine();
			if (isInputFile) {
				processInputCommand(family, command);
			} else {
				processInitCommand(family, command);
			}
		}
	}

	public void processInputCommand(FamilyProcessor family, String command) {
		String[] commandParams = command.split(" ");
		String result;
		switch (commandParams[0]) {
		case ADD_CHILD:
			result = family.addChild(commandParams[1], commandParams[2], commandParams[3]);
			break;

		case GET_RELATIONSHIP:
			result = family.getRelationship(commandParams[1], commandParams[2]);
			break;

		default:
			result = INVALID_COMMAND;
			break;
		}
		System.out.println("Result of processInputCommand : "+result);
	}

	public void processInitCommand(FamilyProcessor family, String command) {
		String[] commandParams = command.split(";");
		switch (commandParams[0]) {

		case ADD_FAMILY_HEAD:
			family.addFamilyHead(commandParams[1], commandParams[2]);
			break;
			
		case ADD_SPOUSE:
			family.addSpouse(commandParams[1], commandParams[2], commandParams[3]);
			break;

		case ADD_CHILD:
			family.addChild(commandParams[1], commandParams[2], commandParams[3]);
			break;

		default:
			System.out.println(INVALID_COMMAND);
			break;
		}
	}

}
