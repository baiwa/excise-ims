import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';

@Injectable()
export class ResultAnalysisSerivce {
    constructor(
        private ajax : AjaxService
    ){}


    findDataFromExciseId =(exciseId): Promise<any>=>{
        let url = "ta/analysis/findByExciseId";
        return new Promise((resolve, reject) => {
            this.ajax.post(url, JSON.stringify(exciseId), res => {
                resolve(res.json())
            })
        });

    }
}