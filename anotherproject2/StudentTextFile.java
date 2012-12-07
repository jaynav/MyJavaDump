import java.io.*;
import java.util.*;

public class StudentTextFile implements StudentDAO
{
	private File studentFile = null;
	private final String FIELD_SEP = "\t";

	public StudentTextFile()
	{
		studentFile = new File("students.txt");
	}

	private void checkFile() throws IOException
	{
		// if the file doesn't exist, create it
		if (!studentFile.exists())
			studentFile.createNewFile();
	}

	private boolean saveStudents(ArrayList<Student> students)
	{
		PrintWriter out = null;
		try
		{
			this.checkFile();

			// open output stream for overwriting
			out = new PrintWriter(
				  new BufferedWriter(
				  new FileWriter(studentFile)));

			// write all students in the array list
			// to the file
			for (Student c : students)
			{
				out.print(c.getEmail() + FIELD_SEP);
				out.print(c.getName() + FIELD_SEP);
				out.print(c.getQ1() + FIELD_SEP);
				out.print(c.getQ2() + FIELD_SEP);
				out.print(c.getMID_TERM() + FIELD_SEP);
				out.print(c.getFINAL_TERM());
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			return false;
		}
		finally
		{
			this.close(out);
		}
		return true;
	}

	private void close(Closeable stream)
	{
		try
		{
			if (stream != null)
				stream.close();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	public ArrayList<Student> getStudents()
	{
		BufferedReader in = null;
		try
		{
			this.checkFile();

			in = new BufferedReader(
				 new FileReader(studentFile));

			ArrayList<Student> students = new ArrayList<Student>();

			// read all students stored in the file
			// into the array list
			String line = in.readLine();
			while(line != null)
			{
				String[] columns = line.split(FIELD_SEP);
				String email = columns[0];
				String name = columns[1];
				String q1 = columns[2];
				String q2 = columns[3];
				String mid_term = columns[4];
				String final_term = columns[5];

				Student c = new Student(
					email, name, q1, q2, mid_term, final_term);
				students.add(c);

				line = in.readLine();
			}
			return students;
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
			return null;
		}
		finally
		{
			this.close(in);
		}
	}

	public Student getStudent(String email)
	{
		ArrayList<Student> students = this.getStudents();
		for (Student c : students)
		{
			if (c.getEmail().equals(email))
				return c;
		}
		return null;
	}

	public boolean addStudent(Student c)
	{
		ArrayList<Student> students = this.getStudents();
	 	students.add(c);
		return this.saveStudents(students);
	}

	public boolean deleteStudent(Student c)
	{
		ArrayList<Student> students = this.getStudents();
		students.remove(c);
		return this.saveStudents(students);
	}

	public boolean updateStudent(Student newStudent)
	{
		ArrayList<Student> students = this.getStudents();

		// get the old croduct and remove it
		Student oldStudent = this.getStudent(newStudent.getEmail());
		int i = students.indexOf(oldStudent);
		students.remove(i);

		// add the updated croduct
		students.add(i, newStudent);

		return this.saveStudents(students);
	}
}