package at.ac.htlstp.demo.javasj224a;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Textsuche {

    private static final String FAUST = "data/faust.txt";
    public static final String OUTPUT= "data/output.txt";
    private static final String BIBEL = "data/bibel02.txt";

    public static void main(String[] args) {
        List<String> data;
        File file = new File(FAUST);
        try {
            data = Files.readAllLines(file.toPath());
            //sucheNachFaust(data);
            //sucheNachF_T(data);
            //ersetze(data);
            //int worte=wortecounter(data);
            //System.out.println("Der Text hat "+worte+" Worte!");
            List<String> enc = rot(data,13);

            Files.write((new File(OUTPUT)).toPath(),enc);
        } catch (IOException e) {
            System.out.println("Datei konnten nicht gelesen werden!");
        }
    }

    private static final int abs = 1+'z'-'a';

    public static char rot(char c, int n) {
        if (c>='a' && c<='z') {
            c = (char)(((c-'a')+n)%abs+'a');
        } else if (c>='A' && c<='Z') {
            c = (char)(((c-'A')+n)%abs+'A');
        }
        return c;
    }

    public static List<String> rot(List<String> data, int n) {
        List<String> ret = new ArrayList<>();
        for (String line:data) {
            char[] ca = line.toCharArray();
            for (int i=0;i<ca.length;i++)
                ca[i] = rot(ca[i],n);
            line = String.copyValueOf(ca);
            ret.add(line);
        }
        return ret;
    }

    public static int wortecounter(List<String> data) {
        int ct=0;
        for (String line:data) {
            String[] worte = line.split("[ ,\\.;!\\?:\"]+");
            for (String wort:worte)
                if (wort.length()>1)
                    ct++;
        }
        return ct;
    }

    public static void ersetze(List<String> data) {
        for (int i=0;i<data.size();i++) {
            String line = data.get(i);
            line = line.replaceAll("[Ff]aust","Fritz");
            data.set(i,line);
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
