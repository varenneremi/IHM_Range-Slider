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

//	DefaultBoundedRangeModel def;
//	
//	protected IRangeSliderModel sliderModel;
//	protected ChangeListener changeListener = createChangeListener();
//	
//	private Dictionary labelTable;
//	
//	//protected int majorTickSpacing;
//	//protected int minorTickSpacing;
//	//protected boolean snapToTicks = false;
//	
//	protected transient ChangeEvent changeEvent = null;
//	
//	public RangeSlider(int min, int max, int valueL, int valueR) {
//		setModel(new RangeSliderModelImpl(valueL, valueR, 0, min, max));	
//		updateUI();
//	}
//	
//
//
//	private void setModel(RangeSliderModelImpl newModel) {
//		IRangeSliderModel oldModel = getModel();
//		
//		if (oldModel != null) {
//			oldModel.removeChangeListener(changeListener);
//		}
//		
//		sliderModel = newModel;
//		
//		if (newModel != null) {
//			newModel.addChangeListener(changeListener);
//		}
//		
//		// maybe add a condition that we can find on the setModel of the JSlider in relation with the accessibleContext
//		firePropertyChange("model", oldModel, sliderModel);
//	}
//
//
//	public IRangeSliderModel getModel() {
//		return sliderModel;
//	}
//
//	public void addChangeListener(ChangeListener l) {
//		listenerList.add(ChangeListener.class, l);
//	}
//
//	public void removeChangeListener(ChangeListener l) {
//		listenerList.remove(ChangeListener.class, l);
//	}
//
//	private ChangeListener createChangeListener() {
//		return new ModelListener();
//	}
//	
//	public int getValue(boolean isRight) {
//		return getModel().getValue(isRight);
//	}
//	
//	public void setValue(boolean isRight, int newValue) {
//		IRangeSliderModel m = getModel();
//		int oldValue = m.getValue(isRight);
//		if (oldValue == newValue)
//			return;
//		m.setValue(isRight, newValue);
//	}
//	
//	public int getMinimum() {
//		return getModel().getMinimum();
//	}
//	
//	public int getMaximum() {
//		return getModel().getMaximum();
//	}
//	
//	public int getExtent() {
//		return getModel().getExtent();
//	}
//	
//	public void setExtent(int extent) {
//		getModel().setExtent(extent);
//	}
//	
//	public Dictionary getLabelTable() {
//		return labelTable;
//	}
//	
//	public void setLabelTable(Dictionary labels) {
//		Dictionary oldTable = labelTable;
//		labelTable = labels;
//		updateLabelUIs();
//		firePropertyChange("labelTable", oldTable, labelTable);
//		if(labels!=oldTable) {
//			revalidate();
//			repaint();
//		}
//	}
//	
//	
//	private void updateLabelUIs() {
//		Dictionary labelTable = getLabelTable();
//		if (labelTable == null) 
//			return;
//		Enumeration labels = labelTable.keys();
//		while(labels.hasMoreElements()) {
//			JComponent component = (JComponent) labelTable.get(labels.nextElement());
//			component.updateUI();
//			component.setSize(component.getPreferredSize());
//		}
//	}
//
//	public Hashtable createStandardLabels(int increment, int start) {
//		if (start > getMaximum() || start < getMinimum())
//            throw new IllegalArgumentException( "Slider label start point out of range." );
//		if (increment <= 0)
//            throw new IllegalArgumentException( "Label incremement must be > 0" );
//		
//		
//		class SmartHashtable extends Hashtable<Object, Object> implements PropertyChangeListener {
//			int increment = 0;
//			int start = 0;
//			boolean startAtMin = false;
//			
//			class LabelUIResource extends JLabel implements UIResource {
//				public LabelUIResource(String text, int aligment) {
//					super(text, aligment);
//					setName("RangeSlider.label");
//				}
//				
//				public Font getFont() {
//					Font font = super.getFont();
//					if (font != null && !(font instanceof UIResource)) {
//						return font;
//					}
//					return RangeSlider.this.getFont();
//				}
//				
//				public Color getForeground() {
//					Color fg = super.getForeground();
//					if (fg != null && !(fg instanceof UIResource)) {
//						return fg;
//					}
//					if (!(RangeSlider.this.getForeground() instanceof UIResource)) {
//						return RangeSlider.this.getForeground();
//					}
//					return fg;
//				}
//			}
//			
//			public SmartHashtable(int increment, int start) {
//				super();
//				this.increment = increment;
//				this.start = start;
//				startAtMin = start == getMinimum();
//				createLabels();
//			}	
//			
//			public void propertyChange(PropertyChangeEvent evt) {
//				if(evt.getPropertyName().equals("minimum") && startAtMin) {
//					start = getMinimum();
//				}
//				if(evt.getPropertyName().equals("minimum") || evt.getPropertyName().equals("maximum")) {
//					Enumeration keys = getLabelTable().keys();
//					Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
//					while(keys.hasMoreElements()) {
//						Object key = keys.nextElement();
//						Object value = labelTable.get(key);
//						if(!(value instanceof LabelUIResource)) {
//							hashtable.put(key,  value);
//						}
//					}
//					clear();
//					createLabels();
//					
//					keys = hashtable.keys();
//					while(keys.hasMoreElements()) {
//						Object key = keys.nextElement();
//						put(key, hashtable.get(key));
//					}
//					((RangeSlider)evt.getSource()).setLabelTable(this);
//				}
//			}
//			
//			private void createLabels() {
//				for(int labelIndex = start; labelIndex <= getMaximum(); labelIndex += increment) {
//					put(Integer.valueOf(labelIndex), new LabelUIResource(""+labelIndex, JLabel.CENTER));
//				}
//			}
//			
//		}
//		SmartHashtable table = new SmartHashtable(increment, start);
//		Dictionary labelTable = getLabelTable();
//		if(labelTable != null && (labelTable instanceof PropertyChangeListener)) {
//			removePropertyChangeListener((PropertyChangeListener)labelTable);
//		}
//		addPropertyChangeListener(table);
//		return table;
//	}
//
//	private class ModelListener implements ChangeListener, Serializable {
//
//		@Override
//		public void stateChanged(ChangeEvent e) {
//			fireStateChanged();
//		}
//	}
//
//	protected void fireStateChanged() {
//		Object[] listeners = listenerList.getListenerList();
//		for (int i = listeners.length - 2; i >= 0; i -= 2) {
//			if (listeners[i]==ChangeListener.class) {
//				if (changeEvent == null) {
//					changeEvent = new ChangeEvent(this);
//				}
//			((ChangeListener)listeners[i+1]).stateChanged(changeEvent);
//			}
//		}
//	}
}
