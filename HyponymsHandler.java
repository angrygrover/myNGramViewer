package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    // pair class in order to keep track of word usage
    private class Pair {
        private String word;
        private Double count;
        private Pair(String word, Double count) {
            this.word = word;
            this.count = count;
        }
        private Double getCount() {
            return count;
        }
        private String getWord() {
            return word;
        }
    }
    //private method to get usage sum
    private Double getUsagesum(TimeSeries t) {
        Double sum = 0.0;
        for (Double x: t.data()) {
            sum += x;
        }
        return sum;
    }
    private WordNet net;
    private NGramMap map;
    public HyponymsHandler(WordNet net, NGramMap map) {
        this.net = net;
        this.map = map;
    }



    @Override
    public String handle(NgordnetQuery q) {

        ArrayList<String> hyponyms = net.getHyponyms(q.words());


        // if k is not selected, it will return the hyponyms regularly
        if (q.k() == 0) {

            return hyponyms.toString();
        }
        // end of k == 0 case


        ArrayList<Pair> usageHistory = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        int startYear = q.startYear();
        int endYear = q.endYear();

        for (String x: hyponyms) {
            Double timesUsed = getUsagesum(map.countHistory(x, startYear, endYear));

            Pair pair = new Pair(x, timesUsed);
            usageHistory.add(pair);
        }
        Collections.sort(usageHistory, Comparator.comparing(p -> -p.getCount()));
        //if k is bigger than num hyponyms, we just return the max number

        /*if (q.k() >= usageHistory.size()) {
            return hyponyms.toString();
        }*/
        if (usageHistory.size() == 0) {
            return result.toString();
        }
        for (int i = 0; i < q.k(); i++) {
            if (i >= usageHistory.size()) {
                break;
            }
            if (usageHistory.get(i).getCount() > 0) {

                result.add(usageHistory.get(i).getWord());

            }


        }
        Collections.sort(result);
        return result.toString();
    }

}
