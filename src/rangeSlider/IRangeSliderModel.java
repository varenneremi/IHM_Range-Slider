package rangeSlider;

import javax.swing.event.ChangeListener;

public interface IRangeSliderModel {
	
	int getMinimum();
	
	//void setMinimum(Boolean isRight, int newMinimmum);
	
	int getMaximum();
	
	//void setMaximum(Boolean isRight, int newMinimmum);
	
	int getValue(Boolean isRight);
	
	void setValue(Boolean isRight, int newValue);
	
	int getExtent();
	
	void setExtent(int newEvent);
	
	void setRangeProperties(int valueL, int valueR, int extent, int min, int max);
	
	void addChangeListener(ChangeListener x);
	
	void removeChangeListener(ChangeListener x);
	
	
	

}
