package link.ebbinghaus.planning.core.model.vo.extension.douban.book;

import java.util.List;

/**
 * rating : {"max":10,"numRaters":99869,"average":"8.6","min":0}
 * subtitle :
 * author : ["(日)东野圭吾"]
 * pubdate : 2014-5
 * tags : [{"count":31648,"name":"东野圭吾","title":"东野圭吾"},{"count":14605,"name":"日本","title":"日本"},{"count":13431,"name":"小说","title":"小说"},{"count":9475,"name":"日本文学","title":"日本文学"},{"count":6235,"name":"治愈","title":"治愈"},{"count":5620,"name":"推理","title":"推理"},{"count":5265,"name":"東野圭吾","title":"東野圭吾"},{"count":2947,"name":"日系推理","title":"日系推理"}]
 * origin_title : ナミヤ雑貨店の奇蹟
 * image : http://img3.doubanio.com/mpic/s27284878.jpg
 * binding : 精装
 * translator : ["李盈春"]
 * catalog : 第一章 回答在牛奶箱里
 第二章 深夜的口琴声
 第三章 在思域车上等到天亮
 第四章 听着披头士默祷
 第五章 来自天上的祈祷
 * pages : 291
 * images : {"small":"http://img3.doubanio.com/spic/s27284878.jpg","large":"http://img3.doubanio.com/lpic/s27284878.jpg","medium":"http://img3.doubanio.com/mpic/s27284878.jpg"}
 * alt : https://book.douban.com/subject/25862578/
 * id : 25862578
 * publisher : 南海出版公司
 * isbn10 : 7544270874
 * isbn13 : 9787544270878
 * title : 解忧杂货店
 * url : http://api.douban.com/v2/book/25862578
 * alt_title : ナミヤ雑貨店の奇蹟
 * author_intro : 东野圭吾
 日本著名作家。
 1985年，《放学后》获第31届江户川乱步奖，开始专职写作；
 1999年，《秘密》获第52届日本推理作家协会奖；
 2005年出版的《嫌疑人X的献身》史无前例地同时获得第134届直木奖、第6届本格推理小说大奖，以及年度三大推理小说排行榜第1名；
 2008年，《流星之绊》获第43届新风奖；
 2009年出版的《新参者》获两大推理小说排行榜年度第1名；
 2012年，《解忧杂货店》获第7届中央公论文艺奖。
 2014年，《祈りの幕が下りる時》（暂译《祈祷落幕时》）获第48届吉川英治文学奖。
 * summary : 现代人内心流失的东西，这家杂货店能帮你找回——
 僻静的街道旁有一家杂货店，只要写下烦恼投进卷帘门的投信口，第二天就会在店后的牛奶箱里得到回答。
 因男友身患绝症，年轻女孩静子在爱情与梦想间徘徊；克郎为了音乐梦想离家漂泊，却在现实中寸步难行；少年浩介面临家庭巨变，挣扎在亲情与未来的迷茫中……
 他们将困惑写成信投进杂货店，随即奇妙的事情竟不断发生。
 生命中的一次偶然交会，将如何演绎出截然不同的人生？
 如今回顾写作过程，我发现自己始终在思考一个问题：站在人生的岔路口，人究竟应该怎么做？我希望读者能在掩卷时喃喃自语：我从未读过这样的小说。——东野圭吾
 * series : {"id":"868","title":"新经典文库·东野圭吾作品"}
 * price : 39.50元
 */
public class Book {

    /**
     * max : 10
     * numRaters : 99869
     * average : 8.6
     * min : 0
     */

    private Rating rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    /**
     * small : http://img3.doubanio.com/spic/s27284878.jpg
     * large : http://img3.doubanio.com/lpic/s27284878.jpg
     * medium : http://img3.doubanio.com/mpic/s27284878.jpg
     */

    private Images images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    /**
     * id : 868
     * title : 新经典文库·东野圭吾作品
     */

    private Series series;
    private String price;
    private List<String> author;
    /**
     * count : 31648
     * name : 东野圭吾
     * title : 东野圭吾
     */

    private List<Tag> tags;
    private List<String> translator;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }
}
