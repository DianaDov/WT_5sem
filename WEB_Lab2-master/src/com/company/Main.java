package com.company;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import DAO.DeviceDAO;
import DAO.DeviceDAOImpl;

/**
 * Class with main method
 * @author Pleskach Aliaksei
 * @version 1.0
 */
public class Main {

    /**
     * Search for all teapots and the cheapest device
     * @param args arguments of command line, not used
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DeviceDAO store = new DeviceDAOImpl("store.xml");
        ArrayList<String> teapots = store.GetDevicesByName("Teapot");

        System.out.println("Чайники:");
        for(int i = 0; i < teapots.size(); i++)
        {
            System.out.println(teapots.get(i));
        }

        System.out.println("Самый дешевый товар:");
        System.out.println(store.GetCheapestDevice());
    }
}
