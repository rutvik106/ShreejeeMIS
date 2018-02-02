package in.fusionbit.shreejeemis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.fusionbit.shreejeemis.apimodels.EfficiencyReport;
import in.fusionbit.shreejeemis.viewholder.VhEfficiencyReport;

/**
 * Created by rutvikmehta on 31/01/18.
 */

public class RvEfficiencyReport extends RecyclerView.Adapter {

    private final Context context;

    private final List<EfficiencyReport> efficiencyReportList;

    public RvEfficiencyReport(final Context context) {
        this.context = context;
        efficiencyReportList = new ArrayList<>();
    }

    public void addEfficiencyReport(EfficiencyReport efficiencyReport) {
        efficiencyReportList.add(efficiencyReport);
        notifyItemInserted(efficiencyReportList.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return VhEfficiencyReport.create(context, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VhEfficiencyReport.bind((VhEfficiencyReport) holder, efficiencyReportList.get(position));
    }

    @Override
    public int getItemCount() {
        return efficiencyReportList.size();
    }
}
