/*
 * 
 */

package tagHandler;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

public class HeadingTagHandler extends TagSupport {
    
// Attributi passati al tag <heading>
    
    private String bgColor; // Unico attributo obbligatorio.
    private String color = null;
    private String align="CENTER";
    private String fontSize="36";
    private String fontList="Arial, Helvetica, sans-serif";
    private String border="0";
    private String width=null;
    
    
    public void setBgColor(String bgColor) {
	this.bgColor = bgColor;
    }
    public void setColor(String color) {
	this.color = color;
    }
    public void setAlign(String align) {
	this.align = align;
    }
    public void setFontSize(String fontSize) {
	this.fontSize = fontSize;
    }
    public void setFontList(String fontList) {
	this.fontList = fontList;
    }
    public void setBorder(String border) {
	this.border = border;
    }
    public void setWidth(String width) {
	this.width = width;
    }
    
    @Override
    public int doStartTag() { // Valuta il tag d'apertura
	try {
	    JspWriter out = pageContext.getOut();
	    out.print("<TABLE BORDER=" + border +
		      " BGCOLOR=\"" + bgColor + "\"" +
		      " ALIGN=\"" + align + "\"");
	    if (width != null) {
		out.print(" WIDTH=\"" + width + "\"");
	    }
	    out.print("><TR><TH>");
	    out.print("<SPAN STYLE=\"" +
		      "font-size: " + fontSize + "px; " +
		      "font-family: " + fontList + "; ");
	    if (color != null) {
		out.println("color: " + color + ";");
	    }
	    out.print("\"> "); // End of <SPAN ...>
	} catch(IOException ioe) {
	    System.out.println("Error in HeadingTag: " + ioe);
	}
	return(EVAL_BODY_INCLUDE); // Include il body del tag
    }
    
    @Override
    public int doEndTag() { // Valuta il tag di chiusura.
	try {
	    JspWriter out = pageContext.getOut();
	    out.print("</SPAN></TABLE>");
	} catch(IOException ioe) {
	    System.out.println("Error in HeadingTag: " + ioe);
	}
	return(EVAL_PAGE); // Continue with rest of JSP page
    }
}