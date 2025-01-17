/*
 * Cloudformation Plugin for SonarQube
 * Copyright (C) 2019 James Pether Sörling
 * james@hack23.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.hack23.sonar.cloudformation.parser;

/**
 * The Class CfnNagScanReport.
 */
public class CfnNagScanReport {

	/** The filename. */
	private String filename;
	
	/** The file results. */
	private CfnNagReport file_results;

	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Sets the filename.
	 *
	 * @param filename the new filename
	 */
	public void setFilename(final String filename) {
		this.filename = filename;
	}
	
	/**
	 * Gets the file results.
	 *
	 * @return the file results
	 */
	public CfnNagReport getFile_results() {
		return file_results;
	}
	
	/**
	 * Sets the file results.
	 *
	 * @param file_results the new file results
	 */
	public void setFile_results(final CfnNagReport file_results) {
		this.file_results = file_results;
	}
	
}
