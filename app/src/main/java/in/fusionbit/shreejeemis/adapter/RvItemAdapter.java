package in.fusionbit.shreejeemis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.fusionbit.shreejeemis.apimodels.Item;
import in.fusionbit.shreejeemis.viewholder.VhItemRow;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class RvItemAdapter extends RecyclerView.Adapter {

    final Context context;

    final List<Item> items;

    public RvItemAdapter(final Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    public void addItem(final Item item) {
        items.add(item);
        notifyItemInserted(items.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return VhItemRow.create(context, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VhItemRow.bind((VhItemRow) holder, items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }
}
