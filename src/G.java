import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;


public class G {

	public static final double SMALL_PROBABILITY = 0.00000000000001;


	public static HashMap<String, Integer> topicsMap;
	public static String[] topics;

	public static ArrayList<Article> trainingArticles;
	public static ArrayList<Article> testArticles;

	public static int trainingArticleCount = 0;
	public static int testArticleCount = 0;

	public static HashMap<String, Integer> nDocumentsContainingWord;

	public static HashSet<String> allWords;



	public static HashSet<String> dontCareWords;


	public static void init(){

		dontCareWords = new HashSet<String>();

		dontCareWords.add("the");
		dontCareWords.add("a");
		dontCareWords.add("to");
		dontCareWords.add("of");
		dontCareWords.add("an");
		dontCareWords.add("over");
		dontCareWords.add("upon");
		dontCareWords.add("on");

	}


	public static int hammingDistance(Article trainArticle, Article testArticle){

		int distance = 0;

		HashSet<String> wordList = new HashSet<String>();
		
		for (Entry<String, Integer> entry : trainArticle.frequency.entrySet())
		{

				wordList.add(entry.getKey());
		}
		

		for (Entry<String, Integer> entry : testArticle.frequency.entrySet())
		{
			if( ! (wordList.contains(entry.getKey())))

				wordList.add(entry.getKey());
		}

		for(String word : wordList){

			if(trainArticle.contains(word) ^ testArticle.contains(word))
				distance++;
		}

		return distance;

	}


	public static int classifyByHammingDistance(Article testArticle, int k){


		// update all distances;
		for(Article trainArticle : trainingArticles ){

			trainArticle.hammingDistance = hammingDistance(trainArticle, testArticle);
//			System.out.println("distance " + trainArticle.hammingDistance);

		}

		Collections.sort(trainingArticles, new HammingDistanceComparator());

		
		int[] topicsFrequency = new int[topicsMap.size()];
		
		for(int i=0; i<k; ++i){
			//System.out.println(topics[trainingArticles.get(i).topic] + " " + trainingArticles.get(i).hammingDistance);
			topicsFrequency[trainingArticles.get(i).topic]++;
			
		}
		
		int iMax = -1;
		int max = 0;
		for(int i =0; i<topics.length; ++i){
			
			if(topicsFrequency[i] > max){
				max = topicsFrequency[i];
				iMax = i;
			}
		}
		
		return iMax;

	}



}
