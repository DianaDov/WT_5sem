package DAO;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for the DAO level
 * @author Pleskach Aliaksei
 */
public interface DeviceDAO {
    /**
     * @param name device name to search
     * @return list of devices and their descriptions
     */
    ArrayList<String> GetDevicesByName(String name);

    /**
     * @return the cheapest device and its description
     */
    String GetCheapestDevice();
}
