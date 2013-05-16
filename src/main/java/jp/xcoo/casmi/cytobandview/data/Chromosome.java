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

import java.util.ArrayList;
import java.util.List;

/**
 * chromosome data
 * 
 * @author K. Nishimura
 *
 */
public class Chromosome {
	
	public static final int NUM_CHROMOSOMES = 24;
	
	public static final List<String> CHROMOSOME_NAMES = new ArrayList<String>();
	
	static {
		CHROMOSOME_NAMES.add("chr1");
		CHROMOSOME_NAMES.add("chr2");
		CHROMOSOME_NAMES.add("chr3");
		CHROMOSOME_NAMES.add("chr4");
		CHROMOSOME_NAMES.add("chr5");
		CHROMOSOME_NAMES.add("chr6");
		CHROMOSOME_NAMES.add("chr7");
		CHROMOSOME_NAMES.add("chr8");
		CHROMOSOME_NAMES.add("chr9");
		CHROMOSOME_NAMES.add("chr10");
		CHROMOSOME_NAMES.add("chr11");
		CHROMOSOME_NAMES.add("chr12");
		CHROMOSOME_NAMES.add("chr13");
		CHROMOSOME_NAMES.add("chr14");
		CHROMOSOME_NAMES.add("chr15");
		CHROMOSOME_NAMES.add("chr16");
		CHROMOSOME_NAMES.add("chr17");
		CHROMOSOME_NAMES.add("chr18");
		CHROMOSOME_NAMES.add("chr19");
		CHROMOSOME_NAMES.add("chr20");
		CHROMOSOME_NAMES.add("chr21");
		CHROMOSOME_NAMES.add("chr22");
		CHROMOSOME_NAMES.add("chrX");
		CHROMOSOME_NAMES.add("chrY");
	}
	
	public Chromosome() {
		this.bands = new ArrayList<Cytoband>();
	}
	
	private List<Cytoband> bands;

	public List<Cytoband> getBands() {
		return bands;
	}

	public void setBands(List<Cytoband> bands) {
		this.bands = bands;
	}
	
	public int getStart() {
		int start = Integer.MAX_VALUE;
		
		for( Cytoband b : bands ) {
			if( start > b.getStart() ) {
				start = b.getStart();
			}
		}
		
		return start;
	}
	
	public int getStop() {
		int stop = Integer.MIN_VALUE;
		
		for( Cytoband b : bands ) {
			if( stop < b.getStop() ) {
				stop = b.getStop();
			}
		}
		
		return stop;
	}
	
	public int getLength() {
		return getStop() - getStart();
	}
	
    /**
     * convert chromosome name to chromosome number
     * 
     * @param chromosome
     * @return
     */
    public static int convertChromosomeNameToIndex(String chromosome){
        int chromosomeNumber;
    	if(chromosome.equalsIgnoreCase("chr1")){
    		chromosomeNumber = 1;
    	}else if(chromosome.equalsIgnoreCase("chr2")){
    		chromosomeNumber = 2;
    	}else if(chromosome.equalsIgnoreCase("chr3")){
    		chromosomeNumber = 3;
    	}else if(chromosome.equalsIgnoreCase("chr4")){
    		chromosomeNumber = 4;
    	}else if(chromosome.equalsIgnoreCase("chr5")){
    		chromosomeNumber = 5;
    	}else if(chromosome.equalsIgnoreCase("chr6")){
    		chromosomeNumber = 6;
    	}else if(chromosome.equalsIgnoreCase("chr7")){
    		chromosomeNumber = 7;
    	}else if(chromosome.equalsIgnoreCase("chr8")){
    		chromosomeNumber = 8;
    	}else if(chromosome.equalsIgnoreCase("chr9")){
    		chromosomeNumber = 9;
    	}else if(chromosome.equalsIgnoreCase("chr10")){
    		chromosomeNumber = 10;
    	}else if(chromosome.equalsIgnoreCase("chr11")){
    		chromosomeNumber = 11;
    	}else if(chromosome.equalsIgnoreCase("chr12")){
    		chromosomeNumber = 12;
    	}else if(chromosome.equalsIgnoreCase("chr13")){
    		chromosomeNumber = 13;
    	}else if(chromosome.equalsIgnoreCase("chr14")){
    		chromosomeNumber = 14;
    	}else if(chromosome.equalsIgnoreCase("chr15")){
    		chromosomeNumber = 15;
    	}else if(chromosome.equalsIgnoreCase("chr16")){
    		chromosomeNumber = 16;
    	}else if(chromosome.equalsIgnoreCase("chr17")){
    		chromosomeNumber = 17;
    	}else if(chromosome.equalsIgnoreCase("chr18")){
    		chromosomeNumber = 18;
    	}else if(chromosome.equalsIgnoreCase("chr19")){
    		chromosomeNumber = 19;
    	}else if(chromosome.equalsIgnoreCase("chr20")){
    		chromosomeNumber = 20;
    	}else if(chromosome.equalsIgnoreCase("chr21")){
    		chromosomeNumber = 21;
    	}else if(chromosome.equalsIgnoreCase("chr22")){
    		chromosomeNumber = 22;
    	}else if(chromosome.equalsIgnoreCase("chrX")){
    		chromosomeNumber = 23;
    	}else if(chromosome.equalsIgnoreCase("chrY")){
    		chromosomeNumber = 24;
    	}else{
    		chromosomeNumber = 0;
    	}
		return chromosomeNumber;
    }
}
