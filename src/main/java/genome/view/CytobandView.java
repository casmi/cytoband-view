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
  
package genome.view;

import genome.data.Chromosome;
import genome.data.Cytoband;
import genome.net.CytobandLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import casmi.Applet;
import casmi.AppletRunner;
import casmi.graphics.Graphics;
import casmi.graphics.color.Color;
import casmi.graphics.color.ColorSet;
import casmi.graphics.element.Text;
import casmi.graphics.font.Font;

/**
 * cytoband view
 * Class for viewer for cytoband (Human Genome. UCSC hg.19)
 * 
 * @author K. Nishimura
 *
 */

public class CytobandView extends Applet {

	private static final String TITLE_NAME = "Cytoband View";
	
	private static final String REMOTE_DATA_URL = "http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/cytoBand.txt.gz";
	
	private Font f;
    private Text t;
    
    private final double X_STEP = 40;
    
    private List<CytobandElement> bandElementList = new ArrayList<CytobandElement>();
    
    @Override
    public void setup(){
    	
    	final Map<String, Chromosome> chrMap = CytobandLoader.load(REMOTE_DATA_URL);
    	
    	
    	
    	// build render elements
    	for( String name : Chromosome.CHROMOSOME_NAMES ) {
    		Chromosome chr = chrMap.get(name);
    		
    		for( Cytoband cb : chr.getBands() ) {
    			CytobandElement e = new CytobandElement(cb, cb.getChromosomeNumber() * X_STEP, - chr.getLength() / 2);
        		bandElementList.add(e);
    		}
    	}
    	
    	setSize(1024, 768);

        f = new Font("San-Serif");
        f.setSize(20);
        
        t = new Text("                                  ", f, 450, 730);
        t.setStrokeColor(Color.color(ColorSet.WHITE));
        
        setFPS(12.0);
    }
    
    @Override
    public void draw(Graphics g) {
    		
    	// hit test and update
    	int mx = getMouseX() - 10;
		int my = getHeight() - (getMouseY() + getHeight() / 2);
		
		String message = null;
		
    	for ( CytobandElement e : bandElementList ) {
    		if( e.isMouseOver(mx, my) ) {
				e.setStroke(true);
				message = e.getName();
			}else{
				e.setStroke(false);
			}
    	}
    	
    	if( message == null ) {
    		message = TITLE_NAME;
    	}
    	
		t.setText(message);
		
    	// render
        g.pushMatrix();
        {
        	g.translate( 10, getHeight()/2.0, 0 );
        	g.rotateX(180);
        	
        	for ( CytobandElement e : bandElementList ){
        		g.pushMatrix();
        		{
        			g.render(e);
        		}
        		g.popMatrix();
        	}
    	}
        g.popMatrix();
        
        g.render(t);
    }
    
    public static void main(String args[]) {
		AppletRunner.run("genome.view.CytobandView", "Cytoband View");
	}
}
