/*
 *   Copyright 2026 Spicule Ltd
 *   Apache License, Version 2.0.
 */
package org.saiku.query.metadata;

import org.olap4j.impl.Named;

/**
 * A reusable MDX named set that can be added to a {@link org.saiku.query.Query}
 * and emitted as a {@code WITH SET [name] AS (expression)} clause ahead of
 * the SELECT, alongside any {@link CalculatedMember}/{@link CalculatedMeasure}
 * {@code WITH MEMBER} clauses (saiku-query#825, saiku#775).
 *
 * <p>Unlike {@link CalculatedMember}, a named set isn't scoped to a single
 * hierarchy — it's tracked at the {@code Query} level, the same way
 * {@link CalculatedMeasure} is.
 */
public class NamedSet implements Named {

	private final String name;
	private final String expression;
	private final String caption;

	public NamedSet(String name, String expression) {
		this(name, expression, null);
	}

	public NamedSet(String name, String expression, String caption) {
		this.name = name;
		this.expression = expression;
		this.caption = caption;
	}

	public String getName() {
		return name;
	}

	public String getExpression() {
		return expression;
	}

	public String getCaption() {
		return caption != null ? caption : name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime * 1 + ((name == null) ? 0 : name.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		NamedSet other = (NamedSet) obj;
		if (name == null) {
			return other.name == null;
		}
		return name.equals(other.name);
	}
}
