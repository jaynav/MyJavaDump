import java.io.*;
import java.util.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


import javax.xml.stream.*;


public class assignment3
{//1
	public static void main(String[] args)
	{	//Locate the text file
		File file = new File("cd_catalog.txt");




		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();

			FileWriter filewriter = new FileWriter("cd_catalog.xml");
			XMLStreamWriter writer = outputFactory.createXMLStreamWriter(filewriter);

		// the while statement reads to the end of the text file
		writer.writeStartDocument("1.0");
		writer.writeComment("cd data");
		writer.writeStartElement("cds");
		while(line != null)
		{
		writer.writeStartElement("cd");

		String [] columns = line.split("\t");
		String title = columns [0];
		String artist = columns [1];
		String country = columns [2];
		String company = columns [3];
		String price = columns [4];
		String year = columns [5];

		writer.writeStartElement("Title");
		writer.writeCharacters(title);
					writer.writeEndElement();



		writer.writeStartElement("Artist");
			writer.writeCharacters(artist);
			writer.writeEndElement();

			writer.writeStartElement("Country");
		writer.writeCharacters(country);
		writer.writeEndElement();

		writer.writeStartElement("Company");
		writer.writeCharacters(company);
		writer.writeEndElement();

		writer.writeStartElement("Price");
		writer.writeCharacters(price);

		writer.writeEndElement();

		writer.writeStartElement("Year");
		writer.writeCharacters(year);
		//writer.writeStartElement("\n");
		writer.writeEndElement();

		writer.writeEndElement();



		line = in.readLine();

		System.out.println(line);



		}

		writer.writeEndElement();
		writer.flush();
		writer.close();
		in.close();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (XMLStreamException e)
		{
			e.printStackTrace();
	    }



   }
}