package menu;

public class TitleGenerator {

    public static String generateTitle(String title, Character ch){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 10; i++){
            sb.append(ch);
        }
        
        sb.append(" " + title + " ");

        for(int i = 0; i < 10; i++){
            sb.append(ch);
        }

        return sb.toString();
    }
}
