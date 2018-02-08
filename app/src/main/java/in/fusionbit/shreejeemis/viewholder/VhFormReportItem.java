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
import in.fusionbit.shreejeemis.apimodels.FormTypeReport;

/**
 * Created by rutvikmehta on 08/02/18.
 */

public class VhFormReportItem extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_formReportAdminName)
    TextView tvFormReportAdminName;
    @BindView(R.id.tv_formReportDate)
    TextView tvFormReportDate;
    @BindView(R.id.tv_formReportRemarks)
    TextView tvFormReportRemarks;
    private FormTypeReport model;

    private VhFormReportItem(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static VhFormReportItem create(final Context context, final ViewGroup parent) {
        return new VhFormReportItem(LayoutInflater.from(context)
                .inflate(R.layout.form_type_report_row, parent, false));
    }

    public static void bind(VhFormReportItem vh, FormTypeReport model) {
        vh.model = model;
        vh.tvFormReportAdminName.setText(model.getAdmin_name());
        vh.tvFormReportDate.setText(model.getForm_date());
        vh.tvFormReportRemarks.setText(model.getRemarks());
    }


}
