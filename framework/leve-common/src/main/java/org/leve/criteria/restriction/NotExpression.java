package org.leve.criteria.restriction;

import org.leve.criteria.Criteria;
import org.leve.criteria.Criterion;

/**
 * Not expression.
 *
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public class NotExpression implements Criterion {

    private Criterion criterion;

    /**
     * Create new not expression from given one.
     *
     * @param criterion criterion
     */
    protected NotExpression(Criterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
        return "not (" + criterion.toSqlString(criteria, criteriaQuery) + ')';
    }
}
