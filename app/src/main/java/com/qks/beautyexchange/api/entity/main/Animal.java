package com.qks.beautyexchange.api.entity.main;

import com.google.auto.value.AutoValue;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/04/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@AutoValue
public abstract class Animal {

    public abstract int id();

    public abstract String title();

    public static Animal create(int id,String title){
        return new AutoValue_Animal(id,title);
    }
}
