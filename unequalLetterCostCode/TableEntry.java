package unequalLetterCostCode;

import java.util.List;
import java.util.Vector;

public class TableEntry {

	private Signature sig;
	private double value;
	boolean terminal;
	private int q;
	private int linkIndex;
	
	public List<Integer> debugLinks;
	public List<Double> debugCost;
	public List <Integer> debugSigLevel;
	
	public TableEntry()
	{
		this(new Signature(), 0);
	}
	
	public TableEntry(Signature signature, double val)
	{
		sig = signature;
		value = val;
		terminal = false;
		q = -1;
		linkIndex= -1;
		
		
		debugLinks = new Vector<Integer>();
		debugCost = new Vector<Double>();
		debugSigLevel = new Vector<Integer>();
		
		
	}
	
	public void setQ(int val)
	{
		q = val;
	}
	
	/*
	 * Set the previous link from the OPT_Table entry, which made this entry
	 */
	public void setLinkIndex(int index)
	{
		linkIndex = index;
	}
	
	public int getQ()
	{
		return q;
	}
	
	public int getLinkIndex()
	{
		return linkIndex;
	}
	public void setSignature(Signature signature)
	{
		sig = signature;
	}
	
	public void setTerminal(boolean val)
	{
		terminal = val;
	}
	
	public boolean getTerminal()
	{
		return terminal;
	}
	
	public boolean isTerminal()
	{
		return terminal;
	}
	
	public void setValue(double val)
	{
		value = val;
	}
	
	public void set(Signature signature, double val)
	{
		sig = signature;
		value = val;
	}
	
	public double getValue()
	{
		return value;
	}
	
	public Signature getSignature()
	{
		return sig;
	}
	
	public int getM()
	{
		return sig.getM();
	}
	public int getL1()
	{
		return sig.getL1();
	}
	
	public String toString()
	{	StringBuffer buf = new StringBuffer();
		buf.append(sig +  "  =  " + value );
		buf.append("  q : " + q  + ", link Index : " + linkIndex);
		if(terminal)
			buf.append("  Terminal  " );
		buf.append("  For Debug : links " + debugLinks  + ", costs : " + debugCost + "Sig" + debugSigLevel);
		buf.append("\n" );
			
		return buf.toString();

	}
}
