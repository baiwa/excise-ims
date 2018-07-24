import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AjaxService, MessageBarService } from '../../../../../common/services';

@Component({
  selector: 'app-ts01-04',
  templateUrl: './ts01-04.component.html',
  styleUrls: ['./ts01-04.component.css']
})
export class Ts0104Component implements OnInit {

  @Output() discard = new EventEmitter<any>();
  numbers: number[];
  obj: Ts0104;
  add: number;

  constructor(   private messageBarService: MessageBarService,
    private ajax: AjaxService) {
    this.add = 0;
    this.numbers = [1, 2, 3];
    this.obj = new Ts0104();
  }

  ngOnInit() {
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

  onAddField = () => {
    let num = this.numbers.length;
    if (num < 5) {
      this.numbers.push(num + 1);
    } else {
      this.messageBarService.errorModal(
        "ไม่สามารถทำรายการได้",
        "เกิดข้อผิดพลาด"
      );
    }
  };

  onDelField = index => {
    this.obj["analys" + (index + 1)] = "";
    this.numbers.splice(index, 1);
  };

  
  onSubmit = e => {
    e.preventDefault();
    const url = "report/pdf/ts/mis_t_s_01_04";
  
    this.ajax.post(url, `'${JSON.stringify(this.obj).toString()}'`, res => {
      if (res.status == 200 && res.statusText == "OK") {
        // var byteArray = new Uint8Array(res.json());
        // var blob = new Blob([byteArray], { type: "application/pdf" });
        // if (window.navigator && window.navigator.msSaveOrOpenBlob) {
        //   window.navigator.msSaveOrOpenBlob(blob);
        // } else {
        //   var objectUrl = URL.createObjectURL(blob);
        //   window.open(objectUrl);
        // }
        window.open("/ims-webapp/api/report/pdf/mis_t_s_01_04/file");
      }
    });
  };
}

class Ts0104 {
  
  onNumber: string;
  onNumber1: string;
  date1: string;
  date2: string;
  address: string;
  month1: string;
  month2: string;
  buddhist1: string;
  subject: string;
  study: string;
  referto: string;
  want: string;
  appointment: string;
  analysControl: string;
  pleasebring: string;
  intpector: string;
  poslion: string;


 
}
