package ngordnet.main;


import java.util.ArrayList;

public class TestGraph {
    public static void main (String[] args) {
        Graph subject = new Graph();
        subject.addWord("dog inu");
        subject.addWord("cat");
        subject.addWord("fish");
        subject.addWord("chihuahua");
        subject.addWord("pug ugly_dog");
        subject.addWord("animal");
        subject.addWord("mammal");
        subject.addWord("cat neko mao");
        subject.addEdge(subject.getIndicesOfWord("animal").get(0), 0);
        subject.addEdge(subject.getIndicesOfWord("dog").get(0), subject.getIndicesOfWord("chihuahua").get(0));
        System.out.println(subject.getChildren(0));
        System.out.println(subject.getWordAtIndex(subject.getIndicesOfWord("fish").get(0)));
        System.out.println(subject.getIndicesOfWord("inu"));
        subject.addEdge(0,4);
        System.out.println(subject.getAllGrandChildren(subject.getIndicesOfWord("animal").get(0)));
        System.out.println(subject.getAllGrandChildrenAsWords(5));
        System.out.println(subject.getIndicesOfWord("this key does not exist"));
        System.out.println(subject.getIndicesOfWord("cat"));
        String test = "1,happening occurrence occurrent natural_event,dummy";
        String[] split = test.split(",");
        System.out.println(split[1]);
        String synsetFile = "./data/wordnet/synsets16.txt";
        String hyponymFile = "./data/wordnet/hyponyms16.txt";
        WordNet net = new WordNet(synsetFile, hyponymFile);
        ArrayList<String> thing = new ArrayList<>();
        ArrayList<String> thing2 = new ArrayList<>();
        thing.add("a");
        thing2.add("a");
        thing.add("b");
        thing2.add("b");
        thing2.add("c");
        thing2.add("d");
        thing2.add("e");
        thing.add("e");
        thing2 = net.getIntersection(thing, thing2);

        System.out.println(thing2);

    }
}
