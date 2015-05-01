import java.util.ArrayList;
import java.util.HashMap;


public class Article {


	public int topic;
	public int wordCount;
	public HashMap<String,Integer> frequency;
	
	

	private boolean isTraining;


	public Article(int topic, boolean isTraining) {
		super();
		wordCount = 0;
		this.topic = topic;
		this.isTraining = isTraining;
		this.frequency = new HashMap<String,Integer>();
	}
	
	
	public boolean contains(String word){
		
		return frequency.containsKey(word);
		
	}


	public void addLine(String line){


		String[] words = line.split("[/\\(\\)\";, \\.]");

		for(int i=0; i<words.length; ++i){

			String theWord = words[i].toLowerCase();


			if(theWord.isEmpty()) continue;

			if(G.dontCareWords.contains(theWord)) continue;

			wordCount++;

			// put in own frequency
			if(frequency.containsKey(theWord)){

				frequency.put(theWord, frequency.get(theWord)+ 1 );
			} else {

				frequency.put(theWord, new Integer(1));

				if(isTraining){

					// update document count containing the word
					if(G.nDocumentsContainingWord.containsKey(theWord)){

						G.nDocumentsContainingWord.put(theWord, G.nDocumentsContainingWord.get(theWord)+ 1 );
					} else {

						G.nDocumentsContainingWord.put(theWord, new Integer(1));

					}
				}


			}

			if(isTraining){
				
				// put in global word list
				if( ! (G.allWords.contains(theWord))){

					G.allWords.add(theWord);
					
				}
				

			}



		}


	}
	
	
	
	public int distance;




	//	for (Map.Entry<String, String> entry : map.entrySet())
	//	{
	//	    System.out.println(entry.getKey() + "/" + entry.getValue());
	//	}

}
