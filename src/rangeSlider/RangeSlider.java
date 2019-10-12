package rangeSlider;

public class RangeSlider {

	protected IRangeSliderModel model;
	protected RangeSliderUI ui;
	
	private boolean leftPressed = false;
	private boolean rightPressed = false;

	public RangeSlider(int valL, int valR, int min, int max, RangeSliderUI ui) {
		model = new RangeSliderModelImpl(valL, valR, 0, min, max);
		this.ui = ui;
	}
	
	public IRangeSliderModel getModel() {
		return model;
	}

	public void clickLeft(int newValLeft) {
		leftPressed = true;
		model.setValue(false, newValLeft);
		ui.computeLeftBut(newValLeft);
	}

	public void clickLeftButton() {
		// TODO Auto-generated method stub
		
	}

	public void clickRightButton() {
		// TODO Auto-generated method stub
		
	}

	public void clickRight() {
		// TODO Auto-generated method stub
		
	}
	
	
}
