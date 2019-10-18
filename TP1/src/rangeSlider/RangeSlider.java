package rangeSlider;

public class RangeSlider {

	protected IRangeSliderModel model;
	
	private boolean leftPressed = false;
	private boolean rightPressed = false;

	public RangeSlider(int valL, int valR, int min, int max, int extent) {
		model = new RangeSliderModelImpl(valL, valR, extent, min, max);
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
