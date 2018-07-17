import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../common/services/ajax.service";

@Component({
  selector: "int05-3-1",
  templateUrl: "./int05-3-1.component.html",
  styleUrls: ["./int05-3-1.component.css"]
})
export class Int0531Component implements OnInit {
  constructor(private ajax: AjaxService) {}

  ngOnInit() {}

  callFn() {
    this.ajax.get(
      "preferences/message?no1=55",
      res => console.log(res.json().data),
      error => console.error(error)
    );
    // this.ajax.post(
    //   'post/me',
    //   {
    //     startBackDate: '01/2539',
    //     month: '10'
    //   },
    //   alert('success')
    // ).then(
    //   res => console.log(res),
    //   err => console.log(err)
    // );
  }
}
