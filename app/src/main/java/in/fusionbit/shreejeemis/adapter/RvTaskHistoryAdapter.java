package in.fusionbit.shreejeemis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.fusionbit.shreejeemis.apimodels.TaskHistory;
import in.fusionbit.shreejeemis.viewholder.VhHistoryItem;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class RvTaskHistoryAdapter extends RecyclerView.Adapter {

    final Context context;

    final List<TaskHistory> taskHistoryItems;

    public RvTaskHistoryAdapter(final Context context) {
        this.context = context;
        taskHistoryItems = new ArrayList<>();
    }

    public void addTaskHistoryItem(TaskHistory taskHistoryItem) {
        taskHistoryItems.add(taskHistoryItem);
        notifyItemInserted(taskHistoryItems.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return VhHistoryItem.create(context, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VhHistoryItem.bind((VhHistoryItem) holder, taskHistoryItems.get(position));
    }

    @Override
    public int getItemCount() {
        return taskHistoryItems.size();
    }
}
