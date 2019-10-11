package rangeSlider;

import javax.swing.event.ChangeListener;

public class RangeSliderModelImpl implements IRangeSliderModel {

	private int maximum;
	private int minimum;
	private int valueL;
	private int valueR;
	private int extent;
	
	@Override
	public int getMinimum(Boolean isRight) {
		return (isRight) ? valueL + extent : minimum;
	}

	@Override
	public void setMinimum(int newMinimmum) {
		int newMax = Math.max(newMinimmum + extent, maximum);
        int newValueL = Math.max(newMinimmum, valueL);
        int newValueR = Math.max(newMinimmum + extent, valueR);
        int newExtent = Math.min(newValueR - newValueL, extent);
        setRangeProperties(newValueL, newValueR, newExtent, newMinimmum, newMax);
	}

	@Override
	public int getMaximum(Boolean isRight) {
		return (isRight) ? maximum : valueR - extent;
	}

	@Override
	public void setMaximum(int newMaximmum) {
		int newMin = Math.min(newMaximmum - extent, minimum);
        int newExtent = Math.min(newMaximmum - newMin, extent);
        int newValueL = Math.min(n - newExtent, value);
        int newValueR = Math.min(n - newExtent, value);
        setRangeProperties(newValue, newExtent, newMin, n, isAdjusting);
	}

	@Override
	public int getValue(Boolean isRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(Boolean isRight, int newValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRangeProperties(int valueL, int valueR, int extent, int min, int max) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addChangeListener(ChangeListener x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChangeListener(ChangeListener x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getExtent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setExtent(int newEvent) {
		// TODO Auto-generated method stub
		
	}

}
