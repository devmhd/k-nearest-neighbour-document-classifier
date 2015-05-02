import java.util.Comparator;

public class DoubleDistanceComparator implements Comparator<Article> {

    @Override
    public int compare(Article a1, Article a2) {
        
        return Double.compare(a1.dDistance, a2.dDistance);
    }
}