import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Assign3Driver {
		

	public static void main(String[] args) throws FileNotFoundException{
		// TODO Auto-generated method stub

		/**
		 * Make a program to find 
		 * 1.	Total number of words
	2.	Total number of lines
	3.	Total number of blank lines
	4.	Total number of vowels
	5.	Total number of characters
	6.	Histogram depicting vowel usage frequencies
	7.	Histogram depicting character usage frequencies
	The histograms, do not allow the maximum length to exceed the width of the display.
	8.	Create a sorted index of all the words (with length >= 2) and their frequencies.  
	
	#########################################################################################
	Author: Kody Dibble 	Class: CIT260-02		Date: 10/6/2016
	#########################################################################################

		 */
		
		
			
		
	//Filename, words, spaces, lines, all arrays and hashmaps, etc.
		
	String filename;
	System.out.println("**** Welcome to the info-filer ****");
	System.out.println("Get all the file info you need." + "\n");
	System.out.println("Enter file name. ");
	Scanner keyboard = new Scanner(System.in);
	filename = keyboard.nextLine();
	File file = new File(filename);
	Scanner infile = new Scanner(file);
	
	String wordString = "";
	String lineString;
	int chars = 0;
	int spaces = 0;
	int lines = 0;
	int words = 0;
	int vowels = 0;
	int counters = 0;
	ArrayList<String> WordArray = new ArrayList<String>();
	ArrayList<String> LineArray = new ArrayList<String>();
	ArrayList<Character> CharsArray = new ArrayList<Character>();
	ArrayList<String> uniqWordsArray = new ArrayList<String>();
	
	DecimalFormat df = new DecimalFormat("###.00");
	
	
	
	HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
	HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
	HashMap<Character, Integer> vowelMap = new HashMap<Character, Integer>();
	//This will get the lines.
	while(infile.hasNextLine()){
		
		
		
		String currentLine;
		currentLine = infile.nextLine();
		LineArray.add(currentLine);
		currentLine += wordString;
		
	
	//Tokenizer for words.
	StringTokenizer st = new StringTokenizer(currentLine, "[ 0123456789\n%@$&^#().?!*/-\";:,]");
	
		if(! currentLine.isEmpty()){
		while(st.hasMoreTokens()){
			
			words++;
			String nextToke = st.nextToken();
			
			WordArray.add(nextToke);
		
			
		
		for(int i = 0; i < nextToke.length(); i++){
		
			if(((int)(Character.toLowerCase(nextToke.charAt(i)))) >= 97 && ((int)(Character.toLowerCase(nextToke.charAt(i))) <= 122))
			{
			CharsArray.add(nextToke.charAt(i));
			if(charMap.containsKey(Character.toLowerCase(nextToke.charAt(i)))){
				charMap.put(Character.toLowerCase(nextToke.charAt(i)), charMap.get(Character.toLowerCase(nextToke.charAt(i))) + 1);	
			}
			else
				charMap.put(Character.toLowerCase(nextToke.charAt(i)), 1);
		}}
		
	}
		
	
	}
		lines++;	
	}
	

	Collections.sort(WordArray);
	for(String s : WordArray)
	{
		if(!uniqWordsArray.contains(s)){
			uniqWordsArray.add(s);
		}
		if(wordMap.containsKey(s) && s.length() > 1){
		wordMap.put(s, wordMap.get(s) + 1);
		}
		else if(!wordMap.containsKey(s) && s.length() > 1)
		wordMap.put(s, 1); 
	
	}
	//counter for lines.
	

	
	
	
	
	
	

	// Check for spaces.
	for(String space : LineArray){
		
		if(space.trim().length() == 0)
		{
			//counter for spaces.
			spaces++;
			
		}
	
	}
		
	//Vowel counter
	for(int vo = 0; vo < CharsArray.size(); vo++){
		
		char lch = Character.toLowerCase(CharsArray.get(vo));
		if(lch == 'a' || lch =='i' || lch =='e' || lch =='u' || lch =='o'){
			vowels++;
			if(vowelMap.containsKey(lch)){
				vowelMap.put(lch, vowelMap.get(lch) + 1);
			}
				else
					vowelMap.put(lch, 1);
			}
			
		}
		
	
	
	
	//PRINT FOR LINES WORDS BLANKS CHARACTERS AND VOWELS
	System.out.println( "\n" + "Lines: " + lines);
	System.out.println("Words: " + words);
	System.out.println("Blank lines: " + spaces);
	System.out.println("Characters: " + CharsArray.size());
	System.out.println("Vowels: " + vowels);
	
	//ITERATOR TO POPULATE HASHMAP
	
	Iterator wordIterator = wordMap.entrySet().iterator();
	Iterator vowelIterator = vowelMap.entrySet().iterator();
	Iterator charIterator = charMap.entrySet().iterator();
	
	while(vowelIterator.hasNext()){
		HashMap.Entry<Character, Integer> pair = (HashMap.Entry<Character, Integer>)(vowelIterator.next());
		System.out.print(pair.getKey() + ": ");
		for(int i = 0; i < pair.getValue() / 140; i++)
			System.out.print("*");
		System.out.print("\t" + (int)((pair.getValue() * 1.0)/(vowels) * 100) + "%");
		System.out.println();
	}
	
	while(charIterator.hasNext()){
		HashMap.Entry<Character, Integer> pair = (HashMap.Entry<Character, Integer>)(charIterator.next());
		System.out.print(pair.getKey() + ": ");
		for(int i = 0; i < pair.getValue() / 140; i++)
			System.out.print("*");
		if((int)pair.getValue()* 1.0 /(CharsArray.size()) * 100 < 1)
			System.out.println("\t" + df.format(((pair.getValue() * 1.0/(CharsArray.size()) * 100))) + "%");
			System.out.println();
		if((int)pair.getValue()* 1.0 /(CharsArray.size()) * 100 > 1)
		System.out.print("\t" + (int)((pair.getValue() * 1.0)/(CharsArray.size()) * 100) + "%" + "\n" + "------------------------");
		System.out.println();
			
	}
	
	
	//PRINT WORDS IN ORDER
	System.out.print("Printing all words: ");
	for(String word : uniqWordsArray){
			if(!word.startsWith(("'")) && word.length() >= 2)
				System.out.print(word +  ": " + wordMap.get(word) + "| ");
			
	}
	
	//sysout prints for charmap hashmap and vowelmap hashmap. 
	System.out.println("\n");
	
	System.out.println("Printing all characters: " + charMap + "\n");
	System.out.println("Printing all vowels: " + vowelMap + "\n");
	
	// MISC WORDS THAT CONTAIN APOSTROPHES 
	
	System.out.println("MISC WORDS!! Words that contained apostrophe symbols in the beginning");
	
	
	//WORDS IN ORDER
	for(String word : uniqWordsArray){
		if(word.startsWith(("'")) && word.length() >= 2)
			System.out.print(word +  ": " + wordMap.get(word) + "| ");
		
}

	
	System.out.println("\n" +"\n" + "Thanks for using info-filer!!!!");
	
	}
	
	
	}


	

