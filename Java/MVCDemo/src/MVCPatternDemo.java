public class MVCPatternDemo {
    public static void main(String[] args) {

        //fetch student record based on his roll no from the database
        Model_Student model = retriveStudentFromDatabase();

        //Create a view : to write student details on console
        View_Student view = new View_Student();

        Controller_Student controller = new Controller_Student(model, view);

        controller.updateView();

        //update model data
        controller.setStudentName("John");

        controller.updateView();
    }

    private static Model_Student retriveStudentFromDatabase() {
        Model_Student student = new Model_Student();
        student.setName("Robert");
        student.setRollNum("10");
        return student;
    }
}
