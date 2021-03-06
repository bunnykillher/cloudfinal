package cloud_final;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;






import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class GetNewWord {
	public static void main(String[] args) {
		Word word = getWord("wife");
		System.out.println("yay "+ word.getName()  + word.getDefinition());

	}
	
	public Word getWordDef(String word){
		Word returnWord = getWord(word);
		return returnWord;
	}
	public static Word getWord(String input) {
		// the SAX way:
		// try{
		// DictionaryHandler handler = new DictionaryHandler();
		// SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		// SAXParser saxParser = saxParserFactory.newSAXParser();
		// URL url = new
		// URL("http://www.dictionaryapi.com/api/v1/references/sd3/xml/"+word+"?key=5f342dec-57b4-4ff8-90fa-98c8cc5ed8c3");
		// URLConnection connection = url.openConnection();
		//
		// saxParser.parse(connection.getInputStream(),handler);
		//
		// }catch(Exception e){
		// System.out.println(e.getMessage());
		//
		// }
		Word word = new Word();
		word.setName(input);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			URL url = new URL("http://www.dictionaryapi.com/api/v1/references/sd3/xml/" + input
					+ "?key=5f342dec-57b4-4ff8-90fa-98c8cc5ed8c3");
			URLConnection connection = url.openConnection();

			Document document = builder.parse(connection.getInputStream());

			NodeList nodeList = document.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				 Node node = nodeList.item(i);
				 if (node.getNodeType() == Node.ELEMENT_NODE) {
					 Element elem = (Element) node;
					 String definition = elem.getElementsByTagName("dt").item(0).getChildNodes().item(0).getNodeValue();
					 System.out.println(definition);
					 if(i==1){
						 System.out.println("Hello");
						 word.setDefinition(definition);
					 }
				 }
			}

		} catch (Exception e) {
		}
		return word;
	}

	public static InputStream get(String url) throws IOException {
		URL urlObj = new URL(url);
		return urlObj.openStream();
	}
}
