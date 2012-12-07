import java.util.*;
import java.sql.*;
import java.io.*;

public class CustomerDB implements CustomerDAO
{
	private Connection connection = null;

	public CustomerDB()
	{
		this.connect();  // initializes connection
	}

	private void connect()
	{
		try
		{
			String url = "jdbc:odbc:StudentsDB";
			String username = "Admin";
			String password = "";
			connection = DriverManager.getConnection(url, username, password);
		}
		catch(SQLException e)
		{
			for (Throwable t : e)
				t.printStackTrace();
		}
	}

	public ArrayList<Customer> getCustomers()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			ArrayList<Customer> customers = new ArrayList<Customer>();

			String query = "SELECT email, name, q1, q2, mid_term, final_term "
						 + "FROM Students ORDER BY email ASC";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next())
			{
				String email = rs.getString("email");
				String name = rs.getString("name");
				String q1 = rs.getString("q1");
				String q2 = rs.getString("q2");
				String mid_term = rs.getString("mid_term");
				String final_term = rs.getString("final_term");

				Customer c = new Customer(name, email, q1, q2, mid_term, final_term);
				customers.add(c);
			}
			return customers;
		}
		catch(SQLException sqle)
		{
			//sqle.printStackTrace();  // for debugging
			return null;
		}
		finally
		{
			try
			{
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			}
			catch(SQLException sqle)
			{
				System.err.println(sqle);
			}
		}
	}

	public Customer getCustomer(String email)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			String selectCustomer =
				"SELECT email, name, q1, q2, mid_term, final_term " +
				"FROM Students " +
				"WHERE email = ?";
			ps = connection.prepareStatement(selectCustomer);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next())
			{
				String name = rs.getString("name");
				String q1 = rs.getString("q1");
				String q2 = rs.getString("q2");
				String mid_term = rs.getString("mid_term");
				String final_term = rs.getString("final_term");

				Customer c = new Customer(email, name, q1, q2, mid_term, final_term);
				return c;
			}
			else
				return null;
		}
		catch(SQLException sqle)
		{
			//sqle.printStackTrace();	// for debugging
			return null;
		}
		finally
		{
			try
			{
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			}
			catch(SQLException sqle)
			{
				System.err.println(sqle);
			}
		}
	}

	public boolean addCustomer(Customer c)
	{
		PreparedStatement ps = null;
		try
		{
			String insert =
				"INSERT INTO Students (email, name, q1, q2, mid_term, final_term) " +
				"VALUES (?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(insert);
			ps.setString(1, c.getEmail());
			ps.setString(2, c.getName());
			ps.setString(3, c.getQ1());
			ps.setString(4, c.getQ2());
			ps.setString(5, c.getMID_TERM());
			ps.setString(6, c.getFINAL_TERM());
			ps.executeUpdate();
			ps.close();
			return true;
		}
		catch(SQLException sqle)
		{
			//sqle.printStackTrace();	// for debugging
			return false;
		}
		finally
		{
			try
			{
				if (ps != null)
					ps.close();
			}
			catch(SQLException sqle)
			{
				System.err.println(sqle);
			}
		}
	}

	public boolean deleteCustomer(Customer c)
	{
		PreparedStatement ps = null;
		try
		{
			String delete =
				"DELETE FROM Students " +
				"WHERE email = ?";
			ps = connection.prepareStatement(delete);
			ps.setString(1, c.getEmail());
			ps.executeUpdate();
			ps.close();
			return true;
		}
		catch(SQLException sqle)
		{
			//sqle.printStackTrace();	// for debugging
			return false;
		}
		finally
		{
			try
			{
				if (ps != null)
					ps.close();
			}
			catch(SQLException sqle)
			{
				System.err.println(sqle);
			}
		}
	}

	public boolean updateCustomer(Customer c)
	{
		PreparedStatement ps = null;
		try
		{
		
			String update =
				"UPDATE Students SET " +
					"name = ? ," +
					"q1 = ? ,"  +
					"q2 = ? ," +
					"mid_term = ? ," +
					"final_term = ? " +
					"WHERE email = ?";
				


			ps = connection.prepareStatement(update);
			ps.setString(1, c.getName());
			ps.setString(2, c.getQ1());
			ps.setString(3, c.getQ2());
			ps.setString(4, c.getMID_TERM());
			ps.setString(5, c.getFINAL_TERM());
			ps.setString(6, c.getEmail());

			ps.executeUpdate();
			ps.close();
			return true;
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();	// for debugging
			return false;
		}
		finally
		{
			try
			{
				if (ps != null)
					ps.close();
			}
			catch(SQLException sqle)
			{
				System.err.println(sqle);
			}
		}
	}
}