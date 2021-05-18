import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class Cryptogram{
    private static final char[] ALPHABET = { // A final char array of the capital English alphabet
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
         'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    private static final String[] PHRASES = { // Strings to be used as encoding examples, can be expanded.
        "I think, therefore I am.",
        "Once upon a midnight dreary...",
        "An apple a day keeps the doctor away!",
        "Always look on the bright side of life!"
        // Add some more!
    };
    private Scanner input;
    private ArrayList<Character> cipher; // A unique cipher held by each Cryptogram object.

    public Cryptogram(){ // Constructor - Randomly generated cipher.
        this.input = new Scanner(System.in);
        this.cipher = new ArrayList<Character>();
        for(int i = 65; i <= 90; i++)
            cipher.add((char)i);
        Collections.shuffle(cipher);
    }

    public Cryptogram(ArrayList<Character> cipher){ // Constructor - Object is passed a cipher from the user.
        this.input = new Scanner(System.in);
        this.cipher = new ArrayList<Character>();
        for(Character c : cipher)
            this.cipher.add(c);
    }

    public String encode(String text){
        // Using the Cryptograph Object's cipher...
        return encode(text, this.cipher);
    }

    public static String encode(String text, ArrayList<Character> cipher){
        // Using the given cipher...
        String output = "";
        boolean changed = false;
        for(int i = 0; i < text.length(); i++){
            changed = false;
            for(int j = 0; j < ALPHABET.length; j++){
                if(text.charAt(i) == ALPHABET[j] || Character.toString(text.charAt(i)).toLowerCase().equals(Character.toString(ALPHABET[j]).toLowerCase())){
                    output += cipher.get(j);
                    changed = true;
                }
            }
            if(!changed)
                output += text.charAt(i);
        }
        return output;
    }

    public String decode(String text){
        // Using the Cryptograph Object's cipher...
        return decode(text, this.cipher);
    }

    public static String decode(String text, ArrayList<Character> cipher){
        // Using the given cipher...
        String output = "";
        boolean changed = false;
        for(int i = 0; i < text.length(); i++){
            changed = false;
            for(int j = 0; j < cipher.size(); j++){
                if(text.charAt(i) == cipher.get(j) || Character.toString(text.charAt(i)).toLowerCase().equals(Character.toString(cipher.get(j)).toLowerCase())){
                    output += ALPHABET[j];
                    changed = true;
                }
            }
            if(!changed)
                output += text.charAt(i);
        }
        return output;
    }

    public static int randInt(int min, int max){ // Returns a random integer between min and max, inclusive.
        return (int)Math.floor(Math.random() * (max - min + 1) + 1);
    }

    public String toString(){
        String output = "[";
        for(int i = 0; i < ALPHABET.length; i++){
            output += ALPHABET[i] + "=>" + this.cipher.get(i);
            if(i < ALPHABET.length - 1)
                output += ", ";
        }
        output += "]";
        return output;
    }

    public static void main(String[] args){
        ArrayList<Character> haha = new ArrayList<Character>();
        for(int i = 65; i <= 90; i++)
            haha.add((char)i);

        Collections.shuffle(haha);

        Cryptogram c1 = new Cryptogram(haha);
        System.out.println(c1);

        System.out.println(encode(PHRASES[0], haha)); // Static
        System.out.println(encode(PHRASES[1], haha));
        System.out.println(encode(PHRASES[2], haha));

        System.out.println(c1.encode(PHRASES[0])); // Non-static
        System.out.println(c1.encode(PHRASES[1]));
        System.out.println(c1.encode(PHRASES[2]));

        System.out.println(decode(c1.encode(PHRASES[0]), haha)); // Static
        System.out.println(decode(c1.encode(PHRASES[1]), haha));
        System.out.println(decode(c1.encode(PHRASES[2]), haha));

        System.out.println(c1.decode(c1.encode(PHRASES[0]))); // Non-static
        System.out.println(c1.decode(c1.encode(PHRASES[1])));
        System.out.println(c1.decode(c1.encode(PHRASES[2])));
    }
}