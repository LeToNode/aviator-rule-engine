package com.github.instaer.ruleengine.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Condition relation operator type
 */
@Getter
@AllArgsConstructor
public enum ConditionRelationType {

    EQUAL("EQUAL", "%s == %s"),

    NOT_EQUAL("NOT EQUAL", "%s != %s"),

    LESS_EQUAL("LESS EQUAL", "%s <= %s"),

    GREATER_EQUAL("GREATER EQUAL", "%s >= %s"),

    LESS("LESS", "%s < %s"),

    GREATER("GREATER", "%s > %s"),

    /**
     * a string type element is included in a list
     */
    INCLUDE_IN_LIST("INCLUDE IN LIST", "include(seq.set(%s), str(%s))"),

    /**
     * a string type element is not included in a list
     */
    NOT_INCLUDE_IN_LIST("NOT INCLUDE IN LIST", "!include(seq.set(%s), str(%s))"),

    /**
     * some characters in a string are contained in a list
     */
    SOME_CONTAINS_IN_LIST("SOME CONTAINS IN LIST", "nil != seq.some(seq.set(%s), lambda(x) -> string.indexOf(str(%s), x) > -1 end)"),

    /**
     * none characters of a string are contained in a list
     */
    NONE_CONTAINS_IN_LIST("NONE CONTAINS IN LIST", "seq.not_any(seq.set(%s), lambda(x) -> string.indexOf(str(%s), x) > -1 end)"),

    /**
     * match regular expression
     */
    REGEX("REGEX", "str(%s) =~ %s");

    private final String desc;
    private final String format;

    public static ConditionRelationType getConditionRelationType(String name) {
        return Arrays.stream(ConditionRelationType.values())
                .filter(t -> t.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static Map<String, String> conditionRelationTypeMap = Arrays.stream(ConditionRelationType.values())
            .collect(Collectors.toMap(ConditionRelationType::name, ConditionRelationType::getDesc));
}
