package Coop.model;

public class ChartData {
	String legendText;
	String indexLabel;
	int y;

	public String getIndexLabel() {
		return indexLabel;
	}

	public void setIndexLabel(String indexLabel) {
		this.indexLabel = indexLabel;
	}

	public String getLegendText() {
		return legendText;
	}

	public void setLegendText(String legendText) {
		this.indexLabel = legendText;
		this.legendText = legendText;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
