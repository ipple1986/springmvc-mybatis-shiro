package cn.jufuns.core.mybatis.paginable;

import java.util.List;

@SuppressWarnings("serial")
public class PageInfo<T> implements java.io.Serializable {

	private Pagination pagination;
	private List<T> datalist;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<T> getDatalist() {
		return datalist;
	}

	public void setDatalist(List<T> datalist) {
		this.datalist = datalist;
	}

}
