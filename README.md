# About cytoband view

## Abstract
"cytoband view" is a viewer for the Human chromosome cytoband (Human Genome, UCSC hg.19). When you point to a band, the band name (like, Chr.8q12.1) will be shown. Colors of bands change to make a good show. 

## Inside of Program
1.  It automatically downloads cytoBand.txt.gz from the UCSC Human annotation site and stores to a local machine cache(CytobandLoader.java).
2.  The data is read and is stored to objects (Cytoband.java).
3.  After that, the data is set to the appropriate position according to the bands (using base pairs) (CytobandView.java).
4.  To draw the data, it uses the Rect method for each band (CytobandView.java).
5.  It also uses Mouseover for testing mouse hits to objects. When the mouse is on the objects, it writes messages to the band name and draw strokes to the band(CytobandView.java, CytobandElement.java).


- For download via HTTP, it uses casmi.net.HTTP
- For parsing downloaded data, it uses casmi.parser.CSV
- For drawing bands, it uses casmi.element.Rect
- For detecting mouse hits, it uses casmi.graphics.element.Mouseover
- For drawing text, it uses casmi.element.Text and casmi.font.Font

