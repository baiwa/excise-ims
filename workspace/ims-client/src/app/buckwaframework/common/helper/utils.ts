import { DecimalFormat } from "helpers/decimalformat";

export class Utils {

    public static isNull(obj) {
        return obj === null || obj === undefined || obj === '';
    }

    public static isNotNull(obj) {
        return obj !== null && obj !== undefined && obj !== '';
    }

    public static moneyFormat(money) {
        console.log(money);
        (money == null || money == "" || money == "null" ? 0 : money)
        var op = "";
        var moneyP = parseFloat(money)
        if (moneyP < 0) {
            // money = money.substring(1);
            money*=-1;
            op = "-";
        }

        var formatter = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
            minimumFractionDigits: 2,
            // the default value for minimumFractionDigits depends on the currency
            // and is usually already 2
        });
        var result = formatter.format(money).substring(1);
        return (op == "-" ? op + result : result);
    }
    public static moneyFormatDecimal(money) {
        if(money == null || money == "") money = 0;
        var df = new DecimalFormat("###,###.00");
        return df.format(money);
    }

    public static moneyFormatInt(money) {
        if(money == null || money == "") money = 0;
        var df = new DecimalFormat("###,###");
        return df.format(money);
    }

}