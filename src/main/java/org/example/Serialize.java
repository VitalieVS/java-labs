package org.example;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serialize {
    public static void main(String[] args) {
        Student john = new Student("John", "Doe", 21, "Letters", 3);

        // Serialize to XML
        john.serializeToXML("src/main/java/org/example/john.xml");

//         Deserialize from XML
        Student deserializedJohn = Student.deserializeFromXML("src/main/java/org/example/john.xml");
        if (deserializedJohn != null) {
            System.out.println("Deserialized Student Information from XML:");
            System.out.println("Name: " + deserializedJohn.getName());
            System.out.println("Surname: " + deserializedJohn.getSurname());
            System.out.println("Age: " + deserializedJohn.getAge());
            System.out.println("Faculty: " + deserializedJohn.getFaculty());
            System.out.println("Course: " + deserializedJohn.getCourse());
        }

        // Call existing methods to read from XML and JSON
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

            NodeList nodeList = doc.getElementsByTagName("students");

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

    public static void readFromJSON() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/main/java/org/example/student.json")));
            JSONObject jsonObject = new JSONObject(content);

            JSONObject student = jsonObject.getJSONObject("student");
            String name = student.getString("name");
            String surname = student.getString("surname");
            int age = student.getInt("age");
            String faculty = student.getString("faculty");
            int course = student.getInt("course");

            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Age: " + age);
            System.out.println("Faculty: " + faculty);
            System.out.println("Course: " + course);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
}

@XmlRootElement
class Student implements Serializable {
    private String name;
    private String surname;
    private int age;
    private String faculty;
    private int course;

    private Student() {

    }

    public Student(String name, String surname, int age, String faculty, int course) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.faculty = faculty;
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getCourse() {
        return course;
    }

    // Serialization method
    public void serializeToXML(String filename) {
        try {
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this, new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Student deserializeFromXML(String filename) {
        try {
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Student) unmarshaller.unmarshal(new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
