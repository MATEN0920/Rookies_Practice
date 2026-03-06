package mylab.book.control;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class StatisticsAnalyzer {

    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Integer> sum = new HashMap<>();
        Map<String, Integer> cnt = new HashMap<>();

        for (Publication p : publications) {
            String type = getPublicationType(p);
            sum.put(type, sum.getOrDefault(type, 0) + p.getPrice());
            cnt.put(type, cnt.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> avg = new HashMap<>();
        for (String type : sum.keySet()) {
            avg.put(type, sum.get(type) / (double) cnt.get(type));
        }
        return avg;
    }

    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> cnt = new HashMap<>();
        for (Publication p : publications) {
            String type = getPublicationType(p);
            cnt.put(type, cnt.getOrDefault(type, 0) + 1);
        }

        int total = publications.length;
        Map<String, Double> dist = new HashMap<>();
        for (String type : cnt.keySet()) {
            dist.put(type, (cnt.get(type) * 100.0) / total);
        }
        return dist;
    }

    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int total = publications.length;
        int matched = 0;

        for (Publication p : publications) {
            String date = p.getPublishDate();
            if (date != null && date.length() >= 4) {
                String y = date.substring(0, 4);
                if (year.equals(y)) matched++;
            }
        }
        return (matched * 100.0) / total;
    }

    private String getPublicationType(Publication pub) {
        if (pub instanceof Novel) return "소설";
        if (pub instanceof Magazine) return "잡지";
        if (pub instanceof ReferenceBook) return "참고서";
        return "기타";
    }

    public void printStatistics(Publication[] publications) {
        DecimalFormat df = new DecimalFormat("#,###.##");

        Map<String, Double> avg = calculateAveragePriceByType(publications);
        Map<String, Double> dist = calculatePublicationDistribution(publications);
        double ratio2007 = calculatePublicationRatioByYear(publications, "2007");

        System.out.println("\n===== 출판물 통계 분석 =====");
        System.out.println("1. 타입별 평균 가격:");

        if (avg.containsKey("소설")) System.out.println(" - 소설: " + df.format(avg.get("소설")) + "원");
        if (avg.containsKey("참고서")) System.out.println(" - 참고서: " + df.format(avg.get("참고서")) + "원");
        if (avg.containsKey("잡지")) System.out.println(" - 잡지: " + df.format(avg.get("잡지")) + "원");

        System.out.println("\n2. 출판물 유형 별 분포:");
        if (dist.containsKey("소설")) System.out.println(" - 소설: " + String.format("%.2f", dist.get("소설")) + "%");
        if (dist.containsKey("참고서")) System.out.println(" - 참고서: " + String.format("%.2f", dist.get("참고서")) + "%");
        if (dist.containsKey("잡지")) System.out.println(" - 잡지: " + String.format("%.2f", dist.get("잡지")) + "%");

        System.out.println("\n3. 2007년에 출판된 출판물 비율: " + String.format("%.2f", ratio2007) + "%");
    }
}