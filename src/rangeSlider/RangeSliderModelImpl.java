package rangeSlider;

import javax.swing.event.ChangeListener;

public class RangeSliderModelImpl implements IRangeSliderModel {

	private int maximum;
	private int minimum;
	private int valueL;
	private int valueR;
	
	@Override
	public int getMinimum(Boolean isRight) {
		return (isRight) ? valueL + 1 : minimum;
	}

	@Override
	public void setMinimum(Boolean isRight, int newMinimmum) {
		if(isRight) 
	}

	@Override
	public int getMaximum(Boolean isRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaximum(Boolean isRight, int newMinimmum) {
		// TODO Auto-generated method stub
		
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
	public void setRangeProperties(int valueL, int valueR, int min, int max) {
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


}
