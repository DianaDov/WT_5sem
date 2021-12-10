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
import java.util.List;

/**
 * Implementation the DAO level
 * @author Pleskach Aliaksei
 */
public class DeviceDAOImpl implements DeviceDAO {

    /**
     * devices with TagName=device
     */
    private NodeList devices;

    /**
     * Loads xml document and initializes devices field
     * @param pathName path to xml document
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public DeviceDAOImpl(String pathName) throws IOException, SAXException, ParserConfigurationException {
        // Создается построитель документа
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Создается дерево DOM документа из файла
        Document document = documentBuilder.parse(new File(pathName));
        // Получаем все приборы
        devices = document.getDocumentElement().getElementsByTagName("device");
    }

    /**
     * Convert node device to string format
     * @param device node to converting
     * @return String representation of node
     */
    private String DeviceToString(Node device)
    {
        String stringDevice = "";
        stringDevice += device.getTextContent() + ". ";

        NamedNodeMap attributes = device.getAttributes();
        for(int j = 0; j < attributes.getLength(); j++)
        {
            stringDevice += attributes.item(j).getNodeName() + ": " + attributes.item(j).getNodeValue() + ". ";
        }

        return stringDevice;
    }

    /**
     * Method implementation of DeviceDAO
     */
    public ArrayList<String> GetDevicesByName(String name)
    {
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < devices.getLength(); i++)
    {
        if (devices.item(i).getTextContent().equals(name))
        {
            Node device = devices.item(i);
            result.add(DeviceToString(device));
        }
    }

        return result;
    }

    /**
     * Method implementation of DeviceDAO
     */
    public String GetCheapestDevice() {
        Node result = null;
        int minPrice = 100000;
        for(int i = 0; i < devices.getLength(); i++)
        {
            int price = Integer.parseInt(devices.item(i).getAttributes().getNamedItem("price").getNodeValue());
            if (price < minPrice)
            {
                minPrice = price;
                result = devices.item(i);
            }
        }

        return DeviceToString(result);
    }
}
