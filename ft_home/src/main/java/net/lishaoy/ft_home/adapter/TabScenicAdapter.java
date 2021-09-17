package net.lishaoy.ft_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import net.lishaoy.ft_home.R;
import net.lishaoy.ft_home.R2;
import net.lishaoy.ft_home.model.TabScenic;
import net.lishaoy.lib_base.lib_webview.service.wrapper.WebViewImpl;
import net.lishaoy.lib_image_loader.app.ImageLoaderManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabScenicAdapter extends RecyclerView.Adapter<TabScenicAdapter.MyViewHolder> {

    private Context mContext;
    private List<TabScenic.DataBean.ProductsBean> products;
    private List<String> ranks = new ArrayList<>();
    private List<String> describes = new ArrayList<>();

    public TabScenicAdapter(Context mContext, List<TabScenic.DataBean.ProductsBean> products) {
        this.mContext = mContext;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        for (TabScenic.DataBean.ProductsBean product : products) {
            // FIXME: 9/17/21 空指针代码;
            if (product.getTaginfos() == null) {
                for (int i = 0; i < 10; i++) {
                    ranks.add("");
                    describes.add("");
                }
                continue;
            }
            for (TabScenic.DataBean.ProductsBean.TaginfosBean taginfosBean : product.getTaginfos()) {
                if (taginfosBean.getTagType().equals("rank")) {
                    ranks.add(taginfosBean.getTagItems().get(0).getTagDesc());
                }

                if (taginfosBean.getTagType().equals("jdrq")) {
                    describes.add(taginfosBean.getTagItems().get(0).getTagName());
                }
            }
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tab_select, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        ImageLoaderManager.getInstance().displayImageForView(holder.tabSelectImg, products.get(position).getCimgurl());
        holder.tabSelectTitle.setText(products.get(position).getName());
        if (describes.get(position).isEmpty()) {
            holder.tabSelectDescribe.setVisibility(View.GONE);
        } else {
            holder.tabSelectDescribe.setText(describes.get(position));
        }
        String price = products.get(position).getPrice();
        if (!price.isEmpty() && !price.equals("0") && !price.equals("0.0000")) {
            price = products.get(position).getPrice().substring(0, products.get(position).getPrice().indexOf("."));
            holder.tabSelectPrice.setText(price);
        } else {
            holder.tabSelectPriceContainer.setVisibility(View.GONE);
        }
        holder.tabSelectRank.setText(ranks.get(position));
        holder.tabSelectContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://m.ctrip.com/webapp/you/gspoi/sight/0/" + products.get(position).getProductId() + ".html?seo=0";
                WebViewImpl.getInstance().gotoWebView(url);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tab_select_container)
        CardView tabSelectContainer;
        @BindView(R2.id.tab_select_img)
        ImageView tabSelectImg;
        @BindView(R2.id.tab_select_title)
        TextView tabSelectTitle;
        @BindView(R2.id.tab_select_describe)
        TextView tabSelectDescribe;
        @BindView(R2.id.tab_select_rank)
        TextView tabSelectRank;
        @BindView(R2.id.tab_select_price_container)
        LinearLayout tabSelectPriceContainer;
        @BindView(R2.id.tab_select_price)
        TextView tabSelectPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
