package com.qks.beautyexchange.api.datasource;

import me.drakeet.multitype.Items;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/04/23
 *     desc   : 首页数据源
 *     version: 1.0
 * </pre>
 */
public interface HomePageDataSource {

    interface LoadHomePageCallBack{
        void onHomePageLoaded(Items items);
    }

}
