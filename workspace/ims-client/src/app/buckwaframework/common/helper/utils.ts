export class Utils {
    public static isNull(obj) {
        return obj === null || obj === undefined || obj === '';
    }

    public static isNotNull(obj) {
        return obj !== null && obj !== undefined && obj !== '';
    }
}