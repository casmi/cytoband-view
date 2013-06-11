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

package jp.xcoo.casmi.cytobandview.view;


import jp.xcoo.casmi.cytobandview.data.Cytoband;
import casmi.graphics.color.ColorSet;
import casmi.graphics.color.RGBColor;
import casmi.graphics.element.Rect;

/**
 * rendering object for cytoband
 *
 * @author K. Nishimura
 * @author Takashi AOKI <federkasten@me.com>
 *
 */
public class CytobandElement extends Rect {

	private static final double WIDTH = 20;
	private static final double HEIGHT_RATIO = 0.0000025;

	private boolean selected;

	private Cytoband cb;

	public CytobandElement(Cytoband cb, double baseX, double baseY) {
	    super(WIDTH, HEIGHT_RATIO * cb.getLength());

		this.cb = cb;

		this.setX( baseX );
		this.setY( (cb.getStart() + cb.getLength() / 2.0 + baseY ) * CytobandElement.HEIGHT_RATIO );

		this.setStrokeColor(ColorSet.GHOST_WHITE);
		this.setStrokeWidth(3);

		this.setStroke(false);
		this.setFill(true);

        if(cb.getColor().equalsIgnoreCase("gpos100")){
            this.setFillColor(new RGBColor(92/255.0, 92/255.0, 81/255.0));
        }else if(cb.getColor().equalsIgnoreCase("gpos")){
            this.setFillColor(new RGBColor(92/255.0, 92/255.0, 81/255.0));
        }else if(cb.getColor().equalsIgnoreCase("gpos75")){
            this.setFillColor(new RGBColor(199/255.0, 192/255.0, 113/255.0));
        }else if(cb.getColor().equalsIgnoreCase("gpos66")){
            this.setFillColor(new RGBColor(199/255.0, 192/255.0, 113/255.0));
        }else if(cb.getColor().equalsIgnoreCase("gpos50")){
            this.setFillColor(new RGBColor(134/255.0, 186/255.0, 204/255.0));
        }else if(cb.getColor().equalsIgnoreCase("gpos33")){
            this.setFillColor(new RGBColor(134/255.0, 186/255.0, 204/255.0));
        }else if(cb.getColor().equalsIgnoreCase("gpos25")){
            this.setFillColor(new RGBColor(221/255.0, 210/255.0, 180/255.0));
        }else if(cb.getColor().equalsIgnoreCase("gvar")){
            this.setFillColor(new RGBColor(241/255.0, 230/255.0, 200/255.0));
        }else if(cb.getColor().equalsIgnoreCase("gneg")){
            this.setFillColor(new RGBColor(250/255.0, 240/255.0, 230/255.0));
        }else if(cb.getColor().equalsIgnoreCase("acen")){
            this.setFillColor(new RGBColor(219/255.0, 98/255.0, 25/255.0));
        }else if(cb.getColor().equalsIgnoreCase("stalk")){
            this.setFillColor(new RGBColor(100/255.0, 127/255.0, 164/255.0));
        }else{
            this.setFillColor(new RGBColor(100/255.0, 100/255.0, 100/255.0));
        }
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (this.selected) {
            this.setStroke(true);
        } else {
            this.setStroke(false);
        }
    }
}
