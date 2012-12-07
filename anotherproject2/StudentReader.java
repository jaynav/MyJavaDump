import java.util.ArrayList;

public interface StudentReader
{
	Student getStudent(String emailAddress);
	ArrayList<Student> getStudents();
}