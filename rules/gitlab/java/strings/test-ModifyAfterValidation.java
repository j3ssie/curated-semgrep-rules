// License: LGPL-3.0 License (c) find-sec-bugs
package strings;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyAfterValidation {

    public String modifyDangerReplaceAll(String str) {
        String s = Normalizer.normalize(str, Normalizer.Form.NFKC);
        Pattern pattern = Pattern.compile("<script>");

        // ruleid: java_strings_rule-ModifyAfterValidation
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
        s = s.replaceAll("[\\p{Cn}]", ""); // modified after validation
        return s;
    }

    public String modifyDangerConcat(String str, String stringVal) {
        Boolean isTrue = false;
        String s = Normalizer.normalize(str, Normalizer.Form.NFKC);
        Pattern pattern = Pattern.compile("<script>");

        // ruleid: java_strings_rule-ModifyAfterValidation
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
        if(isTrue){
            someMethod(s.concat(stringVal));
        }
        return s;
    }

    public String modifyDangerReplace(String str) {
        String s = Normalizer.normalize(str, Normalizer.Form.NFKC);
        Pattern pattern = Pattern.compile("<script>");

        // ruleid: java_strings_rule-ModifyAfterValidation
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
        s = s.replace("[\\p{Cn}]", ""); // modified after validation
        return s;
    }

    public String modifyDangerReplaceFirst(String str) {
        String s = Normalizer.normalize(str, Normalizer.Form.NFKC);
        Pattern pattern = Pattern.compile("<script>");

        // ruleid: java_strings_rule-ModifyAfterValidation
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
        s = s.replaceFirst("[\\p{Cn}]", ""); // modified after validation
        return s;
    }

    public String modifySafeReplaceAll(String str) {
        String s = Normalizer.normalize(str, Normalizer.Form.NFKC);

        // ok: java_strings_rule-ModifyAfterValidation
        s = s.replaceAll("[\\p{Cn}]", "");

        Pattern pattern = Pattern.compile("<script>");
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
        return s;
    }

    public String modifySafeReplace(String str) {
        String s = Normalizer.normalize(str, Normalizer.Form.NFKC);

        // ok: java_strings_rule-ModifyAfterValidation
        s = s.replace("[\\p{Cn}]", "");

        Pattern pattern = Pattern.compile("<script>");
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
        return s;
    }

    public String modifySafeReplaceFirst(String str) {
        String s = Normalizer.normalize(str, Normalizer.Form.NFKC);

        // ok: java_strings_rule-ModifyAfterValidation
        s = s.replaceFirst("[\\p{Cn}]", "");

        Pattern pattern = Pattern.compile("<script>");
        Matcher matcher = pattern.matcher(s);

        if (matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
        return s;
    }

    public String modifySafeConcat(String str) {
        String s = Normalizer.normalize(str, Normalizer.Form.NFKC);

        // ok: java_strings_rule-ModifyAfterValidation
        s = s.concat("test");

        Pattern pattern = Pattern.compile("<script>");
        Matcher matcher = pattern.matcher(s);
        
        if (matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
        return s;
    }


    public String someMethod(String input){
        return input;
    }
}
