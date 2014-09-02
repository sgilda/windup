package org.jboss.windup.rules.apps.java.scan.provider;

import java.io.File;
import java.nio.file.Paths;

import org.jboss.windup.config.GraphRewrite;
import org.jboss.windup.config.RulePhase;
import org.jboss.windup.config.WindupRuleProvider;
import org.jboss.windup.config.operation.ruleelement.AbstractIterationOperation;
import org.jboss.windup.config.query.Query;
import org.jboss.windup.config.query.QueryPropertyComparisonType;
import org.jboss.windup.graph.GraphContext;
import org.jboss.windup.graph.model.WindupConfigurationModel;
import org.jboss.windup.graph.model.resource.FileModel;
import org.jboss.windup.graph.service.GraphService;
import org.jboss.windup.rules.apps.java.model.JavaSourceFileModel;
import org.ocpsoft.rewrite.config.ConditionBuilder;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.context.EvaluationContext;

/**
 */
public class DiscoverJavaFilesRuleProvider extends WindupRuleProvider
{
    @Override
    public RulePhase getPhase()
    {
        return RulePhase.POST_DISCOVERY;
    }

    // @formatter:off
    @Override
    public Configuration getConfiguration(GraphContext context)
    {
        ConditionBuilder javaSourceQuery = Query
            .find(FileModel.class)
            .withProperty(FileModel.IS_DIRECTORY, false)
            .withProperty(FileModel.FILE_PATH, QueryPropertyComparisonType.REGEX, ".*\\.java$");

        return ConfigurationBuilder.begin()
            .addRule()
            .when(javaSourceQuery)
            .perform(new IndexJavaFileIterationOperator());
        // @formatter:on
    }

    private final class IndexJavaFileIterationOperator extends AbstractIterationOperation<FileModel>
    {
        private static final int JAVA_SUFFIX_LEN = 5;

        private IndexJavaFileIterationOperator()
        {
            super();
        }

        @Override
        public void perform(GraphRewrite event, EvaluationContext context, FileModel payload)
        {
            GraphContext graphContext = event.getGraphContext();
            WindupConfigurationModel configuration = new GraphService<>(graphContext, WindupConfigurationModel.class)
                        .getUnique();

            String inputDir = configuration.getInputPath().getFilePath();
            inputDir = Paths.get(inputDir).toAbsolutePath().toString();

            String filepath = payload.getFilePath();
            filepath = Paths.get(filepath).toAbsolutePath().toString();

            if (!filepath.startsWith(inputDir))
            {
                return;
            }

            String classFilePath = filepath.substring(inputDir.length() + 1);
            String qualifiedName = classFilePath.replace(File.separatorChar, '.').substring(0,
                        classFilePath.length() - JAVA_SUFFIX_LEN);

            String packageName = "";
            if (qualifiedName.contains("."))
                packageName = qualifiedName.substring(0, qualifiedName.lastIndexOf("."));

            if (packageName.startsWith("src.main.java."))
            {
                packageName = packageName.substring("src.main.java.".length());
            }

            // make sure we mark this as a Java file
            JavaSourceFileModel javaFileModel = GraphService.addTypeToModel(graphContext, payload,
                        JavaSourceFileModel.class);

            javaFileModel.setPackageName(packageName);
        }

    }
}