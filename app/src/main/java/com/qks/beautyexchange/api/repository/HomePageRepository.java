package com.qks.beautyexchange.api.repository;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/04/23
 *     desc   : 首页仓库
 *     version: 1.0
 * </pre>
 */
public class HomePageRepository {

    private static HomePageRepository sHomePageRepository;

    public static HomePageRepository getInstance() {
        if (sHomePageRepository == null) {
            sHomePageRepository = new HomePageRepository();
        }
        return sHomePageRepository;
    }



}
