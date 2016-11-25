package com.qks.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.orhanobut.logger.Logger;

/**
 * 软键盘相关
 * Created by admin on 2016/3/31.
 */
public class KeyBoardUtils {

    /**
     * 显示软键盘
     * @param context
     * @param view
     */
    public static void showKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 影藏软键盘
     * @param context
     * @param view
     */
    public static void hideKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 监听软键盘状态 和返回软键盘高度
     * @param activity
     * @param keyBoardListener
     */
    public static void attach(final Activity activity, final KeyBoardListener keyBoardListener) {
        final ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        //获取状态栏的高度
        final int statusBarHeight = SmartBarUtils.getStatusBarHeight(activity);

        contentView.getViewTreeObserver().
                addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    public int fullDisplayHeight;
                    boolean isKeyboardVisible;

                    @Override
                    public void onGlobalLayout() {

                        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
                        View userRootView = contentView.getChildAt(0);
                        //计算userRootView的高度
                        Rect r = new Rect();
                        userRootView.getWindowVisibleDisplayFrame(r);
                        int displayHeight = r.bottom - r.top;

                        if (fullDisplayHeight == 0) {
                            fullDisplayHeight = displayHeight;
                            return;
                        }
                        int keyboardHeight = Math.abs(displayHeight - fullDisplayHeight);
                        if (keyboardHeight == 0) {
                            if (isKeyboardVisible) {
                                isKeyboardVisible = false;
                                if (keyBoardListener != null)
                                    keyBoardListener.keyboardShowingChanged(isKeyboardVisible);
                            }
                            return;
                        }

                        Logger.e(String.format("pre display height: %d display height: %d keyboard: %d ",
                                fullDisplayHeight, displayHeight, keyboardHeight));

                        //当前变化由，非全屏到全屏导致，此时应该更新fullDisplayHeight
                        if (keyboardHeight == statusBarHeight) {
                            Logger.e(String.format("On global layout change get keyboard height just equal" +
                                    " statusBar height %d", keyboardHeight));
                            fullDisplayHeight = displayHeight;
                            return;
                        }

                        if (keyBoardListener != null)
                            keyBoardListener.keyboardHeight(keyboardHeight);

                        if (!isKeyboardVisible) {
                            isKeyboardVisible = true;
                            if (keyBoardListener != null)
                                keyBoardListener.keyboardShowingChanged(isKeyboardVisible);

                        }


                    }
                });
    }


    /**
     * 键盘监听回调
     */
    public interface KeyBoardListener {

        void keyboardHeight(int keyboardHeight);

        void keyboardShowingChanged(boolean visible);

    }
}
