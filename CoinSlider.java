import javax.swing.JSlider;


public class CoinSlider extends JSlider{
	public CoinSlider(){
		super(JSlider.HORIZONTAL);
		setMaximum(10);
		setPaintLabels(true);
		setPaintTicks(true);
		setMajorTickSpacing(2);
		setMinorTickSpacing(1);
		setPaintTrack(false);
		setInverted(false);
		setValue(0);
	}
}
