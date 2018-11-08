import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { Utils } from 'helpers/utils';


@Injectable()
export class int0611301Service {

    constructor(
        private ajax: AjaxService
    ) { }

    // ==> checkMoney form
    checkMoneyForm = (form:any) : Promise<any> => {
        return new Promise((resolve,reject)=>{
            let url = "ia/int0611031/checkMoney";
            let idMaster = '';
            let continueId  = true;
            console.log(form);
            if(Utils.isNotNull(form.bursary) && continueId){
                idMaster = form.bursary;
                continueId = false;
            }
            if(Utils.isNotNull(form.typeSubject) && continueId){
                idMaster = form.typeSubject;
                continueId = false;
            }
            if(Utils.isNotNull(form.levelEducation) && continueId){
                idMaster = form.levelEducation;
                continueId = false;
            }
            if(Utils.isNotNull(form.subTypeEducation) && continueId){
                idMaster = form.subTypeEducation;
                continueId = false;
            }       
    
            this.ajax.post(url,JSON.stringify(idMaster),res=>{
                console.log(res.json());
                resolve(res.json());
            });
        });
       
    }

    // ==> dropdown
    typeEducation = (): Promise<any> => {
        let url = "ia/int0611031/typeEducation";
        return new Promise((resolve, reject) => {
            this.ajax.get(url, res => {
                console.log("typeEducation");
                resolve(res.json());
            })
        })
    }
    // ==> dropdown
    subTypeEducation = (idMaster: string): Promise<any> => {
        let url = "ia/int0611031/subTypeEducation";
        return new Promise((resolve, reject) => {
            this.ajax.post(url, JSON.stringify(idMaster), res => {
                resolve(res.json());
            })
        })
    }
    // ==> dropdown
    levelEducation = (idMaster: string): Promise<any> => {
        let url = "ia/int0611031/levelEducation";
        return new Promise((resolve, reject) => {
            this.ajax.post(url, JSON.stringify(idMaster), res => {
                console.log("Level ==> ", res.json());
                resolve(res.json());
            })
        })
    }
    // ==> dropdown
    typeSubject = (idMaster: string): Promise<any> => {
        let url = "ia/int0611031/typeSubject";
        return new Promise((resolve, reject) => {
            this.ajax.post(url, JSON.stringify(idMaster), res => {
                console.log("TypeSubject ==> ", res.json());
                resolve(res.json());
            })
        })
    }

    // ==> dropdown
    bursary = (idMaster: string): Promise<any> => {
        let url = "ia/int0611031/bursary";
        return new Promise((resolve, reject) => {
            this.ajax.post(url, JSON.stringify(idMaster), res => {
                console.log("Bursary ==> ", res.json());
                resolve(res.json());
            })
        })
    }


}