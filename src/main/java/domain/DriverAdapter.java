package domain;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DriverAdapter extends AbstractTableModel {

	protected Driver driver;
	
	protected String[] columnNames = new String[] {"From", "To", "Date", "Places", "Price" };
	
	public DriverAdapter(Driver driver) {
		this.driver = driver;
	}

	@Override
	public int getRowCount() {
		return driver.getCreatedRides().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
	    return columnNames[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<Ride> rideList = driver.getCreatedRides();
		Ride currentRide = rideList.get(rowIndex);
		
		switch(columnIndex){
		case 0: 
	  		return currentRide.getFrom();
	  	case 1: 
	  		return currentRide.getTo();
	  	case 2: 
	  		return currentRide.getDate();
	  	case 3: 
	  		return currentRide.getnPlaces();
	  	case 4: 
			return currentRide.getPrice();
		}
		
		return null;
	}
}
