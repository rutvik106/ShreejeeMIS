package in.fusionbit.shreejeemis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.adapter.RvTaskHistoryAdapter;
import in.fusionbit.shreejeemis.api.Api;
import in.fusionbit.shreejeemis.apimodels.TaskHistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class FragmentTaskCompletionThisMonth extends Fragment {

    @BindView(R.id.rv_taskThisMonth)
    RecyclerView rvTaskThisMonth;
    Unbinder unbinder;

    int taskId;

    RvTaskHistoryAdapter adapter;
    @BindView(R.id.fl_noTaskHistory)
    FrameLayout flNoTaskHistory;

    public FragmentTaskCompletionThisMonth() {

    }

    public static FragmentTaskCompletionThisMonth newInstance(int taskId) {

        Bundle args = new Bundle();

        FragmentTaskCompletionThisMonth fragment = new FragmentTaskCompletionThisMonth();
        fragment.setArguments(args);
        fragment.taskId = taskId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_task_completion_this_month, container, false);
        unbinder = ButterKnife.bind(this, view);

        setupRecyclerView();

        getTaskHistory();

        return view;
    }

    private void setupRecyclerView() {
        rvTaskThisMonth.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTaskThisMonth.setHasFixedSize(true);
        adapter = new RvTaskHistoryAdapter(getActivity());
        rvTaskThisMonth.setAdapter(adapter);
    }

    private void getTaskHistory() {
        Api.Product.getTaskHistory(taskId, new Callback<List<TaskHistory>>() {
            @Override
            public void onResponse(Call<List<TaskHistory>> call, Response<List<TaskHistory>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        for (TaskHistory item : response.body()) {
                            adapter.addTaskHistoryItem(item);
                        }
                    }
                }
                if (adapter.getItemCount() == 0) {
                    flNoTaskHistory.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<TaskHistory>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get Task History.", Toast.LENGTH_SHORT).show();
                if (adapter.getItemCount() == 0) {
                    flNoTaskHistory.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
