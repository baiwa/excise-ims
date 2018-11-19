import { Injectable } from "@angular/core";

@Injectable()
export class Cop9Service {

    cop9: Cop9;

    constructor() { }

    setCop9(cop9: Cop9) {
        this.cop9 = Object.assign(cop9);
    }

    getCop9() {
        return Object.assign(this.cop9);
    }

}

export interface Cop9 {
    analysisNumber: string,
    companyAddress: string,
    companyName: string,
    exciseArea: string,
    exciseId: string,
    exciseSubArea: string,
    flag: string,
    flagDesc: string,
    product: string,
    riskType: string,
    riskTypeDesc: string,
    taYearPlanId: number,
    userId: number
    id: number
} 