package in.fusionbit.shreejeemis.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.activity.ActivityBase;
import in.fusionbit.shreejeemis.activity.ActivityTaskCompletion;
import in.fusionbit.shreejeemis.api.Api;
import in.fusionbit.shreejeemis.apimodels.SimpleResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class FragmentTaskCompletion extends Fragment {

    @BindView(R.id.et_date)
    TextInputEditText etDate;
    @BindView(R.id.et_qtyAmount)
    TextInputEditText etQtyAmount;
    @BindView(R.id.et_remarks)
    TextInputEditText etRemarks;
    Unbinder unbinder;

    Calendar selectedDate = Calendar.getInstance();

    DatePickerDialog datePickerDialog;
    @BindView(R.id.btn_add)
    Button btnAdd;

    private int taskId;

    public FragmentTaskCompletion() {

    }

    public static FragmentTaskCompletion newInstance(int taskId) {

        Bundle args = new Bundle();

        FragmentTaskCompletion fragment = new FragmentTaskCompletion();
        fragment.setArguments(args);
        fragment.taskId = taskId;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_task_completion, container, false);
        unbinder = ButterKnife.bind(this, view);

        etDate.setText(selectedDate.get(Calendar.DAY_OF_MONTH) + "/" +
                (selectedDate.get(Calendar.MONTH) + 1) + "/" + selectedDate.get(Calendar.YEAR));


        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, monthOfYear);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                etDate.setText(date);
            }
        }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH));

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        etDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    datePickerDialog.show();
                }
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        confirmAddTask();
    }

    private void confirmAddTask() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Add Task")
                .setMessage("Are you sure you want to add this task?")
                .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addTask();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    private void addTask() {
        if (fieldsValid()) {
            final int qtyOrAmount = Integer.parseInt(etQtyAmount.getText().toString().trim());

            ((ActivityBase) getActivity()).showProgressDialog("Adding Task...",
                    "Please Wait...");

            Api.Product.insertTask(taskId, etDate.getText().toString(), qtyOrAmount,
                    etRemarks.getText().toString().trim(),
                    new Callback<SimpleResponse>() {
                        @Override
                        public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    Toast.makeText(getContext(), "Task Added Successfully.", Toast.LENGTH_SHORT).show();
                                    ((ActivityTaskCompletion) getActivity()).setResult(Activity.RESULT_OK);
                                    getActivity().finish();
                                }
                            }
                            ((ActivityBase) getActivity()).hideProgressDialog();
                        }

                        @Override
                        public void onFailure(Call<SimpleResponse> call, Throwable t) {
                            ((ActivityBase) getActivity()).hideProgressDialog();
                            Toast.makeText(getContext(), "Failed to add task.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getContext(), "Required fields cannot be empty.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean fieldsValid() {
        boolean isValid = true;
        if (etQtyAmount.getText().toString().trim().isEmpty()) {
            isValid = false;
            etQtyAmount.setError("Invalid Input");
        }
        return isValid;
    }
}
