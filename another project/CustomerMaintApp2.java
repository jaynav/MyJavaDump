// EDIT

import java.util.Scanner;
import java.util.ArrayList;

public class CustomerMaintApp2
{
	// declare two class variables
	private static CustomerDB customerDAO = null;
	private static Scanner sc = null;

	public static void main(String args[])
	{
		// display a welcome message
		System.out.println("Welcome to the Students Manager");
		System.out.println("");


		// set the class variables
		customerDAO = new CustomerDB();
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
				displayAllCustomers();
			else if (action.equalsIgnoreCase("add"))
				addCustomer();
			else if (action.equalsIgnoreCase("update"))
				updateCustomer();
			else if (action.equalsIgnoreCase("del") || action.equalsIgnoreCase("delete"))
				deleteCustomer();
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

	public static void displayAllCustomers()
	{
		System.out.println("STUDENT LIST");

		ArrayList<Customer> customers = customerDAO.getCustomers();
		Customer c = null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < customers.size(); i++)
		{
			c = customers.get(i);
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

	public static void addCustomer()
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

		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setName(name);
		customer.setQ1(q1);
		customer.setQ2(q2);
		customer.setMID_TERM(mid_term);
		customer.setFINAL_TERM(final_term);
		customerDAO.addCustomer(customer);

		System.out.println();
		System.out.println(name+ " " + ""
			+ " was added to the database.\n");
	}

	public static void deleteCustomer()
	{
		String email = Validator.getRequiredString(sc,
			"Enter student email to delete: ");

		Customer c = customerDAO.getCustomer(email);

		System.out.println();
		if (c != null)
		{
			boolean isDeleted = customerDAO.deleteCustomer(c);
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

	public static void updateCustomer()
	{
		String email = Validator.getRequiredString(sc,
			"Enter student email to update: ");
		System.out.println();

		Customer c = customerDAO.getCustomer(email);

		if (c != null)
		{
			String name = Validator.getRequiredLine(
				sc, "Enter new name: ");

			c.setName(name);

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
		

			boolean isUpdated = customerDAO.updateCustomer(c);
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