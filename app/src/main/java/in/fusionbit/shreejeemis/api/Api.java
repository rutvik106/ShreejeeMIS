package in.fusionbit.shreejeemis.api;


import java.util.List;

import in.fusionbit.shreejeemis.App;
import in.fusionbit.shreejeemis.apimodels.EfficiencyReport;
import in.fusionbit.shreejeemis.apimodels.FormTypeReport;
import in.fusionbit.shreejeemis.apimodels.Item;
import in.fusionbit.shreejeemis.apimodels.SimpleResponse;
import in.fusionbit.shreejeemis.apimodels.TaskHistory;
import in.fusionbit.shreejeemis.apimodels.UserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by rutvikmehta on 18/12/17.
 */

public class Api {

    public static class User {
        private static ApiInterface.User user =
                ApiClient.getClient().create(ApiInterface.User.class);

        public static Call<UserModel> tryLogin(final String usrename, final String password,
                                               final Callback<UserModel> callback) {
            Call<UserModel> call = user.tryLogin("try_login", usrename, password);
            call.enqueue(callback);
            return call;
        }
    }

    public static class Product {
        private static ApiInterface.Product product =
                ApiClient.getClient().create(ApiInterface.Product.class);

        public static Call<List<Item>> getTargetProducts(final Callback<List<Item>> callback) {
            Call<List<Item>> call = product.getTargetProducts("list_product_target",
                    App.getCurrentUser().getUser().getSession_id());
            call.enqueue(callback);
            return call;
        }

        public static Call<SimpleResponse> insertTask(final int taskId,
                                                      final String date,
                                                      final int amountOrQty,
                                                      final String remarks,
                                                      final Callback<SimpleResponse> callback) {
            Call<SimpleResponse> call = product.insertTask("add_task_completion", taskId, date,
                    amountOrQty,
                    remarks,
                    App.getCurrentUser().getUser().getSession_id());
            call.enqueue(callback);
            return call;
        }

        public static Call<List<TaskHistory>> getTaskHistory(final int taskId,
                                                             final Callback<List<TaskHistory>> callback) {
            Call<List<TaskHistory>> call = product.getTaskHistory("task_completion_history", taskId,
                    App.getCurrentUser().getUser().getSession_id());
            call.enqueue(callback);
            return call;
        }

        public static Call<List<TaskHistory>> getTaskCompletionReport(final String from,
                                                                      final String to,
                                                                      final String adminIds,
                                                                      final String taskIds,
                                                                      final Callback<List<TaskHistory>> callback) {
            Call<List<TaskHistory>> call = product.getTaskCompletionReport("task_completion_report",
                    from,
                    to,
                    adminIds,
                    taskIds,
                    App.getCurrentUser().getUser().getSession_id());
            call.enqueue(callback);
            return call;
        }


        public static Call<List<EfficiencyReport>> getEfficiencyReport(final String period,
                                                                       final int adminId,
                                                                       final Callback<List<EfficiencyReport>> callback) {
            Call<List<EfficiencyReport>> call = product.getEfficiencyReport("task_effi_report",
                    period, adminId, App.getCurrentUser().getUser().getSession_id());

            call.enqueue(callback);

            return call;
        }

        public static Call<SimpleResponse> addForm(final String formDate,
                                                   final String remarks,
                                                   final int formTypeId,
                                                   final Callback<SimpleResponse> callback) {

            Call<SimpleResponse> call = product.addForm("insert_form", formDate, remarks, formTypeId,
                    App.getCurrentUser().getUser().getSession_id());

            call.enqueue(callback);

            return call;
        }

        public static Call<List<FormTypeReport>> getFormReport(final String formDate,
                                                               final String toDate,
                                                               final int adminId,
                                                               final int formTypeId,
                                                               final Callback<List<FormTypeReport>> callback) {

            Call<List<FormTypeReport>> call = product.getFormReport("form_report", formDate, toDate, adminId,
                    formTypeId, App.getCurrentUser().getUser().getSession_id());

            call.enqueue(callback);

            return call;
        }

    }

}
