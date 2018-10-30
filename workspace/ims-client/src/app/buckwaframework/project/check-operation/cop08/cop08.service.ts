import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';

@Injectable()
export class Cop08Serivce {

    constructor(private ajax : AjaxService){        
    }

    webService = () => {
        let url = "working/taWebService/regFri4000";
        this.ajax.get(url,res=>{

        });
    }
}