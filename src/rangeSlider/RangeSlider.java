package rangeSlider;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

public class RangeSlider extends JComponent {

	BoundedRangeModel model;
	DefaultBoundedRangeModel modelimpl;
	JSlider slider;
	BasicSliderUI sliderUI;
}
