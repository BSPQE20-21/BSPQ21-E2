package es.deusto.profiling;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EmployeeProfiling extends DefaultHandler{
	
	String content="";
    public EmployeeProfiling() {
		super();
	}

	protected void parseContent(File file)
	{
		try {
			SAXParser parser = createParser();			
	        InputStream is = new FileInputStream(file);

	        if (is == null) {
	            return;
	        }
			
			parser.parse(is, this);
			
        } 
		catch (Exception e) {
			
			e.printStackTrace();
			
        }
			
		return;
		
	}
	
	
	protected SAXParser parser;
	protected SAXParser createParser() throws ParserConfigurationException, SAXException {
		if (parser == null) {
			SAXParserFactory f = SAXParserFactory.newInstance();	
			f.setValidating(false);		
			parser = f.newSAXParser();
		}
		
		return parser;
	}
	
	/*
	protected SAXParser createParser() throws ParserConfigurationException, SAXException {
		SAXParserFactory f = SAXParserFactory.newInstance();		
		f.setValidating(false);
		return f.newSAXParser();
	}*/
	
	

	
    /**
     * Receive notification of the beginning of an element.
     */
    public void startElement(String namespaceURI, String localName, String elementName, Attributes atts) throws SAXException {
        
    	content+="\n"+elementName + ":\n";
    	
    	for(int idx=0; idx<atts.getLength(); idx++)
    	{
    		content+="\t"+atts.getQName(idx) + "=" + atts.getValue(idx)+"\n";
    	}
    }
    
    /**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	public void readData(String name) {
		
		if(name == null)
		{
			System.out.println("invalid folder name");
			return;
		}
		
		File file = new File(name);
		if(!file.exists() || !file.isDirectory())
		{
			System.out.println("invalid folder name " + name);
			return;
		}
		
	    File[] files = file.listFiles();
	    for(int idx=0; idx<files.length; idx++)
	    	parseContent(files[idx]);
	}
	

}
