// EDIT

import java.util.Scanner;
import java.util.ArrayList;

public class StudentMaintApp
{
	// declare two class variables
	private static StudentDB studentDAO = null;
	private static Scanner sc = null;

	public static void main(String args[])
	{
		// display a welcome message
		System.out.println("Welcome to the Students Manager");
		System.out.println("");


		// set the class variables
		studentDAO = new StudentDB();
		sc = new Scanner(System.in);

		// display the command menu
		displayMenu();

		// perform 1 or more actions
		String action = "";
		while (!action.equalsIgnoreCase("exit"))
		{
			// get the input from the user
			action = Validator.getString(sc,
				"Enter a command: ");
			System.out.println();

			if (action.equalsIgnoreCase("list"))
				displayAllStudents();
			else if (action.equalsIgnoreCase("add"))
				addStudent();
			else if (action.equalsIgnoreCase("update"))
				updateStudent();
			else if (action.equalsIgnoreCase("del") || action.equalsIgnoreCase("delete"))
				deleteStudent();
			else if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("menu"))
				displayMenu();
			else if (action.equalsIgnoreCase("exit"))
				System.out.println("Bye.\n");
			else
				System.out.println("Error! Not a valid command.\n");
		}
	}

	public static void displayMenu()
	{
		System.out.println("COMMAND MENU");
		System.out.println("list    - List all students");
		System.out.println("add     - Add a student");
		System.out.println("update  - Update a student");
		System.out.println("del     - Delete a student");
		System.out.println("help    - Show this menu");
		System.out.println("exit    - Exit this application\n");
	}

	public static void displayAllStudents()
	{
		System.out.println("STUDENT LIST");

		ArrayList<Student> students = studentDAO.getStudents();
		Student c = null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < students.size(); i++)
		{
			c = students.get(i);
			sb.append
			(
				StringUtils.padWithSpaces(
					c.getEmail(), 20) +
				StringUtils.padWithSpaces(
					c.getName(), 20) +
				StringUtils.padWithSpaces(
					c.getQ1(), 10) +
				StringUtils.padWithSpaces(
					c.getQ2(), 10) +
				StringUtils.padWithSpaces(
					c.getMID_TERM(), 10) +
				StringUtils.padWithSpaces(
					c.getFINAL_TERM(), 10) + "\n"
			);
		}
		System.out.println(sb.toString());
	}

	public static void addStudent()
	{
		String email = Validator.getEmail(
			sc, "Enter student email address: ");
		String name = Validator.getRequiredLine(
			sc, "Enter name: ");
		String q1 = Validator.getRequiredLine(
			sc, "Enter Q1: ");
		String q2 = Validator.getRequiredLine(
			sc, "Enter Q2: ");
		String mid_term = Validator.getRequiredLine(
			sc, "Enter Mid Term: ");
		String final_term = Validator.getRequiredLine(
			sc, "Enter Final Term: ");

		Student student = new Student();
		student.setEmail(email);
		student.setName(name);
		student.setQ1(q1);
		student.setQ2(q2);
		student.setMID_TERM(mid_term);
		student.setFINAL_TERM(final_term);
		studentDAO.addStudent(student);

		System.out.println();
		System.out.println(name+ " " + ""
			+ " was added to the database.\n");
	}

	public static void deleteStudent()
	{
		String email = Validator.getRequiredString(sc,
			"Enter student email to delete: ");

		Student c = studentDAO.getStudent(email);

		System.out.println();
		if (c != null)
		{
			boolean isDeleted = studentDAO.deleteStudent(c);
			if (isDeleted)
			{
				System.out.println(c.getName() + " " + ""
					+ " was deleted from the database.\n");
			}
			else
			{
				System.out.println("Unable to delete: "
					+ c.getName() + " " + "" + " \n");
			}
		}
		else
		{
			System.out.println("No student matches that email address.\n");
		}
	}

	public static void updateStudent()
	{
		String email = Validator.getRequiredString(sc,
			"Enter student email to update: ");
		System.out.println();

		Student c = studentDAO.getStudent(email);

		if (c != null)
		{
			String name = Validator.getRequiredLine(
				sc, "Enter new name: ");

			c.setName(name);

			// ----
						String q1 = Validator.getRequiredLine(
						sc, "Enter new Q1: ");

							c.setQ1(q1);


						String q2 = Validator.getRequiredLine(
							sc, "Enter new Q2: ");

							c.setQ2(q2);

						String mid_term = Validator.getRequiredLine(
							sc, "Enter new mid_term: ");

							c.setMID_TERM(mid_term);

						String final_term = Validator.getRequiredLine(
							sc, "Enter new FINAL_TERM: ");

							c.setFINAL_TERM(final_term);

			// ---

			boolean isUpdated = studentDAO.updateStudent(c);
			if (isUpdated)
			{
				System.out.println("The name for " + c.getEmail()
					+ " was updated to " + c.getName() + " " + "" + "\n");

			}
			else
			{
				System.out.println("Unable to updated: "
					+ c.getEmail() + " \n");
			}
		}
		else
		{
			System.out.println("No student matches that email address.\n");
		}
	}
}