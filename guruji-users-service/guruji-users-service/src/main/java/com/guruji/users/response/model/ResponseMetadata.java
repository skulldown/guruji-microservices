package com.guruji.users.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMetadata {

	private Long limit;
	private Long page;
	private Long totalResults;

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public ResponseMetadata(Long limit, Long page, Long totalResults) {
		super();
		this.limit = limit;
		this.page = page;
		this.totalResults = totalResults;
	}

	public ResponseMetadata() {
	}

}
