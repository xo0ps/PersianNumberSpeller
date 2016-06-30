package info.msadeghi.persiannumberspeller.util;

import java.text.DecimalFormat;


//adapted from http://stackoverflow.com/a/3911987/1559576

public class NumberToWordConverter {


    private static final String[] hundredsNames = {
            "",
            " صد",
            " دویست",
            " سیصد",
            " چهارصد",
            " پانصد",
            " ششصد",
            " هفتصد",
            " هشتصد",
            " نهصد"
    };

    private static final String[] tensNames = {
            "",
            " ده",
            " بیست",
            " سی",
            " چهل",
            " پنجاه",
            " شصت",
            " هفتاد",
            " هشتاد",
            " نود"
    };

    private static final String[] numNames = {
            "",
            " یک",
            " دو",
            " سه",
            " چهار",
            " پنج",
            " شش",
            " هفت",
            " هشت",
            " نه",
            " ده",
            " یازده",
            " دوازده",
            " سیزده",
            " چهارده",
            " پانزده",
            " شانزده",
            " هفده",
            " هجده",
            " نوزده"
    };

    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20)
        {
            soFar = numNames[number % 100];
            number /= 100;
        }
        else
        {
            soFar = " و " + numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }

        if (number == 0)
            return soFar;

        if (soFar.length() > 0)
            soFar = " و " + soFar;

        return hundredsNames[number] + soFar;
    }


    public static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0)
        {
            return "صفر";
        }

        String snumber;

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0,3));
        // nnnXXXnnnnnn
        int millions  = Integer.parseInt(snumber.substring(3,6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6,9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9,12));

        String tradBillions;
        switch (billions)
        {
            case 0:
                tradBillions = "";
                break;
            default :
                tradBillions = convertLessThanOneThousand(billions)
                        + " میلیارد ";
                break;
        }
        String result = tradBillions;

        String tradMillions;
        switch (millions)
        {
            case 0:
                tradMillions = "";
                break;
            default :
                tradMillions = convertLessThanOneThousand(millions)
                        + " میلیون ";
                break;
        }
        if (tradBillions.length() > 0 && (millions > 0 || hundredThousands > 0 || thousands > 0))
        {
            result =  result + " و " + tradMillions;
        }
        else
        {
            result =  result + tradMillions;
        }

        String tradHundredThousands;
        switch (hundredThousands)
        {
            case 0:
                tradHundredThousands = "";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + " هزار ";
                break;
        }
        if (tradHundredThousands.length() > 0 && (hundredThousands > 0 || thousands > 0))
        {
            result =  result + " و " + tradHundredThousands;
        }
        else
        {
            result =  result + tradHundredThousands;
        }

        String tradThousand = convertLessThanOneThousand(thousands);
        if (tradThousand.length() > 0)
        {
            result =  result + " و " + tradThousand;
        }
        else
        {
            result =  result + tradThousand;
        }

        // remove extra spaces!
        result = result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
        if (result.startsWith("و "))
            result = result.substring(2);
        if (result.endsWith(" و "))
            result = result.substring(0, result.length() - 3);

        result = result.trim();
        return result;
    }

}
