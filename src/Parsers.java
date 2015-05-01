import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Parsers {

	public static void readTopicNames(){

		G.topicsMap = new HashMap<String, Integer>();
		G.topics = new String[135];


		FileInputStream fstream;
		BufferedReader br;
		try {


			fstream = new FileInputStream("topics.data");
			br = new BufferedReader(new InputStreamReader(fstream));


			String line;

			int topicsCount = 0;

			while ((line = br.readLine()) != null)   {


				G.topicsMap.put(line, new Integer(topicsCount));

				G.topics[topicsCount] = line;

				topicsCount++;


			}



			br.close();

			System.out.println("## Done loading " + G.topicsMap.size() + " topics");


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}







	public static void readTrainingArticles(){

		FileInputStream fstream;
		BufferedReader br;
		try {

			fstream = new FileInputStream("training.data");
			br = new BufferedReader(new InputStreamReader(fstream));


			String line;



			int currentTopicIndex = -1;
		


			G.nDocumentsContainingWord = new HashMap<String, Integer>();
			G.trainingArticles = new ArrayList<Article>();
			G.allWords = new HashSet<String>();
			
			G.trainingArticleCount = 0;
			

			while ((line = br.readLine()) != null){


				if(line.isEmpty()){
					continue;		
				}

				// found topic name
				else if(   !(line.contains(" "))  && G.topicsMap.containsKey(line)   ){

					currentTopicIndex = G.topicsMap.get(line);
					G.trainingArticleCount++;
//					currentArticleIndex++;
					
					G.trainingArticles.add(new Article(currentTopicIndex, true));


				// found article line
				} else {

					G.trainingArticles.get(G.trainingArticles.size() - 1).addLine(line);

				}
			}

			br.close();



		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}


//		System.out.println(G.allWords.toString());
	}


	
	public static void readTestArticles(){

		FileInputStream fstream;
		BufferedReader br;
		try {

			fstream = new FileInputStream("test.data");
			br = new BufferedReader(new InputStreamReader(fstream));


			String line;



			int currentTopicIndex = -1;
		


			
			G.testArticles = new ArrayList<Article>();
			
			G.testArticleCount = 0;
			

			while ((line = br.readLine()) != null){


				if(line.isEmpty()){
					continue;		
				}

				// found topic name
				else if(   !(line.contains(" "))  && G.topicsMap.containsKey(line)   ){

					currentTopicIndex = G.topicsMap.get(line);
					G.testArticleCount++;

					
					G.testArticles.add(new Article(currentTopicIndex, false));


				// found article line
				} else {

					G.testArticles.get(G.testArticles.size() - 1).addLine(line);

				}
			}

			br.close();



		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}


	
	
	
	
	

}

