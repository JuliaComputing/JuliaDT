package org.julia.lang.parser;

import com.google.common.base.Predicate;
import com.google.common.io.Files;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParserTest {

    public static final Predicate<File> FILE = new Predicate<File>() {
        public boolean apply(final File file) {
            return file.isFile() && file.getName().endsWith(".jl");
        }
    };
    private final File file;

    public ParserTest(final File file) {
        this.file = file;
    }

    @Parameters
    public static Collection<File> data() {
        return Files.fileTreeTraverser().preOrderTraversal(new File(ParserTest.class.getResource("/supported").getFile())).filter(FILE).toList();
    }

    @Test
    public void testParse() throws IOException {
        final ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(file));
        final JuliaLexer lexer = new JuliaLexer(input);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final JuliaParser parser = new JuliaParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        final ParseTree tree = parser.unit();
        System.out.println(tree.toStringTree(parser));
    }
}

