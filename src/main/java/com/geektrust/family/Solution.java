package com.geektrust.family;

import java.io.File;
import java.io.FileNotFoundException;

import com.geektrust.family.model.processor.FamilyProcessor;
import com.geektrust.family.service.ProcessFileService;
import com.geektrust.family.service.impl.ProcessFileServiceImpl;

public class Solution {

	public static void main(String[] args) {
		FamilyProcessor familyProcessor = new FamilyProcessor();
		Solution solution = new Solution();
		try {
			solution.initFileToProcess(familyProcessor, args[0], false);
			solution.initFileToProcess(familyProcessor, args[1], true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initFileToProcess(FamilyProcessor familyProcessor, String fileName, boolean isInputFile)
			throws FileNotFoundException {
		File file = new File(fileName);
		ProcessFileService fileProcessor = new ProcessFileServiceImpl();
		fileProcessor.processInputFile(familyProcessor, file, isInputFile);

	}

}
