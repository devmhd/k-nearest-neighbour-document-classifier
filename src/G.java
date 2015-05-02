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


	private static HashSet<String> keyUnion(HashMap<String, Integer> a, HashMap<String, Integer> b ){

		HashSet<String> wordList = new HashSet<String>();

		for (Entry<String, Integer> entry : a.entrySet())
		{

			wordList.add(entry.getKey());
		}


		for (Entry<String, Integer> entry : b.entrySet())
		{
			if( ! (wordList.contains(entry.getKey())))

				wordList.add(entry.getKey());
		}

		return wordList;
	}


	public static int hammingDistance(Article trainArticle, Article testArticle){

		int distance = 0;

		HashSet<String> wordList = keyUnion(trainArticle.frequency, testArticle.frequency);


		for(String word : wordList){

			if(trainArticle.contains(word) ^ testArticle.contains(word))
				distance++;
		}

		return distance;

	}




	public static int classifyByHammingDistance(Article testArticle, int k){


		// update all distances;
		for(Article trainArticle : trainingArticles ){

			trainArticle.distance = hammingDistance(trainArticle, testArticle);
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


	public static double euclidianDistance(Article trainArticle, Article testArticle){

		int sum = 0;

		HashSet<String> wordList = keyUnion(trainArticle.frequency, testArticle.frequency);

		int difference;

		int countInTrain, countInTest;

		for(String word : wordList){

			if(trainArticle.contains(word))
				countInTrain = trainArticle.frequency.get(word);
			else
				countInTrain = 0;


			if(testArticle.contains(word))
				countInTest = testArticle.frequency.get(word);
			else
				countInTest = 0;


			sum += ((countInTrain - countInTest) * (countInTrain - countInTest));



		}

		return Math.sqrt(sum);

	}

	public static int classifyByEuclidianDistance(Article testArticle, int k){


		// update all distances;
		for(Article trainArticle : trainingArticles ){

			trainArticle.dDistance = euclidianDistance(trainArticle, testArticle);
			//			System.out.println("distance " + trainArticle.hammingDistance);

		}

		Collections.sort(trainingArticles, new DoubleDistanceComparator());


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


	public static double cosineSimilarity(Article trainArticle, Article testArticle){


		int sum = 0;

		HashSet<String> wordList = keyUnion(trainArticle.frequency, testArticle.frequency);

//		int difference;

		int countInTrain, countInTest;

		for(String word : wordList){

			if(trainArticle.contains(word))
				countInTrain = trainArticle.frequency.get(word);
			else
				countInTrain = 0;


			if(testArticle.contains(word))
				countInTest = testArticle.frequency.get(word);
			else
				countInTest = 0;


			sum += (countInTrain * countInTest);



		}

		return (double)sum / trainArticle.magnitude / testArticle.magnitude;

	}
	
	public static int classifyByCosineSimilarity(Article testArticle, int k){


		// update all distances;
		for(Article trainArticle : trainingArticles ){

			trainArticle.dDistance = cosineSimilarity(trainArticle, testArticle);
			//			System.out.println("distance " + trainArticle.hammingDistance);

		}

		Collections.sort(trainingArticles, new DoubleDistanceComparator());


		int[] topicsFrequency = new int[topicsMap.size()];

		for(int i=trainingArticles.size()-1; i>=(trainingArticles.size() - k); --i){
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
