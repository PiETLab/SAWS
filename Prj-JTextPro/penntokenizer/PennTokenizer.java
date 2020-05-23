/*
    Java version of Brill's Part-of-Speech Tagger
    Copyright (C) 2003-2004, Jimmy Lin

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program; if not, please visit 
    http://www.gnu.org/copyleft/gpl.html
*/

package penntokenizer;

import java.io.Serializable;

/**
 * 
 * Tokenizer that conforms to the Penn Treebank conventions for tokenization.
 * 
 * @author Jimmy Lin
 *  
 */
 
public final class PennTokenizer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 767473194928549974L;

	/**
	 * Tokenizes according to the Penn Treebank conventions.
	 */
	public static String tokenize(String str) {
		// str = str.replaceAll("»", " ");
		// str = str.replaceAll("(\\s)>(\\s)", "$1 $2");
		// str = str.replaceAll("(\\s)<(\\s)", "$1 $2");
		// str = str.replaceAll("|", " ");

		// replacing Unicode characters
		str = str.replaceAll("“", "``");
		str = str.replaceAll("”", "''");
		str = str.replaceAll("’", "'");
		
		// searching \" characters and replace with "``" or "''"
		int count = 0;
		while (true) {
		    int index = str.indexOf('\"');
		    
		    if (index < 0) {
			break;
		    }
		    
		    count++;
		    
		    if (count % 2 == 1) {
			str = str.replaceFirst("\"", "``");
		    } else {
			str = str.replaceFirst("\"", "''");
		    }
		}		

		str = str.replaceAll("``", " `` ");
		str = str.replaceAll("''", "  '' ");
		str = str.replaceAll("([?!\";#$%&])", " $1 ");
		str = str.replaceAll("\\.\\.\\.", " ... ");

		str = str.replaceAll("([^.])([.])([\\])}>\"']*)\\s*$", "$1 $2$3 ");

		str = str.replaceAll("([\\[\\](){}<>])", " $1 ");
		str = str.replaceAll("--", " -- ");

		str = str.replaceAll("$", " ");
		str = str.replaceAll("^", " ");

		//str = str.replaceAll("\"", " '' ");
		str = str.replaceAll("([^'])' ", "$1 ' ");
		str = str.replaceAll("'([sSmMdD]) ", " '$1 ");
		str = str.replaceAll("'ll ", " 'll ");
		str = str.replaceAll("'re ", " 're ");
		str = str.replaceAll("'ve ", " 've ");
		str = str.replaceAll("n't ", " n't ");
		str = str.replaceAll("'LL ", " 'LL ");
		str = str.replaceAll("'RE ", " 'RE ");
		str = str.replaceAll("'VE ", " 'VE ");
		str = str.replaceAll("N'T ", " N'T ");

		str = str.replaceAll(" ([Cc])annot ", " $1an not ");
		str = str.replaceAll(" ([Dd])'ye ", " $1' ye ");
		str = str.replaceAll(" ([Gg])imme ", " $1im me ");
		str = str.replaceAll(" ([Gg])onna ", " $1on na ");
		str = str.replaceAll(" ([Gg])otta ", " $1ot ta ");
		str = str.replaceAll(" ([Ll])emme ", " $1em me ");
		str = str.replaceAll(" ([Mm])ore'n ", " $1ore 'n ");
		str = str.replaceAll(" '([Tt])is ", " $1 is ");
		str = str.replaceAll(" '([Tt])was ", " $1 was ");
		str = str.replaceAll(" ([Ww])anna ", " $1an na ");

		str = str.replaceAll("([^0-9]),", "$1 ,");
		str = str.replaceAll(",([^0-9])", ", $1");
		str = str.replaceAll("([^\\s]),([\\s])", "$1 , $2");
		
		str = str.replaceAll("([^\\s]):([\\s])", "$1 : $2");
		
		str = str.trim();
		str = str.replaceAll("\\.$", " .");
		str = str.replaceAll("\\.''$", " . ''");
		
		// "Nicole I. Kidman" gets tokenized as "Nicole I . Kidman"
		str = str.replaceAll(" ([A-Z]) \\.", " $1. ");
		str = str.replaceAll("\\s+", " ");
		str = str.replaceAll("^\\s+", "");
		
		str = str.replaceAll("\\s+", " ");
		str = str.trim();
		
		return str;
	}
}

