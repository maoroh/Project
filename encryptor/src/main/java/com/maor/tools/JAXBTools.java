package com.maor.tools;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBTools {
	
	public static void createSettingsXML(UserSettings settings) 
	{
    	JAXBContext jaxbContext;
    	Marshaller jaxbMarshaller;
		try {
			 jaxbContext = JAXBContext.newInstance(UserSettings.class);
		     jaxbMarshaller = jaxbContext.createMarshaller();
		     jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		     jaxbMarshaller.marshal(settings, System.out);
		     jaxbMarshaller.marshal(settings, new File("settings.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
 
	}
	
	public static UserSettings getSettingsFromXML() 
	{
		JAXBContext jaxbContext;
		UserSettings settings = null;
		try {
			jaxbContext = JAXBContext.newInstance(UserSettings.class);
		    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();   
	        settings = (UserSettings) jaxbUnmarshaller.unmarshal( new File("settings.xml") );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return settings;
	}

}
