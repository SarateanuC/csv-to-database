package com.example.fileToDatabase.service;

import com.example.fileToDatabase.entity.UserXml;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class XmlUserService {
    @SneakyThrows
    public List<UserXml> copyUsersToTable(String path) {
        File xml = new File(path);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xml);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("Документ");
        return IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item)
                .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                .map(this::createUserXml)
                .toList();
    }

    private UserXml createUserXml(Node docNode) {
        Element eElementParent = (Element) docNode;
        NodeList childNodes = eElementParent.getChildNodes();
        Map<String, String> nameDetails = new HashMap<>();
        Map<String, String> regionAndCityDetails = new HashMap<>();
        StringBuilder finalText = new StringBuilder();
        for (int itr = 0; itr < childNodes.getLength(); itr++) {
            Node node = childNodes.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                if (eElement.getTagName() == "ИПВклМСП") {
                    Map<String, String> userName = getUserName(node);
                    String lastname = userName.get("Фамилия");
                    String firstname = userName.get("Имя");
                    String patronymic = userName.get("Отчество");
                    nameDetails.put("firstname", firstname);
                    nameDetails.put("lastname", lastname);
                    nameDetails.put("patronymic", patronymic);
                }
                if (eElement.getTagName() == "СведМН") {
                    regionAndCityDetails.put("Code", eElement.getAttribute("КодРегион"));
                    Map<String, String> stringStringMap = regionAndCity(node);
                    regionAndCityDetails.put("type", stringStringMap.get("Тип"));
                    regionAndCityDetails.put("name", stringStringMap.get("Наим"));
                }
                if (eElement.getTagName() == "СвОКВЭД") {
                    finalText = getText(node);
                }
            }
        }
        return UserXml.builder()
                .category(eElementParent.getAttribute("КатСубМСП"))
                .ONDate(eElementParent.getAttribute("ДатаВклМСП"))
                .documentCreationDate(eElementParent.getAttribute("ДатаСост"))
                .documentId(eElementParent.getAttribute("ИдДок"))
                .productivity(eElementParent.getAttribute("ПризНовМСП"))
                .view(eElementParent.getAttribute("ВидСубМСП"))
                .details(eElementParent.getAttribute("СведСоцПред"))
                .firstname(nameDetails.get("firstname"))
                .lastname(nameDetails.get("lastname"))
                .patronymic(nameDetails.get("patronymic"))
                .regionType(regionAndCityDetails.get("type"))
                .regionName(regionAndCityDetails.get("name"))
                .cityType(regionAndCityDetails.get("type"))
                .cityName(regionAndCityDetails.get("name"))
                .text(finalText)
                .build();

    }


    private Map<String, String> getUserName(Node node) {
        NodeList childNodeName = node.getChildNodes();
        Map<String, String> name = new HashMap<>();
        int i = 0;
        while (i < childNodeName.getLength()) {
            Node item = childNodeName.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                Element item1 = (Element) item;
                String lastname = item1.getAttribute("Фамилия");
                String firstname = item1.getAttribute("Имя");
                String patronymic = item1.getAttribute("Отчество");
                name.put("firstname", firstname);
                name.put("lastname", lastname);
                name.put("patronymic", patronymic);
            }
        }
        return name;
    }

    private Map<String, String> regionAndCity(Node node) {
        NodeList childNodeRegion = node.getChildNodes();
        Map<String, String> cityAndRegion = new HashMap<>();
        IntStream.range(0, childNodeRegion.getLength())
                .mapToObj(childNodeRegion::item)
                .filter(item -> item.getNodeType() == Node.ELEMENT_NODE)
                .map(item -> (Element) item).forEach(item2 -> {
                    String type = item2.getAttribute("Тип");
                    String name = item2.getAttribute("Наим");
                    cityAndRegion.put("Тип", type);
                    cityAndRegion.put("Наим", name);
                });
        return cityAndRegion;
    }

    private StringBuilder getText(Node node) {
        NodeList childNodeText = node.getChildNodes();
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, childNodeText.getLength())
                .mapToObj(childNodeText::item)
                .filter(item -> item.getNodeType() == Node.ELEMENT_NODE)
                .map(item -> (Element) item).forEach(item3 -> {
                    String code = item3.getAttribute("КодОКВЭД");
                    String text = item3.getAttribute("НаимОКВЭД");
                    String version = item3.getAttribute("ВерсОКВЭД");
                    String concat = " КодОКВЭД: " + code +
                            " НаимОКВЭД: " + text +
                            " ВерсОКВЭД: " + version;
                    builder.append(concat);
                });
        return builder;
    }
}
