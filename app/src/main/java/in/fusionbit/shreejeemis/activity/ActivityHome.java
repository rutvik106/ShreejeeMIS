package in.fusionbit.shreejeemis.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.fusionbit.shreejeemis.Constants;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.adapter.RvItemAdapter;
import in.fusionbit.shreejeemis.api.Api;
import in.fusionbit.shreejeemis.apimodels.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityHome extends ActivityBase {

    @BindView(R.id.rv_items)
    RecyclerView rvItems;

    RvItemAdapter adapter;
    @BindView(R.id.srl_refreshTargetItems)
    SwipeRefreshLayout srlRefreshTargetItems;

    public static final int REQUEST_ADD_TASK = 3213;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setTitle("Targets This Month");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Targets This Month");
        }


        srlRefreshTargetItems.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        srlRefreshTargetItems.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTargetProducts();
            }
        });

        setupRecyclerView();

        getTargetProducts();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_reports) {

            final List<String> options = new ArrayList<>();
            options.add("Task Completion");
            options.add("Monthly Efficiency");

            new AlertDialog.Builder(this)
                    .setAdapter(new ArrayAdapter<String>(ActivityHome.this, android.R.layout.simple_list_item_1, options),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int index) {
                                    switch (index) {
                                        case 0:
                                            ActivityReports.start(ActivityHome.this, Constants.TASK_COMPLETION);
                                            //Toast.makeText(ActivityHome.this, "Task Completion", Toast.LENGTH_SHORT).show();
                                            break;
                                        case 1:
                                            ActivityReports.start(ActivityHome.this, Constants.EFFICIENCY_REPORT);
                                            //Toast.makeText(ActivityHome.this, "Monthly Efficiency", Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }
                            })
                    .setTitle("Select Report")
                    .show();
        }
        return true;
    }

    private void setupRecyclerView() {
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        rvItems.setHasFixedSize(true);
        adapter = new RvItemAdapter(this);
        rvItems.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        confirmLogout();
    }

    private void confirmLogout() {

        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Do you want to Logout?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();

    }

    private void getTargetProducts() {
        srlRefreshTargetItems.setRefreshing(true);
        adapter.clear();
        showProgressDialog("Getting Task...", "Please Wait...");
        Api.Product.getTargetProducts(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        for (Item item : response.body()) {
                            adapter.addItem(item);
                        }
                    }
                }
                hideProgressDialog();
                srlRefreshTargetItems.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                hideProgressDialog();
                Toast.makeText(ActivityHome.this, "Failed to get tasks.", Toast.LENGTH_SHORT).show();
                srlRefreshTargetItems.setRefreshing(false);
            }
        });
    }

    public static void start(final Context context) {
        final Intent i = new Intent(context, ActivityHome.class);
        context.startActivity(i);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_ADD_TASK) {
            if (resultCode == RESULT_OK) {
                getTargetProducts();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
