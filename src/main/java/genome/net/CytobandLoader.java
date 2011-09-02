package genome.net;

import genome.data.Cytoband;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import casmi.io.Reader;
import casmi.net.HTTP;
import casmi.parser.CSV;

/**
 * @author K. NIshimura
 *
 */
public class CytobandLoader {
    
    /*
     * Download data (url: gziped file) and restore data to the class
     */
    public static List<Cytoband> load(String url, String csvfile){
    	
    	List<Cytoband> bandList = new ArrayList<Cytoband>();
    	
        HTTP http = null;
        Reader reader = null;
        HttpURLConnection connection;
        
        CSV csv = new CSV(casmi.util.SystemUtil.JAVA_TMP_PATH + csvfile);

        try {
            http = new HTTP(url);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("can not download file");
        }

        try {
        	connection = http.getConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(false);
            reader = gzipRead(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
        	String line = null;
        	
            while ((line = reader.readLine()) != null) {
            	String[] col = line.split("\t");
            	
                csv.writeLine(col[0], col[1], col[2], col[3], col[4]);
                
            	int chromosome = convertChromosomeNameToIndex(col[0]);
            	
            	int start = new Integer(col[1]).intValue();
            	int stop  = new Integer(col[2]).intValue();
            	
            	if(stop < start){
            		// swap start and stop
            		int tmp = stop;
            		stop = start;
            		start = tmp;
            	}
            	
            	Cytoband cb = new Cytoband(chromosome, start, stop, col[3], col[4]);
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
    
    /*
     * reader for gzip InputStream (for cytoband.txt.gz)
     */
    private static Reader gzipRead(java.io.InputStream is) throws IOException {
        final GZIPInputStream gzipInStream = new GZIPInputStream(is);
        try {
            final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            for (;;) {
                int iRead = gzipInStream.read();
                if (iRead < 0) break;
                outStream.write(iRead);
            }
            Reader r = new Reader(new ByteArrayInputStream(outStream.toByteArray()));
            outStream.flush();
            outStream.close();
            return r;
        } finally {
            gzipInStream.close();
        }
    }
    
    /*
     * to change chromosome name to chromosome number
     */
    private static int convertChromosomeNameToIndex(String chromosome){
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
