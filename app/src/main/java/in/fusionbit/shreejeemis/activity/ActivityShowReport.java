package in.fusionbit.shreejeemis.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.fusionbit.shreejeemis.Constants;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.adapter.RvEfficiencyReport;
import in.fusionbit.shreejeemis.adapter.RvFormTypeReportAdapter;
import in.fusionbit.shreejeemis.adapter.RvTaskHistoryAdapter;
import in.fusionbit.shreejeemis.api.Api;
import in.fusionbit.shreejeemis.apimodels.EfficiencyReport;
import in.fusionbit.shreejeemis.apimodels.FormTypeReport;
import in.fusionbit.shreejeemis.apimodels.TaskHistory;
import in.fusionbit.shreejeemis.util.IntentExtra;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityShowReport extends ActivityBase {


    @BindView(R.id.rv_taskCompletionReport)
    RecyclerView rvTaskCompletionReport;

    @BindView(R.id.fl_noReportFound)
    FrameLayout flNoReportFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);
        ButterKnife.bind(this);

        setTitle("Report");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setupRecyclerView();

        final int reportType = getIntent().getIntExtra(IntentExtra.REPORT_TYPE, -1);

        switch (reportType) {
            case Constants.TASK_COMPLETION:
                String from = getIntent().getStringExtra(IntentExtra.FROM);
                String to = getIntent().getStringExtra(IntentExtra.TO);
                String adminIds = getIntent().getStringExtra(IntentExtra.ADMIN_IDS);
                String taskIds = getIntent().getStringExtra(IntentExtra.TASK_IDS);

                getTaskCompletionReport(from, to, adminIds, taskIds);
                break;
            case Constants.EFFICIENCY_REPORT:
                String period = getIntent().getStringExtra(IntentExtra.PERIOD);
                int adminId = getIntent().getIntExtra(IntentExtra.ADMIN_ID, -1);

                getEfficiencyReport(period, adminId);
                break;

            case Constants.FORM_TYPE_REPORT:
                String from2 = getIntent().getStringExtra(IntentExtra.FROM);
                String to2 = getIntent().getStringExtra(IntentExtra.TO);
                int adminId2 = getIntent().getIntExtra(IntentExtra.ADMIN_ID, -1);
                int formTypeId = getIntent().getIntExtra(IntentExtra.FORM_TYPE_ID, -1);

                getFormTypeReport(from2, to2, adminId2, formTypeId);
                break;
        }


    }

    private void getFormTypeReport(String from, String to, int adminId, int formTypeId) {

        showProgressDialog("Getting Report", "Please Wait...");

        final RvFormTypeReportAdapter adapter = new RvFormTypeReportAdapter(this);

        Api.Product.getFormReport(from, to, adminId, formTypeId,
                new Callback<List<FormTypeReport>>() {
                    @Override
                    public void onResponse(Call<List<FormTypeReport>> call, Response<List<FormTypeReport>> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                for (FormTypeReport report : response.body()) {
                                    adapter.addFormTypeReport(report);
                                }
                                rvTaskCompletionReport.setAdapter(adapter);
                            }
                            flNoReportFound.setVisibility(View.VISIBLE);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTitle("Report (" + adapter.getItemCount() + ")");
                            }
                        });
                        hideProgressDialog();
                    }

                    @Override
                    public void onFailure(Call<List<FormTypeReport>> call, Throwable t) {
                        if (!call.isCanceled()) {
                            Toast.makeText(ActivityShowReport.this, "Failed to get report.", Toast.LENGTH_SHORT).show();
                        }
                        if (adapter.getItemCount() == 0) {
                            flNoReportFound.setVisibility(View.VISIBLE);
                        }
                        hideProgressDialog();
                    }
                });

    }

    private void getEfficiencyReport(String period, int adminId) {
        showProgressDialog("Getting Efficiency Report", "Please Wait...");

        final RvEfficiencyReport adapter =
                new RvEfficiencyReport(ActivityShowReport.this);

        Api.Product.getEfficiencyReport(period, adminId,
                new Callback<List<EfficiencyReport>>() {
                    @Override
                    public void onResponse(Call<List<EfficiencyReport>> call, Response<List<EfficiencyReport>> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                for (EfficiencyReport report : response.body()) {
                                    adapter.addEfficiencyReport(report);
                                }
                                rvTaskCompletionReport.setAdapter(adapter);
                            }
                        }
                        if (adapter.getItemCount() == 0) {
                            flNoReportFound.setVisibility(View.VISIBLE);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTitle("Report (" + adapter.getItemCount() + ")");
                            }
                        });
                        hideProgressDialog();
                    }

                    @Override
                    public void onFailure(Call<List<EfficiencyReport>> call, Throwable t) {
                        if (!call.isCanceled()) {
                            Toast.makeText(ActivityShowReport.this, "Failed to get report.", Toast.LENGTH_SHORT).show();
                        }
                        if (adapter.getItemCount() == 0) {
                            flNoReportFound.setVisibility(View.VISIBLE);
                        }
                        hideProgressDialog();
                    }
                });
    }

    private void setupRecyclerView() {
        rvTaskCompletionReport.setLayoutManager(new LinearLayoutManager(this));
        rvTaskCompletionReport.setHasFixedSize(true);
    }

    private void getTaskCompletionReport(String from, String to, String adminIds, String taskIds) {

        final RvTaskHistoryAdapter adapter = new RvTaskHistoryAdapter(this);

        showProgressDialog("Getting Task Completion Report", "Please Wait...");

        Api.Product.getTaskCompletionReport(from, to, adminIds, taskIds,
                new Callback<List<TaskHistory>>() {
                    @Override
                    public void onResponse(Call<List<TaskHistory>> call, Response<List<TaskHistory>> response) {
                        if (response.isSuccessful()) {
                            rvTaskCompletionReport.setAdapter(adapter);
                            if (response.body() != null) {
                                for (TaskHistory task : response.body()) {
                                    adapter.addTaskHistoryItem(task);
                                }
                            }
                        }
                        if (adapter.getItemCount() == 0) {
                            flNoReportFound.setVisibility(View.VISIBLE);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setTitle("Report (" + adapter.getItemCount() + ")");
                            }
                        });
                        hideProgressDialog();
                    }

                    @Override
                    public void onFailure(Call<List<TaskHistory>> call, Throwable t) {
                        if (!call.isCanceled()) {
                            Toast.makeText(ActivityShowReport.this, "Failed to get report.", Toast.LENGTH_SHORT).show();
                        }
                        if (adapter.getItemCount() == 0) {
                            flNoReportFound.setVisibility(View.VISIBLE);
                        }
                        hideProgressDialog();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void generateTaskCompletionReport(final Context context, final String from, final String to,
                                                    final String adminids, final String taskids) {

        Intent i = new Intent(context, ActivityShowReport.class);
        i.putExtra(IntentExtra.FROM, from);
        i.putExtra(IntentExtra.TO, to);
        i.putExtra(IntentExtra.ADMIN_IDS, adminids);
        i.putExtra(IntentExtra.TASK_IDS, taskids);
        i.putExtra(IntentExtra.REPORT_TYPE, Constants.TASK_COMPLETION);
        context.startActivity(i);
    }

    public static void generateEfficiencyReport(final Context context, final String period,
                                                final int adminId) {
        Intent i = new Intent(context, ActivityShowReport.class);
        i.putExtra(IntentExtra.PERIOD, period);
        i.putExtra(IntentExtra.ADMIN_ID, adminId);
        i.putExtra(IntentExtra.REPORT_TYPE, Constants.EFFICIENCY_REPORT);
        context.startActivity(i);
    }

    public static void generateFormTypeReport(Context context, String fromDate, String toDate,
                                              int adminId, int formTypeId) {
        Intent i = new Intent(context, ActivityShowReport.class);
        i.putExtra(IntentExtra.FROM, fromDate);
        i.putExtra(IntentExtra.TO, toDate);
        i.putExtra(IntentExtra.ADMIN_ID, adminId);
        i.putExtra(IntentExtra.FORM_TYPE_ID, formTypeId);
        i.putExtra(IntentExtra.REPORT_TYPE, Constants.FORM_TYPE_REPORT);
        context.startActivity(i);
    }
}
