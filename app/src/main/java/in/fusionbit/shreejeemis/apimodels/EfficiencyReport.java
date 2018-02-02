package in.fusionbit.shreejeemis.apimodels;

/**
 * Created by rutvikmehta on 31/01/18.
 */

public class EfficiencyReport {
    /**
     * task_id : 3
     * task_name : Fixed Deposits
     * task_unit_name : Amount
     * target : 50000
     * done_tasks : null
     */

    private int task_id;
    private String task_name;
    private String task_unit_name;
    private int target;
    private int done_tasks;

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
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

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getDone_tasks() {
        return done_tasks;
    }

    public void setDone_tasks(int done_tasks) {
        this.done_tasks = done_tasks;
    }
}
