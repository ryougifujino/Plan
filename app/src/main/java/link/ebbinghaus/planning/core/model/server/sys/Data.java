package link.ebbinghaus.planning.core.model.server.sys;

import java.util.List;
public class Data<E> {
	
	//数据集总数
	private Long total = 0L;
	
	//list的长度
	private Integer count = 0;
	
	//数据集
	private List<E> list;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
		if(list != null){
			setCount(list.size());
		}
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public void setList(Long total, List<E> list){
		setTotal(total);
		setList(list);
	}
	
	public void setList(Integer total, List<E> list){
		setTotal(total != null ? (long) total : -1L);
		setList(list);
	}


}
