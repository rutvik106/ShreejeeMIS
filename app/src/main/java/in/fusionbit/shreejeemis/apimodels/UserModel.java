package in.fusionbit.shreejeemis.apimodels;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by rutvikmehta on 18/12/17.
 */

public class UserModel {

    /**
     * user : {"admin_name":"jeet","admin_id":"12","admin_rights":["6","7","199"],"admin_logged_in":true,"session_id":"d1adfbdb838bcada580004188329ed96"}
     * user_list : [{"0":"12","admin_id":"12","1":"mostpatel@gmail.com","admin_email":"mostpatel@gmail.com","2":"jeet","admin_name":"jeet","3":"jeet","admin_username":"jeet","4":"2aSOXQ0Gkx5NA","admin_password":"2aSOXQ0Gkx5NA","5":"2017-12-22 20:41:04","last_login":"2017-12-22 20:41:04","6":"2013-06-08 17:58:52","date_added":"2013-06-08 17:58:52","7":"2013-06-08 17:58:52","date_modified":"2013-06-08 17:58:52","8":"2a10820487a25b115131673d3c","admin_hash":"2a10820487a25b115131673d3c","9":"1","is_active":"1","10":"0","type":"0"},{"0":"66","admin_id":"66","1":"rutvik@gmail.com","admin_email":"rutvik@gmail.com","2":"rutvik","admin_name":"rutvik","3":"rutvik","admin_username":"rutvik","4":"2amnt8ZYJY9RY","admin_password":"2amnt8ZYJY9RY","5":"2017-12-22 20:37:18","last_login":"2017-12-22 20:37:18","6":"2017-12-22 20:37:18","date_added":"2017-12-22 20:37:18","7":"2017-12-22 20:37:18","date_modified":"2017-12-22 20:37:18","8":"2a107ccaaa6051a12323840273","admin_hash":"2a107ccaaa6051a12323840273","9":"1","is_active":"1","10":"0","type":"0"},{"0":"65","admin_id":"65","1":"jasanisanket24@gmail.com","admin_email":"jasanisanket24@gmail.com","2":"Sanket Jasani","admin_name":"Sanket Jasani","3":"sanket","admin_username":"sanket","4":"2aSOXQ0Gkx5NA","admin_password":"2aSOXQ0Gkx5NA","5":"2017-12-22 20:30:47","last_login":"2017-12-22 20:30:47","6":"2017-12-22 20:30:47","date_added":"2017-12-22 20:30:47","7":"2017-12-22 20:30:47","date_modified":"2017-12-22 20:30:47","8":"2a10d6d85d9c72825f396d558c","admin_hash":"2a10d6d85d9c72825f396d558c","9":"1","is_active":"1","10":"0","type":"0"}]
     */

    private UserBean user;
    private List<UserListBean> user_list;
    private List<TaskListBean> task_list;
    private List<PeriodListBean> period_list;
    private List<FormTypeListBean> form_type_list;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<UserListBean> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserListBean> user_list) {
        this.user_list = user_list;
    }

    public List<TaskListBean> getTask_list() {
        return task_list;
    }

    public void setTask_list(List<TaskListBean> task_list) {
        this.task_list = task_list;
    }

    public List<PeriodListBean> getPeriod_list() {
        return period_list;
    }

    public void setPeriod_list(List<PeriodListBean> period_list) {
        this.period_list = period_list;
    }

    public List<FormTypeListBean> getForm_type_list() {
        return form_type_list;
    }

    public void setForm_type_list(List<FormTypeListBean> form_type_list) {
        this.form_type_list = form_type_list;
    }

    public static class UserBean {
        /**
         * admin_name : jeet
         * admin_id : 12
         * admin_rights : ["6","7","199"]
         * admin_logged_in : true
         * session_id : d1adfbdb838bcada580004188329ed96
         */

        private String admin_name;
        private String admin_id;
        private boolean admin_logged_in;
        private String session_id;
        private List<String> admin_rights;

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public boolean isAdmin_logged_in() {
            return admin_logged_in;
        }

        public void setAdmin_logged_in(boolean admin_logged_in) {
            this.admin_logged_in = admin_logged_in;
        }

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public List<String> getAdmin_rights() {
            return admin_rights;
        }

        public void setAdmin_rights(List<String> admin_rights) {
            this.admin_rights = admin_rights;
        }
    }

    public static class UserListBean {
        /**
         * 0 : 12
         * admin_id : 12
         * 1 : mostpatel@gmail.com
         * admin_email : mostpatel@gmail.com
         * 2 : jeet
         * admin_name : jeet
         * 3 : jeet
         * admin_username : jeet
         * 4 : 2aSOXQ0Gkx5NA
         * admin_password : 2aSOXQ0Gkx5NA
         * 5 : 2017-12-22 20:41:04
         * last_login : 2017-12-22 20:41:04
         * 6 : 2013-06-08 17:58:52
         * date_added : 2013-06-08 17:58:52
         * 7 : 2013-06-08 17:58:52
         * date_modified : 2013-06-08 17:58:52
         * 8 : 2a10820487a25b115131673d3c
         * admin_hash : 2a10820487a25b115131673d3c
         * 9 : 1
         * is_active : 1
         * 10 : 0
         * type : 0
         */

        private int admin_id;
        private String admin_email;
        private String admin_name;
        private String admin_username;
        private String admin_password;
        private String last_login;
        private String date_added;
        private String date_modified;
        private String admin_hash;
        private int is_active;
        private int type;

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public String getAdmin_email() {
            return admin_email;
        }

        public void setAdmin_email(String admin_email) {
            this.admin_email = admin_email;
        }

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public String getAdmin_username() {
            return admin_username;
        }

        public void setAdmin_username(String admin_username) {
            this.admin_username = admin_username;
        }

        public String getAdmin_password() {
            return admin_password;
        }

        public void setAdmin_password(String admin_password) {
            this.admin_password = admin_password;
        }

        public String getLast_login() {
            return last_login;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public String getDate_added() {
            return date_added;
        }

        public void setDate_added(String date_added) {
            this.date_added = date_added;
        }

        public String getDate_modified() {
            return date_modified;
        }

        public void setDate_modified(String date_modified) {
            this.date_modified = date_modified;
        }

        public String getAdmin_hash() {
            return admin_hash;
        }

        public void setAdmin_hash(String admin_hash) {
            this.admin_hash = admin_hash;
        }

        public int getIs_active() {
            return is_active;
        }

        public void setIs_active(int is_active) {
            this.is_active = is_active;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class TaskListBean {
        /**
         * task_id : 4
         * task_name : Bike
         * task_unit_id : 1
         * task_unit_name : Qty
         */

        private int task_id;
        private String task_name;
        private int task_unit_id;
        private String task_unit_name;

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

        public int getTask_unit_id() {
            return task_unit_id;
        }

        public void setTask_unit_id(int task_unit_id) {
            this.task_unit_id = task_unit_id;
        }

        public String getTask_unit_name() {
            return task_unit_name;
        }

        public void setTask_unit_name(String task_unit_name) {
            this.task_unit_name = task_unit_name;
        }
    }

    public static class PeriodListBean {
        /**
         * period : 2018-02-01
         */

        private Date period;

        public String getPeriod() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return dateFormat.format(period);
        }

        public void setPeriod(Date period) {
            this.period = period;
        }

        public String getDisplayDate(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy", Locale.getDefault());
            return dateFormat.format(period);
        }

    }

    public static class FormTypeListBean {
        /**
         * form_type_id : 1
         * form_type : Activity
         */

        private int form_type_id;
        private String form_type;

        public int getForm_type_id() {
            return form_type_id;
        }

        public void setForm_type_id(int form_type_id) {
            this.form_type_id = form_type_id;
        }

        public String getForm_type() {
            return form_type;
        }

        public void setForm_type(String form_type) {
            this.form_type = form_type;
        }
    }
}
