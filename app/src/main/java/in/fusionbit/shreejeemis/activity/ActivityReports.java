package in.fusionbit.shreejeemis.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.fusionbit.shreejeemis.App;
import in.fusionbit.shreejeemis.Constants;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.adapter.CheckSpinnerAdapter;
import in.fusionbit.shreejeemis.apimodels.UserModel;
import in.fusionbit.shreejeemis.model.CheckSpinnerModel;
import in.fusionbit.shreejeemis.util.IntentExtra;

public class ActivityReports extends AppCompatActivity {

    @BindView(R.id.et_fromDate)
    TextInputEditText etFromDate;
    @BindView(R.id.et_toDate)
    TextInputEditText etToDate;
    @BindView(R.id.tv_selectUser)
    TextView tvSelectUser;
    @BindView(R.id.spin_users)
    Spinner spinUsers;
    @BindView(R.id.btn_generateReport)
    Button btnGenerateReport;

    Calendar fromDate = Calendar.getInstance();
    DatePickerDialog datePickerFromDate;

    Calendar toDate = Calendar.getInstance();
    DatePickerDialog datePickerToDate;

    CheckSpinnerAdapter adapter;

    CheckSpinnerAdapter itemsAdapter;
    @BindView(R.id.spin_task)
    Spinner spinTask;
    @BindView(R.id.ll_taskCompletionReport)
    LinearLayout llTaskCompletionReport;
    @BindView(R.id.spin_period)
    Spinner spinPeriod;
    @BindView(R.id.spin_users2)
    Spinner spinUsers2;
    @BindView(R.id.ll_efficiencyReport)
    LinearLayout llEfficiencyReport;
    @BindView(R.id.tv_selectUser2)
    TextView tvSelectUser2;
    @BindView(R.id.et_fromDate2)
    TextInputEditText etFromDate2;
    @BindView(R.id.et_toDate2)
    TextInputEditText etToDate2;
    @BindView(R.id.tv_selectUser3)
    TextView tvSelectUser3;
    @BindView(R.id.spin_users3)
    Spinner spinUsers3;
    @BindView(R.id.ll_FormTypeReport)
    LinearLayout llFormTypeReport;


    private int reportType = -1;

    private int formTypeId = -1;

    private ArrayAdapter<UserModel.PeriodListBean> periodSpinnerAdapter;

    private ArrayAdapter<UserModel.UserListBean> userSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        ButterKnife.bind(this);

        reportType = getIntent().getIntExtra(IntentExtra.REPORT_TYPE, -1);

        if (reportType == -1) {
            finish();
        }

        switch (reportType) {
            case Constants.TASK_COMPLETION:
                llTaskCompletionReport.setVisibility(View.VISIBLE);
                setActivityTitle("Task Completion Report");
                setupViewForTaskCompletionReport();
                break;
            case Constants.EFFICIENCY_REPORT:
                llEfficiencyReport.setVisibility(View.VISIBLE);
                setActivityTitle("Monthly Efficiency Report");
                setupViewForEfficiencyReport();
                break;
            case Constants.FORM_TYPE_REPORT:
                llFormTypeReport.setVisibility(View.VISIBLE);
                setActivityTitle(getIntent().getStringExtra(IntentExtra.FORM_TYPE_NAME) + " Report");
                formTypeId = getIntent().getIntExtra(IntentExtra.FORM_TYPE_ID, -1);
                setupViewForFormTypeReport();
                break;
        }


    }

    private void setupViewForFormTypeReport() {

        if (App.getCurrentUser().getUser().getAdmin_rights()
                .contains(Constants.UserRights.CAN_GENERATE_REPORT_FOR_OTHER_USERS) ||
                App.getCurrentUser().getUser().getAdmin_rights()
                        .contains(Constants.UserRights.FULL)) {

            tvSelectUser3.setVisibility(View.VISIBLE);
            spinUsers3.setVisibility(View.VISIBLE);

            userSpinnerAdapter = new ArrayAdapter<UserModel.UserListBean>(this,
                    android.R.layout.simple_list_item_1, App.getCurrentUser().getUser_list()) {

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(ActivityReports.this)
                                .inflate(android.R.layout.simple_list_item_1, parent, false);
                    }
                    ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getAdmin_name());
                    return convertView;
                }

                @Override
                public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(ActivityReports.this)
                                .inflate(android.R.layout.simple_list_item_1, parent, false);
                    }
                    ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getAdmin_name());
                    return convertView;
                }

            };

            spinUsers3.setAdapter(userSpinnerAdapter);
        }

        etFromDate2.setText("1/" + (fromDate.get(Calendar.MONTH) + 1) + "/" +
                fromDate.get(Calendar.YEAR));

        datePickerFromDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                fromDate.set(Calendar.YEAR, year);
                fromDate.set(Calendar.MONTH, monthOfYear);
                fromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                etFromDate2.setText(date);
            }
        }, fromDate.get(Calendar.YEAR), fromDate.get(Calendar.MONTH),
                fromDate.get(Calendar.DAY_OF_MONTH));

        //datePickerFormDate.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());

        etFromDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerFromDate.show();
            }
        });

        etFromDate2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    datePickerFromDate.show();
                }
            }
        });

        datePickerToDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                toDate.set(Calendar.YEAR, year);
                toDate.set(Calendar.MONTH, monthOfYear);
                toDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                etToDate2.setText(date);
            }
        }, toDate.get(Calendar.YEAR), toDate.get(Calendar.MONTH),
                toDate.get(Calendar.DAY_OF_MONTH));

        //datePickerToDate.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());

        etToDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerToDate.show();
            }
        });

        etToDate2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    datePickerToDate.show();
                }
            }
        });

    }

    private void setupViewForEfficiencyReport() {
        if (App.getCurrentUser().getUser().getAdmin_rights()
                .contains(Constants.UserRights.CAN_GENERATE_REPORT_FOR_OTHER_USERS) ||
                App.getCurrentUser().getUser().getAdmin_rights()
                        .contains(Constants.UserRights.FULL)) {

            tvSelectUser2.setVisibility(View.VISIBLE);
            spinUsers2.setVisibility(View.VISIBLE);

            userSpinnerAdapter = new ArrayAdapter<UserModel.UserListBean>(this,
                    android.R.layout.simple_list_item_1, App.getCurrentUser().getUser_list()) {

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(ActivityReports.this)
                                .inflate(android.R.layout.simple_list_item_1, parent, false);
                    }
                    ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getAdmin_name());
                    return convertView;
                }

                @Override
                public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(ActivityReports.this)
                                .inflate(android.R.layout.simple_list_item_1, parent, false);
                    }
                    ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getAdmin_name());
                    return convertView;
                }

            };

            spinUsers2.setAdapter(userSpinnerAdapter);
        }

        periodSpinnerAdapter = new ArrayAdapter<UserModel.PeriodListBean>(this,
                android.R.layout.simple_list_item_1, App.getCurrentUser().getPeriod_list()) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(ActivityReports.this)
                            .inflate(android.R.layout.simple_list_item_1, parent, false);
                }
                ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getDisplayDate());
                return convertView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(ActivityReports.this)
                            .inflate(android.R.layout.simple_list_item_1, parent, false);
                }
                ((TextView) convertView.findViewById(android.R.id.text1)).setText(getItem(position).getDisplayDate());
                return convertView;
            }
        };

        spinPeriod.setAdapter(periodSpinnerAdapter);
    }

    private void setupViewForTaskCompletionReport() {

        etFromDate.setText("1/" + (fromDate.get(Calendar.MONTH) + 1) + "/" +
                fromDate.get(Calendar.YEAR));

        datePickerFromDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                fromDate.set(Calendar.YEAR, year);
                fromDate.set(Calendar.MONTH, monthOfYear);
                fromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                etFromDate.setText(date);
            }
        }, fromDate.get(Calendar.YEAR), fromDate.get(Calendar.MONTH),
                fromDate.get(Calendar.DAY_OF_MONTH));

        //datePickerFormDate.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());

        etFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerFromDate.show();
            }
        });

        etFromDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    datePickerFromDate.show();
                }
            }
        });

        datePickerToDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                toDate.set(Calendar.YEAR, year);
                toDate.set(Calendar.MONTH, monthOfYear);
                toDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                etToDate.setText(date);
            }
        }, toDate.get(Calendar.YEAR), toDate.get(Calendar.MONTH),
                toDate.get(Calendar.DAY_OF_MONTH));

        //datePickerToDate.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());

        etToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerToDate.show();
            }
        });

        etToDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    datePickerToDate.show();
                }
            }
        });

        if (App.getCurrentUser().getUser().getAdmin_rights()
                .contains(Constants.UserRights.CAN_GENERATE_REPORT_FOR_OTHER_USERS) ||
                App.getCurrentUser().getUser().getAdmin_rights()
                        .contains(Constants.UserRights.FULL)) {

            tvSelectUser.setVisibility(View.VISIBLE);
            spinUsers.setVisibility(View.VISIBLE);

            final List<CheckSpinnerModel> models = new ArrayList<>();

            for (UserModel.UserListBean user : App.getCurrentUser().getUser_list()) {
                models.add(new CheckSpinnerModel(user.getAdmin_id(), user.getAdmin_name()));
            }

            adapter = new CheckSpinnerAdapter(this, 0, models);

            spinUsers.setAdapter(adapter);
        }

        final List<CheckSpinnerModel> models = new ArrayList<>();

        for (UserModel.TaskListBean task : App.getCurrentUser().getTask_list()) {
            models.add(new CheckSpinnerModel(task.getTask_id(), task.getTask_name()));
        }

        itemsAdapter = new CheckSpinnerAdapter(this, 0, models);

        spinTask.setAdapter(itemsAdapter);
    }

    private void setActivityTitle(final String title) {
        setTitle(title);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(final Context context, final int reportType) {
        Intent i = new Intent(context, ActivityReports.class);
        i.putExtra(IntentExtra.REPORT_TYPE, reportType);
        context.startActivity(i);
    }

    @OnClick(R.id.btn_generateReport)
    public void onViewClicked() {
        switch (reportType) {
            case Constants.TASK_COMPLETION:
                ActivityShowReport.generateTaskCompletionReport(this, etFromDate.getText().toString(),
                        etToDate.getText().toString(), adapter.getSelected(), itemsAdapter.getSelected());
                break;
            case Constants.EFFICIENCY_REPORT:
                final String period = periodSpinnerAdapter
                        .getItem(spinPeriod.getSelectedItemPosition()).getPeriod();
                final int adminId = userSpinnerAdapter
                        .getItem(spinUsers2.getSelectedItemPosition()).getAdmin_id();
                ActivityShowReport.generateEfficiencyReport(this, period, adminId);
                break;
            case Constants.FORM_TYPE_REPORT:
                final int adminId2 = userSpinnerAdapter
                        .getItem(spinUsers3.getSelectedItemPosition()).getAdmin_id();
                ActivityShowReport.generateFormTypeReport(this, etFromDate2.getText().toString(),
                        etToDate2.getText().toString(), adminId2, formTypeId);
                break;
        }

    }

    public static void startForActivityReport(Context context, String formTypeName, int formTypeId) {
        Intent i = new Intent(context, ActivityReports.class);
        i.putExtra(IntentExtra.REPORT_TYPE, Constants.FORM_TYPE_REPORT);
        i.putExtra(IntentExtra.FORM_TYPE_NAME, formTypeName);
        i.putExtra(IntentExtra.FORM_TYPE_ID, formTypeId);
        context.startActivity(i);
    }
}
