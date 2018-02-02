package in.fusionbit.shreejeemis.model;

/**
 * Created by rutvikmehta on 30/01/18.
 */

public class CheckSpinnerModel {

    int id;
    String label;
    boolean isChecked = false;

    public CheckSpinnerModel(final int id, final String label) {
        this.id = id;
        this.label = label;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
