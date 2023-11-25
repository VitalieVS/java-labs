package org.example;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        readFromXML();
        readFromJSON();
    }

    public static void readFromXML() {
        try {
            File inputFile = new File("src/main/java/org/example/student.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("student");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String surname = element.getElementsByTagName("surname").item(0).getTextContent();
                    String age = element.getElementsByTagName("age").item(0).getTextContent();
                    String faculty = element.getElementsByTagName("faculty").item(0).getTextContent();
                    String course = element.getElementsByTagName("course").item(0).getTextContent();

                    System.out.println("Name: " + name);
                    System.out.println("Surname: " + surname);
                    System.out.println("Age: " + age);
                    System.out.println("Faculty: " + faculty);
                    System.out.println("Course: " + course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * JAVA DOC
     * This method demonstrates reading student information from a JSON file.
     */

    /**
     * Method to read and display student information from a JSON file.
     *
     * @param args Command-line arguments (not used in this example)
     */
    public static void readFromJSON() {
        try {
            // Read the content of the JSON file
            String content = new String(Files.readAllBytes(Paths.get("src/main/java/org/example/student.json")));
            // Create a JSONObject from the JSON content
            JSONObject jsonObject = new JSONObject(content);

            // Extract student information from the JSON object
            JSONObject student = jsonObject.getJSONObject("student");
            String name = student.getString("name");
            String surname = student.getString("surname");
            int age = student.getInt("age");
            String faculty = student.getString("faculty");
            int course = student.getInt("course");
            // Display student information
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Age: " + age);
            System.out.println("Faculty: " + faculty);
            System.out.println("Course: " + course);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}