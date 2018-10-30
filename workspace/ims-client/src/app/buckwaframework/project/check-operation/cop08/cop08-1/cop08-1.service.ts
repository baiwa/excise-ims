import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { SummaryModel } from 'projects/check-operation/cop08/cop08-1/summaryFooter.model';

@Injectable()
export class Cop081Service {

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