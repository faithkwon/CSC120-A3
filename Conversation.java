import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

class Conversation implements Chatbot {

  // Attributes 
    String[] responses = {"That's awesome.", 
                          "Who said that?", 
                          "Mhm.", 
                          "You're being really weird right now.", 
                          "Cool!", 
                          "Oh my God, you can't say that!", 
                          "Okay!"};
    String user_response;
    String comp_response;
    int rounds;
    
    Scanner input = new Scanner(System.in);
    Random randomInt = new Random();
    ArrayList<String> chat = new ArrayList<String>();
  
  /**
   * Constructor 
   */
  Conversation() {

  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {
    System.out.println("How many rounds?");
    rounds = input.nextInt();

    input.nextLine(); // Clears the Scanner buffer

    System.out.println("Hello! What's up?");
    chat.add("Hello! What's up?");

    for (int i = 0; i < rounds; i++) {
      user_response = input.nextLine();
      chat.add(user_response);

      comp_response = respond(user_response);
      System.out.println(comp_response);
      chat.add(comp_response);
    }

    System.out.println("Catch you on the flip side!");
    chat.add("Catch you on the flip side!");

    System.out.println("\nTRANSCRIPT:");
  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    for (int i = 0; i < chat.size(); i++) {
      System.out.println(chat.get(i));
    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String inputString) {
    /*
      * I -> you
      * me -> you
      * am -> are
      * you -> I
      * my -> your
      * your -> my
     */

    String returnString = "";

    String[] sentence = inputString.split("[!\\?\\.\\s]");

    for (int i = 0; i < sentence.length; i++) {
      if (i == 0 && sentence[i].equals("I")) {
        // Making sure that the "I" is the first word in the sentence to capitalize the "You" 
        returnString += "You ";
      } else if (sentence[i].equals("I")) {
        returnString += "you ";
      } else if (sentence[i].equals("me")) {
        returnString += "you ";
      } else if (sentence[i].equals("am")) {
        returnString += "are ";
      } else if (sentence[i].equals("you") || sentence[i].equals("You")) {
        returnString += "I ";
      } else if (sentence[i].equals("my")) {
        returnString += "your ";
      } else if (sentence[i].equals("My")) { 
        // Only adding alternatives if "You" or "My" are capitalized because those are most commonly at the beginning of sentences
        returnString += "Your ";
      } else if (sentence[i].equals("your")) {
        returnString += "my ";
      } else {
        returnString += sentence[i] + " ";
      }
    }

    if (returnString.equals(String.join(" ", sentence) + " ")) {
      returnString = responses[randomInt.nextInt(responses.length)];
    } else {
      returnString += "?";
    }

    return returnString;
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
