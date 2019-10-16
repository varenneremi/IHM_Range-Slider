package rangeSlider;

import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicSliderUI;

public class RangeSlider {

	protected IRangeSliderModel model;
	
	private boolean leftPressed = false;
	private boolean rightPressed = false;

	public RangeSlider(int valL, int valR, int min, int max) {
		model = new RangeSliderModelImpl(valL, valR, 0, min, max);
	}
	
	public IRangeSliderModel getModel() {
		return model;
	}
	
	public boolean getLeftPressed() {
		return leftPressed;
	}

	public boolean getRightPressed() {
		return rightPressed;
	}
	
	public void clickLeft(int newValLeft) {
		leftPressed = true;
		model.setValue(false, newValLeft);
	}

	public void clickLeftButton() {
		leftPressed = true;
	}

	public void clickRightButton() {
		rightPressed = true;
	}

	public void clickRight(int newValRight) {
		rightPressed = true;
		model.setValue(true, newValRight);
	}

	public void mouseReleased() {
		leftPressed = false;
		rightPressed = false;
	}

	public void dragLeftButton(int newValLeft) {
		if(leftPressed) {
			model.setValue(false, newValLeft);
		}
	}

	public void dragRightButton(int newValRight) {
		if(rightPressed) {
			model.setValue(true, newValRight);
		}
	}

}
