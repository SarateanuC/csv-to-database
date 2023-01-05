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
public class TestService {
    @SneakyThrows
    public List<UserXml> copyUsersToTable(String path) {
        File xmlTest = new File(path);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlTest);
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
            Node basicNode = childNodes.item(itr);
            if (basicNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) basicNode;
                if (eElement.getTagName() == "ИПВклМСП") {
                    NodeList childNodes1 = basicNode.getChildNodes();
                    //System.out.println(childNodes1.getLength());
                    for (int i = 0; itr < childNodes1.getLength(); itr++) {
                        Node node1 = childNodes.item(i);
                        if (node1.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement1 = (Element) node1;
                            String lastname = eElement1.getAttribute("Фамилия");
                            String firstname = eElement1.getAttribute("Имя");
                            String patronymic = eElement1.getAttribute("Отчество");
                            nameDetails.put("firstname", firstname);
                            nameDetails.put("lastname", lastname);
                            nameDetails.put("patronymic", patronymic);
                        }
                    }
                }
                if (eElement.getTagName() == "СведМН") {
                    regionAndCityDetails.put("Code", eElement.getAttribute("КодРегион"));
                    NodeList childNodes2 = basicNode.getChildNodes();
                   // System.out.println(childNodes2.getLength());
                    for (int i = 0; itr < childNodes2.getLength(); itr++) {
                        Node node2 = childNodes.item(i);
                        if (node2.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement2 = (Element) node2;
                            regionAndCityDetails.put("type" + i, eElement2.getAttribute("Тип"));
                            regionAndCityDetails.put("name" + i, eElement2.getAttribute("Наим"));
                        }
                    }
                }
                if (eElement.getTagName() == "СвОКВЭД") {
                    NodeList childNodes3 = basicNode.getChildNodes();
                    //System.out.println(childNodes3.getLength());
                    for (int i = 0; itr < childNodes3.getLength(); itr++) {
                        Node node3 = childNodes.item(i);
                        if (node3.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement3 = (Element) node3;
                            String code = eElement3.getAttribute("КодОКВЭД");
                            String text = eElement3.getAttribute("НаимОКВЭД");
                            String version = eElement3.getAttribute("ВерсОКВЭД");
                            String concat = " КодОКВЭД: " + code +
                                    " НаимОКВЭД: " + text +
                                    " ВерсОКВЭД: " + version;
                            finalText.append(concat);
                        }
                    }
                  //  System.out.println("stop");
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
                .regionCode(regionAndCityDetails.get("Code"))
                .patronymic(nameDetails.get("patronymic"))
                .regionType(regionAndCityDetails.get("type"))
                .regionName(regionAndCityDetails.get("name"))
                .cityType(regionAndCityDetails.get("type"))
                .cityName(regionAndCityDetails.get("name"))
                .text(finalText)
                .build();
    }
}
