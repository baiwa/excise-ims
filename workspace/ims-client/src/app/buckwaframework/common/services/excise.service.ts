import { Injectable } from '@angular/core';
import { Excise } from '../models/excise';

@Injectable()
export class ExciseService {

    private excise: Excise[];
    private before: string;
    private last: string;
    private num1: number[];
    private num2: number[];
    private percent1: number[];
    private percent2: number[];

    constructor() {
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
            return true;
        } else {
            return false;
        }
    }

    setNumber(before, last, num1, num2, percent1, percent2){
        this.before = before;
        this.last = last;
        this.num1 = num1;
        this.num2 = num2;
        this.percent1 = percent1;
        this.percent2 = percent2;
    }

    getNumber(){
        return {
            before: this.before ,
            last: this.last ,
            num1: this.num1 ,
            num2: this.num2 ,
            percent1: this.percent1 ,
            percent2: this.percent2
        };
    }

}
