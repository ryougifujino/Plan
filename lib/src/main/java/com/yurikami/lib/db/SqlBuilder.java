package com.yurikami.lib.db;

/**
 * Created by WINFIELD on 2016/2/28.
 * 生成sql字符串的类(限定建表语句)
 */
public class SqlBuilder {
    private StringBuilder sql;
    public static final String INTEGER = "integer";
    public static final String REAL = "real";
    public static final String TEXT = "text";

    public SqlBuilder create(String table){
        sql = new StringBuilder();
        sql.append("create table if not exists ").append(table);
        return this;
    }

    public SqlBuilder pk(String pk){
        if(sql != null){
            sql.append("("+ pk +" integer primary key autoincrement,");
        }
        return this;
    }

    public SqlBuilder column(String column, String type){
        if(sql != null){
            sql.append(column + " " + type + ",");
        }
        return this;
    }
    public SqlBuilder integer(String column){ return column(column,INTEGER);}
    public SqlBuilder real(String column){
        return column(column,REAL);
    }
    public SqlBuilder text(String column){ return column(column,TEXT); }


    public SqlBuilder last(String column,String type){
        if(sql != null){
            sql.append(column + " " + type + ")");
        }
        return this;
    }

    public String sql(){ return sql == null? "" : sql.toString(); }

}
