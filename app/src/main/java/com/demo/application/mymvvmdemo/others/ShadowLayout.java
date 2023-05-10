//package com.demo.application.mymvvmdemo;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.RectF;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Build;
//import android.util.AttributeSet;
//import android.widget.FrameLayout;
//
//import androidx.annotation.ColorInt;
//
//
//public class ShadowLayout extends FrameLayout {
//    /**
//     * 阴影颜色
//     **/
//    @ColorInt
//    private int mShadowColor;
//
//    /**
//     * 背景颜色
//     **/
//    @ColorInt
//    private int mFillColor;
//
//    /**
//     * 阴影范围大小
//     **/
//    private float mShadowRadius;
//    /**
//     * 阴影圆角光滑度
//     **/
//    private float mCornerRadius;
//    /**
//     * 阴影偏离原位置x坐标多少
//     **/
//    private float mDx;
//    /**
//     * 阴影偏离原位置y坐标多少
//     **/
//    private float mDy;
//
//    public ShadowLayout(Context context) {
//        super(context);
//        initView(context, null);
//    }
//
//    public ShadowLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initView(context, attrs);
//    }
//
//    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initView(context, attrs);
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        if (w > 0 && h > 0 && getBackground() == null) {
//            setBackgroundCompat(w, h);
//        }
//    }
//
//    private void initView(Context context, AttributeSet attrs) {
//        initAttributes(context, attrs);
//        /** x偏离量 **/
//        int xPadding = (int) (mShadowRadius + Math.abs(mDx));
//        /** y偏离量 **/
//        int yPadding = (int) (mShadowRadius + Math.abs(mDy));
//        /** 设置偏离量，分别为left,top,right,bottom **/
//        setPadding(xPadding, yPadding, xPadding, yPadding);
//    }
//
//    public void setDx(float dx) {
//        this.mDx = dx;
//        /** x偏离量 **/
//        int xPadding = (int) (mShadowRadius + Math.abs(mDx));
//        /** y偏离量 **/
//        int yPadding = (int) (mShadowRadius + Math.abs(mDy));
//        /** 设置偏离量，分别为left,top,right,bottom **/
//        setPadding(xPadding, yPadding, xPadding, yPadding);
//    }
//
//    public void setDy(float dy) {
//        this.mDy = dy;
//        /** x偏离量 **/
//        int xPadding = (int) (mShadowRadius + Math.abs(mDx));
//        /** y偏离量 **/
//        int yPadding = (int) (mShadowRadius + Math.abs(mDy));
//        /** 设置偏离量，分别为left,top,right,bottom **/
//        setPadding(xPadding, yPadding, xPadding, yPadding);
//    }
//
//    public void setShadowColor(@ColorInt int color) {
//        mShadowColor = color;
//        setBackground(null);
//        if (getWidth() > 0 && getHeight() > 0) {
//            setBackgroundCompat(getWidth(), getHeight());
//        }
//        requestLayout();
//    }
//
//    public void setFillColor(@ColorInt int color) {
//        mFillColor = color;
//        setBackground(null);
//        if (getWidth() > 0 && getHeight() > 0) {
//            setBackgroundCompat(getWidth(), getHeight());
//        }
//        requestLayout();
//    }
//
//
//    public void setShadowRadius(float mShadowRadius) {
//        this.mShadowRadius = mShadowRadius;
//        requestLayout();
//    }
//
//    public void setCornerRadius(float mCornerRadius) {
//        this.mCornerRadius = mCornerRadius;
//        requestLayout();
//    }
//
//    @SuppressWarnings("deprecation")
//    private void setBackgroundCompat(int w, int h) {
//        try {
//            Bitmap bitmap = createShadowBitmap(w, h, mCornerRadius, mShadowRadius, mDx, mDy, mShadowColor, mFillColor);
//            BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
//            //判断版本，设置背景
//            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
//                setBackgroundDrawable(drawable);
//            } else {
//                setBackground(drawable);
//            }
//        }catch (OutOfMemoryError error){
//            System.gc();
//        }
//
//    }
//
//    /**
//     * 初始化 initAttributes
//     *
//     * @param context
//     * @param attrs
//     */
//    private void initAttributes(Context context, AttributeSet attrs) {
//        // TypedArray attr = getTypedArray(context, attrs, R.styleable.ShadowLayout);
//        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.ShadowLayout);
//        if (attr == null) {
//            return;
//        }
//        try {
//            mCornerRadius = attr.getDimension(R.styleable.ShadowLayout_sl_cornerRadius, getResources().getDimension(R.dimen.default_corner_radius));
//            mShadowRadius = attr.getDimension(R.styleable.ShadowLayout_sl_shadowRadius, getResources().getDimension(R.dimen.default_shadow_radius));
//            mDx = attr.getDimension(R.styleable.ShadowLayout_sl_dx, 0);
//            mDy = attr.getDimension(R.styleable.ShadowLayout_sl_dy, 0);
//            mFillColor = attr.getColor(R.styleable.ShadowLayout_sl_fillColor, Color.TRANSPARENT);
//            mShadowColor = attr.getColor(R.styleable.ShadowLayout_sl_shadowColor, getResources().getColor(R.color.common_primary_500_alpha_50));
//        } finally {
//            attr.recycle();
//        }
//    }
//
//    /**
//     * 获取TypedArray
//     *
//     * @param context
//     * @param attributeSet
//     * @param attr
//     * @return
//     */
//    private TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
//        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
//    }
//
//    /**
//     * 产生阴影Bitmap
//     *
//     * @param shadowWidth
//     * @param shadowHeight
//     * @param cornerRadius
//     * @param shadowRadius
//     * @param dx
//     * @param dy
//     * @param shadowColor
//     * @param fillColor
//     * @return
//     */
//    private Bitmap createShadowBitmap(int shadowWidth, int shadowHeight, float cornerRadius, float shadowRadius,
//                                      float dx, float dy, int shadowColor, int fillColor) {
//
//        Bitmap output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//
//        RectF shadowRect = new RectF(
//                shadowRadius,
//                shadowRadius,
//                shadowWidth - shadowRadius,
//                shadowHeight - shadowRadius);
//
//        if (dy > 0) {
//            shadowRect.top += dy;
//            shadowRect.bottom -= dy;
//        } else if (dy < 0) {
//            shadowRect.top += Math.abs(dy);
//            shadowRect.bottom -= Math.abs(dy);
//        }
//
//        if (dx > 0) {
//            shadowRect.left += dx;
//            shadowRect.right -= dx;
//        } else if (dx < 0) {
//            shadowRect.left += Math.abs(dx);
//            shadowRect.right -= Math.abs(dx);
//        }
//
//        Paint shadowPaint = new Paint();
//        shadowPaint.setAntiAlias(true);
//        shadowPaint.setDither(true);
//        shadowPaint.setColor(fillColor);
//        shadowPaint.setStyle(Paint.Style.FILL);
//
//        if (!isInEditMode()) {
//            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor);
//        }
//
//        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint);
//
//        return output;
//    }
//}