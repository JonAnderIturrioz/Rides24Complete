package functionalTestingClasses;

import java.net.MalformedURLException;

import businessLogic.BLFacade;
import businessLogic.BusinessLogicFactory;
import configuration.ConfigXML;
import domain.Driver;
import domain.DriverTable;

public class AdapterTestMain {

	public static void main(String[] args) {
		// the BL is local
		// boolean isLocal = true;
		
		BLFacade blFacade;
		try {
			blFacade = new BusinessLogicFactory().createBusinessLogic();
			Driver d= blFacade. getDriver("Urtzi");
			DriverTable dt=new DriverTable(d);
			dt.setVisible(true);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

}
