package com.geektrust.family.service;

import java.io.File;
import java.io.FileNotFoundException;

import com.geektrust.family.model.processor.FamilyProcessor;

/**
 * Process file service
 * 
 * @author kenarayan
 *
 */
public interface ProcessFileService {

	public void processInputFile(FamilyProcessor family, File file, boolean isInputFile) throws FileNotFoundException;

	public void processInputCommand(FamilyProcessor family, String command);

	public void processInitCommand(FamilyProcessor family, String command);
}
