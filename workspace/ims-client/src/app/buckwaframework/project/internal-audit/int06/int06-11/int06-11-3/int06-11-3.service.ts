import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';

const SAVE_SUCCESS = 'บันทึกรายการ';
const SAVE_ERROR = 'บันทึกไม่สำเร็จ';
@Injectable()
export class Int06113Service {
    
    constructor(
        private ajax : AjaxService,
        private messege : MessageBarService    
    ){}
    save(form:any):Promise<any>{
        let url = "/ia/int061103/save";
        console.log(form);
        return new Promise((resolve,reject)=>{
            this.ajax.post(url,JSON.stringify(form),res=>{
                console.log(res.json());
                this.messege.successModal(SAVE_SUCCESS);
                resolve();
            },err=>{
                this.messege.errorModal(SAVE_ERROR);
                reject();
            });
        })
    }
}
