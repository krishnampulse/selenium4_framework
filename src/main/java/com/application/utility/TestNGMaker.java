package com.application.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TestNGMaker {
	
	private Map<String, ArrayList<String>> classAndMethodsName;
	
	public TestNGMaker(Map<String, ArrayList<String>> classAndMethodsName) {
		this.classAndMethodsName=classAndMethodsName;
	}
	
	public String makeTestNGFile(String fileName) {
		
		String filePath = System.getProperty("user.dir") + "/src/test/resources/" + fileName + ".xml";
		
		try {

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// suite element
			Element suite = document.createElement("suite");
			document.appendChild(suite);			
			// set attributes to suite element
			suite.setAttribute("name", "test suite for re test");
			suite.setAttribute("parallel", "tests");

				// listeners element
				Element listeners = document.createElement("listeners");
				suite.appendChild(listeners);
			
					// listener element
					Element listener = document.createElement("listener");
					listeners.appendChild(listener);
					// set an attribute to listener element
					listener.setAttribute("class-name", "com.mPulse.listeners.AnnotationTransformer");
				
				// test element
				Element test1 = document.createElement("test");
				suite.appendChild(test1);
				// set attributes to suite
				test1.setAttribute("name", "Test01");
				
				// classes element
			    Element classes1 = document.createElement("classes");
			    test1.appendChild(classes1);
				
				// test element
				Element test2 = document.createElement("test");
				suite.appendChild(test2);
				// set attributes to suite
				test2.setAttribute("name", "Test02");
				
				// classes element
			    Element classes2 = document.createElement("classes");
			    test2.appendChild(classes2);
					
				int counter = 0;
				
				for(Map.Entry<String, ArrayList<String>> entry : this.classAndMethodsName.entrySet()) {
			    	String className = entry.getKey();
			    	int methodsCount = entry.getValue().size();
			    	
			    	if (counter < 1) {
					    		
					    		// class element
						    	Element classEle = document.createElement("class");
						    	classes1.appendChild(classEle);
						    	// set an attribute to class 
						    	classEle.setAttribute("name", className);
						    	
						    		// methods element
						    		Element methods = document.createElement("methods");
						    		classEle.appendChild(methods);
							
					    		for(int i=0; i<methodsCount; i++) {
					    			String method = entry.getValue().get(i);
					    			// include element
					    			Element include = document.createElement("include");
					    			include.setAttribute("name", method);
					    			methods.appendChild(include);
					    		}
					     counter++;
			    	}
					
			    	else
					{
					    		
					    		// class element
						    	Element classEle = document.createElement("class");
						    	classes2.appendChild(classEle);
						    	// set an attribute to class 
						    	classEle.setAttribute("name", className);
						    	
						    		// methods element
						    		Element methods = document.createElement("methods");
						    		classEle.appendChild(methods);
							
					    		for(int i=0; i<methodsCount; i++) {
					    			String method = entry.getValue().get(i);
					    			// include element
					    			Element include = document.createElement("include");
					    			include.setAttribute("name", method);
					    			methods.appendChild(include);
					    		}
					    		
					     if (counter == 1) {
					    	 counter = 0;
					     }
					}
				}

			//transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://testng.org/testng-1.0.dtd");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(filePath));

			transformer.transform(domSource, streamResult);
			SysLogger.log("Created TestNG xml file");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		
		return filePath;
	}

}
