import { Injectable } from "@angular/core";
import { AjaxService } from "services/ajax.service";
import { MessageBarService } from "services/message-bar.service";
import { promise } from "protractor";
import { Observable } from "rxjs/Observable";

const URL = {
  SAVE_TIME: "/ia/int0671/saveTime",
  QUERY_TIME: "/ia/int0671/queryTime",
  UPDATE_TIME: "/ia/int0671/updateTime",
  DELETE_TIME: "/ia/int0671/deleteTime",
  DATAINIT: "/ia/int0671/queryInit"
};

declare var $: any;

@Injectable()
export class Int0671Service {
  dataList: any;
  table: any;

  constructor(private ajax: AjaxService, private msg: MessageBarService) {
    // TODO
  }

  // dataInit = (callback: Function) => {
  //   console.log("callback");

  //   this.ajax.post(URL.DATAINIT, {}, async res => {
  //     let init = await res.json();
  //     console.log("init : ", init);
  //     callback(init);
  //     this.dataList = await res.json().data;
  //   });
  // };
  dataInit = (): Observable<any> => {
    return new Observable<any>(obs => {
      this.ajax
        .post(URL.DATAINIT, {}, async res => {
          let init = await res.json();
          console.log("init : ", init);
          this.dataList = await res.json().data;
        })
        .then(() => obs.next(this.dataList));
      // setTimeout(() => {
      //   this.initDatatable();
      // }, 1000);
    }).share();
  };

  saveTime = (startDateTime: string, endDateTime: string): Observable<any> => {
    const DATA = { startDateTime: startDateTime, endDateTime: endDateTime };
    return new Observable<any>(obs => {
      this.ajax
        .post(URL.SAVE_TIME, DATA, async res => {
          this.dataList = await res.json().data;
          if ((await res.json().msg.messageType) === "E") {
            await this.msg.errorModal(res.json().msg.messageTh);
          } else {
            await this.msg.successModal(res.json().msg.messageTh);
          }
        })
        .then(() => obs.next(this.dataList));
      // setTimeout(() => {
      //   this.initDatatable();
      // }, 1000);
    });
  };

  updateTime = (
    timeSetId: string,
    startDateTime: string,
    endDateTime: string
  ): Observable<any> => {
    const DATA = {
      timeSetId: timeSetId,
      startDateTime: startDateTime,
      endDateTime: endDateTime
    };

    return new Observable<any>(obs => {
      this.ajax
        .post(URL.UPDATE_TIME, DATA, res => {
          this.dataList = res.json().data;
          console.log(res.json());
          if (res.json().msg.messageType === "C") {
            this.msg.successModal(res.json().msg.messageTh);
          } else {
            this.msg.errorModal(res.json().msg.messageTh);
          }
        })
        .then(() => obs.next(this.dataList));
      // setTimeout(() => {
      //   this.initDatatable();
      // }, 1000);
    });
  };

  deleteTime(timeSetId: string): Observable<any> {
    return new Observable<any>(obs => {
      this.ajax
        .post(URL.DELETE_TIME, { timeSetId: timeSetId }, async res => {
          this.dataList = await res.json().data;
          console.log(await res.json());
          if ((await res.json().msg.messageType) === "C") {
            await this.msg.successModal(res.json().msg.messageTh);
            // await this.initDatatable();
          } else {
            await this.msg.errorModal(res.json().msg.messageTh);
          }
        })
        .then(() => obs.next(this.dataList));
    });
  }

  async initDatatable() {
    // Initial Datatable
    if (this.table) {
      await this.table.destroy();
    }
    this.table = await $("#table").DataTable({
      scrollY: "410px",
      scrollCollapse: true,
      scrollX: true,
      searching: false,
      lengthChange: false,
      paging: false
      // order: [[0, "asc"]]
      // columnDefs: [
      //   {
      //     orderable: false,
      //     targets: [4, 8, 9, 10, 11, 12, 13, 14, 15, 16]
      //   }
      // ]
    });
  }
}
