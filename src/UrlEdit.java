
/**
 *
 * @author AMINUL
 */
class UrlEdit {

    public String urlEdit(String userText) {
        userText = userText.trim();
        String st = "";
        int String_length = userText.length();

        if (userText.charAt(0) == 'w' && userText.charAt(1) == 'w' && userText.charAt(2) == 'w' && userText.charAt(3) == '.') {
            st = "https://";
            st += userText;
        } else if (userText.charAt(String_length - 4) == '.' && userText.charAt(String_length - 3) == 'c'
                && userText.charAt(String_length - 2) == 'o' && userText.charAt(String_length - 1) == 'm') {
            st = "https://www.";
            st += userText;
        } else if (userText.charAt(String_length - 4) == '.' && userText.charAt(String_length - 3) == 'e'
                && userText.charAt(String_length - 2) == 'd' && userText.charAt(String_length - 1) == 'u') {
            st = "https://www.";
            st += userText;
        } else {
            st = "https://www.duckduckgo.com/search?q=";
            st += userText;
        }
        return st;
    }

}
