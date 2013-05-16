/*
 *   cytoband View
 *   http://casmi.github.com/
 *   Copyright (C) 2011, Xcoo, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package jp.xcoo.casmi.cytobandview.data;


/**
 * cytoband data
 * 
 * @author K. Nishimura
 *
 */
public class Cytoband {

	private String chrName;
	private int start;
	private int stop;
	private String band;
	private String color;
	
	public Cytoband(String chrName, int start, int stop, String band, String color){
		this.chrName = chrName;
		this.start = start;
		this.stop = stop;
		this.band = band;
		this.color = color;
	}
	
	public String getChrName() {
		return chrName;
	}

	public void setChrName(String chrName) {
		this.chrName = chrName;
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
	
	public int getChromosomeNumber() {
		return Chromosome.convertChromosomeNameToIndex(chrName);
	}
}
