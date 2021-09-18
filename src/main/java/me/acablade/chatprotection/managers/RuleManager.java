package me.acablade.chatprotection.managers;

import lombok.Getter;
import me.acablade.chatprotection.objects.Rule;

import java.util.ArrayList;
import java.util.List;

public class RuleManager {

    @Getter
    private static final List<Rule> ruleList = new ArrayList<>();

}
