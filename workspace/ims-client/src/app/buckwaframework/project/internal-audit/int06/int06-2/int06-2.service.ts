import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AjaxService } from "services/ajax.service";
import { Router, ActivatedRoute } from "@angular/router";
import { MessageBarService } from "services/message-bar.service";

const URL = {
  UPLOAD_EXCEL: "ia/int062/uploadExcel"
};

declare var $: any;

@Injectable()
export class Int062Service {
  fileExcel: File[];
  loading: boolean;
  fileExcel2: File[];

  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute,
    private msg: MessageBarService
  ) {}

  //   onChangeUpload = (event: any, loadingUpload: Function) => {
  //     if (event.target.files && event.target.files.length > 0) {
  //       let reader = new FileReader();

  //       reader.onload = (e: any) => {
  //         const f = {
  //           name: event.target.files[0].name,
  //           type: event.target.files[0].type,
  //           value: e.target.result
  //         };
  //         this.fileExcel = [f];
  //         console.log(this.fileExcel);
  //       };
  //       reader.readAsDataURL(event.target.files[0]);
  //     }
  //     setTimeout(() => {
  //       this.loading = false;
  //       loadingUpload(this.loading);
  //     }, 1000);
  //   };

  //   onChangeUpload2 = (event: any, loadingUpload: Function) => {
  //     if (event.target.files && event.target.files.length > 0) {
  //       let reader = new FileReader();

  //       reader.onload = (e: any) => {
  //         const f = {
  //           name: event.target.files[0].name,
  //           type: event.target.files[0].type,
  //           value: e.target.result
  //         };
  //         this.fileExcel2 = [f];
  //         console.log(this.fileExcel);
  //       };
  //       reader.readAsDataURL(event.target.files[0]);
  //     }
  //     setTimeout(() => {
  //       this.loading = false;
  //       loadingUpload(this.loading);
  //     }, 1000);
  //   };

  onUpload = (event: any, loadingTable: Function): Promise<any> => {
    event.preventDefault();

    console.log("UPLOAD Excel!!!!!!");
    const form = $("#upload-form")[0];
    console.log(form);
    let formBody = new FormData(form);

    return new Promise<any>((resovle, reject) => {
      this.ajax.upload(URL.UPLOAD_EXCEL, formBody, res => {
        console.log(res.json());
        resovle();
        // this.loading = false;
        // loadingTable(this.loading);
      });
      reject("ERROR");
    });
  };

  // dropdown = (
  //     type: string,
  //     id?: number,
  //     cb: Function = () => { }
  // ): Observable<any> => {
  //     const DATA = { type: type, lovIdMaster: id || null };
  //     return new Observable<ComboBox[]>(obs => {
  //         this.ajax
  //             .post(URL.DROPDOWN, DATA, res => {
  //                 // const response = res.json();
  //                 this[type] = res.json();
  //             })
  //             .then(() => {
  //                 cb();
  //                 obs.next(this[type]);
  //             });
  //     });
  // };

  // quryBudgetName = (): Promise<any> => {
  //     return new Promise<any>((resovle, reject) => {
  //         let combobox3 = null;
  //         this.ajax.post(URL.COMBOBOX3, {}, res => {
  //             combobox3 = res.json();
  //             resovle(combobox3);
  //         });
  //         // .then(() => obs.next(data));
  //     });
  // };
}

class File {
  [x: string]: any;
  name: string;
  type: string;
  value: any;
}
