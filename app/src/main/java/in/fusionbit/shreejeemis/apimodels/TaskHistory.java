package in.fusionbit.shreejeemis.apimodels;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class TaskHistory {
    /**
     * 0 : 12
     * task_completion_id : 12
     * 1 : 4
     * task_id : 4
     * 2 : 2018-01-27
     * completion_date : 2018-01-27
     * 3 :
     * remarks :
     * 4 : 72
     * created_by : 72
     * 5 : 72
     * last_updated_by : 72
     * 6 : 2018-01-27 16:07:23
     * date_added : 2018-01-27 16:07:23
     * 7 : 2018-01-27 16:07:23
     * date_modified : 2018-01-27 16:07:23
     * 8 : 1
     * qty : 1
     * 9 : Bike
     * task_name : Bike
     * 10 : Qty
     * task_unit_name : Qty
     */

    private int task_completion_id;
    private int task_id;
    private Date completion_date;
    private String remarks;
    private int created_by;
    private String last_updated_by;
    private Date date_added;
    private Date date_modified;
    private int qty;
    private String task_name;
    private String task_unit_name;
    private String admin_username;

    public int getTask_completion_id() {
        return task_completion_id;
    }

    public void setTask_completion_id(int task_completion_id) {
        this.task_completion_id = task_completion_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getCompletion_date() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(completion_date);
    }

    public void setCompletion_date(Date completion_date) {
        this.completion_date = completion_date;
    }

    public String getRemarks() {
        if (remarks == null) {
            return "N/A";
        } else {
            if (remarks.isEmpty()) {
                return "N/A";
            }
            return remarks;
        }

    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public String getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public Date getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(Date date_modified) {
        this.date_modified = date_modified;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_unit_name() {
        return task_unit_name;
    }

    public void setTask_unit_name(String task_unit_name) {
        this.task_unit_name = task_unit_name;
    }

    public String getAdmin_username() {
        return admin_username;
    }
}
