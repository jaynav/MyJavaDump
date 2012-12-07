public class DAOFactory
{
	// this method maps the StudentDAO interface
	// to the appropriate data storage mechanism
	public static StudentDAO getStudentDAO()
	{
		StudentDAO cDAO = new StudentDB();
		return cDAO;
	}
}