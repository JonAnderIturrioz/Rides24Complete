package businessLogic;

import java.util.List;
import java.util.NoSuchElementException;

public class ExtendedCityIterator implements ExtendedIterator<String> {
	
	List<String> departLocations;
	int index;
	
	public ExtendedCityIterator(List<String> departLocations) {
		
		this.departLocations = departLocations;
		this.index = 0;
	}

	@Override
    public boolean hasNext() {
        return index < departLocations.size();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No next element available.");
        }
        index ++;
        return departLocations.get(index);
    }

    @Override
    public String previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException("No previous element available.");
        }
        index --; 
        return departLocations.get(index);
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public void goFirst() {
        index = 0; // Move to the beginning of the list
    }

    @Override
    public void goLast() {
        index = departLocations.size(); // Move to one past the last element
    }
}
