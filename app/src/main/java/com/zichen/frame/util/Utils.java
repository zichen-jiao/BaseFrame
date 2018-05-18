package com.zichen.frame.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 * </pre>
 */
public final class Utils {

  private static Context context;


  private Utils() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }


  /**
   * 初始化工具类
   *
   * @param context 上下文
   */
  public static void init(Context context) {
    Utils.context = context.getApplicationContext();
  }


  /**
   * 获取ApplicationContext
   *
   * @return ApplicationContext
   */
  public static Context getContext() {
    if (context != null) return context;
    throw new NullPointerException("u should init first");
  }


  /** 获取资源 */
  public static Resources getResources() {
    return getContext().getResources();
  }

  /** 获取文字 */
  public static String getString(int resId) {
    return getResources().getString(resId);
  }

  /** 获取drawable */
  public static Drawable getDrawable(int resId) {
    return getResources().getDrawable(resId);
  }

  /** 获取颜色 */
  public static int getColor(int resId) {
    return getResources().getColor(resId);
  }
}