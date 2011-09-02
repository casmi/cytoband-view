/*
 *   cytoband view examples
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

  
package genome.viewer;

import genome.data.Cytoband;
import genome.net.CytobandLoader;

import java.util.ArrayList;
import java.util.List;

import casmi.Applet;
import casmi.AppletRunner;
import casmi.graphics.Graphics;
import casmi.graphics.color.Color;
import casmi.graphics.color.ColorSet;
import casmi.graphics.element.Rect;
import casmi.graphics.element.Text;
import casmi.graphics.font.Font;
import casmi.hit.HitRect;

/**
 * Cytoband View
 * Class for viewer for cytoband (Human Genome. UCSC hg.19)
 * @author K. NIshimura
 *
 */

public class CytobandView extends Applet {

	private static final double chrw  = 40;
	private static final int numChromosome = 24;
	
	class CytobandElement {
		
		public static final double SIZEW = 20;
		public static final double SIZEP = 0.0000025;
		
		private Cytoband cb;
		
		private Rect rect;
		private HitRect hitRect;

		private double x, y;
		
		public CytobandElement(Cytoband cb) {
			this.rect = makeRect(cb);
			this.hitRect = makeHitRect(cb);
			this.cb = cb;
		}

		public Rect getRect() {
			return rect;
		}

		public void setRect(Rect rect) {
			this.rect = rect;
		}

		public HitRect getHitRect() {
			return hitRect;
		}

		public void setHitRect(HitRect hitRect) {
			this.hitRect = hitRect;
		}
		
		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
			this.hitRect.setX(x);
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
			this.hitRect.setY(y);
		}
		
		public boolean isHit(int x, int y) {
			return this.hitRect.hit(x,y); 
		}
		
	    /*
	     * to set up color for box
	     */
	    private HitRect makeHitRect(Cytoband cb){
	    	HitRect result = new HitRect(SIZEW, SIZEP * cb.getLength() );
	    	return result;
	    }
	    
	    /*
	     * to set up color for box
	     */
	    private Rect makeRect(Cytoband cb){
	    	Rect result = new Rect(SIZEW, SIZEP * cb.getLength() );
	    	
	    	result.setStrokeColor(Color.color(ColorSet.GHOSTWHITE));
	    	result.setStrokeWidth(3);
	    	
	    	if(cb.getColor().equalsIgnoreCase("gpos100")){
	    		result.setFillColor(new Color(92, 92, 81));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("gpos")){
	    		result.setFillColor(new Color(92, 92, 81));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("gpos75")){
	    		result.setFillColor(new Color(199, 192, 113));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("gpos66")){
	    		result.setFillColor(new Color(199, 192, 113));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("gpos50")){
	    		result.setFillColor(new Color(134, 186, 204));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("gpos33")){
	    		result.setFillColor(new Color(134, 186, 204));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("gpos25")){
	    		result.setFillColor(new Color(221, 210, 180));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("gvar")){
	    		result.setFillColor(new Color(241, 230, 200));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("gneg")){
	    		result.setFillColor(new Color(250, 240, 230));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("acen")){
	    		result.setFillColor(new Color(219, 98, 25));
	    		result.setStroke(false);
	    	}else if(cb.getColor().equalsIgnoreCase("stalk")){
	    		result.setFillColor(new Color(100, 127, 164));
	    		result.setStroke(false);
	    	}else{
	    		result.setFillColor(new Color(100, 100, 100));
	    		result.setStroke(false);
	    	}
	    	
	    	return result;
	    }
	    
	    public void setStroke(boolean stroke) {
	    	this.rect.setStroke(stroke);
	    }
	    
	    public String getName() {
	    	if(this.cb.getChr() == 23){
    			return "Chr.X " + this.cb.getBand();
			}else if(this.cb.getChr() == 24){
				return "Chr.Y " + this.cb.getBand();
			}else{
				return "Chr." + this.cb.getChr() + " " + this.cb.getBand();
			}
	    }
	}
	
	private Font f = null;
    private Text t;
    
    private List<Cytoband> bandList = null;
    private List<CytobandElement> bandElementList = new ArrayList<CytobandElement>();
    
    @Override
    public void setup(){
    	bandList = CytobandLoader.load("http://hgdownload.cse.ucsc.edu/goldenPath/hg19/database/cytoBand.txt.gz", "hg19_cytoBand.csv");
    	
    	int [] chrLength = new int[numChromosome];
    	
    	for( Cytoband cb : bandList ) {
    		if( chrLength[cb.getChr()-1] < cb.getStop() ) {
    			chrLength[cb.getChr()-1] = cb.getStop();
    		}
    	}
    	
    	// build render elements
    	for ( Cytoband cb : bandList ){
    		CytobandElement e = new CytobandElement(cb);
    		
    		e.setX(cb.getChr() * chrw);
    		e.setY((cb.getStart() + cb.getLength() / 2.0 - chrLength[cb.getChr()-1] / 2.0) * CytobandElement.SIZEP);
    		
    		bandElementList.add(e);
    	}
    	
        setSize(1024, 768);

        f = new Font("San-Serif");
        f.setSize(20);
        
        t = new Text("                                  ", 450, 730, f);
        t.setStrokeColor(Color.color(ColorSet.WHITE));
    }
    
    /*
     * draw method 
     * @see casmi.Applet#draw(casmi.graphics.Graphics)
     */
    @Override
    public void draw(Graphics g) {
    		
    	// hit test and update
    	int mx = getMouseX() - 10;
		int my = getHeight() - (getMouseY()+getHeight()/2);
		
		String message = null;
		
    	for ( CytobandElement e : bandElementList ) {
    		if( e.isHit(mx, my) ) {
				e.setStroke(true);
				message = e.getName();
			}else{
				e.setStroke(false);
			}
    	}
    	
    	if( message == null ) {
    		message = "Cytoband View";
    	}
    	
		t.setText(message);
		
    	// render
        g.pushMatrix();
        {
        	g.translate( 10, getHeight()/2.0, 0);
        	for ( CytobandElement e : bandElementList ){
        		g.pushMatrix();
        		{
        			g.rotateX(180);
        			g.translate(e.getX(), e.getY());
        			g.render(e.getRect());
        		}
        		g.popMatrix();
        	}
    	}
        g.popMatrix();
        g.render(t);
    }
    
    public static void main(String args[]) {
        AppletRunner.run( "genome.viewer.CytobandView", "Cytoband View");
    }
}
