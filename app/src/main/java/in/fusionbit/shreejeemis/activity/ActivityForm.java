package in.fusionbit.shreejeemis.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.fusionbit.shreejeemis.R;
import in.fusionbit.shreejeemis.api.Api;
import in.fusionbit.shreejeemis.apimodels.SimpleResponse;
import in.fusionbit.shreejeemis.util.IntentExtra;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityForm extends ActivityBase {

    @BindView(R.id.et_formDate)
    TextInputEditText etFormDate;
    @BindView(R.id.et_formRemarks)
    TextInputEditText etFormRemarks;

    int formId;

    String formName;

    Calendar formDate = Calendar.getInstance();
    DatePickerDialog datePickerFormDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);

        formName = getIntent().getStringExtra(IntentExtra.FORM_NAME);

        setTitle("Add " + formName);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        formId = getIntent().getIntExtra(IntentExtra.FORM_ID, -1);

        etFormDate.setText(formDate.get(Calendar.DAY_OF_MONTH) + "/" + (formDate.get(Calendar.MONTH) + 1) + "/" +
                formDate.get(Calendar.YEAR));

        datePickerFormDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                formDate.set(Calendar.YEAR, year);
                formDate.set(Calendar.MONTH, monthOfYear);
                formDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                etFormDate.setText(date);
            }
        }, formDate.get(Calendar.YEAR), formDate.get(Calendar.MONTH),
                formDate.get(Calendar.DAY_OF_MONTH));

        //datePickerFormDate.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());

        etFormDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerFormDate.show();
            }
        });

        etFormDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    datePickerFormDate.show();
                }
            }
        });

    }


    @OnClick(R.id.btn_formAdd)
    public void onViewClicked() {
        if (fieldsValid()) {
            confirmAddForm();
        } else {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmAddForm() {
        new AlertDialog.Builder(this)
                .setTitle("Add " + formName)
                .setMessage("Are you sure you want to add " + formName)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addForm(formId, etFormDate.getText().toString(), etFormRemarks.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private boolean fieldsValid() {
        boolean isValid = true;
        if (etFormRemarks.getText().toString().trim().isEmpty()) {
            isValid = false;
            etFormRemarks.setError("Required");
        }
        return isValid;
    }

    private void addForm(int formId, String date, String remarks) {
        showProgressDialog("Adding " + formName, "Please Wait...");

        Api.Product.addForm(date, remarks, formId, new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResponse().getStatus().equals("1")) {
                            Toast.makeText(ActivityForm.this, formName + " Added Successfully",
                                    Toast.LENGTH_SHORT).show();
                            ActivityForm.this.finish();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Toast.makeText(ActivityForm.this, "Failed to add " + formName, Toast.LENGTH_SHORT).show();
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

    public static void start(final Context context, final String formName, final int formId) {
        final Intent i = new Intent(context, ActivityForm.class);
        i.putExtra(IntentExtra.FORM_NAME, formName);
        i.putExtra(IntentExtra.FORM_ID, formId);
        context.startActivity(i);
    }
}
