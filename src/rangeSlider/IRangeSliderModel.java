package rangeSlider;

import javax.swing.event.ChangeListener;

public interface IRangeSliderModel {
	
	int getMinimum(Boolean isRight);
	
	void setMinimum(Boolean isRight, int newMinimmum);
	
	int getMaximum(Boolean isRight);
	
	void setMaximum(Boolean isRight, int newMinimmum);
	
	int getValue(Boolean isRight);
	
	void setValue(Boolean isRight, int newValue);
	
	void setRangeProperties(Boolean isRight, int value, int min, int max);
	
	void addChangeListener(ChangeListener x);
	
	void removeChangeListener(ChangeListener x);
	
	
	

}
