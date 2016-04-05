package link.ebbinghaus.planning.core.model.po;

import android.content.ContentValues;
import android.database.Cursor;

import com.yurikami.lib.util.NonNullContentValues;

import link.ebbinghaus.planning.common.constant.config.DBConfig;

/**
 * Created by WINFIELD on 2016/3/28.
 */
public class GreekAlphabet {
    private Long pkGreekAlphabetId;
    private String greekAlphabet;
    private Long usage;

    public Long getPkGreekAlphabetId() {
        return pkGreekAlphabetId;
    }

    public void setPkGreekAlphabetId(Long pkGreekAlphabetId) {
        this.pkGreekAlphabetId = pkGreekAlphabetId;
    }

    public String getGreekAlphabet() {
        return greekAlphabet;
    }

    public void setGreekAlphabet(String greekAlphabet) {
        this.greekAlphabet = greekAlphabet;
    }

    public Long getUsage() {
        return usage;
    }

    public void setUsage(Long usage) {
        this.usage = usage;
    }

    /* --- */

    public void convertToContentValues(ContentValues values){
        NonNullContentValues nonNullValues = new NonNullContentValues(values);
        nonNullValues.put(DBConfig.GreekAlphabetColumn.PK_GREEK_ALPHABET_ID, pkGreekAlphabetId);
        nonNullValues.put(DBConfig.GreekAlphabetColumn.GREEK_ALPHABET, greekAlphabet);
        nonNullValues.put(DBConfig.GreekAlphabetColumn.USAGE, usage);
    }

    public void filledByCursor(Cursor cursor){
        setPkGreekAlphabetId(cursor.getLong(cursor.getColumnIndex(DBConfig.GreekAlphabetColumn.PK_GREEK_ALPHABET_ID)));
        setGreekAlphabet(cursor.getString(cursor.getColumnIndex(DBConfig.GreekAlphabetColumn.GREEK_ALPHABET)));
        setUsage(cursor.getLong(cursor.getColumnIndex(DBConfig.GreekAlphabetColumn.USAGE)));
    }

    public void copyFrom(GreekAlphabet greekAlphabet){
        setPkGreekAlphabetId(greekAlphabet.getPkGreekAlphabetId());
        setGreekAlphabet(greekAlphabet.getGreekAlphabet());
        setUsage(greekAlphabet.getUsage());
    }
}
