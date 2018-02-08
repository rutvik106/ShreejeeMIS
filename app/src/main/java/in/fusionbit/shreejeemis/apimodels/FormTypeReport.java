package in.fusionbit.shreejeemis.apimodels;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rutvikmehta on 08/02/18.
 */

public class FormTypeReport {
    /**
     * form_id : 3
     * form_date : 2018-02-01
     * remarks : this is a test updated
     * form_type_id : 4
     * created_by : 12
     * last_updated_by : 12
     * date_added : 2013-06-08 17:58:52
     * date_modified : 2013-06-08 17:58:52
     * admin_id : 12
     * admin_email : mostpatel@gmail.com
     * admin_name : jeet
     * admin_username : jeet
     * admin_password : 2aSOXQ0Gkx5NA
     * last_login : 2018-02-08 16:03:56
     * admin_hash : 2a10820487a25b115131673d3c
     * is_active : 1
     * type : 0
     */

    private int form_id;
    private Date form_date;
    private String remarks;
    private int form_type_id;
    private int created_by;
    private int last_updated_by;
    private Date date_added;
    private Date date_modified;
    private int admin_id;
    private String admin_email;
    private String admin_name;
    private String admin_username;
    private String admin_password;
    private String last_login;
    private String admin_hash;
    private int is_active;
    private int type;

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public String getForm_date() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(form_date);
    }

    public void setForm_date(Date form_date) {
        this.form_date = form_date;
    }

    public String getRemarks() {
        if (remarks == null) {
            return "N/A";
        }
        if (remarks.isEmpty()) {
            return "N/A";
        }
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getForm_type_id() {
        return form_type_id;
    }

    public void setForm_type_id(int form_type_id) {
        this.form_type_id = form_type_id;
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
