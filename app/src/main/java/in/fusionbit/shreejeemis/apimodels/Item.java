package in.fusionbit.shreejeemis.apimodels;

import java.util.Date;

/**
 * Created by rutvikmehta on 29/01/18.
 */

public class Item {
    /**
     * target_id : 4
     * task_id : 4
     * admin_id : 12
     * target : 10
     * created_by : 12
     * last_updated_by : 12
     * date_added : 2018-01-24 18:19:07
     * date_modified : 2018-01-24 18:19:07
     * period : 2018-01-01
     * task_name : Bike
     * task_unit_name : Qty
     * done_tasks : 5
     */

    private int target_id;
    private int task_id;
    private int admin_id;
    private int target;
    private int created_by;
    private int last_updated_by;
    private Date date_added;
    private Date date_modified;
    private Date period;
    private String task_name;
    private String task_unit_name;
    private int done_tasks;

    public int getTarget_id() {
        return target_id;
    }

    public void setTarget_id(int target_id) {
        this.target_id = target_id;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public int getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(int last_updated_by) {
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

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
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

    public int getDone_tasks() {
        return done_tasks;
    }

    public void setDone_tasks(int done_tasks) {
        this.done_tasks = done_tasks;
    }
}
