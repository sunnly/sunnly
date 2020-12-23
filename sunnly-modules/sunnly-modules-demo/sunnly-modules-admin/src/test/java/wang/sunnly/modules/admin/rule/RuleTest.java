package wang.sunnly.modules.admin.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import wang.sunnly.modules.admin.domain.rule.Condition;
import wang.sunnly.modules.admin.domain.rule.Option;
import wang.sunnly.modules.admin.domain.rule.Rules;
import wang.sunnly.modules.admin.domain.rule.Type;

import java.util.Map;

/**
 * RuleTest
 *
 * @author Sunnly
 * @since 2020/12/23
 */
public class RuleTest {

    @Test
    public void string2Rules(){
//        String rules = "{\"value\":[{\"filed\": \"column_1\",\"conditions\": \"EQ\",\"value\": \"aaa\",\"myType\": \"STRING_myType\"},{\"filed\": \"column_1\",\"conditions\": \"LIKE\",\"value\": \"aaa\",\"myType\": \"STRING_myType\",\"op\": \"AND\"},{\"filed\": \"column_1\",\"conditions\": \"LESS\",\"value\": \"aaa\",\"myType\": \"DATE_myType\",\"op\": \"OR\"},{\"value\": [{\"filed\": \"column_1\",\"conditions\": \"EQ\",\"value\": \"aaa\",\"myType\": \"STRING_myType\"},{\"filed\": \"column_1\",\"conditions\": \"LIKE\",\"value\": \"aaa\",\"myType\": \"STRING_myType\",\"op\": \"AND\"}],\"myType\": \"GROUP_myType\",\"op\": \"AND\"}],\"myType\":\"GROUP_myType\",\"op\": \"AND\"}";
        String rules = "{\n" +
                "\t\"value\": [{\n" +
                "\t\t\"filed\": \"column_1\",\n" +
                "\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\"value\": \"aaa\",\n" +
                "\t\t\"myType\": \"STRING_myType\"\n" +
                "\t}, {\n" +
                "\t\t\"filed\": \"column_1\",\n" +
                "\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\"value\": \"aaa\",\n" +
                "\t\t\"myType\": \"STRING_myType\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}, {\n" +
                "\t\t\"filed\": \"column_1\",\n" +
                "\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\"value\": \"aaa\",\n" +
                "\t\t\"myType\": \"DATE_myType\",\n" +
                "\t\t\"op\": \"OR\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": [{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"myType\": \"STRING_myType\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"myType\": \"STRING_myType\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"myType\": \"GROUP_myType\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}],\n" +
                "\t\"myType\": \"GROUP_myType\",\n" +
                "\t\"op\": \"AND\"\n" +
                "}";
//        String rules = "[{\"name\":\"JSON\",\"address\":\"北京市西城区\",\"age\":25}]";
//        Object parse = JSON.parse(rules);
        Rules rules1 = Rules.getRules(rules);
        System.out.println(rules1);
//        System.out.println(parse);
    }

    @Test
    public void getSmipleString(){
        Rules rules = new Rules();
        rules.setOp(Option.AND);
        rules.setFiled("column_1");
//        rules.setType(Type.STRING_TYPE);
        rules.setConditions(Condition.IN);
        rules.setValue("aaa,bb,ccc");
        System.out.println(rules.getSmipleString(rules));
    }

    @Test
    public void  test(){
        String rules = "{\n" +
                "\t\t\"value\":[{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": \"ddd\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_3\",\n" +
                "\t\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\t\"value\": \"eee\",\n" +
                "\t\t\t\"type\": \"DATE_TYPE\",\n" +
                "\t\t\t\"op\": \"OR\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"value\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"filed\": \"column_4\",\n" +
                "\t\t\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\t\t\"value\": \"bbb\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t\t\t},{\n" +
                "\t\t\t\t\t\"filed\": \"column_5\",\n" +
                "\t\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\t\"value\": \"ccc\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"type\":\"GROUP_TYPE\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}";
        Rules rules1 = Rules.getRules(rules);
        System.out.println(rules1.toConditions());
    }

    @Test
    public void  test2(){
        String rules = "{\n" +
                "\t\t\"value\":[{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": [{\n" +
                "\t\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"ddd\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_3\",\n" +
                "\t\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\t\"value\": \"eee\",\n" +
                "\t\t\t\"type\": \"DATE_TYPE\",\n" +
                "\t\t\t\"op\": \"OR\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"value\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"filed\": \"column_4\",\n" +
                "\t\t\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\t\t\"value\": \"bbb\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t\t\t},{\n" +
                "\t\t\t\t\t\"filed\": \"column_5\",\n" +
                "\t\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\t\"value\": \"ccc\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"type\":\"GROUP_TYPE\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}";
        Rules rules1 = Rules.getRules(rules);
        rules1.setOp(null);
        System.out.println(rules1.toConditions());
    }
    @Test
    public void  test3(){
        String rules = "{\n" +
                "\t\t\"value\":[{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": [{\n" +
                "\t\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"ddd\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"filed\": \"column_222\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"222\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"OR\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_3\",\n" +
                "\t\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\t\"value\": \"eee\",\n" +
                "\t\t\t\"type\": \"DATE_TYPE\",\n" +
                "\t\t\t\"op\": \"OR\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"value\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"filed\": \"column_4\",\n" +
                "\t\t\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\t\t\"value\": \"bbb\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t\t\t},{\n" +
                "\t\t\t\t\t\"filed\": \"column_5\",\n" +
                "\t\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\t\"value\": \"ccc\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"type\":\"GROUP_TYPE\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}";
        Rules rules1 = Rules.getRules(rules);
        rules1.setOp(null);
        System.out.println(rules1.toConditions());
    } @Test
    public void  test4(){
        String rules = "{\"keyset\":{\n" +
                "\t\t\"value\":[{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": [{\n" +
                "\t\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"ddd\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"filed\": \"column_222\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"222\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"OR\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_3\",\n" +
                "\t\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\t\"value\": \"eee\",\n" +
                "\t\t\t\"type\": \"DATE_TYPE\",\n" +
                "\t\t\t\"op\": \"OR\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"value\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"filed\": \"column_4\",\n" +
                "\t\t\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\t\t\"value\": \"bbb\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t\t\t},{\n" +
                "\t\t\t\t\t\"filed\": \"column_5\",\n" +
                "\t\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\t\"value\": \"ccc\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"type\":\"GROUP_TYPE\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}}";
        Map<String, Rules> rulesMap = Rules.getRulesMap(rules);
        for (String next : rulesMap.keySet()){
            Rules rules1 = rulesMap.get(next);
            System.out.println(rules1.toConditions());
        }
    }
}
