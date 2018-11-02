import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';

@Injectable()
export class Cop10Service {
    
    constructor(
        private ajax: AjaxService
    ){}

    getData(fiscalYear):Promise<any> {
        return new Promise((resolve,reject)=>{
            let url = "cop/cop071/list";
            this.ajax.post(url,{ 
                searchFlag: "TRUE",
                fiscalYear: fiscalYear
            }, res => {              
              resolve(res.json());
            });
        });        
      }
}