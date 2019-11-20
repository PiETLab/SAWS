package unequalLetterCostCode;

import java.util.List;
import java.util.Vector;

public class OPTTable {
	
	private List <TableEntry> OPT;
	
	public OPTTable()
	{
		OPT = new Vector<TableEntry>();
		
	}
	
	public void add(Signature sig, double val)
	{
		add(new TableEntry(sig, val));
	}
	
	public void add(int index , Signature sig, double val)
	{
		add(index , new TableEntry(sig, val));
	}

	public void add(TableEntry entry)
	{
		//search if this entry exists before for the following signature;
		OPT.add(entry);
	}
	
	public void add(int index, TableEntry entry)
	{
		//search if this entry exists before for the following signature;
		OPT.add(index, entry);
	}
	
	public void set(int index, TableEntry entry)
	{
		//search if this entry exists before for the following signature;
		if(index < OPT.size())
		{	
			OPT.set(index, entry);
		}
		else
		{
			OPT.add(index, entry);
		}
	}
	
	public void set(int index , Signature sig, double val)
	{
		set(index , new TableEntry(sig, val));
	}
	
	public TableEntry get(int index )
	{
		return OPT.get(index);
	}
	
	public TableEntry get(Signature sig)
	{
		int result = entryExists(sig);
		if(result != -1)
		{
			return get(result); 
		}
		return null;
	}
	
	public int size()
	{
		return OPT.size();
	}
	public double getValue(Signature sig)
	{
		int result = entryExists(sig);
		if(result != -1)
		{
			return (get(result)).getValue(); 
		}
		return -1;
	}
	
	public double getValue(int index)
	{
		return (get(index)).getValue(); 
		
	}
	
	/*
	 * If the following signature already exists in the table
	 */
	
	public int entryExists(Signature sig)
	{
		return entryExists(0, sig);
	}
	
	/*
	 * index is the index from which we want to start 
	 * the search for the signature in the opt table
	 * 
	 * @return returns the index in the OPT table
	 * else it returns -1 
	 */
	public int entryExists(int index , Signature sig)
	{
		if(index < OPT.size())
		{
			for(int i = index; i < OPT.size(); i ++)
			{
				if( (OPT.get(i).getSignature()).equals(sig))
				{
					return i;
				}
			}
				
		}
		return -1;
	}
	
	public String toString()
	{	StringBuffer buf = new StringBuffer();
		buf.append("OPT TABLE \n" );
		for (int i = 0; i <OPT.size();i++)
		{
			buf.append("Opt[" + i + "] = " +OPT.get(i) );
		}
		return buf.toString();

	}
}
