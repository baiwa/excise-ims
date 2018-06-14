import { Injectable } from '@angular/core';
import { Excise, File } from '../models';
import { AjaxService } from './ajax.service';

@Injectable()
export class ExciseService {

    private excise: Excise[];
    private before: string;
    private last: string;
    private num1: number[];
    private num2: number[];
    private percent1: number[];
    private percent2: number[];
    private from: any;
    private analysNumber: any;
    private month: any;
    private currYear: number;
    private prevYear: number;

    constructor(
        private ajax: AjaxService
    ) {
        console.log('Call ExciseService');
        this.excise = new Array<Excise>();
    }

    add(data: Excise): void {
        this.excise.push(data);
    }

    get(id: string): Excise {
        const index = this.excise.findIndex(obj => obj.exciseId == id);
        if (index > -1) {
            return this.excise[index];
        } else {
            return null;
        }
    }

    update(data: Excise): boolean {
        const index = this.excise.findIndex(obj => obj.exciseId == data.exciseId);
        if (index > -1) {
            this.excise[index] = data;
            const url = `excise/detail/list/${data.exciseId}`;
            const new_data: File[] = data.file;
            this.ajax.put(url, new_data, null).then(
                res => {
                    console.log(res);
                }
            );
            return true;
        } else {
            return false;
        }
    }

    setformValues(before, last, from, month, currYear, prevYear){
        this.before = before;
        this.last = last;
        this.from = from;
        this.month = month;
        this.currYear = currYear;
        this.prevYear = prevYear;
    }

    getformValues(){
        return {
            before: this.before,
            last: this.last,
            from: this.from,
            month: this.month,
            currYear: this.currYear,
            prevYear: this.prevYear
        };
    }

    setformNumber(num1, num2, percent1, percent2, analysNumber){
        this.num1 = num1;
        this.num2 = num2;
        this.percent1 = percent1;
        this.percent2 = percent2;
        this.analysNumber = analysNumber;
    }

    getformNumber(){
        return {
            num1: this.num1 ,
            num2: this.num2 ,
            percent1: this.percent1 ,
            percent2: this.percent2 ,
            analysNumber: this.analysNumber
        };
    }

}
