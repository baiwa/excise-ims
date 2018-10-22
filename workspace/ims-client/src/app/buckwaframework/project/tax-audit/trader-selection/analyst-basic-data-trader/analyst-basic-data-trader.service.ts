import { Injectable } from '@angular/core';
import { SummaryModel } from 'projects/tax-audit/trader-selection/analyst-basic-data-trader/summaryFooter.model';
import { AjaxService } from 'services/ajax.service';

@Injectable()
export class AnalystBasicDataTraderService {

    summary: SummaryModel = new SummaryModel();
    constructor(
        private ajax : AjaxService
    ) {

    }

    countList = (productionType, coordinatesFlag, formSearch,dateFrom,dateTo): Promise<any> => {
        
        var productionTypeSplit =  productionType.split("*");       
        if(productionTypeSplit.length){
            productionType = productionTypeSplit[1];
        }

        let url = "working/test/countList";
        let data = {
            "productionType":  productionType,
            "coordinatesFlag": coordinatesFlag,
            "formSearch": formSearch,
            "dateFrom" : dateFrom,
            "dateTo" : dateTo
        }
        let promise = new Promise((resolve, reject) => {
            this.ajax.post(url,JSON.stringify(data) ,res => {
                resolve(res.json())                
            })
        });
        return promise;
    }
}