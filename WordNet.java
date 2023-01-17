package ngordnet.main;
import java.util.*;
import edu.princeton.cs.algs4.In;

public class WordNet {
    private In synsets;
    private In hyponyms;
    private Graph net;

    public WordNet(String synsetsFile, String hyponymsFile){
        synsets = new In(synsetsFile);
        hyponyms = new In(hyponymsFile);
        net = new Graph();

        //add all the words first
        while (synsets.hasNextLine()) {
            String line = synsets.readLine();
            String[] split = line.split(",");
            net.addWord(split[1]);  //split[1] is the synset that we care about
        }

        while (hyponyms.hasNextLine()) {
            String line = hyponyms.readLine();
            String[] nums = line.split(",");
            int parent = Integer.parseInt(nums[0]);
            for (int i = 1; i < nums.length; i++) {
                int child = Integer.parseInt(nums[i]);
                net.addEdge(parent, child);
            }
        }



    }
    public ArrayList<String> getHyponyms(String word) {
        ArrayList<String> result = new ArrayList<>();
        Set<String> distinct = new HashSet<>();
        if (!net.WordExists(word)) return result;
        ArrayList<Integer> parentIndex = net.getIndicesOfWord(word);
        if (parentIndex.isEmpty()) {

            return result;
        }
        for (int x: parentIndex) {
            distinct.addAll(net.getAllGrandChildrenAsWords(x));
        }
        result.addAll(distinct);
        Collections.sort(result);
        return result;
    }

    public ArrayList<String> getHyponyms(List<String> words) {
        if (words.size() == 0) {
            return new ArrayList<>();
        }
        ArrayList<String> head = getHyponyms(words.get(0));

        for (String x: words) {
            head = getIntersection(head, getHyponyms(x));
        }
        return head;
    }

    public ArrayList<String> getIntersection(ArrayList<String> words1, ArrayList<String> words2) {
        ArrayList<String> result = new ArrayList<>();
        for (String word: words1) {
            if (words2.contains(word)) {
                result.add(word);
            }
        }
        return result;
    }
    public boolean wordExists(String word) {
        return net.WordExists(word);
    }

    public ArrayList<String> getAllWords() {
        return net.getWords();
    }

    public Graph getGraph() {
        return net;
    }



}
