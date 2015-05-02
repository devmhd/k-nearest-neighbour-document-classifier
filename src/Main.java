
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		G.init();
		
		Parsers.readTopicNames();
		Parsers.readTrainingArticles();
		Parsers.readTestArticles();
		
		
		int pos = 0;
		int count = 0;
		for(Article testArticle : G.testArticles){
			
			
			if(G.classifyByEuclidianDistance(testArticle, 3) == testArticle.topic)
				pos++;
			
			System.out.println("Now Processing: " + (count++) + " Accuracy: " + (double)pos/count);
		}
		
		
	//	System.out.println("Accuracy: " + (double)pos/count);
		

	}

}
