
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		G.init();

		Parsers.readTopicNames();
		Parsers.readTrainingArticles();
		Parsers.readTestArticles();

		//		G.testArticles.get(0).calculateTFIDFs();
		//		System.out.println("magnitude: " + G.testArticles.get(0).magnitude);

		for(Article article : G.trainingArticles){

			article.calculateTFIDFs();

		}

		for(Article article : G.testArticles){

			article.calculateTFIDFs();

		}

		System.out.println("All Tf-IDF weights are calculated");



		int pos = 0;
		int count = 0;
		for(Article testArticle : G.testArticles){



			if(G.classifyByCosineSimilarity(testArticle, 3) == testArticle.topic)
				pos++;

			System.out.println("Now Processing: " + (count++) + " Accuracy: " + (double)pos/count);
		}





		//	System.out.println("Accuracy: " + (double)pos/count);


	}

}
