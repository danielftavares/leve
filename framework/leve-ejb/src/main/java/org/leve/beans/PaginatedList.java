package org.leve.beans;

import java.util.List;

public class PaginatedList<T> {

	private List<T> data;
	private Long rowCount;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Long getRowCount() {
		return rowCount;
	}
	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}
	
	
	
}
