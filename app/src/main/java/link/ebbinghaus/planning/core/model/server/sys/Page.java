package link.ebbinghaus.planning.core.model.server.sys;

public class Page {

	private Integer pageCount = 5, page = 1;

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getStartOffset() {
		return (page - 1) * pageCount;
	}
}
