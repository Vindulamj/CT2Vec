package org.ipl.word2vec;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class CorpusTrainer {

    static BufferedWriter bw = null;
    static FileWriter fw = null;
    static String text = null;
    private static final String FILENAME = "C:\\Users\\User\\Desktop\\results.txt";


    public static void main(String[] args) throws Exception {

        fw = new FileWriter("C:\\Users\\User\\Desktop\\newCorpus.txt");
        bw = new BufferedWriter(fw);
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((sCurrentLine = br.readLine()) != null) {
                LemmatizeAndWriteToFile(sCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }


        if (bw != null)
            bw.close();

        if (fw != null)
            fw.close();
    }

    public static void LemmatizeAndWriteToFile(String text) throws IOException {

        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // this is the lemmatized version of the token
                String word = token.word();
                if(String.valueOf(word).chars().allMatch(Character::isLetter)) {
                    bw.write(String.valueOf(word) + " ");
                }
            }
        }
    }

}
