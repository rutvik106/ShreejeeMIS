package in.fusionbit.shreejeemis.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.apimodels.TaskHistory;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class VhHistoryItem extends RecyclerView.ViewHolder {

    private final Context context;
    @BindView(R.id.tv_historyProductName)
    TextView tvHistoryProductName;
    @BindView(R.id.tv_historyDate)
    TextView tvHistoryDate;
    @BindView(R.id.tv_historyQtyOrAmount)
    TextView tvHistoryQtyOrAmount;
    @BindView(R.id.tv_historyRemark)
    TextView tvHistoryRemark;
    @BindView(R.id.tv_historyTaskDoneBy)
    TextView tvHistoryTaskDoneBy;

    private TaskHistory model;

    private VhHistoryItem(final Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
    }

    public static VhHistoryItem create(final Context context, final ViewGroup parent) {
        return new VhHistoryItem(context, LayoutInflater.from(context)
                .inflate(R.layout.task_history_row, parent, false));
    }

    public static void bind(VhHistoryItem vh, TaskHistory model) {
        vh.model = model;
        vh.tvHistoryProductName.setText(model.getTask_name());
        vh.tvHistoryDate.setText(model.getCompletion_date());
        vh.tvHistoryQtyOrAmount.setText(model.getQty() + "");
        vh.tvHistoryRemark.setText(model.getRemarks());
        vh.tvHistoryTaskDoneBy.setText(model.getAdmin_username());
    }

}
