import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EscribirXmlDom {
    public static void main(String[] argv) throws Exception {
        //Declara los ficheros HTML y XML
        File fichHtml = new File("src/ficheroHTML.html");
        File fichXml = new File("src/FicheroXML.xml");
        FileReader leerHtml = new FileReader(fichHtml);
        FileWriter escribirXml = new FileWriter(fichXml);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        DOMImplementation impl = db.getDOMImplementation();
        Document doc = impl.createDocument(null, null, null);

        Element e1base = doc.createElement("clientes");
        doc.appendChild(e1base);

        Element e1 = doc.createElement("cliente");
        e1.setAttribute("DNI", "12345678Z");
        Element e1Apel = doc.createElement("apellidos");
        e1Apel.appendChild(doc.createTextNode("ROJAS"));
        e1.appendChild(e1Apel);
        Element e1Validez = doc.createElement("validez");
        e1Validez.setAttribute("estado", "borrado");
        e1Validez.setAttribute("timeStamp", "1528286082");
        e1.appendChild(e1Validez);
        e1base.appendChild(e1);

        DOMSource domSource = new DOMSource(doc);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");	// Omite la cabecera del XML
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(domSource, sr);
        System.out.println(sw.toString());
    }
}