package info.msadeghi.persiannumberspeller.util;

/**
 * Created by mahdi on 6/30/16 AD.
 */
public class Utils {

    public static String clear(String amount)
    {
        if (amount != null && amount.length() > 0) {

            boolean isminus = amount.startsWith("-");

            amount = amount.replaceAll("[^\\d]", "");
            if (isminus) {
                amount = "-" + amount;
            }

            return amount;
        } else {
            return "";
        }
    }

    public static String amountFormater(String amount)
    {
        if (amount.length() > 0) {

            boolean isminus = amount.startsWith("-");
            amount = amount.replaceAll("[^\\d]", "");
            for (int i = amount.length() - 3; i > 0; i -= 3) {
                amount = amount.substring(0, i) + "," + amount.substring(i);
            }
            if (isminus) {
                amount = "-" + amount;
            }
            return amount;
        } else {
            return "";
        }
    }
}
