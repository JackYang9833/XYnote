package com.mycompany.myapp;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuInflater;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.MenuBuilder;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.internal.ThemeEnforcement;

public class GapBottomNavigationView extends FrameLayout{
    
	private BottomNavigationPresenter presenter;
	private BottomNavigationMenu menu;
	private MenuInflater menuInflater;
	private MenuBuilder builder;
	private BottomNavigationMenuView menuView;
	
	BottomNavigationView.OnNavigationItemSelectedListener selectedListener;
	
	
    public static final String TAG = "GapBottomNavigationView";
    
    public GapBottomNavigationView(Context context){
		super(context);
	}
	
    public GapBottomNavigationView(Context context,AttributeSet attrs){
		super(context,attrs);
	}
	
	public GapBottomNavigationView(Context context,AttributeSet attrs,int defStyle){
		super(context,attrs,defStyle);
		
		this.presenter=new BottomNavigationPresenter();
		this.menu=new BottomNavigationMenu(context);
		this.menuView=new BottomNavigationMenuView(context);
		LayoutParams params=new LayoutParams(-2,-2);
		params.gravity=17;
		
		this.menuView.setLayoutParams(params);
		this.presenter.setBottomNavigationMenuView(menuView);
		this.presenter.setId(1);
		this.menuView.setPresenter(this.presenter);
		this.menu.addMenuPresenter(this.presenter);
        this.presenter.initForMenu(this.getContext(), this.menu);
		//TintTypedArray tintType=ThemeEnforcement.obtainTintedStyledAttributes(context,attrs,);
		
	}
	
	
}
