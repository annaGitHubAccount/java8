package de.inden.starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationInfoImporter {
    // List alle Application-Infos aus einer Datei ein und stellt diese
    // als List<ApplicationInfo> bereit. Dabei wird die Struktur NICHT
    // abgebildet, sondern muss in der Starter-Applikation erzeugt werden.
    public static List<ApplicationInfo> importAppicationInfos(final InputStream is) throws IOException {
        final List<ApplicationInfo> availableApps = new ArrayList<>();

        // Vertr�gt keine Umlaute, wenn Datei nicht als UTF-8 gespeichetr ist
        // (Achtung Windows normal CP1252!!!)
        final BufferedReader br = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
        final List<String> allLines = br.lines().collect(Collectors.toList());

        for (final String currentLine : allLines) {
            final String line = currentLine.trim();
            if (line.isEmpty() || isComment(line))
                continue;

            final String[] tokens = line.split("\t");
            if (tokens.length != 4 && tokens.length != 5) {
                System.err.println("Expected 5 tokesn, but got: " + tokens.length + "! Skipping invalid line: " + line);
                continue;
            }

            String comment = "";
            if (tokens.length == 5) {
                comment = tokens[4].trim();
            }

            final ApplicationInfo appInfo = new ApplicationInfo(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(),
                    tokens[3].trim(), comment);

            availableApps.add(appInfo);

        }

        // Sortierung gem�ss Kapiteltext
        availableApps.sort(Comparator.comparing(ApplicationInfo::getChapterAndSectionOrderingNumber)
                //.thenComparing(ApplicationInfo::getChapterAndSectionOrderingNumber)
                .thenComparing(ApplicationInfo::getPages));
//        availableApps.sort(Comparator.comparing(ApplicationInfo::getPages).thenComparing(ApplicationInfo::getChapterAndSection));

        return availableApps;
    }

    private static boolean isComment(final String currentLine) {
        return currentLine.startsWith("//") || currentLine.startsWith("%");
    }
}
