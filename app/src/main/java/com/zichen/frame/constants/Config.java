package com.zichen.frame.constants;

/**
 * author : zichen
 * time : 2018/5/15
 * desc : 应用配置常量
 * version: 1.0
 */
public class Config {

    private Config() {
        throw new RuntimeException("can't instante Config");
    }

    /**
     * 连接超时时间
     */
    public static final int CONNECT_TIMEOUT = 15;

    /**
     * 写入超时时间
     */
    public static final int WRITE_TIMEOUT = 15;

    /**
     * 读取超时时间
     */
    public static final int READ_TIMEOUT = 15;

    /**
     * Banner自动滑动间隔
     */
    public static final int BANNER_DELAYTIME = 3000;
}
