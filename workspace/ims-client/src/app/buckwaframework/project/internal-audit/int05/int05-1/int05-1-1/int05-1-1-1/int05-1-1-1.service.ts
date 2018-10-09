import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';

@Injectable()
export class Int05111Service {

  constructor(
    private ajax: AjaxService
    ) { }

  status = ():Promise<any> => {
    let url = "ia/int05111/status";

    let promise = new Promise((resolve, reject) => {
      this.ajax.get(url,res=>{
        resolve(res.json())
        // console.log("service : ",res.json());
    })});
    return promise;
  }
}
