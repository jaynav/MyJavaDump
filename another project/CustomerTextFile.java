import java.io.*;
import java.util.*;

public class CustomerTextFile implements CustomerDAO
{
	private File customerFile = null;
	private final String FIELD_SEP = "\t";

	public CustomerTextFile()
	{
		customerFile = new File("customers.txt");
	}

	private void checkFile() throws IOException
	{
		// if the file doesn't exist, create it
		if (!customerFile.exists())
			customerFile.createNewFile();
	}

	private boolean saveCustomers(ArrayList<Customer> customers)
	{
		PrintWriter out = null;
		try
		{
			this.checkFile();

			// open output stream for overwriting
			out = new PrintWriter(
				  new BufferedWriter(
				  new FileWriter(customerFile)));

			// write all customers in the array list
			// to the file
			for (Customer c : customers)
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

	public ArrayList<Customer> getCustomers()
	{
		BufferedReader in = null;
		try
		{
			this.checkFile();

			in = new BufferedReader(
				 new FileReader(customerFile));

			ArrayList<Customer> customers = new ArrayList<Customer>();

			// read all customers stored in the file
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

				Customer c = new Customer(
					email, name, q1, q2, mid_term, final_term);
				customers.add(c);

				line = in.readLine();
			}
			return customers;
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

	public Customer getCustomer(String email)
	{
		ArrayList<Customer> customers = this.getCustomers();
		for (Customer c : customers)
		{
			if (c.getEmail().equals(email))
				return c;
		}
		return null;
	}

	public boolean addCustomer(Customer c)
	{
		ArrayList<Customer> customers = this.getCustomers();
	 	customers.add(c);
		return this.saveCustomers(customers);
	}

	public boolean deleteCustomer(Customer c)
	{
		ArrayList<Customer> customers = this.getCustomers();
		customers.remove(c);
		return this.saveCustomers(customers);
	}

	public boolean updateCustomer(Customer newCustomer)
	{
		ArrayList<Customer> customers = this.getCustomers();

		// get the old croduct and remove it
		Customer oldCustomer = this.getCustomer(newCustomer.getEmail());
		int i = customers.indexOf(oldCustomer);
		customers.remove(i);

		// add the updated croduct
		customers.add(i, newCustomer);

		return this.saveCustomers(customers);
	}
}