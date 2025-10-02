package ds_algorithm.extras;

import java.util.*;

public class GoldLast60YearsGrowthVsConsildation {

    public static class DataPoint {
        int year;
        double price;

        public DataPoint(int year, double price) {
            this.year = year;
            this.price = price;
        }
    }

    public static class Phase {
        String type;
        int startYear;
        int endYear;
        int years;
        double startPrice;
        double endPrice;
        double multiple;
        double cagr; // Annual growth rate

        public Phase(String type, int startYear, int endYear, int years, double startPrice, double endPrice) {
            this.type = type;
            this.startYear = startYear;
            this.endYear = endYear;
            this.years = years;
            this.startPrice = startPrice;
            this.endPrice = endPrice;
            this.multiple = endPrice / startPrice;
            this.cagr = years > 0 ? Math.pow(endPrice / startPrice, 1.0 / years) - 1 : 0;
        }
    }

    public static void main(String[] args) {
        List<DataPoint> data = Arrays.asList(
            new DataPoint(1964, 63.25), new DataPoint(1965, 71.75), new DataPoint(1966, 83.75),
            new DataPoint(1967, 102.50), new DataPoint(1968, 162.00), new DataPoint(1969, 176.00),
            new DataPoint(1970, 184.00), new DataPoint(1971, 193.00), new DataPoint(1972, 202.00),
            new DataPoint(1973, 278.50), new DataPoint(1974, 506.00), new DataPoint(1975, 540.00),
            new DataPoint(1976, 432.00), new DataPoint(1977, 486.00), new DataPoint(1978, 685.00),
            new DataPoint(1979, 937.00), new DataPoint(1980, 1330.00), new DataPoint(1981, 1670.00),
            new DataPoint(1982, 1645.00), new DataPoint(1983, 1800.00), new DataPoint(1984, 1970.00),
            new DataPoint(1985, 2130.00), new DataPoint(1986, 2140.00), new DataPoint(1987, 2570.00),
            new DataPoint(1988, 3130.00), new DataPoint(1989, 3140.00), new DataPoint(1990, 3200.00),
            new DataPoint(1991, 3466.00), new DataPoint(1992, 4334.00), new DataPoint(1993, 4140.00),
            new DataPoint(1994, 4598.00), new DataPoint(1995, 4680.00), new DataPoint(1996, 5160.00),
            new DataPoint(1997, 4725.00), new DataPoint(1998, 4045.00), new DataPoint(1999, 4234.00),
            new DataPoint(2000, 4400.00), new DataPoint(2001, 4300.00), new DataPoint(2002, 4990.00),
            new DataPoint(2003, 5600.00), new DataPoint(2004, 5850.00), new DataPoint(2005, 7000.00),
            new DataPoint(2007, 10800.00), new DataPoint(2008, 12500.00), new DataPoint(2009, 14500.00),
            new DataPoint(2010, 18500.00), new DataPoint(2011, 26400.00), new DataPoint(2012, 31050.00),
            new DataPoint(2013, 29600.00), new DataPoint(2014, 28006.50), new DataPoint(2015, 26343.50),
            new DataPoint(2016, 28623.50), new DataPoint(2017, 29667.50), new DataPoint(2018, 31438.00),
            new DataPoint(2019, 35220.00), new DataPoint(2020, 48651.00), new DataPoint(2021, 48720.00),
            new DataPoint(2022, 52670.00), new DataPoint(2023, 65330.00), new DataPoint(2024, 77913.00),
            new DataPoint(2025, 110290.00)
        );

        double thresholdPercent = 6.0; // Adjustable threshold for Growth vs Consolidation

        List<Phase> phases = new ArrayList<>();
        String currentPhaseType = null;
        int phaseStartYear = data.get(0).year;
        double phaseStartPrice = data.get(0).price;

        for (int i = 1; i < data.size(); i++) {
            double pctChange = ((data.get(i).price - data.get(i - 1).price) / data.get(i - 1).price) * 100.0;
            String yearType = pctChange >= thresholdPercent ? "Growth" : "Consolidation";

            if (currentPhaseType == null) currentPhaseType = yearType;

            if (!yearType.equals(currentPhaseType)) {
                phases.add(new Phase(currentPhaseType, phaseStartYear, data.get(i - 1).year,
                        data.get(i - 1).year - phaseStartYear,
                        phaseStartPrice, data.get(i - 1).price));
                phaseStartYear = data.get(i - 1).year;
                phaseStartPrice = data.get(i - 1).price;
                currentPhaseType = yearType;
            }
        }

        // Add last phase
        phases.add(new Phase(currentPhaseType, phaseStartYear, data.get(data.size() - 1).year,
                data.get(data.size() - 1).year - phaseStartYear,
                phaseStartPrice, data.get(data.size() - 1).price));

        // Table header
        System.out.printf("%-15s %-10s %-10s %-6s %-12s %-12s %-10s %-10s\n",
                "Type", "StartYear", "EndYear", "Years", "StartPrice", "EndPrice", "Multiple", "CAGR%");
        System.out.println("--------------------------------------------------------------------------------------");

        // Print table
        for (Phase p : phases) {
            System.out.printf("%-15s %-10d %-10d %-6d %-12.2f %-12.2f %-10.2f %-10.2f\n",
                    p.type, p.startYear, p.endYear, p.years, p.startPrice, p.endPrice, p.multiple, p.cagr * 100);
        }
    }
}
