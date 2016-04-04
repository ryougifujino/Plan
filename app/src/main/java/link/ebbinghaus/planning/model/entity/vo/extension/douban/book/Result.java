package link.ebbinghaus.planning.model.entity.vo.extension.douban.book;

import java.util.List;

/**
 * 豆瓣读书查询结果集
 */
public class Result {
    /**
     * count : 20
     * start : 0
     * total : 26
     * books : ...
     */

    private int count;
    private int start;
    private int total;
    private List<Book> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
