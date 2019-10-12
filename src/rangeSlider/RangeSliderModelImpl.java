package rangeSlider;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class RangeSliderModelImpl implements IRangeSliderModel {

	private int minimum;
	private int maximum;
	private int valueL;
	private int valueR;
	private int extent;
	
	protected EventListenerList listenerList = new EventListenerList();
	
	protected transient ChangeEvent changeEvent = null;
	
	public RangeSliderModelImpl(int valueL, int valueR, int extent, int min, int max) {
		if ((max >= min) &&
				(valueL >= min) &&
				(valueR >= valueL) &&
				((valueR + extent) >= valueR) &&
				((valueR + extent) <= max)) {
			this.valueL = valueL;
			this.valueR = valueR;
			this.extent = extent;
			this.minimum = min;
			this.maximum = max;
		}
		else {
			throw new IllegalArgumentException("invalid range properties");
		}
	}
	
	public int getMinimum() {
		return minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	@Override
	public int getValue(Boolean isRight) {
		return (isRight ? valueR : valueL); 
	}

	@Override
	public void setValue(Boolean isRight, int newValue) {
		if (isRight) {
			this.valueR = newValue;
			setRangeProperties(this.valueL, newValue, this.extent, this.minimum, this.maximum);
		}
		else {
			this.valueL = newValue;
			setRangeProperties(newValue, this.valueR, this.extent, this.minimum, this.maximum);
		}
	}
		

	public int getExtent() {
		return this.extent;
	}

	public void setExtent(int newEvent) {
		int newExtent = Math.max(0, newEvent);
		if(valueL + newExtent > valueR)
			newExtent = valueR - valueL;
		else if (valueR + newExtent > maximum)
			newExtent = maximum - valueR;
	}
		
	public void setRangeProperties(int valueL, int valueR, int extent, int min, int max) {
		if (min > max)		// why in the defaultBoundedRangeModel, the code don't change also max = min 
			min = max;
		if (valueR > max)
			max = valueR;
		if (valueL > valueR)
			throw new IllegalArgumentException("You must give the value " + valueR + " before " + valueL);
		if (valueL > min)
			min = valueL;
		if (((long)extent + (long)valueR > max))
			extent = max - valueR;
		if (extent < 0)
			extent = 0;
		
		boolean isChange = 
				(this.valueL != valueL) ||
				(this.valueR != valueR) ||
				(this.extent != extent) ||
				(this.minimum != min) ||
				(this.maximum != max);

		if (isChange) {
			this.valueL = valueL;
			this.valueR = valueR;
			this.extent = extent;
			this.minimum = min;
			this.maximum = max;
		}
		fireStateChanged();		// I don't understand if it's useful and how it works
	}

	protected void fireStateChanged() {
		Object[] listeners = listenerList.getListenerList();
		for(int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ChangeListener.class) {
				if (changeEvent == null) {
					changeEvent = new ChangeEvent(this);
				}
				((ChangeListener)listeners[i+1]).stateChanged(changeEvent);
			}
		}
	}

	@Override
	public void addChangeListener(ChangeListener x) {
		listenerList.add(ChangeListener.class, x);
	}

	@Override
	public void removeChangeListener(ChangeListener x) {
		listenerList.remove(ChangeListener.class, x);
	}

}
