package in.fusionbit.shreejeemis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.fusionbit.shreejeemis.apimodels.FormTypeReport;
import in.fusionbit.shreejeemis.viewholder.VhFormReportItem;

/**
 * Created by rutvikmehta on 08/02/18.
 */

public class RvFormTypeReportAdapter extends RecyclerView.Adapter {

    final Context context;

    final List<FormTypeReport> formTypeReport;

    public RvFormTypeReportAdapter(final Context context) {
        this.context = context;
        formTypeReport = new ArrayList<>();
    }

    public void addFormTypeReport(FormTypeReport report) {
        formTypeReport.add(report);
        notifyItemInserted(formTypeReport.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return VhFormReportItem.create(context, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VhFormReportItem.bind((VhFormReportItem) holder, formTypeReport.get(position));
    }

    @Override
    public int getItemCount() {
        return formTypeReport.size();
    }
}
