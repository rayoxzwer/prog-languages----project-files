import word_occurrences.Occurrences;

import java.io.FileNotFoundException;

public class OccurrencesTester {

    public static void main(String[] args) throws FileNotFoundException {
        test1();
        test2();
    }

    public static void test1() throws FileNotFoundException {

        System.out.println("Running test1");

        Occurrences occ = new Occurrences("dir1");
        System.out.println(occ);
        System.out.println("distinct words: " + occ.distinctWords());
        System.out.println("total occurrences: " + occ.totalOccurrences());
        System.out.println();
    }

    public static void test2() throws FileNotFoundException {

        System.out.println("------------------");
        System.out.println("Running test2");

        Occurrences occ = new Occurrences("test_dir");

        System.out.println("distinct words: " + occ.distinctWords());
        System.out.println("total occurrences: " + occ.totalOccurrences());
        System.out.println("total occurrences of love: " + occ.totalOccurrencesOfWord("love"));
        System.out.println("total occurrences of ob-la-di: " + occ.totalOccurrencesOfWord("ob-la-di"));
        System.out.println("total occurrences of detest: " + occ.totalOccurrencesOfWord("detest"));

        System.out.println("total occurrences of that's in Money.txt: "
                + occ.totalOccurrencesOfWordInFile("that's", "test_dir\\Music\\Beatles\\Money.txt"));

        System.out.println("total occurrences of ob-la-di in Money.txt: "
                + occ.totalOccurrencesOfWordInFile("ob-la-di", "test_dir\\Music\\Beatles\\Money.txt"));

        System.out.println("total occurrences of the in Funny.txt: "
                + occ.totalOccurrencesOfWordInFile("the", "test_dir\\Music\\Beatles\\Funny.txt"));

        System.out.print(occ);
    }

}
