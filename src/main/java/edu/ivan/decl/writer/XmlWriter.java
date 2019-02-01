package edu.ivan.decl.writer;

import edu.ivan.decl.entity.Declaration;
import edu.ivan.decl.entity.Transport;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XmlWriter {

    private File file;

    public XmlWriter(File file) {
        this.file = file;
    }

    public void createFile(List<Transport> transportList){
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root elements
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("ver3:NDS_v3_reestr0");
            rootElement.setAttribute("xmlns:ver3","http://mns/edeclaration/xml/nds_reestr0/ver3");
            rootElement.setAttribute("kodIMNS",String.valueOf(Declaration.getTaxNumber()));
            rootElement.setAttribute("UNP",Declaration.getUNP());
            rootElement.setAttribute("year",String.valueOf(Declaration.getYear()));
            rootElement.setAttribute("period",String.valueOf(Declaration.getPeriod()));
            rootElement.setAttribute("leader",Declaration.getDirector().getLastName());
            doc.appendChild(rootElement);

            Element rootNDS = doc.createElement("NDS_v3_reestr0_t001");
            rootElement.appendChild(rootNDS);

            for(Transport transport : transportList){

                Element nodeNDS = doc.createElement("NDS_v3_reestr0_t001_ri");

                Element contractDateNode = doc.createElement("NDS_v3_reestr0_t001_ric2a");
                String date = transport.getContract().getDate().toString() + "+03:00";
                contractDateNode.appendChild(doc.createTextNode(date));
                nodeNDS.appendChild(contractDateNode);

                Element contractNumberNode = doc.createElement("NDS_v3_reestr0_t001_ric2b");
                String number = String.valueOf(transport.getContract().getNumber());
                contractNumberNode.appendChild(doc.createTextNode(number));
                nodeNDS.appendChild(contractNumberNode);


                Element ItnDateNode = doc.createElement("NDS_v3_reestr0_t001_ric3a");
                String ItnDate = String.valueOf(transport.getItn().getDate());
                ItnDateNode.appendChild(doc.createTextNode(ItnDate));
                nodeNDS.appendChild(ItnDateNode);

                Element ItnNumberNode = doc.createElement("NDS_v3_reestr0_t001_ric3b");
                String ItnNumber = String.valueOf(transport.getItn().getNumber());
                ItnNumberNode.appendChild(doc.createTextNode(ItnNumber));
                nodeNDS.appendChild(ItnNumberNode);


                Element emptyElement = doc.createElement("NDS_v3_reestr0_t001_ric4b");
                nodeNDS.appendChild(emptyElement);

                Element routeNode = doc.createElement("NDS_v3_reestr0_t001_ric5");
                String route = transport.getRoute();
                routeNode.appendChild(doc.createTextNode(route));
                nodeNDS.appendChild(routeNode);

                Element actDateNode = doc.createElement("NDS_v3_reestr0_t001_ric6");
                String actDate = transport.getActDate();
                actDateNode.appendChild(doc.createTextNode(actDate));
                nodeNDS.appendChild(actDateNode);

                Element profitNode = doc.createElement("NDS_v3_reestr0_t001_ric7");
                String profit = transport.getProfit().toString();
                profitNode.appendChild(doc.createTextNode(profit));
                nodeNDS.appendChild(profitNode);


                rootNDS.appendChild(nodeNDS);
            }
            Element quartalProfitNode = doc.createElement("NDS_v3_reestr0_t001_rnc7");
            String quartalProfit = Declaration.getQuartalProfit().toString();
            quartalProfitNode.appendChild(doc.createTextNode(quartalProfit));
            rootNDS.appendChild(quartalProfitNode);

            //write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Done");

        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }catch(TransformerException tfe){
            tfe.printStackTrace();
        }
    }
}
