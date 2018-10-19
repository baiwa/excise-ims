import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';

@Injectable()
export class AnalysisService {
    constructor(
        private ajax: AjaxService
    ) { }

    exciseIdList = (): Promise<any> => {
        let url = "ta/analysis/findExciseId";

        return new Promise((resolve, reject) => {
            this.ajax.get(url, res => {
                resolve(res.json())
            })
        });

    }
}