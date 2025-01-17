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
package com.hack23.sonar.cloudformation;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

/**
 * The Class CloudformationProperties.
 */
public final class CloudformationProperties {

	/** The Constant FILE_SUFFIXES_KEY. */
	public static final String FILE_SUFFIXES_KEY = "sonar.cloudformation.file.suffixes";

	/** The Constant FILE_SUFFIXES_DEFAULT_VALUE. */
	public static final String FILE_SUFFIXES_DEFAULT_VALUE = ".template";

	/**
	 * Instantiates a new cloudformation properties.
	 */
	private CloudformationProperties() {
		// only statics
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public static List<PropertyDefinition> getProperties() {
		return Arrays.asList(
				PropertyDefinition.builder(FILE_SUFFIXES_KEY).defaultValue(FILE_SUFFIXES_DEFAULT_VALUE)
						.category("Cloudformation").name("File Suffixes")
						.description("Comma-separated list of suffixes for files to analyze.").multiValues(true)
						.build(),
				PropertyDefinition.builder(CloudformationConstants.REPORT_FILES_PROPERTY).subCategory("Paths")
						.name(CloudformationConstants.REPORT_FILES_PROPERTY)
						.description("path to the '.nag or .nagscan' files").onQualifiers(Qualifiers.PROJECT).defaultValue("").build());
	}
}
