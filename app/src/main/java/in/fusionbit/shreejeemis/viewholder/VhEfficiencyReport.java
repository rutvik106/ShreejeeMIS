package in.fusionbit.shreejeemis.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.activity.ActivityHome;
import in.fusionbit.shreejeemis.activity.ActivityTaskCompletion;
import in.fusionbit.shreejeemis.apimodels.EfficiencyReport;
import in.fusionbit.shreejeemis.apimodels.Item;

/**
 * Created by rutvikmehta on 31/01/18.
 */

public class VhEfficiencyReport extends RecyclerView.ViewHolder {

    final Context context;

    EfficiencyReport model;

    @BindView(R.id.tv_itemName)
    TextView tvItemName;
    @BindView(R.id.tv_target)
    TextView tvTarget;
    @BindView(R.id.ll_targetItem)
    LinearLayout llTargetItem;

    private VhEfficiencyReport(final Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
    }

    public static VhEfficiencyReport create(final Context context, final ViewGroup parent) {
        return new VhEfficiencyReport(context, LayoutInflater.from(context)
                .inflate(R.layout.item_row, parent, false));
    }

    public static void bind(VhEfficiencyReport vh, EfficiencyReport model) {
        vh.model = model;
        vh.tvItemName.setText(model.getTask_name());

        final String targetText =
                model.getTask_unit_name() + " " + model.getDone_tasks() + "/" + model.getTarget();

        vh.tvTarget.setText(targetText);

        if (model.getDone_tasks() >= model.getTarget() && model.getTarget() > 0) {
            vh.tvTarget.setTextColor(vh.context.getResources().getColor(R.color.green));
            vh.llTargetItem.setBackground(vh.context.getResources()
                    .getDrawable(R.drawable.card_green));
        } else if (model.getTarget() > 0) {
            vh.tvTarget.setTextColor(vh.context.getResources().getColor(R.color.colorAccent));
            vh.llTargetItem.setBackground(vh.context.getResources()
                    .getDrawable(R.drawable.card_orange));
        } else {
            vh.tvTarget.setTextColor(vh.context.getResources().getColor(R.color.colorPrimary));
            vh.llTargetItem.setBackground(vh.context.getResources()
                    .getDrawable(R.drawable.card_grey));
        }
    }


}
