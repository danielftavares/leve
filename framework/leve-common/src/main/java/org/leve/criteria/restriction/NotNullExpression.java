package org.leve.criteria.restriction;

import org.leve.criteria.Criteria;
import org.leve.criteria.Criterion;

/**
 * Not null expression.
 *
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public class NotNullExpression implements Criterion {

    private final String property;

    /**
     * Create new not null expression.
     *
     * @param property property
     */
    protected NotNullExpression(String property) {
        this.property = property;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return criteriaQuery.getPropertyName(property, criteria) + " is not null";
    }
}
