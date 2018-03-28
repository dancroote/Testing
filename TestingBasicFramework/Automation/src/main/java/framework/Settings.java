package framework;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;

public class Settings {

    private HashMap<String, String> settingsMap = new HashMap<>();

    public Settings() {
        //Parse settings from settings.xml, load them into a hashmap
        try{
            //Find file
            File settingsFile = new File("config/settings.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(settingsFile);
            doc.normalizeDocument();
            NodeList nodeList = doc.getElementsByTagName("setting");

            //Parse each setting name+value into SettingsMap
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nNode = nodeList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    getSettingsMap().put(element.getAttribute("name"), element.getAttribute("value"));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Could not parse settings file");
        }
    }

    public Environment getEnvironment(){
        return new Environment(getSettingsMap().get("environment"));
    }

    public boolean getCloseBrowser(){
        return Boolean.parseBoolean(getSettingsMap().get("closebrowser"));
    }

    private HashMap<String, String> getSettingsMap() {
        return settingsMap;
    }
}
