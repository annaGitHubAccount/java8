package de.inden.starter;

public class ApplicationInfo {
    private final String chapterAndSection; // 2, 3.1 oder 4.2.5

    private final String pages;            // 47 or 50-52

    private final String name;             // Figures3DExample

    private final String packageName;

    private final String comment;

    public ApplicationInfo(final String chapterAndSection, final String pages, final String name,
                           final String packageName, final String comment) {
        this.chapterAndSection = chapterAndSection;
        this.pages = pages;
        this.name = name;
        this.packageName = packageName;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public int getChapterAndSectionOrderingNumber() {
        final String[] values = chapterAndSection.split("\\.");
        final int[] ordering = {10000, 100, 1};

        int orderingNumber = 0;
        for (int i = 0; i < values.length; i++) {
            orderingNumber += Integer.valueOf(values[i]) * ordering[i];
        }
        return orderingNumber;
    }

    public String getChapterAndSection() {
        return chapterAndSection;
    }

    public Integer getChapter() {
        // Kapitelangabe
        final int posOfDot = chapterAndSection.indexOf(".");
        if (posOfDot == -1) {
            return Integer.valueOf(chapterAndSection);
        }

        return Integer.valueOf(chapterAndSection.substring(0, posOfDot));
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPages() {
        return pages;
    }

    public String getComment() {
        return comment;
    }

}
