/* RISO: an implementation of distributed belief networks.
 * Copyright (C) 2004, Robert Dodier.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA, 02111-1307, USA,
 * or visit the GNU web site, www.gnu.org.
 */
package riso.distributions;
import java.io.*;
import java.rmi.*;
import riso.numerical.*;
import riso.general.*;

/** An instance of this class represents an exponential distribution.
  */
public class Exponential extends AbstractDistribution
{
	/** The mean of this distribution.
	  */
	protected double lambda;

	/** Create and return a copy of this distribution.
	  */
	public Object clone() throws CloneNotSupportedException
	{
		Exponential copy = (Exponential) super.clone();
		copy.lambda = this.lambda;
		return copy;
	}

	/** Constructs an exponential distribution with the specified parameter.
	  */
	public Exponential( double lambda )
	{
		this.lambda = lambda;
	}

	/** Default constructor for this class. Set mean to 1.
	  */
	public Exponential() { lambda = 1; }

	/** Returns the number of dimensions in which this distribution lives.
	  * Always returns 1.
	  */
	public int ndimensions() { return 1; }

	/** Computes the density at the point <code>x</code>.
	  * @param x Point at which to evaluate density -- must
	  *   be a one-element array.
	  */
	public double p( double[] x )
	{
		if ( x[0] <= 0 ) return 0;

		return (1/lambda) * Math.exp( -x[0]/lambda );
	}

	/** Compute the cumulative distribution function.
	  * @returns Mass to the left of the point <tt>x</tt>.
	  */
	public double cdf( double x ) throws Exception
	{
		return 1 - Math.exp( -x/lambda );
	}

	/** Computes the log of the prior probability of the parameters of
	  * this distribution, assuming some prior distribution has been 
	  * established. This may not be meaningful for all distributions.
	  */
	public double log_prior() throws Exception
	{
		throw new Exception( "Exponential.log_prior: not implemented." );
	}

	/** Return an instance of a random variable from this distribution.
	  */
	public double[] random() throws Exception
	{
        double[] x = new double[1];
        double u = Math.random();

        x[0] = lambda * Math.log (1/(1 - u));
        
        return x;
	}

	/** Use data to modify the parameters of the distribution.
	  * This method is not implemented.
	  */
	public double update( double[][] x, double[] responsibility, int niter_max, double stopping_criterion ) throws Exception
	{
		throw new Exception( "Exponential.update: not implemented." );
	}

	/** Returns the expected value of this distribution.
	  * This is equal to the parameter <tt>lambda</tt>.
	  */
	public double expected_value() 
	{
		return lambda;
	}

	/** Returns the square root of the variance of this distribution.
	  * This is equal to the parameter <tt>lambda</tt>.
	  * the shape parameter.
	  */
	public double sqrt_variance()
	{
		return lambda;
	}

	/** Returns an interval which contains almost all the mass of this
	  * distribution. Since <tt>cdf(x) == 1 - exp(-x/lambda)</tt>,
      * we have <tt>epsilon == 1 - cdf(x) == exp(-x/lambda)</tt>, thus
      * <tt>x == lambda log(1/epsilon)</tt>.
	  *
	  * @param epsilon This much mass or less lies outside the interval
	  *   which is returned.
	  * @return An interval represented as a 2-element array; element 0 is
	  *   zero, and element 1 is <tt>x</tt>, as defined above.
	  */
	public double[] effective_support( double epsilon ) throws Exception
	{
		double[] interval = new double[2];
		interval[0] = 0;
		interval[1] = lambda * Math.log( 1/epsilon );
		return interval;
	}

	/** Formats a string representation of this distribution.
	  * Since the representation is only one line of output, 
	  * the argument <tt>leading_ws</tt> is ignored.
	  */
	public String format_string( String leading_ws )
	{
		String result = "";
		result += this.getClass().getName()+" { ";
		result += "lambda "+lambda;
		result += " }"+"\n";
		return result;
	}

	/** Read an instance of this distribution from an input stream.
	  * This is intended for input from a human-readable source; this is
	  * different from object serialization.
	  * @param st Stream tokenizer to read from.
	  * @throws IOException If the attempt to read the model fails.
	  */
	public void pretty_input( SmarterTokenizer st ) throws IOException
	{
		boolean found_closing_bracket = false;

		try
		{
			st.nextToken();
			if ( st.ttype != '{' )
				throw new IOException( "Exponential.pretty_input: input doesn't have opening bracket." );

			for ( st.nextToken(); !found_closing_bracket && st.ttype != StreamTokenizer.TT_EOF; st.nextToken() )
			{
				if ( st.ttype == StreamTokenizer.TT_WORD && st.sval.equals( "lambda" ) )
				{
					st.nextToken();
					lambda = Double.parseDouble( st.sval );
				}
				else if ( st.ttype == '}' )
				{
					found_closing_bracket = true;
					break;
				}
			}
		}
		catch (IOException e)
		{
			throw new IOException( "Exponential.pretty_input: attempt to read object failed:\n"+e );
		}

		if ( ! found_closing_bracket )
			throw new IOException( "Exponential.pretty_input: no closing bracket on input." );
	}
}
