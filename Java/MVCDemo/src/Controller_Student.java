
public class Controller_Student {
    private Model_Student model;
    private View_Student view;

    public Controller_Student(Model_Student model, View_Student view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        this.model.setName(name);
    }

    public String getStudentName() {
        return this.model.getName();
    }

    public void setStudentRollNum(String rollNum) {
        this.model.setRollNum(rollNum);
    }

    public String getStudentRollNum() {
        return this.model.getRollNum();
    }

    public void updateView() {
        this.view.printStudentDetails(this.model.getName(),
                this.model.getRollNum());
    }

}
