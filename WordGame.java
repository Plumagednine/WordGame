import java.util.ArrayList; 
import java.util.Scanner;

public class WordGame{
  
  public static boolean inCS(char letter) {
  return letter == 'c' || letter == 'o'
    || letter == 'm' || letter == 'p'
    || letter == 'u' || letter == 't'
    || letter == 'e' || letter == 'r'
    || letter == 's' || letter == 'i'
    || letter == 'n';
  }
  
  public static ArrayList<String> strToArray(String str) {
    String tempWords = "";
    ArrayList<String> words = new ArrayList<String>();
    for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == ' ') {
          tempWords = tempWords.replaceAll("\\s","");
          words.add(tempWords);
          tempWords = "";
        }
        else{
          tempWords += str.charAt(i);
          tempWords = tempWords.replaceAll("\\s","");
          
        }
    }
    tempWords = tempWords.replaceAll("\\s","");
    words.add(tempWords);
    tempWords = "";
    for(int c = 0; c < words.size(); c++) {
      for(int i = 0; i < words.get(c).length(); i++) {
        if (inCS(words.get(c).charAt(i)) == false){ 
          words.remove(c);
        }
        else {
          words.set(c, words.get(c).replaceAll("\\s",""));
        }
        
      }
    }
    words = dupecheck(words);
    
    return words;
  }
  
  public static ArrayList<String> dupecheck(ArrayList<String> words){
    ArrayList<String> wordschecked = new ArrayList<String>();
    for(int c = 0; c < words.size(); c++) {
      String wordChecking = (String) words.get(c);
      wordChecking = wordChecking.replaceAll("\\s+","");
      if (wordschecked.contains(wordChecking) == false) {
        wordschecked.add(wordChecking.replaceAll("\\s+",""));
      }
    }
    while (!(wordschecked.indexOf(" ") == -1)){
      wordschecked.remove(wordschecked.indexOf(" "));
    }
    
    
    return wordschecked;
  }

  public static Object[] similarWords(ArrayList<String> p1, ArrayList<String> p2){
    ArrayList<String> p1de = new ArrayList<String>();
    ArrayList<String> p2de = new ArrayList<String>();
    ArrayList<String> samewords = new ArrayList<String>();
    for(int c = 0; c < p1.size(); c++) {
      if (p2.contains(p1.get(c)) == false) {
        p1de.add(((String) p1.get(c)).replaceAll("\\s+",""));
      }
      else {
        if(samewords.contains(p1.get(c)) == false) {
          samewords.add(((String) p1.get(c)).replaceAll("\\s+",""));
        }
      }
    }
    
    for(int c = 0; c < p2.size(); c++) {
      if (p1.contains(p2.get(c)) == false) {
        p2de.add(((String) p2.get(c)).replaceAll("\\s+",""));
      }
      else {
        if(samewords.contains(p2.get(c)) == false) {
          samewords.add(((String) p2.get(c)).replaceAll("\\s+",""));
        }
      }
    }
  
    return new Object[]{p1de,p2de,samewords};
    
  }
  
  public static Object[] pointCount(Object lists, Object lists2) {
    int p1p = ((ArrayList<String>) lists).size();
    int p2p = ((ArrayList<String>) lists2).size();
    String winner = "";
    if (p1p > p2p) {
      winner = "The winner is Player 1!";
    }
    else if (p1p < p2p) {
      winner = "The winner is Player 2!";
    }
    else {
      winner = "Its a tie!";
    }
    return new Object[] {p1p,p2p,winner};
    
    
  }
  
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.println("Player 1 enter a all your words and hit enter.");
    String p1Input = input.nextLine();
    ArrayList<String> p1words = strToArray(p1Input.toLowerCase());
    System.out.println("Player 2 enter a all your words and hit enter.");
    String p2Input = input.nextLine();
    ArrayList<String> p2words = strToArray(p2Input.toLowerCase());
    Object[] lists = similarWords(p1words,p2words);
    System.out.println("Player 1 words: " + lists[0]);
    System.out.println("Player 2 words: " + lists[1]);
    System.out.println("Similar words: " + lists[2]);
    Object[] points = pointCount(lists[0], lists[1]);
    System.out.println("Player 1 points: " + points[0]);
    System.out.println("Player 2 points: " + points[1]);
    System.out.println(points[2]);
    
    
    input.close();
  }
}
