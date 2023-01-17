package ngordnet.main;
import java.util.*;

public class Graph {
    // i forgot what i was tryna do;
    private ArrayList<ArrayList<Integer>> edges;
    private ArrayList<String> words;
    // "words" are actually synsets: they may contain multiple words in one string
    private int numNodes;

    private HashMap<String, ArrayList<Integer>> findIDfromWord;


    public Graph() {
        this.edges = new ArrayList<ArrayList<Integer>>();
        this.words = new ArrayList<String>();
        this.numNodes = 0;
        findIDfromWord = new HashMap<>();
    }

    public void addEdge(int origin, int destination) {
        edges.get(origin).add(destination);

    }

    public boolean WordExists(String word) {
        return findIDfromWord.keySet().contains(word);
    }

    public String getWordAtIndex(int index) {
        return words.get(index);
    }
    public void addWord(String word) {
        words.add(word);
        edges.add(new ArrayList<>());
        String[] wordsInword = word.split(" ");
        for (String w: wordsInword) {
            if (findIDfromWord.containsKey(w)) {
                findIDfromWord.get(w).add(numNodes);
            }
            else{
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(numNodes);
                findIDfromWord.put(w, temp);
            }
        }
        numNodes += 1;

    }

    public ArrayList<Integer> getIndicesOfWord(String word) {
        return findIDfromWord.get(word);
    }

    public ArrayList<Integer> getChildren(int index) {
        return edges.get(index);
    }

    public ArrayList<String> getChildrenAsWords(int index) {
        ArrayList<Integer> indices = getChildren(index);
        ArrayList<String> result = new ArrayList<>();
        for (int x: indices) {
            result.add(getWordAtIndex(x));
        }
        return result;
    }

    public ArrayList<Integer> getAllGrandChildren(int index) {
        ArrayList<Integer> q = new ArrayList<>(getChildren(index));
        ArrayList<Integer> result = new ArrayList<>();


        while (!q.isEmpty()) {
            int temp = q.remove(0);
            result.add(temp);
            q.addAll(getChildren(temp));
        }
        return result;

    }

    public ArrayList<String> getAllGrandChildrenAsWords(int index) {
        ArrayList<Integer> indices = new ArrayList<>(getAllGrandChildren(index));
        ArrayList<String> result = new ArrayList<>();

        //first add the words within the parent

        String parentWords = getWordAtIndex(index);
        String[] splitParents = parentWords.split(" ");

        for (String parentWord: splitParents) {
            result.add(parentWord);
        }
        // done adding the parent words

        for (int x: indices) {
            result.addAll(getWordFromIndexAndSplit(x));
        }
        return result;
    }

    public ArrayList<String> getWordFromIndexAndSplit(int index) {
        String temp = getWordAtIndex(index);
        ArrayList<String> result = new ArrayList<>();
        if (temp == null) {
            return null;
        }
        String[] split = temp.split(" ");
        for (String word: split) {
            result.add(word);
        }
        return result;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public ArrayList<String> getWords(){
        return words;
    }






}
