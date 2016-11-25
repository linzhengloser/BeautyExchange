package com.qks.mylibrary.application;

/**
 * 正则表达式 严格按照命名规范
 * Created by admin on 2016/4/22.
 */
public class RegExpConstant {

    /**
     * 是否是中文
     */
    public static final String REG_EXP_IS_CHINESE = "^[\\\\u4e00-\\\\u9fa5]{0,}$";

    /**
     * 是否是由 26个英文名+数字+下划线组成
     */
    public static final String REG_EXP_NUMBER_UNDERLINE_LETTER = "^\\\\w+$";

    /**
     * 是否是Email
     */
    public static final String REG_EXT_IS_EMAIL = "[\\\\w!#$%&'*+/=?^_`{|}~-]+(?:\\\\.[\\\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\\\w](?:[\\\\w-]*[\\\\w])?\\\\.)+[\\\\w](?:[\\\\w-]*[\\\\w])?";

    /**
     * 是否是15位的身份证号码
     */
    public static final String REG_EXT_IS_IDCARD_15 ="^[1-9]\\\\d{7}((0\\\\d)|(1[0-2]))(([0|1|2]\\\\d)|3[0-1])\\\\d{3}$";

    /**
     * 是否是18位的身份证号码
     */
    public static final String REG_EXT_IS_IDCARD_18 = "^[1-9]\\\\d{5}[1-9]\\\\d{3}((0\\\\d)|(1[0-2]))(([0|1|2]\\\\d)|3[0-1])\\\\d{3}([0-9]|X)$";

    /**
     * 是否是日期 yyyy-mm-dd 格式 已考虑评闰年
     */
    public static final String REG_EXT_IS_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";

    /**
     * 是否是金额,精确到小数点后2位数
     */
    public static final String REG_EXT_IS_DOUBLE_PRICE = "^[0-9]+(.[0-9]{2})?$";
    /**
     * 是否是手机号码 13 15 18 开头的手机号码
     *
     */
    public static final String REG_EXT_IS_PHONE_NUMBER = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\\\d{8}$";


    /**
     * 筛选出一个字符串中的URL
     */
    public static final String REG_EXT_FILTER_URL2STRING = "^(f|ht){1}(tp|tps):\\\\/\\\\/([\\\\w-]+\\\\.)+[\\\\w-]+(\\\\/[\\\\w- ./?%&=]*)?";

}
