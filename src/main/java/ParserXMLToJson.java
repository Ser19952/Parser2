import org.w3c.dom.Document;
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

public class ParserXMLToJson {

    public static final String fileName = "data.xml";

    private static List<Employee> parseXML(String fileName) throws ParserConfigurationException, IOException, SAXException {

        ArrayList<Employee> employees = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(fileName));

        NodeList employeeElements = doc.getDocumentElement().getElementsByTagName("employee");

        for (int i = 0; i < employeeElements.getLength(); i++) {
            Node employee = employeeElements.item(i);

            employees.add(new Employee(Long.parseLong(employee.getChildNodes().item(1).getTextContent()),
                    employee.getChildNodes().item(3).getTextContent(),
                    employee.getChildNodes().item(5).getTextContent(),
                    employee.getChildNodes().item(7).getTextContent(),
                    Integer.parseInt(employee.getChildNodes().item(9).getTextContent())));
        }
        return employees;
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        parseXML(fileName);
        ParserCsvToJson.writeString(parseXML(fileName).toString(), "data2.json");
    }

}
