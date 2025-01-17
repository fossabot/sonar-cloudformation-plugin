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

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

/**
 * The Class CloudformationRulesDefinition.
 */
public final class CloudformationRulesDefinition implements RulesDefinition {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Loggers.get(CloudformationRulesDefinition.class);

	/** The Constant PATH_TO_RULES_XML. */
	private static final String PATH_TO_RULES_XML = "/cloudformation-rules.xml";

	/** The Constant KEY. */
	protected static final String KEY = "repo";

	/** The Constant NAME. */
	protected static final String NAME = "repository";

	/** The Constant REPO_KEY. */
	public static final String REPO_KEY = CloudformationLanguage.KEY + "-" + KEY;

	/** The Constant REPO_NAME. */
	protected static final String REPO_NAME = CloudformationLanguage.NAME + "-" + NAME;

	/**
	 * Rules definition file path.
	 *
	 * @return the string
	 */
	protected String rulesDefinitionFilePath() {
		return PATH_TO_RULES_XML;
	}

	/**
	 * Define rules for language.
	 *
	 * @param context        the context
	 * @param repositoryKey  the repository key
	 * @param repositoryName the repository name
	 * @param languageKey    the language key
	 */
	private void defineRulesForLanguage(final Context context, final String repositoryKey, final String repositoryName,
			final String languageKey) {
		final NewRepository repository = context.createRepository(repositoryKey, languageKey).setName(repositoryName);

		final InputStream rulesXml = this.getClass().getResourceAsStream(rulesDefinitionFilePath());
		if (rulesXml != null) {
			final RulesDefinitionXmlLoader rulesLoader = new RulesDefinitionXmlLoader();
			rulesLoader.load(repository, rulesXml, StandardCharsets.UTF_8.name());

			for (final NewRule newRule : repository.rules()) {

				addNewRule(newRule);
			}
		}
		repository.done();

	}

	/**
	 * Adds the new rule.
	 *
	 * @param newRule the new rule
	 */
	private void addNewRule(final NewRule newRule) {
		try {
			final Set<String> tags = (Set<String>) FieldUtils.readField(newRule, "tags", true);
			for (final String tag : tags) {

				if (tag.contains("cweid-")) {
					newRule.addCwe(Integer.parseInt(tag.replace("cweid-", "")));
				}

				if (tag.contains("owasp-")) {
					newRule.addOwaspTop10(OwaspTop10.valueOf(tag.replace("owasp-", "").toUpperCase()));
				}
			}
		} catch (final IllegalAccessException e) {
			LOGGER.warn("Problem parsing security tags", e);
		}
	}

	/**
	 * Define.
	 *
	 * @param context the context
	 */
	@Override
	public void define(final Context context) {
		defineRulesForLanguage(context, REPO_KEY, REPO_NAME, CloudformationLanguage.KEY);
	}

}
