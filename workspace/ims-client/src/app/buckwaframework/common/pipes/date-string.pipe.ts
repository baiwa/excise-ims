import { Pipe, PipeTransform } from '@angular/core';
import { digit, toDateLocale } from '../helper';

@Pipe({
    name: 'dateString',
    pure: false
})
export class DateStringPipe implements PipeTransform {
    transform(value: Date, args: any[]): any {
        if (value) {
            let day = value.getDate();
            let _month = toDateLocale(value)[0].split("/")[1];
            let _year = toDateLocale(value)[0].split("/")[2];
            return digit(day) + "/" + digit(_month) + "/" + _year.toString();
        } else {
            return "-";
        }
    }
}