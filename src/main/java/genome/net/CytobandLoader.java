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

package genome.net;

import genome.data.Chromosome;
import genome.data.Cytoband;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import casmi.io.Reader;
import casmi.net.HTTP;
import casmi.parser.CSV;
import casmi.util.FileUtil;

/**
 * Load cytoband data from csv
 * 
 * @author K. Nishimura
 *
 */
public class CytobandLoader {
    
	private static final String CACHE_FILE_NAME = "hg19_cytoBand.csv";
	private static final String CACHE_FILE_PATH = casmi.util.SystemUtil.JAVA_TMP_PATH + CACHE_FILE_NAME;
	
    /**
     * Read cached cytoband CSV file
     * 
     * @param filePath
     * @return
     */
    private static List<Cytoband> readFromCache(String filePath) {
    	
    	List<Cytoband> bandList = new ArrayList<Cytoband>();
    	
        try {
        	
        	CSV csv = new CSV(filePath);
        	String[] col;
        	
            while ((col = csv.readLine()) != null) {
            	int start = new Integer(col[1]).intValue();
            	int stop  = new Integer(col[2]).intValue();
            	
            	if(stop < start){
            		// swap start and stop
            		int tmp = stop;
            		stop = start;
            		start = tmp;
            	}
            	
            	Cytoband cb = new Cytoband(col[0], start, stop, col[3], col[4]);
            	bandList.add(cb);
            }
            csv.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        return bandList;
    }
    
    /**
     * Download original data (gziped file) and parse it
     * 
     * @param url
     * @param cachePath
     * @return
     */
    private static List<Cytoband> readFromServer(String url, String cachePath) {
    	List<Cytoband> bandList = new ArrayList<Cytoband>();
		
        HTTP http = null;
        Reader reader = null;
        
        CSV csv = new CSV(cachePath); // disk cache as csv

        try {
            http = new HTTP(url);
            reader = http.requestGetWithGzip();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("can not download file");
        }

        try {
        	String line = null;
        	
            while ((line = reader.readLine()) != null) {
            	String[] col = line.split("\t");
            	
                csv.writeLine(col[0], col[1], col[2], col[3], col[4]);
                
            	int start = new Integer(col[1]).intValue();
            	int stop  = new Integer(col[2]).intValue();
            	
            	if(stop < start){
            		// swap start and stop
            		int tmp = stop;
            		stop = start;
            		start = tmp;
            	}
            	
            	Cytoband cb = new Cytoband(col[0], start, stop, col[3], col[4]);
            	bandList.add(cb);
            }
            csv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        http.disconnect();
        reader.close();
        
        return bandList;
    }
    
    /**
     * Load cytoband data from cache if exist, otherwise download from original data
     * 
     * @param url
     * @return
     */
    public static Map<String, Chromosome> load(String url){
    	List<Cytoband> bands = null;
    	
    	if( FileUtil.exist(CACHE_FILE_PATH) ) {
    		bands = readFromCache(CACHE_FILE_PATH);
    	} else {
    		bands = readFromServer(url, CACHE_FILE_PATH);
    	}
    	
    	Map<String, Chromosome> result = new HashMap<String, Chromosome>();
    	
    	for( Cytoband b : bands ) {
    		Chromosome chr = result.get(b.getChrName());
    		
    		if( chr == null ) { 
    			chr = new Chromosome();
    			chr.getBands().add(b);
    			result.put(b.getChrName(), chr);
    		} else {
    			chr.getBands().add(b);
    		}
    	}
    	
    	return result;
    }
}
