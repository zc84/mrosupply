package com.jbehave;

import com.utils.Excel;
import com.utils.FileHelper;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoryProvider {

    Excel report;
    String STORIES_PATH = "src/test/resources/stories";

    public void generate_report() throws IOException, WriteException {

        try {
            report = new Excel("docs/Test spec breakdown.xls");
            report.create_book();
            report.create_sheet("");

            List<File> storiesList = (List<File>) FileUtils.listFiles(new File(STORIES_PATH), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

            List<String> storyData = new ArrayList<>();
            for (File file : storiesList)
                if (file.getName().endsWith(".story")) {
                    storyData.addAll(getStoryData(file));
                }
            writeStoryData(storyData);
            report.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeStoryData(List<String> storyData) throws WriteException {

        report.add_header(new String[]{"Capability", "Feature", "Sub Feature", "Story", "Scenario", "Type", "Status", "Issue ID"}, "");

        String capability;
        String feature;
        String subFeature;
        String story;

        for (int i = 0; i < storyData.size(); i++) {

            int row = i + 1;
            String[] storyInfo = storyData.get(i).split(";");

            capability = storyInfo[0];
            feature = storyInfo[1];
            subFeature = storyInfo[2];
            story = storyInfo[3];

            report.writeToCell("", capability, 0, row);
            report.writeToCell("", feature, 1, row);
            report.writeToCell("", subFeature, 2, row);
            report.writeToCell("", story, 3, row);
            report.writeToCell("", storyInfo[4], 4, row);
            report.writeToCell("", storyInfo[5], 5, row);
            report.writeToCell("", storyInfo[6], 7, row);
        }

    }

    private List<String> getStoryData(File file) throws IOException {

        List<String> rows = new FileHelper(file).readFile();
        List<String> scenarios;
        scenarios = new ArrayList<>();
        String globalIssueId = "";

        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).startsWith("Scenario:"))
                break;
            else if (rows.get(i).startsWith("@issue"))
                globalIssueId = rows.get(i).replace("@issues", "").replace("@issue", "").trim();
        }
        for (int i = 0; i < rows.size(); i++) {

            String issueId = "";
            String capability = "";
            String feature = "";
            String subfeature = "";
            String story = "";
            String scenarioSteps = "";
            String scenarioType = "AUTO";

            String row = rows.get(i);
            if (row.startsWith("Scenario:")) {
                String subRow;
                for (int j = (i + 1); j < (i + 5); j++) {
                    subRow = rows.get(j);
                    if (subRow.contains("@type manual"))
                        scenarioType = "MANUAL";
                    if (subRow.startsWith("@issue"))
                        issueId = subRow.replace("@issues", "").replace("@issue", "").trim();
                }

                scenarioSteps = rows.get(i).replaceAll("Scenario:|;", "").trim() + "\n\n";

                String path = file.getCanonicalPath().split("stories\\\\")[1];
                String[] parentFolders = path.replace(file.getName(), "").split("\\\\");
                story = file.getName().replace(".story", "");

                capability = parentFolders[0];
                try {
                    feature = parentFolders[1];
                } catch (Exception e) {

                }
                try {
                    subfeature = parentFolders[2];
                } catch (Exception e) {

                }

                String scenarioRow = "";
                for (int j = (i + 1); j < rows.size(); j++) {
                    scenarioRow = rows.get(j);
                    if (scenarioRow.startsWith("Given") || scenarioRow.startsWith("When") || scenarioRow.startsWith("Then") || scenarioRow.startsWith("And"))
                        scenarioSteps += scenarioRow.replace(";", "") + "\n";
                    else if (scenarioRow.startsWith("Scenario"))
                        break;
                }

                scenarios.add(capability + ";" + feature + ";" + subfeature + ";" + story + ";" + scenarioSteps + ";" + scenarioType + ";" + globalIssueId + " " +
                        issueId);
            }
        }
        return scenarios;
    }
}
