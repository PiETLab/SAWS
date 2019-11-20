package unequalLetterCostCode;

import java.util.List;
import java.util.Vector;

public class Signature {
	
	private List<Integer> signature;
	//the ith level of the signature
	private int level;
	
	public Signature()
	{
		signature = new Vector<Integer>();
		level = 0;
	}
	
	public Signature(List<Integer> other)
	{
		signature = other;
		level = 0;
	}
	
	public void add(int i)
	{
		signature.add(i);
		
	}
	
	public void set(List<Integer> other)
	{
		signature = other;
		
	}
	
	public int size()
	{
		return signature.size();
	}
	
	public void set(int index, int element)
	{
		if(signature.size()> index)
		{
			signature.set(index, element);
		}
		else
		{
			signature.add(index, element);
		}
		
		
	}
	
	public void setM(int element)
	{
		if(signature.size()>0)
		{
			signature.set(0, element);
		}
		else
		{
			signature.add(0, element);
		}
	}
	
	public int get(int index)
	{
		return signature.get(index);
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int i)
	{
		level = i;
	}
	

	
	public int getM()
	{
		return signature.get(0);
	}
	
	public int getL1()
	{
		return signature.get(1);
	}
	
	 public boolean equals(Object other) 
	 {
		if(other instanceof Signature)
		{
			if(this.size() == ((Signature)other).size())
			{
				for(int i = 0; i <signature.size(); i++)
				{
					if(signature.get(i) != ((Signature)other).get(i) )
						return false;
				}
				return true;
			}
			return false;
		}
		return false;
	 }
	
	
	/*
	 * This method calculate the signature shift for the method expand, as
	 * described on page 1775 of golin '98
	 * Signature (m; l1, l2, ......lC) is shifted to
	 * (m+l1; l2, l3, ....,lC,0)
	 * @return returns a new signature object that is shifted.  
	 */
	public Signature shiftSignature()
	{
		Signature temp = new Signature();
		temp.setM(this.getM() + this.getL1());
		for(int index = 1; index < this.size() - 1 ; index++)
		{
			temp.set(index, this.get(index + 1));
			
		}
		temp.set(this.size()-1, 0);
		return temp;
		
	}
	
	public Signature scalarMultiplication(int q)
	{
		Signature temp = new Signature();
		for(int index = 0; index < this.size() ; index++)
		{
			temp.set(index, this.get(index)*q);
			
		}
		return temp;

	}
	
	public Signature vectorAddition(Signature other)
	{
		Signature temp = new Signature();
		for(int index = 0; index < this.size() ; index++)
		{
			temp.set(index, this.get(index) + other.get(index));
			
		}
		return temp;

	}
	/*
	 * This method calculates the signature after Expand is applied. 
	 * 
	 * It is calculated according to EQ(3) on page 1775 of golin'98
	 * 
	 * @param charVector it is the characteristic vector, and its first entry must
	 * 			be zero
	 * sig i+1 (T) = (m+l1; l2, l3, ....,lC,0) + q . (-1;d1,d2,.....,dC)
	 */
	public Signature calculateExpandedSignature(int q, Signature charVector)
	{
		if(charVector.getM()== -1)
		{
			Signature expandedSig = shiftSignature() ;
			Signature temp = charVector.scalarMultiplication(q);
			expandedSig = expandedSig.vectorAddition(temp);
			
			expandedSig.setLevel(this.getLevel()+1);
			
			return expandedSig;
		}
		return null;
	}
	
	public Signature clone()
	{
		Signature temp = new Signature();
		for(int index = 0; index < this.size() ; index++)
		{
			temp.add(this.get(index) );
			
		}
		return temp;
	}
	
	/*
	 * This method returns a Signature object with 
	 * all the entries of the Signature added i.e.
	 * For signature (m, l1, l2 , ..... , lc)
	 * it returns (m+l1+l2+l3+.....+lc) 
	 */
	public int sumAll()
	{
		int result = 0;
		for(int index = 0; index < this.size() ; index++)
		{
			result += this.get(index);
			
		}
		return result;
	}
	/*
	 * checks if (l1,.....lC) == (0,0,.....0)
	 */
	public boolean allZeros()
	{
		for(int index = 1; index < this.size() ; index++)
		{
			if ( this.get(index) != 0)
				return false;
			
		}
		return true;
	}
	
	/*
	 * Calculates the partial Sum for the lexicographical linear 
	 * ordering, as described on page 1777 
	 */
	public Signature partialSum()
	{
		Signature newSig = new Signature();
		newSig.setM(this.getM());
		int totalSum = this.getM();
		for(int i = 1; i <this.size(); i ++)
		{
			totalSum += this.get(i);
			newSig.add(totalSum);
		}
		return newSig;
	}
	
	/**
	 * It returns 1 if this > other
	 * it returns -1 if this < other
	 * it returns 0 if this == other
	 * @param other
	 * @return
	 */
	
	public int partialSumCompare(Signature other)
	{
		if(other instanceof Signature)
		{
			if(this.size() == ((Signature)other).size())
			{
				for(int i= signature.size()-1; i >= 0; i--)
				{
					if(signature.get(i) < ((Signature)other).get(i) )
						return -1;
					else if(signature.get(i) > ((Signature)other).get(i))
						return 1;
				}
				return 0;
			}
			
		}
		return -100;
	}
	
	/**
	 * 
	 * @param level
	 * @return
	 */
	
	public String toString()
	{	StringBuffer buf = new StringBuffer();
		buf.append("sig(T)[level " + level +  "] = (" );
		for (int i = 0; i <signature.size();i++)
		{
			if(i < (signature.size()-1))
				buf.append(signature.get(i) + ", " );
			else
				buf.append(signature.get(i) + " )" );
		}
		return buf.toString();

	}
	/*
	public String toString(int level)
	{	StringBuffer buf = new StringBuffer();
		buf.append( "sig(T)[level " + level+  "] = ( " );
		for (int i = 0; i <signature.size();i++)
		{
			if(i < (signature.size()-1))
				buf.append(signature.get(i) + ", " );
			else
				buf.append(signature.get(i) + " )\n" );
		}
		return buf.toString();

}*/

public String toStringProbLaTeX() {
	StringBuffer buf = new StringBuffer();
	buf.append("\\chunk{" + "[]" + "$_{"
			+ this.toString() + "}$}");
	// buf.append("\\chunk{" + super.getNodeID() + "$_{"
	// + super.toStringExpectedFrequency() + "}$}");
	return buf.toString();
}

		
	

}
