import { Injectable } from '@angular/core';
import { resolve } from 'dns';
import { reject } from 'q';
import { AjaxService } from 'services/ajax.service';


@Injectable()
export class Int06113Service {

    constructor(
        private ajax : AjaxService
    ){}
    save(form:any):Promise<any>{
        let url = "/ia/int061103/save";
        console.log(form);
        return new Promise((resolve,reject)=>{
            this.ajax.post(url,form,res=>{
                console.log(res.json());
            });
        })

    }
}