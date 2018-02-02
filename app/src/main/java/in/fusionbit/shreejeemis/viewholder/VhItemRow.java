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
import in.fusionbit.shreejeemis.App;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.activity.ActivityForm;
import in.fusionbit.shreejeemis.activity.ActivityHome;
import in.fusionbit.shreejeemis.activity.ActivityTaskCompletion;
import in.fusionbit.shreejeemis.apimodels.Item;
import in.fusionbit.shreejeemis.apimodels.UserModel;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class VhItemRow extends RecyclerView.ViewHolder {

    final Context context;

    Item model;

    @BindView(R.id.tv_itemName)
    TextView tvItemName;
    @BindView(R.id.tv_target)
    TextView tvTarget;
    @BindView(R.id.ll_targetItem)
    LinearLayout llTargetItem;
    @BindView(R.id.ll_formButtons)
    LinearLayout llFormButtons;

    private VhItemRow(final Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityTaskCompletion.startForResult((ActivityHome) context, model.getTask_id(),
                        model.getTask_name());
            }
        });

    }

    public static VhItemRow create(final Context context, final ViewGroup parent) {
        return new VhItemRow(context, LayoutInflater.from(context)
                .inflate(R.layout.item_row, parent, false));
    }

    public static void bind(final VhItemRow vh, final Item model) {
        vh.model = model;
        vh.tvItemName.setText(model.getTask_name());

        if (model.getTarget_id() == -1) {
            vh.llTargetItem.setVisibility(View.GONE);
            vh.llFormButtons.setVisibility(View.VISIBLE);

            vh.llFormButtons.removeAllViews();

            for (final UserModel.FormTypeListBean formType : App.getCurrentUser().getForm_type_list()) {
                View v = LayoutInflater.from(vh.context)
                        .inflate(R.layout.form_action, null, false);

                ((TextView) v.findViewById(R.id.tv_formActionName)).setText(formType.getForm_type());

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActivityForm.start(vh.context, formType.getForm_type(), formType.getForm_type_id());
                    }
                });

                vh.llFormButtons.addView(v);
            }


        } else {

            vh.llTargetItem.setVisibility(View.VISIBLE);
            vh.llFormButtons.setVisibility(View.GONE);

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

}
