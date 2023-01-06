package com.example.fileToDatabase.service;

import com.example.fileToDatabase.entity.UserXml;
import com.example.fileToDatabase.repository.XmlRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
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
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class XmlUserService {
    private final XmlRepository xmlRepository;

    @SneakyThrows
    public void copyUsersToTable(String path) {
        File xmlTest = new File(path);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlTest);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("Документ");
        List<UserXml> userXmls = IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item)
                .filter(node -> node.getNodeType() == Node.ELEMENT_NODE)
                .map(this::createUserXml)
                .toList();
        String csvFromXml = copyToCsv(userXmls);
        xmlRepository.copyFromXml(csvFromXml);

    }

    private UserXml createUserXml(Node docNode) {
        Element eElementParent = (Element) docNode;

        NodeList childNodes = docNode.getChildNodes();
        Map<String, String> nameDetails = new HashMap<>();
        Map<String, String> regionAndCityDetails = new HashMap<>();
        AtomicReference<StringBuilder> finalText = new AtomicReference<>(new StringBuilder());
        IntStream.range(0, childNodes.getLength())
                .mapToObj(childNodes::item)
                .forEach(basicNode -> {
                    Element eElementBasic = (Element) basicNode;
                    if ("ИПВклМСП".equals(eElementBasic.getTagName())) {
                        Map<String, String> fullName = getNameDetails(eElementBasic);
                        nameDetails.put("firstname", fullName.get("firstname"));
                        nameDetails.put("lastname", fullName.get("lastname"));
                        nameDetails.put("patronymic", fullName.get("patronymic"));
                    }
                    if ("СведМН".equals(eElementBasic.getTagName())) {
                        Map<String, String> locationDetails = getLocationDetails(eElementBasic);
                        regionAndCityDetails.put("Code", locationDetails.get("Code"));
                        regionAndCityDetails.put("region type", locationDetails.get("region type"));
                        regionAndCityDetails.put("region name", locationDetails.get("region name"));
                        regionAndCityDetails.put("city type", locationDetails.get("city type"));
                        regionAndCityDetails.put("city name", locationDetails.get("city name"));

                    }
                    if ("СвОКВЭД".equals(eElementBasic.getTagName())) {
                        finalText.set(getText(eElementBasic));
                    }
                });

        return UserXml.builder()
                .documentId(eElementParent.getAttribute("ИдДок"))
                .documentCreationDate(eElementParent.getAttribute("ДатаСост"))
                .ONDate(eElementParent.getAttribute("ДатаВклМСП"))
                .productivity(eElementParent.getAttribute("ПризНовМСП"))
                .category(eElementParent.getAttribute("КатСубМСП"))
                .view(eElementParent.getAttribute("ВидСубМСП"))
                .details(eElementParent.getAttribute("СведСоцПред"))
                .firstname(nameDetails.get("firstname"))
                .lastname(nameDetails.get("lastname"))
                .regionCode(regionAndCityDetails.get("Code"))
                .patronymic(nameDetails.get("patronymic"))
                .regionType(regionAndCityDetails.get("region type"))
                .regionName(regionAndCityDetails.get("region name"))
                .cityType(regionAndCityDetails.get("city type"))
                .cityName(regionAndCityDetails.get("city name"))
                .text(finalText)
                .build();
    }


    private Map<String, String> getNameDetails(Element element) {
        Map<String, String> name = new HashMap<>();
        NodeList childNodes1 = element.getChildNodes();
        Node item = childNodes1.item(0);
        Element item1 = (Element) item;
        String firstname = item1.getAttribute("Имя");
        String lastname = item1.getAttribute("Фамилия");
        String patronymic = item1.getAttribute("Отчество");
        name.put("firstname", firstname);
        name.put("lastname", lastname);
        name.put("patronymic", patronymic);
        return name;
    }

    private Map<String, String> getLocationDetails(Element element) {
        Map<String, String> location = new HashMap<>();
        location.put("Code", element.getAttribute("КодРегион"));
        NodeList secondNodeChilds = element.getChildNodes();
        IntStream.range(0, secondNodeChilds.getLength())
                .mapToObj(secondNodeChilds::item)
                .forEach(childNode -> {
                    Element element2 = (Element) childNode;
                    if ("Регион".equals(childNode.getNodeName())) {
                        location.put("region type", element2.getAttribute("Тип"));
                        location.put("region name", element2.getAttribute("Наим"));
                    }
                    if ("Город".equals(childNode.getNodeName())) {
                        location.put("city type", element2.getAttribute("Тип"));
                        location.put("city name", element2.getAttribute("Наим"));
                    }

                });
        return location;
    }


    private StringBuilder getText(Element element) {
        StringBuilder wholeRow = new StringBuilder();
        NodeList thirdNodeChilds = element.getChildNodes();
        int bound = thirdNodeChilds.getLength();
        for (int i = 0; i < bound; i++) {
            Node nodeChild = thirdNodeChilds.item(i);
            if (nodeChild.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement3 = (Element) nodeChild;
                if ("СвОКВЭДОсн".equals(nodeChild.getNodeName()) || "СвОКВЭДДоп".equals(nodeChild.getNodeName())) {
                    String code = eElement3.getAttribute("КодОКВЭД");
                    String text = eElement3.getAttribute("НаимОКВЭД");
                    String version = eElement3.getAttribute("ВерсОКВЭД");
                    String concat = " КодОКВЭД: " + code +
                            " НаимОКВЭД: " + text +
                            " ВерсОКВЭД: " + version;
                    wholeRow.append(concat);
                }
            }
        }
        return wholeRow;
    }

    @SneakyThrows
    public String copyToCsv(List<UserXml> list) {
        File csvOutputFile = new File("/home/administrator/Downloads/files/user_output.csv");

        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                 .addColumn("documentId")
                .addColumn("creationDate")
                .addColumn("ONDate")
                .addColumn("view")
                .addColumn("details")
                .addColumn("category")
                .addColumn("productivity")
                .addColumn("firstname")
                .addColumn("lastname")
                .addColumn("patronymic")
                .addColumn("regionCode")
                .addColumn("regionType")
                .addColumn("regionName")
                .addColumn(" cityType")
                .addColumn("cityName")
                .addColumn("text")
                .build();

        ObjectWriter writer = mapper.writerFor(UserXml.class).with(schema);

        writer.writeValues(csvOutputFile).writeAll(list);
        System.out.println(csvOutputFile.getPath());
        return csvOutputFile.getPath();
    }
}
