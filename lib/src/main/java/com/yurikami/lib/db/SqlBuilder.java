package com.yurikami.lib.db;

/**
 * Created by WINFIELD on 2016/2/28.
 * 生成sql字符串的类(限定建表语句)
 */
public class SqlBuilder {
    private static SqlBuilder builder;
    private StringBuilder sql;
    public static final String INTEGER = "integer";
    public static final String REAL = "real";
    public static final String TEXT = "text";

    private SqlBuilder() {
    }

    public static SqlBuilder build() {
        if (builder == null) {
            builder = new SqlBuilder();
        }
        return builder;
    }

    /**
     * 可在使用完SqlBuilder之后释放
     */
    public static void release() {
        builder = null;
    }

    public SqlBuilder create(String table) {
        sql = new StringBuilder();
        sql.append("create table if not exists ").append(table);
        return this;
    }

    public SqlBuilder pk(String pk) {
        sql.append("(" + pk + " integer primary key autoincrement,");
        return this;
    }

    public SqlBuilder column(String column, String type) {
        sql.append(column + " " + type + ",");
        return this;
    }

    public SqlBuilder integer(String column) {
        return column(column, INTEGER);
    }

    public SqlBuilder real(String column) {
        return column(column, REAL);
    }

    public SqlBuilder text(String column) {
        return column(column, TEXT);
    }

    public SqlBuilder defaultCurrTimestamp() {
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" DEFAULT " + "(strftime('%s','now')*1000),");
        return this;
    }
    public SqlBuilder _default(String text){
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" DEFAULT " + "'"+ text +"',");
        return this;
    }

    public SqlBuilder _default(long integer){
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" DEFAULT " + integer + ",");
        return this;
    }


    public SqlBuilder _default(double real){
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" DEFAULT " + real + ",");
        return this;
    }

    public String sql() {
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

}
