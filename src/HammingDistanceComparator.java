import java.util.Comparator;

public class HammingDistanceComparator implements Comparator<Article> {

    @Override
    public int compare(Article a1, Article a2) {
        
        return a1.distance - a2.distance;
    }
}