package framework;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Environment {

        private String environmentName;
        private String environmentURL;

        public String getEnvironmentName() {
            return environmentName;
        }
        private void setEnvironmentName(String environmentName) {
            this.environmentName = environmentName;
        }
        public String getEnvironmentURL() {
            return environmentURL;
        }
        private void setEnvironmentURL(String environmentURL) {
            this.environmentURL = environmentURL;
        }
        public String getURL(){
            return environmentURL;
        }

    public Environment(String environmentName){
        //Parse settings from settings.xml, load them into environment variables declared at the top
        try{
            //Find File
            File environmentsFile = new File("config/environments.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(environmentsFile);
            doc.normalizeDocument();
            NodeList nodeList = doc.getElementsByTagName("environment");

            //Parse each environment name + url into environment variables declared at the top
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nNode = nodeList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    if (element.getAttribute("name").equalsIgnoreCase(environmentName)){
                        setEnvironmentName(element.getAttribute("name"));
                        setEnvironmentURL(element.getAttribute("url"));
                        return;
                    }
                }
            }
            //If we get here, the environment hasn't been found in the environment.xml file
            throw new RuntimeException("Could not locate environment information, check environments.xml file.");
        } catch (Exception e){
            throw new RuntimeException("Could not parse environments file");
        }
    }
    }

