package com.example.greendao.gen;

import android.content.Context;
import android.text.TextUtils;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * Created by liulu on 2017/6/13
 */

public class UserInfoUtils {

    private static final String DB_NAME = "greendaotest";
    private volatile static UserInfoUtils userInfoUtils;
    private static DaoMaster daoMaster;
    private static DaoMaster.OpenHelper helper;
    private static DaoSession daoSession;
    private static Context appContext;
    private static GreendaoBeanDao userDao;

    public static UserInfoUtils getInstance(Context context) {

        if (userInfoUtils == null) {
            setDebug();
            userInfoUtils = new UserInfoUtils();
            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
            daoSession = getDaoSession(context);
            userDao = daoSession.getGreendaoBeanDao();
        }
        return userInfoUtils;
    }


    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                MySQLiteHelper helper = new MySQLiteHelper(context, DB_NAME, null);
                daoMaster = new DaoMaster(helper.getWritableDatabase());
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /*存储*/
    public void saveUser(GreendaoBean greendaoBean) {
        try {
            GreendaoBean bean = loadUser(greendaoBean.getName());
            if (bean != null) {
                greendaoBean.set_id(bean.get_id());
            }
            userDao.insertOrReplace(greendaoBean);
        } finally {
            closeConnection();
        }
    }

    /*查询*/
    public GreendaoBean loadUser(String name) {
        try {
            if (!TextUtils.isEmpty(name)) {
                QueryBuilder<GreendaoBean> where = userDao.queryBuilder().where(GreendaoBeanDao.Properties.Name.eq(name));
                return where.unique();
            }
        } finally {
            closeConnection();
        }
        return null;
    }

    /**
     * 打开输出日志，默认关闭
     */
    public static void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }


}
