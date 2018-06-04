import { Injectable } from '@angular/core';
import { Excise } from '../models/excise';

@Injectable()
export class ExciseService {

    private excise: Excise[];

    constructor() {
        console.log('Call ExciseService');
        this.excise = new Array<Excise>();
    }

    add(data: Excise): void {
        this.excise.push(data);
    }

    get(id: string): Excise {
        const index = this.excise.findIndex( obj => obj.exciseId == id);
        if (index > -1) {
            return this.excise[index];
        } else {
            return null;
        }
    }

    update(data: Excise): boolean{
        const index = this.excise.findIndex( obj => obj.exciseId == data.exciseId);
        if (index > -1) {
            this.excise[index] = data;
            return true;
        } else {
            return false;
        }
    }

}
