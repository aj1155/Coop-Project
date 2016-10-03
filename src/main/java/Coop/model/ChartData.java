package Coop.model;

public class ChartData {
	String legendText;
	String label;
	int y;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLegendText() {
		return legendText;
	}

	public void setLegendText(String legendText) {
		this.label = legendText;
		this.legendText = legendText;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
