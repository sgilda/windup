package org.jboss.windup.reporting.model;

import org.jboss.windup.graph.model.ProjectModel;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.modules.typedgraph.TypeValue;

/**
 * These reports are directly associated with an application, and that application's project model. These can include
 * things like an Application Overview report (with various hints, etc) as well as more specific reports (hibernate
 * reports, ejb reports, classloading reports, etc).
 * 
 * @author jsightler <jesse.sightler@gmail.com>
 */
@TypeValue(ApplicationReportModel.TYPE)
public interface ApplicationReportModel extends ReportModel
{
    public static final String TYPE = "ApplicationReport";
    public static final String REPORT_TO_APPLICATION_NOTE = "reportToApplicationNote";
    public static final String REPORT_TO_PROJECT_MODEL = "reportToProjectModel";
    public static final String REPORT_PRIORITY = "reportPriority";

    /**
     * Provides a link to the Navigation Index that is used for this particular report
     */
    @Adjacency(label = ApplicationReportIndexModel.APPLICATION_REPORT_INDEX_TO_REPORT_MODEL, direction = Direction.IN)
    public void setApplicationReportIndexModel(ApplicationReportIndexModel navIndex);

    /**
     * Provides a link to the Navigation Index that is used for this particular report
     */
    @Adjacency(label = ApplicationReportIndexModel.APPLICATION_REPORT_INDEX_TO_REPORT_MODEL, direction = Direction.IN)
    public ApplicationReportIndexModel getApplicationReportIndexModel();

    /**
     * This can be used to determine a reports location in a navigation bar. The primary purpose is sorting.
     */
    @Property(REPORT_PRIORITY)
    public int getReportPriority();

    /**
     * This can be used to determine a reports location in a navigation bar. The primary purpose is sorting.
     */
    @Property(REPORT_PRIORITY)
    public void setReportPriority(int priority);

    @Property("displayInApplicationReportIndex")
    public Boolean getDisplayInApplicationReportIndex();

    @Property("displayInApplicationReportIndex")
    public void setDisplayInApplicationReportIndex(Boolean displayInIndex);

    /**
     * Application notes allow custom text to be added
     */
    @Adjacency(label = REPORT_TO_APPLICATION_NOTE, direction = Direction.OUT)
    public Iterable<String> getApplicationNotes();

    /**
     * Application notes allow custom text to be added
     */
    @Adjacency(label = REPORT_TO_APPLICATION_NOTE, direction = Direction.OUT)
    public void addApplicationNote(String applicationNote);

    /**
     * The ProjectModel associated with this Application Report.
     */
    @Adjacency(label = REPORT_TO_PROJECT_MODEL, direction = Direction.OUT)
    public ProjectModel getProjectModel();

    /**
     * The ProjectModel associated with this Application Report.
     */
    @Adjacency(label = REPORT_TO_PROJECT_MODEL, direction = Direction.OUT)
    public void setProjectModel(ProjectModel projectModel);

}