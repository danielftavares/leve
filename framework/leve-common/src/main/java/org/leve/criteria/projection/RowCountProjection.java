package org.leve.criteria.projection;

import org.leve.criteria.Criteria;

/**
 * Row count projection.
 *
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public class RowCountProjection extends SimpleProjection {
    
	/**
	 * Create new row count projection.
	 */
	protected RowCountProjection() {}

        @Override
	public String toSqlString(Criteria criteria, Criteria.CriteriaQuery criteriaQuery) {
		return "count(*)";
	}

}
