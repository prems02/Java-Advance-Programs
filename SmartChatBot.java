import java.util.*;
import java.io.*;

public class SmartChatBot {
    private static final String HISTORY_FILE = "chat_history.txt";
    private static Map<String, List<String>> responses = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        loadResponses();
        System.out.println("ChatBot: Hello! I'm MedoraBot — your AI friend. Type 'exit' to end.");
        System.out.println("-------------------------------------------------------");

        while (true) {
            System.out.print("You: ");
            String userInput = sc.nextLine().toLowerCase();

            if (userInput.equals("exit")) {
                System.out.println("ChatBot: Bye! Your conversation is saved.");
                break;
            }

            String reply = getResponse(userInput);
            System.out.println("ChatBot: " + reply);
            saveHistory("You: " + userInput + "\nBot: " + reply + "\n");
        }
    }

    private static void loadResponses() {
        responses.put("hi", Arrays.asList("Hello!", "Hey there!", "Hi, nice to see you!"));
        responses.put("how are you", Arrays.asList("I'm doing great! What about you?", "Feeling awesome today!", "Always ready to chat!"));
        responses.put("your name", Arrays.asList("I'm MedoraBot!", "You can call me Medora.", "My name is MedoraBot 🤖"));
        responses.put("what is java", Arrays.asList(
                "Java is a high-level, object-oriented programming language used everywhere!",
                "Java runs on billions of devices — from phones to servers.",
                "Java is like coffee ☕ for programmers!"
        ));
        responses.put("bye", Arrays.asList("Goodbye!", "See you soon!", "Take care!"));
        responses.put("default", Arrays.asList(
                "Hmm... I didn’t quite get that.",
                "Interesting! Tell me more.",
                "Could you rephrase that?"
        ));
    }

    private static String getResponse(String input) {
        for (String key : responses.keySet()) {
            if (input.contains(key)) {
                List<String> possibleReplies = responses.get(key);
                return possibleReplies.get(new Random().nextInt(possibleReplies.size()));
            }
        }
        List<String> defaultReplies = responses.get("default");
        return defaultReplies.get(new Random().nextInt(defaultReplies.size()));
    }

    private static void saveHistory(String text) throws IOException {
        FileWriter fw = new FileWriter(HISTORY_FILE, true);
        fw.write(text);
        fw.close();
    }
}
