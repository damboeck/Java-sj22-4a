package at.ac.htlstp.demo.javasj224a;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Textsuche {

    private static final String FAUST = "data/faust.txt";
    private static final String BIBEL = "data/bibel02.txt";

    public static void main(String[] args) {
        List<String> data;
        File file = new File(FAUST);
        try {
            data = Files.readAllLines(file.toPath());
            //sucheNachFaust(data);
            sucheNachF_T(data);
        } catch (IOException e) {
            System.out.println("Datei konnten nicht gelesen werden!");
        }
    }

    public static void sucheNachFaust(List<String> data) {
        Pattern p = Pattern.compile("(^|[^a-zA-ZöäüÖÄÜß])[Ff]aust($|[^a-zA-ZöäüÜÖÄß])");
        for (String line:data) {
            Matcher m = p.matcher(line);
            if (m.find()) {
                System.out.println(line);
            }
        }
    }

    public static void sucheNachF_T(List<String> data) {
        HashMap<String,Integer> worte = new HashMap<>();
        Pattern p = Pattern.compile("(^|[^a-zA-ZöäüÖÄÜß])(?<ft>[Ff]\\w*[tT])($|[^a-zA-ZöäüÜÖÄß])");
        Matcher m;
        for (String line:data) {
            while ((m = p.matcher(line)).find()) {
                System.out.println(m.group("ft"));
                line = line.substring(m.end());
            }
        }
    }
}
