package org.leve.criteria.restriction;

import org.leve.criteria.Criteria;
import org.leve.criteria.Criterion;

/**
 * Simple expression.
 *
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public class SimpleExpression implements Criterion {

    private final String property;
    private final Object value;
    private final String operator;

    /**
     * Create new simple expression.
     *
     * @param property property
     * @param value value
     * @param operator operator
     */
    protected SimpleExpression(String property, Object value, String operator) {
        this.property = property;
        this.value = value;
        this.operator = operator;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        criteriaQuery.setParam(value);
        return criteriaQuery.getPropertyName(property, criteria) + operator + "?";
    }
}
