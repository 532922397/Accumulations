package com.example.liulu.accumulations.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.adapter.LeftMenuAdapter;
import com.example.liulu.accumulations.adapter.RightDishAdapter;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.imp.ShopCartImp;
import com.example.liulu.accumulations.model.Dish;
import com.example.liulu.accumulations.model.DishMenu;
import com.example.liulu.accumulations.model.ShopCart;
import com.example.liulu.accumulations.wiget.FakeAddImageView;
import com.example.liulu.accumulations.wiget.PointFTypeEvaluator;
import com.example.liulu.accumulations.wiget.ShopCartDialog;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 仿饿了么购物车添加动画
 */
public class ShoppingcarEActivity extends BaseActivity implements LeftMenuAdapter.onItemSelectedListener, ShopCartImp, ShopCartDialog.ShopCartDialogImp {


    @Bind(R.id.shopping_cart_total_tv)
    TextView shoppingCartTotalTv;
    @Bind(R.id.shopping_cart_bottom)
    LinearLayout shoppingCartBottom;
    @Bind(R.id.left_menu)
    RecyclerView leftMenu;
    @Bind(R.id.right_menu)
    RecyclerView rightMenu;
    @Bind(R.id.right_menu_tv)
    TextView rightMenuTv;
    @Bind(R.id.right_menu_item)
    LinearLayout rightMenuItem;
    @Bind(R.id.shopping_cart)
    ImageView shoppingCart;
    @Bind(R.id.shopping_cart_layout)
    FrameLayout shoppingCartLayout;
    @Bind(R.id.shopping_cart_total_num)
    TextView shoppingCartTotalNum;
    @Bind(R.id.main_layout)
    RelativeLayout mainLayout;
    private ShopCart shopCart;
    private ArrayList<DishMenu> dishMenuList;
    private LeftMenuAdapter leftAdapter;
    private RightDishAdapter rightAdapter;
    private DishMenu headMenu;
    private boolean leftClickType = false;// 左侧菜单点击引发的右侧联动

    @Override
    protected void initData() {
        initDatas();
        initView();
        initAdapter();
    }

    private void initView() {
        leftMenu.setLayoutManager(new LinearLayoutManager(this));
        rightMenu.setLayoutManager(new LinearLayoutManager(this));
        rightMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1) == false) {// 无法下滑
                    showHeadView();
                    return;
                }
                View underView = null;
                if (dy > 0)
                    underView = rightMenu.findChildViewUnder(rightMenuItem.getX(), rightMenuItem.getMeasuredHeight() + 1);
                else
                    underView = rightMenu.findChildViewUnder(rightMenuItem.getX(), 0);
                if (underView != null && underView.getContentDescription() != null) {
                    int position = Integer.parseInt(underView.getContentDescription().toString());
                    DishMenu menu = rightAdapter.getMenuOfMenuByPosition(position);

                    if (leftClickType || !menu.getMenuName().equals(headMenu.getMenuName())) {
                        if (dy > 0 && rightMenuItem.getTranslationY() <= 1 && rightMenuItem.getTranslationY() >= -1 * rightMenuItem.getMeasuredHeight() * 4 / 5 && !leftClickType) {// underView.getTop()>9
                            int dealtY = underView.getTop() - rightMenuItem.getMeasuredHeight();
                            rightMenuItem.setTranslationY(dealtY);
                            // Log.e(TAG, "onScrolled: "+rightMenuItem.getTranslationY()+"   "+rightMenuItem.getBottom()+"  -  "+rightMenuItem.getMeasuredHeight() );
                        } else if (dy < 0 && rightMenuItem.getTranslationY() <= 0 && !leftClickType) {
                            rightMenuTv.setText(menu.getMenuName());
                            int dealtY = underView.getBottom() - rightMenuItem.getMeasuredHeight();
                            rightMenuItem.setTranslationY(dealtY);
                            // Log.e(TAG, "onScrolled: "+rightMenuItem.getTranslationY()+"   "+rightMenuItem.getBottom()+"  -  "+rightMenuItem.getMeasuredHeight() );
                        } else {
                            rightMenuItem.setTranslationY(0);
                            headMenu = menu;
                            rightMenuTv.setText(headMenu.getMenuName());
                            for (int i = 0; i < dishMenuList.size(); i++) {
                                if (dishMenuList.get(i) == headMenu) {
                                    leftAdapter.setSelectedNum(i);
                                    break;
                                }
                            }
                            if (leftClickType) leftClickType = false;
                        }
                    }
                }
            }
        });

        shoppingCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCart(view);
            }
        });
    }

    private void showCart(View view) {
        if (shopCart != null && shopCart.getShoppingAccount() > 0) {
            ShopCartDialog dialog = new ShopCartDialog(this, shopCart, R.style.cartdialog);
            Window window = dialog.getWindow();
            dialog.setShopCartDialogImp(this);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.show();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            params.dimAmount = 0.5f;
            window.setAttributes(params);
        }
    }

    private void initAdapter() {
        leftAdapter = new LeftMenuAdapter(this, dishMenuList);
        rightAdapter = new RightDishAdapter(this, dishMenuList, shopCart);
        rightMenu.setAdapter(rightAdapter);
        leftMenu.setAdapter(leftAdapter);
        leftAdapter.addItemSelectedListener(this);
        rightAdapter.setShopCartImp(this);
        initHeadView();
    }

    private void initHeadView() {
        headMenu = rightAdapter.getMenuOfMenuByPosition(0);
        rightMenuItem.setContentDescription("0");
        rightMenuTv.setText(headMenu.getMenuName());
    }

    private void initDatas() {
        shopCart = new ShopCart();
        dishMenuList = new ArrayList<>();
        ArrayList<Dish> dishs1 = new ArrayList<>();
        dishs1.add(new Dish("面包", 1.0, 10));
        dishs1.add(new Dish("蛋挞", 1.0, 10));
        dishs1.add(new Dish("牛奶", 1.0, 10));
        dishs1.add(new Dish("肠粉", 1.0, 10));
        dishs1.add(new Dish("绿茶饼", 1.0, 10));
        dishs1.add(new Dish("花卷", 1.0, 10));
        dishs1.add(new Dish("包子", 1.0, 10));
        DishMenu breakfast = new DishMenu("早点", dishs1);

        ArrayList<Dish> dishs2 = new ArrayList<>();
        dishs2.add(new Dish("粥", 1.0, 10));
        dishs2.add(new Dish("炒饭", 1.0, 10));
        dishs2.add(new Dish("炒米粉", 1.0, 10));
        dishs2.add(new Dish("炒粿条", 1.0, 10));
        dishs2.add(new Dish("炒牛河", 1.0, 10));
        dishs2.add(new Dish("炒菜", 1.0, 10));
        DishMenu launch = new DishMenu("午餐", dishs2);

        ArrayList<Dish> dishs3 = new ArrayList<>();
        dishs3.add(new Dish("淋菜", 1.0, 10));
        dishs3.add(new Dish("川菜", 1.0, 10));
        dishs3.add(new Dish("湘菜", 1.0, 10));
        dishs3.add(new Dish("粤菜", 1.0, 10));
        dishs3.add(new Dish("赣菜", 1.0, 10));
        dishs3.add(new Dish("东北菜", 1.0, 10));
        DishMenu evening = new DishMenu("下午茶", dishs3);

        ArrayList<Dish> dishs4 = new ArrayList<>();
        dishs4.add(new Dish("淋菜", 1.0, 10));
        dishs4.add(new Dish("川菜", 1.0, 10));
        dishs4.add(new Dish("湘菜", 1.0, 10));
        dishs4.add(new Dish("粤菜", 1.0, 10));
        dishs4.add(new Dish("赣菜", 1.0, 10));
        dishs4.add(new Dish("东北菜", 1.0, 10));
        DishMenu menu1 = new DishMenu("晚餐", dishs4);

        dishMenuList.add(breakfast);
        dishMenuList.add(launch);
        dishMenuList.add(evening);
        dishMenuList.add(menu1);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_shoppingcar_e;
    }

    @Override
    public void onLeftItemSelected(int position, DishMenu menu) {
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += dishMenuList.get(i).getDishList().size() + 1;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) rightMenu.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(sum, 0);
        leftClickType = true;
    }

    @Override
    public void add(View view, int postion) {
        int[] addLocation = new int[2];
        int[] cartLocation = new int[2];
        int[] recycleLocation = new int[2];
        view.getLocationInWindow(addLocation);
        shoppingCart.getLocationInWindow(cartLocation);
        rightMenu.getLocationInWindow(recycleLocation);

        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();

        startP.x = addLocation[0];
        startP.y = addLocation[1] - recycleLocation[1];
        endP.x = cartLocation[0];
        endP.y = cartLocation[1] - recycleLocation[1];
        controlP.x = endP.x;
        controlP.y = startP.y;

        final FakeAddImageView fakeAddImageView = new FakeAddImageView(this);
        mainLayout.addView(fakeAddImageView);
        fakeAddImageView.setImageResource(R.drawable.ic_add_circle_blue_700_36dp);
        fakeAddImageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        fakeAddImageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        fakeAddImageView.setVisibility(View.VISIBLE);
        ObjectAnimator addAnimator = ObjectAnimator.ofObject(fakeAddImageView, "mPointF",
                new PointFTypeEvaluator(controlP), startP, endP);
        addAnimator.setInterpolator(new AccelerateInterpolator());
        addAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                fakeAddImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                fakeAddImageView.setVisibility(View.GONE);
                mainLayout.removeView(fakeAddImageView);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        ObjectAnimator scaleAnimatorX = new ObjectAnimator().ofFloat(shoppingCart, "scaleX", 0.6f, 1.0f);// 购物车图片动画
        ObjectAnimator scaleAnimatorY = new ObjectAnimator().ofFloat(shoppingCart, "scaleY", 0.6f, 1.0f);
        scaleAnimatorX.setInterpolator(new AccelerateInterpolator());
        scaleAnimatorY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleAnimatorX).with(scaleAnimatorY).after(addAnimator);
        animatorSet.setDuration(800);
        animatorSet.start();

        showTotalPrice();
    }


    private void showHeadView() {
        rightMenuItem.setTranslationY(0);
        View underView = rightMenu.findChildViewUnder(rightMenuTv.getX(), 0);
        if (underView != null && underView.getContentDescription() != null) {
            int position = Integer.parseInt(underView.getContentDescription().toString());
            DishMenu menu = rightAdapter.getMenuOfMenuByPosition(position + 1);
            headMenu = menu;
            rightMenuTv.setText(headMenu.getMenuName());
            for (int i = 0; i < dishMenuList.size(); i++) {
                if (dishMenuList.get(i) == headMenu) {
                    leftAdapter.setSelectedNum(i);
                    break;
                }
            }
        }
    }

    private void showTotalPrice() {
        if (shopCart != null && shopCart.getShoppingTotalPrice() > 0) {
            shoppingCartTotalTv.setVisibility(View.VISIBLE);
            shoppingCartTotalTv.setText("￥ " + shopCart.getShoppingTotalPrice());
            shoppingCartTotalNum.setVisibility(View.VISIBLE);
            shoppingCartTotalNum.setText("" + shopCart.getShoppingAccount());

        } else {
            shoppingCartTotalTv.setVisibility(View.GONE);
            shoppingCartTotalNum.setVisibility(View.GONE);
        }
    }

    @Override
    public void remove(View view, int position) {
        showTotalPrice();
    }

    @Override
    public void dialogDismiss() {
        showTotalPrice();
        rightAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        leftAdapter.removeItemSelectedListener(this);
    }
}
