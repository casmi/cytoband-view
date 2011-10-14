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

import genome.data.Cytoband;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import casmi.graphics.color.Color;
import casmi.graphics.color.ColorSet;
import casmi.graphics.element.MouseOver;
import casmi.graphics.element.Rect;
import casmi.graphics.element.Renderable;


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

/**
 * rendering object for cytoband 
 * 
 * @author K. Nishimura
 *
 */
public class CytobandElement implements Renderable {
	
	private static final double WIDTH = 20;
	private static final double HEIGHT_RATIO = 0.0000025;
	
	private Cytoband cb;
	
	private Rect rect;
	private MouseOver mrect;

	private double x, y;
	
	public CytobandElement(Cytoband cb, double baseX, double baseY) {
		this.rect = makeRect(cb);
		this.cb = cb;
		
		this.setX( baseX );
		this.setY( (cb.getStart() + cb.getLength() / 2.0 + baseY ) * CytobandElement.HEIGHT_RATIO );
		rect.setXY(this.getX(), this.getY());
		this.mrect = new MouseOver(this.rect);
	}

	public Rect getRect() {
		return rect;
	}

	public void setRect(Rect rect) {
		this.rect = rect;
	}

	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public boolean isMouseOver(int x, int y){
		return this.mrect.isMouseOver(x, y);
	}
    
    private static Rect makeRect(Cytoband cb){
    	Rect result = new Rect(WIDTH, HEIGHT_RATIO * cb.getLength() );
    	
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
    	if( cb.getChromosomeNumber() == 23 ){
			return "Chr.X " + this.cb.getBand();
		}else if( cb.getChromosomeNumber() == 24 ){
			return "Chr.Y " + this.cb.getBand();
		}else{
			return "Chr." + this.cb.getChromosomeNumber() + " " + this.cb.getBand();
		}
    }

	public void render(GL gl, GLU glu, int width, int height) {
		gl.glPushMatrix();
		{
			mrect.render(gl, glu, width, height);
		}
		gl.glPopMatrix();
	}

	public void setAlpha(double alpha) {
		// TODO Auto-generated method stub
		
	}

	public void setEndTweenXYZ(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}

	public void setEndTweenRotate(float xr, float yr, float zr) {
		// TODO Auto-generated method stub
		
	}

	public void setEndTweenScale(double sx, double sy, double sz) {
		// TODO Auto-generated method stub
		
	}

	public void setEndTweenA(double as, double af) {
		// TODO Auto-generated method stub
		
	}

	public double[] setTween() {
		// TODO Auto-generated method stub
		return null;
	}


}
