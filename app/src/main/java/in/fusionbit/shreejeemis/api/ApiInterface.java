package in.fusionbit.shreejeemis.api;

import java.util.List;

import in.fusionbit.shreejeemis.apimodels.EfficiencyReport;
import in.fusionbit.shreejeemis.apimodels.Item;
import in.fusionbit.shreejeemis.apimodels.SimpleResponse;
import in.fusionbit.shreejeemis.apimodels.TaskHistory;
import in.fusionbit.shreejeemis.apimodels.UserModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by rutvikmehta on 18/12/17.
 */

public interface ApiInterface {

    interface User {
        @FormUrlEncoded
        @POST("webservice.php")
        Call<UserModel> tryLogin(@Field("method") String method,
                                 @Field("username") String username,
                                 @Field("password") String password);
    }

    interface Product {

        @FormUrlEncoded
        @POST("webservice.php")
        Call<List<Item>> getTargetProducts(@Field("method") String method,
                                           @Field("session_id") String sessionId);

        //add_task_completion
        //completion_date
        //remarks
        //task_id
        //qty
        //session_id

        @FormUrlEncoded
        @POST("webservice.php")
        Call<SimpleResponse> insertTask(@Field("method") String method,
                                        @Field("task_id") int taskId,
                                        @Field("completion_date") String completionDate,
                                        @Field("qty") int qty,
                                        @Field("remarks") String remarks,
                                        @Field("session_id") String sessionId);

        @FormUrlEncoded
        @POST("webservice.php")
        Call<List<TaskHistory>> getTaskHistory(@Field("method") String method,
                                               @Field("task_id") int taskId,
                                               @Field("session_id") String sessionId);

        //task_completion_report
        ////from
        ////to
        ////admin_id
        ////task_id
        @FormUrlEncoded
        @POST("webservice.php")
        Call<List<TaskHistory>> getTaskCompletionReport(@Field("method") String method,
                                                        @Field("from") String from,
                                                        @Field("to") String to,
                                                        @Field("admin_id") String adminIds,
                                                        @Field("task_id") String taskIds,
                                                        @Field("session_id") String sessionId);

        //task_effi_report
        ////period
        ////admin_id
        @FormUrlEncoded
        @POST("webservice.php")
        Call<List<EfficiencyReport>> getEfficiencyReport(@Field("method") String method,
                                                         @Field("period") String period,
                                                         @Field("admin_id") int adminId,
                                                         @Field("session_id") String sessionId);

        //insert_form
        ////form_date
        ////remarks
        ////form_type_id
        @FormUrlEncoded
        @POST("webservice.php")
        Call<SimpleResponse> addForm(@Field("method") String method,
                                     @Field("form_date") String formDate,
                                     @Field("remarks") String remarks,
                                     @Field("form_type_id") int formTypeId,
                                     @Field("session_id") String sessionId);

    }

}
