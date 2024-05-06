import java.io.*;
import java.nio.charset.*;
import java.net.*;
import java.util.*;

public class Leiturahtml {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
    static PrintStream out = new PrintStream(System.out, true, Charset.forName("UTF-8"));

    static int outCharset = 0;

    static char[] letterFilter = { '\u0061', '\u0062', '\u0063', '\u0064', '\u0065', '\u0066', '\u0067', '\u0068',
            '\u0069', '\u006A', '\u006B', '\u006C', '\u006D', '\u006E', '\u006F', '\u0070', '\u0071', '\u0072',
            '\u0073', '\u0074', '\u0075', '\u0076', '\u0077', '\u0078', '\u0079', '\u007A', '\u00E1', '\u00E9',
            '\u00ED', '\u00F3', '\u00FA', '\u00E0', '\u00E8', '\u00EC', '\u00F2', '\u00F9', '\u00E3', '\u00F5',
            '\u00E2', '\u00EA', '\u00EE', '\u00F4', '\u00FB'
    };

    static Map<Character, Integer> CharCounter(String Text, char[] charSet) {
        Map<Character, Integer> counter = new HashMap<>();
        outCharset = 0;

        for (int i = 0; i < charSet.length; i++) {
            counter.put(charSet[i], 0);
        }

        // out.println(counter);

        for (int i = 0; i < Text.length(); i++) {
            char g = Text.charAt(i);
            boolean contains = false;
            for (char c : letterFilter) {
                if (c == g) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                if (Character.isUpperCase(g)) {
                    g = Character.toLowerCase(g);
                }
                Integer number = counter.get(Text.charAt(i));
                if (number == null) {
                    outCharset++;
                } else {
                    number++;
                    counter.put(Text.charAt(i), number);
                }
            }
        }

        return counter;
    }

    static Map<String, Integer> StringCounter(String text, String[] stringSet) {
        Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < stringSet.length; i++) {
            countMap.put(stringSet[i], 0);
        }

        char start = '<';
        char stop = '>';
        boolean read = false;
        String testing = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == start) {
                read = true;
                testing = "";
            }
            if (read) {
                testing += c;
                Integer count = countMap.get(testing);
                if (count != null) {
                    count++;
                    countMap.put(testing, count);
                }
                if (c == stop) {
                    read = false;
                }
            }
        }

        return countMap;
    }

    public static void main(String[] args) {
        try {

            String name = in.readLine();
            while (!name.equals("FIM")) {

                String html = in.readLine();

                URL url = new URL(html);
                URLConnection con = url.openConnection();

                BufferedReader urlRead = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = urlRead.readLine()) != null) {
                    content.append(inputLine);
                }

                urlRead.close();
                String appendCont = content.toString();

                char[] charset = { '\u0061', '\u0065', '\u0069', '\u006F', '\u0075', '\u00E1', '\u00E9', '\u00ED',
                        '\u00F3', '\u00FA', '\u00E0', '\u00E8', '\u00EC', '\u00F2', '\u00F9', '\u00E3', '\u00F5',
                        '\u00E2', '\u00EA', '\u00EE', '\u00F4', '\u00FB' };

                Map<Character, Integer> charCountMap = CharCounter(appendCont, charset);

                String ret = "";
                for (int i = 0; i < charset.length; i++) {
                    ret += charset[i] + "(" + charCountMap.get(charset[i]) + ") ";
                }
                ret += "consoante(" + outCharset + ") ";

                String[] stringSet = { "<br>", "<table>" };

                Map<String, Integer> stringCountMap = StringCounter(appendCont, stringSet);

                for (int i = 0; i < stringSet.length; i++) {
                    ret += stringSet[i] + "(" + stringCountMap.get(stringSet[i]) + ") ";
                }
                ret += name;

                out.println(ret);

                name = in.readLine();
            }

        } catch (Exception e) {
            out.println(e);

        }
    }

}