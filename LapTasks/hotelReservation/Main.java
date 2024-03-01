package HotelReservation;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] line = scanner.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(line[0]);
        int countDays = Integer.parseInt(line[1]);
        Season season = Season.valueOf(line[2].toUpperCase());
        DiscountType discountType = DiscountType.valueOf(line[3].toUpperCase());

        double holidayPrice = PriceCalculator.calculateHolidayPrice(pricePerDay, countDays, season, discountType);

        System.out.printf("%.2f", holidayPrice);

    }
}
