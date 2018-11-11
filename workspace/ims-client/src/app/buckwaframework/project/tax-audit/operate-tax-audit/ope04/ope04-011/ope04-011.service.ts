import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';


@Injectable()
export class Ope04011Service {

    constructor(
        private ajax:AjaxService,
    ){}

    findexciseI():Promise<any>{
        return new Promise((resolve,reject)=>{
            let url = "ta/opo04011/findExciseId";
            return this.ajax.get(url,res=>{
                resolve(res.json());
            })
        });       
    }

    findByExciseId(exciseId):Promise<any>{
        return new Promise((resolve,reject)=>{
            let url = "ta/opo04011/findByExciseId";
            return this.ajax.post(url,JSON.stringify({exciseId}),res=>{
                resolve(res.json());
            })
        });
    }
}