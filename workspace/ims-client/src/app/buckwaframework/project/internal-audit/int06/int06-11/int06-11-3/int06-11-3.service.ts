import { Injectable } from '@angular/core';
import { AjaxService } from 'services/ajax.service';
import { MessageBarService } from 'services/message-bar.service';
import { Utils } from 'helpers/utils';
import { Router } from '@angular/router';
declare var $: any;
const SAVE_SUCCESS = 'บันทึกรายการ';
const SAVE_ERROR = 'บันทึกไม่สำเร็จ';
const URL = {    
    UPLOAD: "ia/int061103/upload"    
  };
@Injectable()
export class Int06113Service {
    // ==> perams

    fileUpload: any = [];
    readDtl: any = [];
    constructor(
        private ajax: AjaxService,
        private messege: MessageBarService,
        private router: Router
    ) { }
    save(form: any): Promise<any> {
        let url = "/ia/int061103/save";
        console.log(form);
        return new Promise((resolve, reject) => {
            this.ajax.post(url, JSON.stringify(form), res => {
                console.log(res.json());                
                resolve(res.json());
            }, err => {
                this.messege.errorModal(SAVE_ERROR);
                reject();
            });
        })
    }

    saveFile = (obj) => {        

        if (Utils.isNotNull(obj.id)) {
            let execelFiles = new FormData();            
            execelFiles.append("id", obj.id);
            /**
             * loop get file
             */
            this.fileUpload.forEach(file => {
              execelFiles.append("files", file.inputFIle.files[0]);
            });
            console.log("fileUpload : ", this.fileUpload);
            console.log("execelFiles : ", execelFiles)
            this.ajax.upload(URL.UPLOAD, execelFiles, res => {
              this.messege.successModal(res.json().messageTh);              
              this.router.navigate(["/int06/11"]);
            }),
              error => {
                this.messege.errorModal("ไม่สามารถบันทึกได้");                
              };
          }
    }

    onUpload() {
        let inputTypeFile = `<input type="file" name="files" id="files" accept=".pdf, image/*, text/plain, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,
                        application/vnd.ms-excel,.doc,.docx">`;

        let f = $(".upload-panel > input")[0];
        $(".upload-panel").html(inputTypeFile); //add to html

        let lastText = f.value.split("\\").length;
        let u = {
            name: f.value.split("\\")[lastText - 1],
            // type: f.type,
            // size: f.size,
            // value: f.value,
            date: new Date().toLocaleDateString(),
            typePage: "RH",
            inputFIle: f
        };
        this.fileUpload.push(u);

        return new Promise<any>((resolve, reject) => {
            resolve(this.fileUpload);
        });
    }
    onDel(index: number) {
        this.fileUpload.splice(index, 1);
    }

}
