import java.util.ArrayList;
import java.util.HashMap;


public class Article {


	public String title;
	public int topic;
	public ArrayList<String> lines;

	private HashMap<String,Integer> frequency;


	public Article(int topic) {
		super();

		this.topic = topic;
		this.lines = new ArrayList<String>();
		this.frequency = new HashMap<String,Integer>();
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public void addLine(String line){

		this.lines.add(line);

		String[] words = line.split("[;, \\.]");

		for(int i=0; i<words.length; ++i){

//			if(frequency.containsKey(words[i])){
//
//				frequency.put(words[i], frequency.get(words[i])+ 1 );
//			} else {
//
//				frequency.put(words[i], new Integer(0));
//
//			}


//
//			if(G.topicFrequency.get(topic).containsKey(words[i])){
//
//				G.topicFrequency.get(topic).put(words[i], G.topicFrequency.get(topic).get(words[i])+ 1 );
//			} else {
//
//				G.topicFrequency.get(topic).put(words[i], new Integer(1));
//				
//				
//
//			}



		}


	}




	//	for (Map.Entry<String, String> entry : map.entrySet())
	//	{
	//	    System.out.println(entry.getKey() + "/" + entry.getValue());
	//	}

}
