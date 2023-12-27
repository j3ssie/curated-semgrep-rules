// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/script/ScriptEngineSample.java
// hash: a7694d0
package script;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;

public class ScriptInjection {
    public static void scripting(String userInput) throws ScriptException {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByExtension("js");

        Object result = scriptEngine.eval("test=1;" + userInput);

    }

    //The potential injection will require manual review of the code flow but some false positive can be avoid.
    public static void scriptingSafe() throws ScriptException {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByExtension("js");

        String code = "var test=3;test=test*2;";
        Object result = scriptEngine.eval(code);
    }
}
