package com.util;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomParsing {

    ArrayList quantities_in_cart = new ArrayList();
    ArrayList products_in_cart_list = new ArrayList();

    public static void card_n_shipping_info(String card_type, String card_name, String card_num, String expiration_month, String expiration_year, String cvv, String ref) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("CustomerInfo");
            doc.appendChild(rootElement);

            Element payinfo = doc.createElement("Credit_Card_Details");
            rootElement.appendChild(payinfo);
                
                Element typeofcard = doc.createElement("Card_Type");                
                typeofcard.appendChild(doc.createTextNode(card_type));
                payinfo.appendChild(typeofcard);
                
                Element cardnum = doc.createElement("card_num");
                cardnum.appendChild(doc.createTextNode(card_num));
                payinfo.appendChild(cardnum);
                
                Element cardname = doc.createElement("Name_on_Card");                
                cardname.appendChild(doc.createTextNode(card_name));
                 payinfo.appendChild(cardname);
                 
               Element month = doc.createElement("Month");                               
               month.appendChild(doc.createTextNode(expiration_month));
               payinfo.appendChild(month);
               
               Element year = doc.createElement("Year");                               
               year.appendChild(doc.createTextNode(expiration_year));
               payinfo.appendChild(year);
                
               Element cvvnum = doc.createElement("cvv");                               
               cvvnum.appendChild(doc.createTextNode(cvv));
               payinfo.appendChild(cvvnum);
               
               Element reference = doc.createElement("ref");                
               reference.appendChild(doc.createTextNode(ref));
               payinfo.appendChild(reference);
               
                
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\Naitik\\workspaceSE1\\easy_online_shop\\orders\\Bank\\payment_info.xml"));
            transformer.transform(source, result);
            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static void writexml(String fullname, String address, String country, String zipcode, String refid,ArrayList quantities_in_cart, ArrayList products_in_cart_list) {
        try {
        	
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc1 = docBuilder.newDocument();
            Element rootElement = doc1.createElement("item");
            doc1.appendChild(rootElement);
            
            Element name = doc1.createElement("Fullname");
            name.appendChild(doc1.createTextNode(fullname));
            rootElement.appendChild(name); 
            
            Element cus_address = doc1.createElement("Address");
            cus_address.appendChild(doc1.createTextNode(address));
            rootElement.appendChild(cus_address);
            
            Element Country = doc1.createElement("Country");
            Country.appendChild(doc1.createTextNode(country));
            rootElement.appendChild(Country);
            
            Element Zipcode = doc1.createElement("Zipcode");
            Zipcode.appendChild(doc1.createTextNode(zipcode));
            rootElement.appendChild(Zipcode);   
            
            Element ref = doc1.createElement("Reference_Num");
    		ref.appendChild(doc1.createTextNode(refid));
    		rootElement.appendChild(ref);
                 
            for (int i = 0; i < products_in_cart_list.size(); i++) {
                Element item = doc1.createElement("item");
                rootElement.appendChild(item);
              
                Attr attr = doc1.createAttribute("item_id");
                attr.setValue(String.valueOf(i + 1));
                item.setAttributeNode(attr);
             
                Element itemname = doc1.createElement("ItemName");
                itemname.appendChild(doc1.createTextNode((String) products_in_cart_list.get(i)));
                item.appendChild(itemname);
              
                Element quantity = doc1.createElement("Quantity");
                quantity.appendChild(doc1.createTextNode((String) quantities_in_cart.get(i)));
                item.appendChild(quantity);
                
            }
            
           
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc1);
            StreamResult result = new StreamResult(new File("C:\\Users\\Naitik\\workspaceSE1\\easy_online_shop\\orders\\Seller\\order_details.xml"));

            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
    /*public static void writeAdress(String fullname, String address, String country, String zipcode, String refid){
    	try{
    		DocumentBuilderFactory docfac = DocumentBuilderFactory.newInstance();
    		DocumentBuilder docbuilder = docfac.newDocumentBuilder();
    		
    		Document doc = docbuilder.newDocument();
    		Element root = doc.createElement("Address");
    		doc.appendChild(root);
    		
//    		Element name = doc.createElement("Full_Name");
//    		name.appendChild(doc.createTextNode(fullname));
//    		root.appendChild(name);
    		
    		Element add = doc.createElement("Address");
    		add.appendChild(doc.createTextNode(address));
    		root.appendChild(add);
    		
    		Element cntry = doc.createElement("Country");
    		cntry.appendChild(doc.createTextNode(country));
    		root.appendChild(cntry);
    		
    		Element zip = doc.createElement("Zip_Code");
    		zip.appendChild(doc.createTextNode(zipcode));
    		root.appendChild(zip);
    		
    		Element ref = doc.createElement("Reference_Num");
    		ref.appendChild(doc.createTextNode(refid));
    		root.appendChild(ref);
    		
    		TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\Smit-PC\\workspace\\SamStore\\orders\\Seller\\address.xml"));
            transformer.transform(source, result);
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    }
*/}
