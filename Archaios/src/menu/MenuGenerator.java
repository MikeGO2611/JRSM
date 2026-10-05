package menu;

import java.util.List;

import commons.input.UserInput;
import commons.operations.IOperation;

public class MenuGenerator {

    public static void askUserMenuOption(String title, Character ch, List<IOperation> entryCollection, String exitOption, String errorMessage){
        printMenu(title, ch, entryCollection, exitOption);

        boolean userExit = false;
        while (!userExit) {
            int userInput = UserInput.getUserIntegerInRange(0, entryCollection.size(), errorMessage);
            
            if(userInput != 0) entryCollection.get(userInput - 1).operation();
            else userExit = true;
        }
    }

    private static void printMenu(String title, Character ch, List<IOperation> entryCollection, String exitOption) {
        System.out.println(MenuGenerator.generateMenu(title, ch, entryCollection));
        System.out.println(exitOption);
    }

    private static String generateMenu(String title, Character ch, List<IOperation> entryCollection){
        StringBuilder sb = new StringBuilder();

        sb.append(generateTitle(title, ch) + '\n');

        for(int i = 0; i < entryCollection.size(); i++){
            sb.append((i + 1) + ". " + entryCollection.get(i).getName() + '\n');
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    private static String generateTitle(String title, Character ch){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 20; i++){
            sb.append(ch);
        }
        
        sb.append(" " + title + " ");

        for(int i = 0; i < 20; i++){
            sb.append(ch);
        }

        return sb.toString();
    }
}
