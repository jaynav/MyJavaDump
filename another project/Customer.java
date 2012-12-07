public class Customer
{
	private String name;
	private String email;
	private String q1;
	private String q2;
	private String mid_term;
	private String final_term;

	public Customer()
	{
		name = "";
		email = "";
		q1 = "";
		q2 = "";
		mid_term = "";
		final_term = "";
	}

	public Customer(String email, String name, String q1, String q2, String mid_term, String final_term)
	{
		this.email = email;
		this.name = name;
		this.q1 = q1;
		this.q2 = q2;
		this.mid_term = mid_term;
		this.final_term = final_term;
	}


	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setQ1(String q1)
	{
		this.q1 = q1;
	}

	public String getQ1(){
		return q1;
	}

	public void setQ2(String q2)
	{
		this.q2 = q2;
	}

	public String getQ2(){
		return q2;
	}

	public void setMID_TERM(String mid_term)
	{
		this.mid_term = mid_term;
	}

	public String getMID_TERM(){
		return mid_term;
	}

	public void setFINAL_TERM(String final_term)
	{
		this.final_term = final_term;
	}

	public String getFINAL_TERM(){
		return final_term;
	}

	public boolean equals(Object object)
	{
		if (object instanceof Customer)
		{
			Customer customer2 = (Customer) object;
			if(this.email.equals(customer2.getEmail()))
			{
				return true;
			}
		}
		return false;
	}

}