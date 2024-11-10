package functionalTestingClasses;

import java.net.MalformedURLException;

import businessLogic.BLFacade;
import businessLogic.BusinessLogicFactory;
import businessLogic.ExtendedIterator;
import configuration.ConfigXML;

public class IteratorTestMain {

	public static void main(String[] args) {
		// the BL is local
		ConfigXML config = ConfigXML.getInstance();
		BLFacade blFacade;
		try {
			blFacade = new BusinessLogicFactory().createBusinessLogic(config);
			
			ExtendedIterator<String> i = blFacade.getDepartCitiesIterator();
			String c;
			System.out.println("_____________________");
			System.out.println("FROM LAST TO FIRST");
			i.goLast(); // Go to last element
			
			while (i.hasPrevious()) {
				c = i.previous();
				System.out.println(c);
			}
			
			System.out.println();
			System.out.println("_____________________");
			System.out.println("FROM FIRST TO LAST");
			i.goFirst(); // Go to first element
			
			while (i.hasNext()) {
				c = i.next();
				System.out.println(c);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
