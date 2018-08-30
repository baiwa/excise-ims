import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { AjaxService } from "../../../../../common/services";

const URL = {
  MAINDETAIL: "/ia/int02m3/getQtnMainList",
  MINORDETAIL: "/ia/int02m3/getQtnMinorList"
};

@Component({
  selector: "app-int02-m3-1",
  templateUrl: "./int02-m3-1.component.html",
  styleUrls: ["./int02-m3-1.component.css"]
})
export class Int02M31Component implements OnInit {
  headerCode: any;
  qtnMainList: any;
  qtnMinorList: any;
  mainId: any;
  constructor(
    private ajax: AjaxService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.mainId = "";
    this.qtnMinorList = [];
    this.qtnMinorList.push({
      mainId: this.mainId
    });
    console.log(this.qtnMinorList);
  }

  ngOnInit() {
    //get params link "/int02/m3"
    this.headerCode = this.route.snapshot.queryParams["qtnHeaderCode"];
    console.log(this.headerCode);

    // const URL = "/ia/int02m3/getQtnMainList";
    this.ajax.post(URL.MAINDETAIL, { headerCode: this.headerCode }, res => {
      this.qtnMainList = res.json();
      console.log("qtnMainList: ", this.qtnMainList);

      // this.ajax.post(URL.MINORDETAIL, { headerCode: this.headerCode }, res => {
      //   this.qtnMinorList = res.json();
      //   console.log("qtnMinorList: ", this.qtnMinorList);

      //   for (let i = 0; i <= this.qtnMainList.length; i++) {
      //     for (let j = 0; j <= this.qtnMinorList.length; j++) {
      //       console.log(this.qtnMinorList[j]["mainId"]);
      //       // if (
      //       //   this.qtnMainList[i].qtnMainDetailId == this.qtnMinorList[j].mainId
      //       // ) {
      //       //   // this.qtnMainList = [];
      //       //   // this.qtnMainList[i].qtnMainDetailIdpush.push(
      //       //   //   this.qtnMinorList[j].mainId
      //       //   // );
      //       //   alert("sc");
      //       // } else {
      //       //   alert("fail!!!!!");
      //       // }
      //     }
      //   }
      // });
    });
  }
}
