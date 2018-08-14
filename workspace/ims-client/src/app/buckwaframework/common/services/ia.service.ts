import { Injectable } from "@angular/core";

@Injectable()
export class IaService {
    
    data: any;

    constructor() {}
    
    setData(data: any) {
        this.data = data;
    }

    getData(): any {
        return this.data;
    }
}