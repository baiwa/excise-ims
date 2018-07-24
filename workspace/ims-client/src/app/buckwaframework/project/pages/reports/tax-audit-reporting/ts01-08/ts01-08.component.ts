import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { AjaxService } from "../../../../../common/services";

@Component({
  selector: "app-ts01-08",
  templateUrl: "./ts01-08.component.html",
  styleUrls: ["./ts01-08.component.css"]
})
export class Ts0108Component implements OnInit {
  @Output() discard = new EventEmitter<any>();

  add: number;
  obj: Ts0108[];

  constructor(private ajax: AjaxService) {
    this.add = 0;
    this.obj = [];
  }

  ngOnInit() {
    for (let i = 0; i < 3; i++) {
      this.onAddField();
    }
  }

  onDiscard = () => {
    // on click this view hide them
    // ... Don't change or delete this
    this.discard.emit(false);
  };

  onAddField = () => {
    this.obj.push(new Ts0108());
    this.add++;
  };

  removeField = i => {
    this.obj.splice(i);
  };

  onSubmit = e => {
    e.preventDefault();
    const url = "report/pdf/ts/mis_t_s_01_08";
    this.ajax.post(
      url,
      `'${JSON.stringify({ Bean: this.obj }).toString()}'`,
      res => {
        if (res.status == 200 && res.statusText == "OK") {
          window.open("/ims-webapp/api/report/pdf/mis_t_s_01_08/file");
        }
      }
    );
  };
}

class Ts0108 {
  [x: string]: any;
  numId: string;
  chkDate: string;
  checkName: string;
  position: string;
  chkOutDate: string;
  chkWhere: string;
  chkWhat: string;
  whoToSign: string;
  checkerOk: string;
  dateToSend: string;
  note: string;
}
