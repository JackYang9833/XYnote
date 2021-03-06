package com.xiaoze.xynote;
//com.xiaoze.xynote.GapBottomNavigationView

/**
 * @SuppressLint("RestrictedApi")
 **/

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.FrameLayout;
import androidx.annotation.*;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.ThemeEnforcement;

import java.lang.reflect.Array;

/**@SuppressLint("RestrictedApi")**/

public class GapBottomNavigationView extends FrameLayout {

    private static final int  MENU_PRESENTER_ID = 1;

    private final MenuBuilder menu;

    private final BottomNavigationMenuView menuView;

    private final BottomNavigationPresenter presenter;

    private MenuInflater menuInflater;
    private BottomNavigationView.OnNavigationItemReselectedListener reselectedListener;
    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener;

    public GapBottomNavigationView(Context context) {

        this(context, (AttributeSet) null);

    }

    public GapBottomNavigationView(Context context, AttributeSet attrs) {

        this(context, attrs,R.attr.bottomNavigationStyle);

    }

    @SuppressLint("RestrictedApi")
    public GapBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        this.presenter = new BottomNavigationPresenter();

        this.menu = new BottomNavigationMenu(context);

        this.menuView = new BottomNavigationMenuView(context);

        LayoutParams params = new LayoutParams(-2, -2);

        params.gravity = 17;

        this.menuView.setLayoutParams(params);

        this.presenter.setBottomNavigationMenuView(this.menuView);

        this.presenter.setId(1);

        this.menuView.setPresenter(this.presenter);

        this.menu.addMenuPresenter(this.presenter);

        this.presenter.initForMenu(this.getContext(), this.menu);

        TintTypedArray a = ThemeEnforcement.obtainTintedStyledAttributes(context, attrs,R.styleable.BottomNavigationView, defStyleAttr, R.style.Widget_Design_BottomNavigationView, new int[]{R.styleable.BottomNavigationView_itemTextAppearanceInactive,R.styleable.BottomNavigationView_itemTextAppearanceActive});

        if (a.hasValue(android.R.color.holo_red_dark)) {//????????? ????????????

            this.menuView.setIconTintList(a.getColorStateList(android.R.color.holo_blue_light));//????????? ????????????

        } else {

            this.menuView.setIconTintList(this.menuView.createDefaultColorStateList(16842808));

        }

        this.setItemIconSize(a.getDimensionPixelSize(R.attr.BottomNavigationView_itemIconSize, this.getResources().getDimensionPixelSize(R.attr.design_bottom_navigation_icon_size)));

        if (a.hasValue(R.styleable.BottomNavigationView_itemTextAppearanceInactive)) {///////////////////

            this.setItemTextAppearanceInactive(a.getResourceId(R.styleable.BottomNavigationView_itemTextAppearanceInactive, 0));

        }/////////////////////////////////

        if (a.hasValue(R.styleable.BottomNavigationView_itemTextAppearanceActive)) {

            this.setItemTextAppearanceActive(a.getResourceId(R.styleable.BottomNavigationView_itemTextAppearanceActive, 0));

        }

        if (a.hasValue(R.styleable.BottomNavigationView_itemTextColor)) {

            this.setItemTextColor(a.getColorStateList(R.styleable.BottomNavigationView_itemTextColor));

        }

        if (a.hasValue(R.styleable.BottomNavigationView_elevation)) {

            ViewCompat.setElevation(this, (float) a.getDimensionPixelSize(R.styleable.BottomNavigationView_elevation, 0));

        }

        this.setLabelVisibilityMode(a.getInteger(R.styleable.BottomNavigationView_labelVisibilityMode, -1));

        this.setItemHorizontalTranslationEnabled(a.getBoolean(R.styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));

        int itemBackground = a.getResourceId(R.styleable.BottomNavigationView_itemBackground, 0);

        this.menuView.setItemBackgroundRes(itemBackground);

        if (a.hasValue(R.styleable.BottomNavigationView_menu)) {

            this.inflateMenu(a.getResourceId(R.styleable.BottomNavigationView_menu, 0));

        }

        a.recycle();

        this.addView(this.menuView, params);

        if (Build.VERSION.SDK_INT < 21) {

            this.addCompatibilityTopDivider(context);

        }


    }

    public void setOnNavigationItemSelectedListener(@Nullable BottomNavigationView.OnNavigationItemSelectedListener listener) {

        this.selectedListener = listener;

    }

    public void setOnNavigationItemReselectedListener(@Nullable BottomNavigationView.OnNavigationItemReselectedListener listener) {

        this.reselectedListener = listener;

    }

    @NonNull

    public Menu getMenu() {

        return this.menu;

    }

    public void inflateMenu(int resId) {

        this.presenter.setUpdateSuspended(true);

        this.getMenuInflater().inflate(resId, this.menu);

        this.presenter.setUpdateSuspended(false);

        this.presenter.updateMenuView(true);

    }

    public int getMaxItemCount() {

        return 5;

    }

    @Nullable

    public ColorStateList getItemIconTintList() {

        return this.menuView.getIconTintList();

    }

    public void setItemIconTintList(@Nullable ColorStateList tint) {

        this.menuView.setIconTintList(tint);

    }

    public void setItemIconSize(@Dimension int iconSize) {

        this.menuView.setItemIconSize(iconSize);

    }

    public void setItemIconSizeRes(@DimenRes int iconSizeRes) {

        this.setItemIconSize(this.getResources().getDimensionPixelSize(iconSizeRes));

    }

    @Dimension

    public int getItemIconSize() {

        return this.menuView.getItemIconSize();

    }

    @Nullable

    public ColorStateList getItemTextColor() {

        return this.menuView.getItemTextColor();

    }

    public void setItemTextColor(@Nullable ColorStateList textColor) {

        this.menuView.setItemTextColor(textColor);

    }

    /**
     * @deprecated

     */

    @Deprecated

    @DrawableRes

    public int getItemBackgroundResource() {

        return this.menuView.getItemBackgroundRes();

    }

    public void setItemBackgroundResource(@DrawableRes int resId) {

        this.menuView.setItemBackgroundRes(resId);

    }

    @Nullable

    public Drawable getItemBackground() {

        return this.menuView.getItemBackground();

    }

    public void setItemBackground(@Nullable Drawable background) {

        this.menuView.setItemBackground(background);

    }

    @IdRes

    public int getSelectedItemId() {

        return this.menuView.getSelectedItemId();

    }

    public void setSelectedItemId(@IdRes int itemId) {

        MenuItem item = this.menu.findItem(itemId);

        if (item != null && !this.menu.performItemAction(item, this.presenter, 0)) {

            item.setChecked(true);

        }

    }

    public void setLabelVisibilityMode(int labelVisibilityMode) {

        if (this.menuView.getLabelVisibilityMode() != labelVisibilityMode) {

            this.menuView.setLabelVisibilityMode(labelVisibilityMode);

            this.presenter.updateMenuView(false);

        }

    }

    public int getLabelVisibilityMode() {

        return this.menuView.getLabelVisibilityMode();

    }

    public void setItemTextAppearanceInactive(@StyleRes int textAppearanceRes) {

        this.menuView.setItemTextAppearanceInactive(textAppearanceRes);

    }

    @StyleRes

    public int getItemTextAppearanceInactive() {

        return this.menuView.getItemTextAppearanceInactive();

    }

    public void setItemTextAppearanceActive(@StyleRes int textAppearanceRes) {

        this.menuView.setItemTextAppearanceActive(textAppearanceRes);

    }

    @StyleRes

    public int getItemTextAppearanceActive() {

        return this.menuView.getItemTextAppearanceActive();

    }

    public void setItemHorizontalTranslationEnabled(boolean itemHorizontalTranslationEnabled) {

        if (this.menuView.isItemHorizontalTranslationEnabled() != itemHorizontalTranslationEnabled) {

            this.menuView.setItemHorizontalTranslationEnabled(itemHorizontalTranslationEnabled);

            this.presenter.updateMenuView(false);

        }

    }

    public boolean isItemHorizontalTranslationEnabled() {

        return this.menuView.isItemHorizontalTranslationEnabled();

    }

    private void addCompatibilityTopDivider(Context context) {

        View divider = new View(context);

        divider.setBackgroundColor(ContextCompat.getColor(context,R.attr.design_bottom_navigation_shadow_color));////////????????????

        LayoutParams dividerParams = new LayoutParams(-1, this.getResources().getDimensionPixelSize(R.attr.design_bottom_navigation_shadow_height));

        divider.setLayoutParams(dividerParams);

        this.addView(divider);

    }

    private MenuInflater getMenuInflater() {

        if (this.menuInflater == null) {

            this.menuInflater = new SupportMenuInflater(this.getContext());

        }

        return this.menuInflater;

    }

    protected Parcelable onSaveInstanceState() {

        Parcelable superState = super.onSaveInstanceState();

        GapBottomNavigationView.SavedState savedState = new GapBottomNavigationView.SavedState(superState);

        savedState.menuPresenterState = new Bundle();

        this.menu.savePresenterStates(savedState.menuPresenterState);

        return savedState;

    }

    protected void onRestoreInstanceState(Parcelable state) {

        if (!(state instanceof GapBottomNavigationView.SavedState)){

            super.onRestoreInstanceState(state);

        }else{

            GapBottomNavigationView.SavedState savedState = (GapBottomNavigationView.SavedState) state;

            super.onRestoreInstanceState(savedState.getSuperState());

            this.menu.restorePresenterStates(savedState.menuPresenterState);

        }

    }

    static class SavedState extends AbsSavedState {

        Bundle menuPresenterState;

        public static final Creator CREATOR =new

        ClassLoaderCreator() {

public GapBottomNavigationView.SavedState createFromParcel(Parcel in, ClassLoader loader){

                return new GapBottomNavigationView.SavedState(in, loader);

            }

public GapBottomNavigationView.SavedState createFromParcel(Parcel in) {

                return new GapBottomNavigationView.SavedState(in, (ClassLoader) null);

            }

            public GapBottomNavigationView.SavedState[] newArray(int size){

                return new GapBottomNavigationView.SavedState[size];

            }

        };

        public SavedState(Parcelable superState) {

            super(superState);

        }

        public SavedState(Parcel source, ClassLoader loader) {

            super(source,loader);

            this.readFromParcel(source, loader);

        }

        public void writeToParcel(Parcel out, int flags) {

//            super.writeToParcel(out, flags);
            writeToParcel(out,flags);
            out.writeBundle(this.menuPresenterState);

        }

        private void readFromParcel(Parcel in, ClassLoader loader) {

            this.menuPresenterState = in.readBundle(loader);

        }

    }

    public interface OnNavigationItemReselectedListener {

        void onNavigationItemReselected(@NonNull MenuItem var1);

    }

    public interface OnNavigationItemSelectedListener {

        boolean onNavigationItemSelected(@NonNull MenuItem var1);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    @SuppressLint("DrawAllocation")

    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        //setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        int centerRadius = getHeight() * 3 / 4;

        float shadowLength = 5f;

        Paint paint = new Paint();

        paint.setAntiAlias(true);

        Path path = new Path();

        path.moveTo(0, shadowLength);

        path.lineTo(getWidth() / 2f - centerRadius, shadowLength);

        path.lineTo(getWidth() / 2f - centerRadius / 3f * 2f, shadowLength + centerRadius / 4f);

        path.lineTo(getWidth() / 2f - centerRadius / 4f, shadowLength + centerRadius * 3 / 4f);

        path.lineTo(getWidth() / 2f + centerRadius / 4f, shadowLength + centerRadius * 3 / 4f);

        path.lineTo(getWidth() / 2f + centerRadius / 3f * 2f, shadowLength + centerRadius / 4f);

        path.lineTo(getWidth() / 2f + centerRadius, shadowLength);

        path.lineTo(getWidth(), shadowLength);

        path.lineTo(getWidth(), getHeight());

        path.lineTo(0, getHeight());

        path.lineTo(0, shadowLength);

        path.close();

        paint.setPathEffect(new CornerPathEffect(centerRadius / 4f));

        //?????????

        paint.setStyle(Paint.Style.STROKE);

        paint.setColor(Color.GRAY);

        paint.setStrokeWidth(1);

        //paint.setMaskFilter(new BlurMaskFilter(shadowLength - 1, BlurMaskFilter.Blur.NORMAL));

        canvas.drawPath(path, paint);

        //????????????

        paint.setStyle(Paint.Style.FILL);

        paint.setColor(Color.WHITE);

        paint.setStrokeWidth(1);

        paint.setMaskFilter(null);

        canvas.drawPath(path, paint);


       // Log.i(TAG, "onDraw: ");
    }

}
