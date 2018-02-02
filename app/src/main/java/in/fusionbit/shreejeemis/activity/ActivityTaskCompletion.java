package in.fusionbit.shreejeemis.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.adapter.VpAdapter;
import in.fusionbit.shreejeemis.fragment.FragmentTaskCompletion;
import in.fusionbit.shreejeemis.fragment.FragmentTaskCompletionThisMonth;
import in.fusionbit.shreejeemis.util.IntentExtra;

public class ActivityTaskCompletion extends ActivityBase {

    String taskName;

    int taskId;

    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_taskCompletion)
    ViewPager vpTaskCompletion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_completion);
        ButterKnife.bind(this);

        taskName = getIntent().getStringExtra(IntentExtra.TASK_NAME);
        taskId = getIntent().getIntExtra(IntentExtra.TASK_ID, 0);

        setTitle(taskName);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(taskName);
        }

        setupViewPager();

    }

    private void setupViewPager() {
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentTaskCompletion.newInstance(taskId), "Add Task Completion");
        adapter.addFragment(FragmentTaskCompletionThisMonth.newInstance(taskId), "Task Completions This Month");
        vpTaskCompletion.setAdapter(adapter);
        tlTabs.setupWithViewPager(vpTaskCompletion);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(final Context context, final int taskId, final String taskName) {
        final Intent intent = new Intent(context, ActivityTaskCompletion.class);
        intent.putExtra(IntentExtra.TASK_NAME, taskName);
        intent.putExtra(IntentExtra.TASK_ID, taskId);
        context.startActivity(intent);
    }

    public static void startForResult(final ActivityHome activityHome, final int taskId, final String taskName) {
        final Intent intent = new Intent(activityHome, ActivityTaskCompletion.class);
        intent.putExtra(IntentExtra.TASK_NAME, taskName);
        intent.putExtra(IntentExtra.TASK_ID, taskId);
        activityHome.startActivityForResult(intent, ActivityHome.REQUEST_ADD_TASK);
    }

}
