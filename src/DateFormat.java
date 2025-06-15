import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DateFormat {
    private static String DateFormat = "dd-MM-yyyy";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormat);

    public static LocalDate convertToDate(String s) {
        return LocalDate.parse(s, formatter);
    }

    public static String convertToString(LocalDate s) {
        return s.format(formatter);
    }
}
