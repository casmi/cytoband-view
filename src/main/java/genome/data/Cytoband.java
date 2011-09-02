package genome.data;


/**
 *  * class for cyto band
 * 
 * @author K. NIshimura
 *
 */
public class Cytoband {

	private int chr;
	private int start;
	private int stop;
	private String band;
	private String color;
	
	public Cytoband(int chr, int start, int stop, String band, String color){
		this.chr = chr;
		this.start = start;
		this.stop = stop;
		this.band = band;
		this.color = color;
	}
	
	public int getChr() {
		return chr;
	}

	public void setChr(int chr) {
		this.chr = chr;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

	public int getLength() {
		return Math.abs(this.start - this.stop);
	}
	
	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
