package ngordnet.main;

import ngordnet.hugbrowsermagic.HugNgordnetServer;
import ngordnet.ngrams.NGramMap;

public class Main {
    public static void main(String[] args) {
        HugNgordnetServer hns = new HugNgordnetServer();
        String wordFile = "./data/ngrams/top_49887_words.csv";
        String countFile = "./data/ngrams/total_counts.csv";

        String synsetFile = "./data/wordnet/synsets.txt";
        String hyponymFile = "./data/wordnet/hyponyms.txt";
        /*here
        String WORDS_FILE = "data/ngrams/very_short.csv";
        String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
        String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
        String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
        NGramMap testngm = new NGramMap(WORDS_FILE, TOTAL_COUNTS_FILE);
        WordNet testwordNet = new WordNet(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        */

        NGramMap ngm = new NGramMap(wordFile, countFile);
        WordNet wordNet = new WordNet(synsetFile, hyponymFile);

        hns.startUp();
        hns.register("history", new HistoryHandler(ngm));
        hns.register("historytext", new HistoryTextHandler(ngm));
        hns.register("hyponyms", new HyponymsHandler(wordNet, ngm));
    }
}
