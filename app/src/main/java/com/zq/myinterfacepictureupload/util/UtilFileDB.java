package com.zq.myinterfacepictureupload.util;

/**
 * Created by zq on 2016/6/11.
 */

public class UtilFileDB {

    /****
     * 永久缓存
     * @param aCache
     * @param key
     * @param content
     */
    public static final void ADDFile(ACache aCache, String key, String content)
    {
        aCache.put(key,content);
    }

    /***
     *查询数据
     * @param aCache
     * @param key
     * @return
     */
    public static final String SELETEFile(ACache aCache, String key)
    {
        return aCache.getAsString(key);
    }

    /***
     * 清空
     * @param aCache
     * @param key
     */
    public static final void DELETEFile(ACache aCache,String key)
    {
        aCache.remove(key);
    }


    /***
     * 获取用户头像路径
     * @return
     */
    public static final String LOGINIMGURL(ACache aCache) {
        return aCache.getAsString("stscimage");
    }


}
