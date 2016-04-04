package link.ebbinghaus.planning.ebbinghaus.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import link.ebbinghaus.planning.ebbinghaus.constant.config.DBConfig;
import link.ebbinghaus.planning.model.entity.po.GreekAlphabet;

/**
 * Created by WINFIELD on 2016/3/28.
 */
public class GreekAlphabetDao extends BaseDao<GreekAlphabet> implements DBConfig.GreekAlphabetColumn{

    public GreekAlphabetDao() {
        super(PK_GREEK_ALPHABET_ID, DBConfig.Table.GREEK_ALPHABET);
    }

    @Override
    protected List<GreekAlphabet> _select(String querySql, String[] selectionArgs) {
        Cursor cursor = db.rawQuery(querySql, selectionArgs);
        List<GreekAlphabet> greekAlphabets = new ArrayList<>();
        while (cursor.moveToNext()){
            GreekAlphabet greekAlphabet = new GreekAlphabet();
            greekAlphabet.filledByCursor(cursor);
            greekAlphabets.add(greekAlphabet);
        }
        cursor.close();
        return greekAlphabets;
    }

    @Override
    protected long _insert(GreekAlphabet greekAlphabet) {
        ContentValues values = new ContentValues();
        greekAlphabet.convertToContentValues(values);
        return db.insert(mTableName, null, values);
    }

    @Override
    protected int _update(GreekAlphabet greekAlphabet, String where, String[] args) {
        ContentValues values = new ContentValues();
        greekAlphabet.convertToContentValues(values);
        return db.update(mTableName, values, where, args);
    }

    @Override
    public long insert(GreekAlphabet greekAlphabet) {
        greekAlphabet.setPkGreekAlphabetId(_insert(greekAlphabet));
        return greekAlphabet.getPkGreekAlphabetId();
    }

    @Override
    public void updateByPrimaryKey(GreekAlphabet greekAlphabet) {
        String whereClause = mPkColumn + " = ?";
        _update(greekAlphabet, whereClause, new String[]{greekAlphabet.getPkGreekAlphabetId().toString()});
    }

    @Override
    public void insertSome(List<GreekAlphabet> greekAlphabets) {
        for (GreekAlphabet greekAlphabet : greekAlphabets) {
            greekAlphabet.setPkGreekAlphabetId(_insert(greekAlphabet));
        }
    }

    /**
     * !只可以再创建此表时可调用此方法
     * 此方法的作用是插入默认值
     */
    public static void presetGreekAlphabetValues(SQLiteDatabase db){
        String[] greekAlphabetRes = {"α", "β", "γ", "δ", "ε", "ϝ", "ζ", "θ", "ι", "κ", "λ", "μ", "ν", "ξ", "ο", "π", "ρ", "σ", "τ", "υ", "φ", "χ", "ψ", "ω"};

        db.beginTransaction();
        try {
            for (String greekAlphabet : greekAlphabetRes) {
                GreekAlphabet entity = new GreekAlphabet();
                entity.setGreekAlphabet(greekAlphabet);
                ContentValues values = new ContentValues();
                entity.convertToContentValues(values);
                db.insert(DBConfig.Table.GREEK_ALPHABET, null, values);
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }

    }

    /* 以下方法为非通用方法 */
}
