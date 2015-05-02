import java.util.HashMap;
import java.util.Map;


public class Article {


	public int topic;
	public int wordCount;
	public HashMap<String,Integer> frequency;
	public HashMap<String,Double> tfidf;
	public double magnitude;



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


					// update document count containing the word
					if(G.nDocumentsContainingWord.containsKey(theWord)){

						G.nDocumentsContainingWord.put(theWord, G.nDocumentsContainingWord.get(theWord)+ 1 );
					} else {

						G.nDocumentsContainingWord.put(theWord, new Integer(1));

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

	public void calculateTFIDFs(){
		
		tfidf = new HashMap<String,Double>();
		
		
		double sum = 0;

		for (Map.Entry<String, Integer> entry : frequency.entrySet())
		{
//			String word = entry.getKey() + "/" + entry.getValue());
			
			double tf = (double) entry.getValue() / (double) wordCount;
			double idf = Math.log((double) G.testArticles.size() / (double) G.nDocumentsContainingWord.get(entry.getKey()));
			
//			System.out.println(entry.getKey() + " " + idf + " " + G.testArticles.size() + " " + nDocumentsContainingTheWord);
			
			double tfidfVal = tf * idf;
			
			tfidf.put(entry.getKey(), new Double(tfidfVal));
			
			sum += (tfidfVal) ;
		}
		
		magnitude = Math.sqrt(sum);
	}



	public int distance;
	public double dDistance;



}
