import java.util.Scanner;

public class Validator
{
	public static String getLine(Scanner sc, String prompt)
	{
		System.out.print(prompt);
		String s = sc.nextLine();        // read the whole line
		return s;
	}

	public static String getString(Scanner sc, String prompt)
	{
		System.out.print(prompt);
		String s = sc.next();        // read the first string on the line
		sc.nextLine();               // discard the rest of the line
		return s;
	}

	// force the user to enter a single string with no spaces
	public static String getRequiredString(Scanner sc, String prompt)
	{
		String s = "";
		boolean isValid = false;
		while (isValid == false)
		{
			System.out.print(prompt);
			s = sc.next();
			sc.nextLine();               // discard the rest of the line
			if (s.equals(""))
			{
				System.out.println("Error! This entry is required. Try again.");
			}
			else
			{
				isValid = true;
			}
		}
		return s;
	}

	// force the user to enter a string that can include spaces
	public static String getRequiredLine(Scanner sc, String prompt)
	{
		String s = "";
		boolean isValid = false;
		while (isValid == false)
		{
			System.out.print(prompt);
			s = sc.nextLine();
			if (s.equals(""))
			{
				System.out.println("Error! This entry is required. Try again.");
			}
			else
			{
				isValid = true;
			}
		}
		return s;
	}

	// validate an email address
	// for example, 'x@x.x' is valid while 'xxx' is not
	// NOTE: this could be enhanced to accound for all possible suffixes (.com, .org, .net, .edu, etc.)
	public static String getEmail(Scanner sc, String prompt)
	{
		String s = "";
		boolean isValid = false;
		while (isValid == false)
		{
			// use the getRequiredString method to get a string
			s = getRequiredString(sc, prompt);

			// check the @ and . indexes to see if they're valid
			int atIndex = s.indexOf("@");
			int lastDotIndex = s.lastIndexOf(".", s.length());

			if (atIndex == -1)
			{
				System.out.println("Error! Missing @ symbol. Try again.");
			}
			else if (atIndex < 1)
			{
				System.out.println("Error! Missing characters before @ symbol. Try again.");
			}
			else if (lastDotIndex == -1)
			{
				System.out.println("Error! Missing dot symbol. Try again.");
			}
			else if ((lastDotIndex - atIndex) < 2)
			{
				System.out.println("Error! Missing characters between @ and dot symbols. Try again.");
			}
			else if ((s.length() - lastDotIndex) < 2)
			{
				System.out.println("Error! Missing characters after dot symbol. Try again.");
			}
			else
			{
				isValid = true;
			}
		}
		return s;
	}

	public static int getInt(Scanner sc, String prompt)
	{
		boolean isValid = false;
		int i = 0;
		while (isValid == false)
		{
			System.out.print(prompt);
			if (sc.hasNextInt())
			{
				i = sc.nextInt();
				isValid = true;
			}
			else
			{
				System.out.println("Error! Invalid integer value. Try again.");
			}
			sc.nextLine();  // discard any other data entered on the line
		}
		return i;
	}

	public static int getInt(Scanner sc, String prompt,
	int min, int max)
	{
		int i = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			i = getInt(sc, prompt);
			if (i <= min)
				System.out.println(
					"Error! Number must be greater than " + min);
			else if (i >= max)
				System.out.println(
					"Error! Number must be less than " + max);
			else
				isValid = true;
		}
		return i;
	}

	public static double getDouble(Scanner sc, String prompt)
	{
		boolean isValid = false;
		double d = 0;
		while (isValid == false)
		{
			System.out.print(prompt);
			if (sc.hasNextDouble())
			{
				d = sc.nextDouble();
				isValid = true;
			}
			else
			{
				System.out.println("Error! Invalid decimal value. Try again.");
			}
			sc.nextLine();  // discard any other data entered on the line
		}
		return d;
	}

	public static double getDouble(Scanner sc, String prompt,
	double min, double max)
	{
		double d = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			d = getDouble(sc, prompt);
			if (d <= min)
				System.out.println(
					"Error! Number must be greater than " + min);
			else if (d >= max)
				System.out.println(
					"Error! Number must be less than " + max);
			else
				isValid = true;
		}
		return d;
	}

}