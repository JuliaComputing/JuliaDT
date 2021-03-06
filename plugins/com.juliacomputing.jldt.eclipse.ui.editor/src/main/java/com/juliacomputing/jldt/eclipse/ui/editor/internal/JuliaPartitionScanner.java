package com.juliacomputing.jldt.eclipse.ui.editor.internal;

import com.juliacomputing.jldt.eclipse.core.JuliaPartition;

import org.eclipse.jface.text.rules.*;

import java.util.ArrayList;
import java.util.List;

public class JuliaPartitionScanner extends RuleBasedPartitionScanner {

  public JuliaPartitionScanner() {
    IToken string = new Token(JuliaPartition.STRING);
    IToken comment = new Token(JuliaPartition.COMMENT);

    List<IRule> rules = new ArrayList<>();
    rules.add(new EndOfLineRule("#", comment));
    rules.add(new MultiLineRule("\"\"\"", "\"\"\"", string, '\\'));
    rules.add(new MultiLineRule("\'\'\'", "\'\'\'", string, '\\'));
    rules.add(new MultiLineRule("\'", "\'", string, '\\'));
    rules.add(new MultiLineRule("\"", "\"", string, '\\'));

    IPredicateRule[] result = new IPredicateRule[rules.size()];
    rules.toArray(result);
    setPredicateRules(result);
  }
}