package org.leve.criteria.restriction;

import org.leve.criteria.Criteria;
import org.leve.criteria.Criterion;

/**
 * Null expression.
 *
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public class NullExpression implements Criterion {

    private final String property;

    /**
     * Create new null expression.
     *
     * @param property property
     */
    protected NullExpression(String property) {
        this.property = property;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return criteriaQuery.getPropertyName(property, criteria) + " is null";
    }
}
